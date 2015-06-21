package com.webservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webservice.dao.DAO;
import com.webservice.dao.ShopBriefDAO;
import com.webservice.javabean.ShopBriefInfo;
import com.webservice.javabean.ShopFilter;
import com.webservice.javabean.ShopInfo;

public class ShopBriefImpl extends DAO<ShopBriefInfo> implements ShopBriefDAO {

	// 获取手机的简要信息 ，用于查询时显示在界面
	@Override
	public List<ShopBriefInfo> getShopBriefInfo(ShopFilter shopFilter, int order, int num) throws Exception {
		if (shopFilter == null) {
			throw new Exception("shopFilter is null");
		}
		String sql = "";

		// 解析得到相应的sql语句
		sql = getSql(shopFilter, order, num);

		List<ShopBriefInfo> result = new ArrayList<ShopBriefInfo>();
		result = getForList(sql, null);
		return result;
	}

	// 获取手机的图片信息，用于给listview的getview使用
	// 反回图片在服务器的地址
	@Override
	public String getShopHeadPic(int id) throws Exception {
		String sql = "select goods_main_picture from goods_view where goods_id = " + id;
		return String.valueOf(getValue(sql, null));
	}

	private String getSql(ShopFilter shopFilter, int order, int num) {
		// 提取信息
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
		// 拼凑select语句
		if (!content.equals("")) {
			sql = sql + "goods_name  like  '%" + content + "%' and ";
		}
		if (!brand.equals("全部")) {
			sql = sql + "phonebrand = '" + brand + "' and ";
		}
		if (!cpu.equals("全部")) {
			sql = sql + "cpu = '" + cpu + "' and ";
		}
		if (!ram.equals("全部")) {
			sql = sql + "ram = '" + ram + "' and ";
		}
		if (!rom.equals("全部")) {
			sql = sql + "rom = '" + rom + "' and ";
		}
		if (!px.equals("全部")) {
			sql = sql + "resolution = '" + px + "' and ";
		}
		if (!color.equals("全部")) {
			sql = sql + "phonecolor = '" + color + "' and ";
		}
		if (!web.equals("全部")) {
			sql = sql + "communication = '" + web + "' and ";
		}
		if (!price.equals("全部")) {
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
		// 去掉最后一个and
		int last = sql.lastIndexOf("and");
		sql = sql.substring(0, last);

		// 判断排序
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
		// 获取用户id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

		// 执行
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
