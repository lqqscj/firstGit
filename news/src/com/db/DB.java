package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	//1.驱动
	private static String driver = "com.mysql.jdbc.Driver";
	//2.连接地址
	private static String url 	 = "jdbc:mysql://localhost:3306/newsdb";
	//3.用户名
	private static String user 	 = "root";
	//4.密码
	private static String pwd 	 = "root";
	/**
	 * @desc:创建一个mjysql连接
	 * @return:Connection
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
