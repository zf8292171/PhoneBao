package com.webservice.dao;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.webservice.db.JdbcUtils;
import com.webservice.utils.CustomerException;

public class DAO<T> {

	
	private Class<T> clazz;
	
	private QueryRunner  queryRunner = new QueryRunner();
	
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType)superClass;
			
			Type[] typeArgs = parameterizedType.getActualTypeArguments();
			if(typeArgs != null && typeArgs.length>0){
				if(typeArgs[0] instanceof Class){
					clazz = (Class<T>)typeArgs[0];
				}
			}
		}
	}
	
	/**
	 * ���ز�ѯ���һ��ֵ
	 * @param sql:��Ҫִ�е�sql���
	 * @param args:sql����еĲ���
	 * @return
	 */
	public <E> E getValue(String sql,Object ...args) throws Exception{
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return  (E) queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("DAOGetValue");
		}finally{
			try {
				JdbcUtils.releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ���ظ�ʵ����һ�鼯��
	 * @param sql:��Ҫִ�е�sql���
	 * @param args:sql����еĲ���
	 * @return
	 */
	public List<T> getForList(String sql,Object ...args) throws Exception{
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return  queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("DAOGetForList");
		}finally{
			try {
				JdbcUtils.releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ����һ��ʵ������
	 * @param sql:��Ҫִ�е�sql���
	 * @param args:sql����еĲ���
	 * @return
	 */
	public T get(String sql,Object ...args) throws Exception{
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			return  queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("DAOGet");
		}finally{
			try {
				JdbcUtils.releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �÷�����װ��INSERT , UPDATE , DELETE ����
	 * @param sql:��Ҫִ�е�sql���
	 * @param args:sql����еĲ���
	 * @throws SQLException 
	 */
	public void update(String sql,Object ...args) throws Exception{
		Connection conn = null;
		try {
			conn = JdbcUtils.getConnection();
			queryRunner.update(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("DAOupdate");
		}finally{
			try {
				JdbcUtils.releaseConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
