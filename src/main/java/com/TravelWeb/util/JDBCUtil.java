package com.TravelWeb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

	public static void close(Connection conn,
					  PreparedStatement pstmt,
					  ResultSet rs) {
		try {
			if(conn != null) conn.close();
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
		} catch (Exception e2) {
			System.out.println("close 에러");
		}
		
		
	}
	
}
