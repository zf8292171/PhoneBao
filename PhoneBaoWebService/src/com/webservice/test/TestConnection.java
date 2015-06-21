package com.webservice.test;


import java.sql.Connection;
import java.sql.SQLException;



import org.junit.Test;

import com.webservice.dao.impl.UserDaoImpl;
import com.webservice.db.JdbcUtils;
import com.webservice.javabean.ShopFilter;
import com.webservice.javabean.UserInfoBean;

public class TestConnection {

	@Test
	public void testGetConnection() {
		ShopFilter shopFilter = new ShopFilter();
		shopFilter.setContent("��Ϊ��ҫ");
		shopFilter.setPrice("2000~4000");
		String sql = getSql(shopFilter);
		System.out.println(sql);
	}

	private String getSql(ShopFilter shopFilter) {
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
		String sql = "select goods_id,goods_name,goods_price,goods_sell_num,goods_introduce" + " from goods_view where ";
		// ƴ��select���
		if (!content.equals("")) {
			sql = sql + "goods_name like  '%" + content + "%' and ";
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
		if (last != -1) {
			sql = sql.substring(0, last);
		}else{
			//ȥ��where
			int index = sql.lastIndexOf("where");
			sql =sql.substring(0,index);
		}

		return sql;

	}

}
