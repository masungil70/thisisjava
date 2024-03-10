package ch20.oracle.sec05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

/**
 * oracle 11g -> 
 * 	ojdbc6.jar -> jdk8 이하
 * 	ojdbc7.jar -> jdk8 이하
 *  ojdbc8.jar -> jdk17 이상
 */
public class ConnectionExample10 {
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
			
			CallableStatement cstmt = conn.prepareCall("{ call USERS_CREATE(?,?,?,?,?, ?) }");
			Scanner scanner = new Scanner(System.in);
			while(true) {
				System.out.print("아이디 입력 : ");
				String userid = scanner.nextLine();
				if (userid.equals("q")) break;
	
				//입력 값을 설정 한다
				cstmt.setString(1, userid);
				cstmt.setString(2, "홍길동");
				cstmt.setString(3, "1004");
				cstmt.setInt(4, 20);
				cstmt.setString(5, "hong1@naver.com");
				//리턴위치 및 자료형 설정 
				cstmt.registerOutParameter(6, Types.INTEGER);
				
				System.out.println("입력된 아이디 : " + userid);
				
				cstmt.execute();
				int updated = cstmt.getInt(6);
				
				if (updated != 0) {
					//변경된 건 수 
					System.out.println("회원 가입 성공");
				} else {
					System.out.println("회원 가입 실패");
				}
			}
			
			
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