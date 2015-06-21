package com.webservice.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static DataSource dataSource = null;
	
	static{
		dataSource =  new ComboPooledDataSource("mvcapp");
	}
	/**
	 * 释放一个Connection连接
	 * @param conn
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection conn) throws SQLException{ 
		if(conn != null){
			conn.close();
		}
		
	}
	
	/**
	 * 反回数据源的一个datasourse
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
