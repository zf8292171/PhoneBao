package com.webservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.ShopBriefDAO;
import com.webservice.javabean.ShopBriefInfo;
import com.webservice.javabean.ShopFilter;
import com.webservice.javabean.ShopInfo;

public class ShopBriefImpl extends DAO<ShopBriefInfo> implements ShopBriefDAO {

	// ��ȡ�ֻ��ļ�Ҫ��Ϣ �����ڲ�ѯʱ��ʾ�ڽ���
	@Override
	public List<ShopBriefInfo> getShopBriefInfo(ShopFilter shopFilter, int order, int num) throws Exception {
		if (shopFilter == null) {
			throw new Exception("shopFilter is null");
		}
		String sql = "";

		// �����õ���Ӧ��sql���
		sql = getSql(shopFilter, order, num);

		List<ShopBriefInfo> result = new ArrayList<ShopBriefInfo>();
		result = getForList(sql, null);
		return result;
	}

	// ��ȡ�ֻ���ͼƬ��Ϣ�����ڸ�listview��getviewʹ��
	// ����ͼƬ�ڷ������ĵ�ַ
	@Override
	public String getShopHeadPic(int id) throws Exception {
		String sql = "select goods_main_picture from goods_view where goods_id = " + id;
		return String.valueOf(getValue(sql, null));
	}

	private String getSql(ShopFilter shopFilter, int order, int num) {
		// ��ȡ��Ϣ
		String content = shopFilter.getContent();
		String brand = shopFilter.getBrand();
		String cpu = shopFilter.getCpu();
		String ram = shopFilter.getRam();
		String rom = shopFilter.getRom();
		String px = shopFilter.getPx();
		String color = shopFilter.getColor();
		String web = shopFilter.getWeb();
		String price = shopFilter.getPrice();
		String sql = "select goods_id as id,goods_name as goodsName,goods_price as price,goods_sell_num as sellNum,"
				+ " province,city,shop_name as shopName" + " from goods_view ,t_shop,dm_province,dm_city,`dm_county`"
				+ " where goods_view.shop_id = `t_shop`.shop_id and t_shop.`county_id` = `dm_county`.`county_id`"
				+ " and `dm_county`.`city_id` = dm_city.`city_id` and dm_city.`province_id` = dm_province.`province_id` and ";
		// ƴ��select���
		if (!content.equals("")) {
			sql = sql + "goods_name  like  '%" + content + "%' and ";
		}
		if (!brand.equals("ȫ��")) {
			sql = sql + "phonebrand = '" + brand + "' and ";
		}
		if (!cpu.equals("ȫ��")) {
			sql = sql + "cpu = '" + cpu + "' and ";
		}
		if (!ram.equals("ȫ��")) {
			sql = sql + "ram = '" + ram + "' and ";
		}
		if (!rom.equals("ȫ��")) {
			sql = sql + "rom = '" + rom + "' and ";
		}
		if (!px.equals("ȫ��")) {
			sql = sql + "resolution = '" + px + "' and ";
		}
		if (!color.equals("ȫ��")) {
			sql = sql + "phonecolor = '" + color + "' and ";
		}
		if (!web.equals("ȫ��")) {
			sql = sql + "communication = '" + web + "' and ";
		}
		if (!price.equals("ȫ��")) {
			if (price.equals("<1000")) {
				sql = sql + "goods_price <1000  and ";
			} else if (price.equals("1000~2000")) {
				sql = sql + "goods_price between 1000 and 2000  and ";
			} else if (price.equals("2000~4000")) {
				sql = sql + "goods_price between 2000 and 4000  and ";
			} else {
				sql = sql + "goods_price > 4000  and ";
			}
		}
		// ȥ�����һ��and
		int last = sql.lastIndexOf("and");
		sql = sql.substring(0, last);

		// �ж�����
		switch (order) {
		case 0:
			sql = sql + " order by goods_click_rate desc";
			break;
		case 1:
			sql = sql + " order by goods_sell_num desc";
			break;
		case 2:
			sql = sql + " order by goods_price asc";
			break;
		case 3:
			sql = sql + " order by goods_price desc";
			break;
		default:
			break;
		}
		sql = sql + " limit " + num;

		return sql;

	}

	@Override
	public List<ShopBriefInfo> getGoodsCollect(String userName) throws Exception {
		List<ShopBriefInfo> result = new ArrayList<ShopBriefInfo>();
		// ��ȡ�û�id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

		// ִ��
		result = getForList("select goods_view.goods_id as id,goods_name as goodsName,goods_price as price,goods_sell_num as sellNum,"
				+ "province,city,shop_name as shopName "
				+ "from goods_view ,t_shop,dm_province,dm_city,`dm_county`,`t_goods_collect`,`t_bao_users` "
				+ " where goods_view.shop_id = `t_shop`.shop_id and t_shop.`county_id` = `dm_county`.`county_id`"
				+ " and `dm_county`.`city_id` = dm_city.`city_id` and dm_city.`province_id` = dm_province.`province_id` "
				+ "  and t_goods_collect.`goods_id` = goods_view.`goods_id` and  t_goods_collect.`user_id` = t_bao_users.`user_id` "
				+ " and t_bao_users.user_id =" + userID);
		return result;
	}

}
