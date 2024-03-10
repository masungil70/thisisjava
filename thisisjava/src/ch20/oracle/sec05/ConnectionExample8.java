package ch20.oracle.sec05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 간단하게 update 만 확인 
 */
public class ConnectionExample8 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"bituser", //계정이름 
					"1004" //계정비밀번호
					);
			
			System.out.println("연결 성공");
			
			PreparedStatement pstmt = conn.prepareStatement("select * from users");
			Scanner scanner = new Scanner(System.in);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//찾고자 하는 자료가 있음 
				String userid = rs.getString("userid");
				String username = rs.getString("username");
				String userpassword = rs.getString("userpassword");
				int    userage = rs.getInt("userage");
				String useremail = rs.getString("useremail");
				
				System.out.println("userid : " + userid);
				System.out.println("username : " + username);
				System.out.println("userpassword : " + userpassword);
				System.out.println("userage : " + userage);
				System.out.println("useremail : " + useremail);
				System.out.println("====================\n");
				
			} 
			rs.close();
			pstmt.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//연결 끊기
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {}
			}
		}
	}
}		