package com.notify.model;

import java.util.*;
import java.sql.*;

public class NotifyJDBCDAO implements NotifyDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/hw_schema?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12352";

	private static final String INSERT_STMT = 
		"INSERT INTO notify (mem_id, notify_content) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT notify_no, mem_id, notify_content FROM notify order by notify_no";
	private static final String GET_ONE_STMT = 
		"SELECT notify_no, mem_id, notify_content FROM notify where notify_no = ?";
	private static final String DELETE = 
		"DELETE FROM notify where notify_no = ?";
	private static final String UPDATE = 
		"UPDATE notify set mem_id=?, notify_content=? where notify_no = ?";

	@Override
	public void insert(NotifyVO notifyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT); //處理問號

			pstmt.setInt(1, notifyVO.getMem_id()); // 提取屬性
			pstmt.setString(2, notifyVO.getNotify_content());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(NotifyVO notifyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, notifyVO.getMem_id());
			pstmt.setString(2, notifyVO.getNotify_content());
			pstmt.setInt(3, notifyVO.getNotify_no()); 



			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer notify_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, notify_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public NotifyVO findByPrimaryKey(Integer notifyno) {

		NotifyVO notifyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, notifyno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// notifyVo 也稱為 Domain objects
				notifyVO = new NotifyVO();
				notifyVO.setNotify_no(rs.getInt("notify_no"));
				notifyVO.setMem_id(rs.getInt("mem_id"));
				notifyVO.setNotify_content(rs.getString("notify_content"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return notifyVO;
	}

	@Override
	public List<NotifyVO> getAll() {
		List<NotifyVO> list = new ArrayList<NotifyVO>();
		NotifyVO notifyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// notifyVO 也稱為 Domain objects
				notifyVO = new NotifyVO();
				notifyVO.setNotify_no(rs.getInt("notify_no"));
				notifyVO.setMem_id(rs.getInt("mem_id"));
				notifyVO.setNotify_content(rs.getString("notify_content"));

				list.add(notifyVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		NotifyJDBCDAO dao = new NotifyJDBCDAO();

		// 新增
//		NotifyVO notifyVO1 = new NotifyVO();
//		notifyVO1.setMem_id(223);
//		notifyVO1.setNotify_content("通知訊息2");
//		dao.insert(notifyVO1);

		// 修改
//		NotifyVO notifyVO2 = new NotifyVO();
//		notifyVO2.setNotify_no(30);
//		notifyVO2.setMem_id(2);
//		notifyVO2.setNotify_content("通知訊息5");
//		dao.update(notifyVO2);

		// 刪除
//		dao.delete(29);

		// 查詢 (單一)
//		NotifyVO notifyVO3 = dao.findByPrimaryKey(1);
//		System.out.print(notifyVO3.getNotify_no() + ",");
//		System.out.print(notifyVO3.getMem_id() + ",");
//		System.out.print(notifyVO3.getNotify_content() + "\n");
//		System.out.println("---------------------");

		// 查詢 (全部)
//		List<NotifyVO> list = dao.getAll();
//		for (NotifyVO aNotify : list) {
//			System.out.print(aNotify.getNotify_no() + ",");
//			System.out.print(aNotify.getMem_id() + ",");
//			System.out.print(aNotify.getNotify_content() + ",");
//			System.out.println();
//		}
		
	}
}