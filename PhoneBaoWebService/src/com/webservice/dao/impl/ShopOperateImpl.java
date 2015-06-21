package com.webservice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;

import com.webservice.dao.DAO;
import com.webservice.dao.ShopOperateDAO;
import com.webservice.db.JdbcUtils;
import com.webservice.utils.CustomerException;

public class ShopOperateImpl extends DAO<String> implements ShopOperateDAO {

	@Override
	public void playOrder(String userName, Map<Integer, Integer> info, int addressID) throws Exception {

		// ��ȡ���ݿ�����
		Connection conn = null;
		PreparedStatement prea = null, prea2 = null;
		try {

			conn = JdbcUtils.getConnection();
			// ȡ��
			conn.setAutoCommit(false);

			// ��ȡ�û�id
			Long userID = 0L;
			userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

			// key:goods_id value:goods_num

			for (Entry<Integer, Integer> entry : info.entrySet()) {
				int goodsID = entry.getKey();
				int goodsNum = entry.getValue();
				// ��ȡgoods�۸�
				Double price = getValue("select goods_price from t_goods where goods_id = " + goodsID, null);
				Double sumPrice = goodsNum * price;
				String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				// ����order��
				String sql = "insert into t_ordergoods(goods_id,user_id,ordergoods_num,og_sum_price,deal_state_id,address_id,order_time) values("
						+ goodsID + "," + userID + "," + goodsNum + "," + sumPrice + "," + 1 + "," + addressID + ",'"+time+"')";
				prea = conn.prepareStatement(sql);
				prea.execute();
				// ɾ�����ﳵ��
				String sql2 = "delete from t_shopping_cart where user_id = " + userID + " and goods_id = " + goodsID;
				prea2 = conn.prepareStatement(sql2);
				prea2.execute();
				conn.commit();
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			// ����ľͻع�����
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			throw new CustomerException("����ʧ��");

		} finally {
			if (conn != null) {
				conn.close();
			}
			if (prea != null) {
				prea.close();
			}
			if (prea2 != null) {
				prea2.close();
			}
		}

	}

	@Override
	public void collect(String userName, int goodsID) throws Exception {
		String sql = "insert into t_goods_collect(goods_id,user_id) values(?,?)";
		// ��ȡ�û�id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

		update(sql, goodsID, userID);
	}

	@Override
	public void disCollect(String userName, int goodsID) throws Exception {
		String sql = "delete from t_goods_collect where goods_id=? and user_id =? ";
		// ��ȡ�û�id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

		update(sql, goodsID, userID);
	}

	@Override
	public boolean isCollect(String userName, int goodsID) throws Exception {
		String sql = "select count(*) from t_goods_collect where goods_id=? and user_id =? ";
		// ��ȡ�û�id
		Long userID = 0L;
		userID = getValue("select user_id from t_bao_users where user_name = ?", userName);
		Long length = 0L;
		length = getValue(sql, goodsID, userID);
		if(length>0)
			return true;
		else
			return false;
	}

	@Override
	public void orderCancle(String userName, Map<Integer, Integer> info) throws Exception {
		// ��ȡ���ݿ�����
				Connection conn = null;
				PreparedStatement prea = null, prea2 = null;
				try {

					conn = JdbcUtils.getConnection();
					// ȡ��
					conn.setAutoCommit(false);

					// ��ȡ�û�id
					Long userID = 0L;
					userID = getValue("select user_id from t_bao_users where user_name = ?", userName);

					// key:goods_id value:goods_num

					for (Entry<Integer, Integer> entry : info.entrySet()) {
						int goodsID = entry.getKey();
						int goodsNum = entry.getValue();
						// ɾ�����ﳵ��
						String sql2 = "delete from t_shopping_cart where user_id = " + userID + " and goods_id = " + goodsID;
						prea2 = conn.prepareStatement(sql2);
						prea2.execute();
						conn.commit();
						conn.setAutoCommit(true);
					}
				} catch (Exception e) {
					// ����ľͻع�����
					e.printStackTrace();
					if (conn != null) {
						conn.rollback();
					}
					throw new CustomerException("����ʧ��");

				} finally {
					if (conn != null) {
						conn.close();
					}
					if (prea != null) {
						prea.close();
					}
					if (prea2 != null) {
						prea2.close();
					}
				}
	}

}
