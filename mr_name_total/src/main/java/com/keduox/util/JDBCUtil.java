package com.keduox.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/**
 * @author 马普琪 微信(bamaoer77) 微博（八猫儿77）
 */
public class JDBCUtil {
	private Properties properties;
	private final static JDBCUtil jdbcUtil = new JDBCUtil() ;

	private JDBCUtil() {
		properties = new Properties();
	}
	
	public static JDBCUtil getInstance() {
		return jdbcUtil;
	}
	
	static {
		// ① 注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() throws SQLException {
		String url = null;
		String user = null;
		String password = null;
		try {
			properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("mysqlconfig.properties"));
			url = properties.get("url").toString();
			user = properties.get("user").toString();
			password = properties.get("password").toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	/**
	 * 
	 * @param ps
	 * @param objs
	 *            一定要和 问号的顺序一致 对应上
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement psValues(PreparedStatement ps, Object... objs) throws SQLException {

		for (int i = 1, len = objs.length; i <= len; i++) {
			ps.setObject(i, objs[i - 1]);
		}

		return ps;
	}

	public void close(Statement st, Connection conn) {
		// ⑥ 关闭释放资源（十万分的关键，特别特别特别的关键，一定要关闭）
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void close(Statement st, ResultSet rs, Connection conn) {
		// ⑥ 关闭释放资源（十万分的关键，特别特别特别的关键，一定要关闭）
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(st, conn);
	}

	// 需要接收 rs 内部自己循环，返回一个 List<Map>
	public List<Map<String, Object>> getList(ResultSet rs) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();

		// 结果集表的 字段、结构、性质、表名
		ResultSetMetaData rsd = rs.getMetaData();
		// 获取当前结果集表中的字段有多少列
		int cols = rsd.getColumnCount();

		while (rs.next()) {
			Map<String, Object> map = new HashMap<>();
			// 字段顺序，从1 开始
			for (int i = 1; i <= cols; i++) {
				map.put(rsd.getColumnName(i), rs.getObject(i));
			}
			list.add(map);
		}

		return list;
	}

	// 接收 rs ，不需要判断 是否可执行， 将当前 rs 读取出来 构造成 Map 返回 Map
	public Map<String, Object> getMap(ResultSet rs) throws SQLException {
		// 结果集表的 字段、结构、性质、表名
		ResultSetMetaData rsd = rs.getMetaData();
		// 获取当前结果集表中的字段有多少列
		int cols = rsd.getColumnCount();

		Map<String, Object> map = new HashMap<>();
		for (int i = 1; i <= cols; i++) {
			map.put(rsd.getColumnName(i), rs.getObject(i));
		}

		return map;
	}
}
