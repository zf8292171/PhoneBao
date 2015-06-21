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
	 * 反回查询后的一个值
	 * @param sql:需要执行的sql语句
	 * @param args:sql语句中的参数
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
	 * 反回该实例的一组集合
	 * @param sql:需要执行的sql语句
	 * @param args:sql语句中的参数
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
	 * 反回一个实例对象
	 * @param sql:需要执行的sql语句
	 * @param args:sql语句中的参数
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
	 * 该方法封装了INSERT , UPDATE , DELETE 操作
	 * @param sql:需要执行的sql语句
	 * @param args:sql语句中的参数
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
