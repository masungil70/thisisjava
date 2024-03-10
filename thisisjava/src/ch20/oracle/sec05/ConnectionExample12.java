package ch20.oracle.sec05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

/**
 * 간단하게 update 만 확인 
 */
public class ConnectionExample12 {
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
			
			CallableStatement cstmt = conn.prepareCall("{ ? = call user_login(?,?) }");
			Scanner scanner = new Scanner(System.in);
			while(true) {
				System.out.print("아이디 : ");
				String userid = scanner.nextLine();
				if (userid.equals("q")) break;
				
				System.out.print("비밀번호 : ");
				String userpassword = scanner.nextLine();

				//리턴값 위치 및 자료형 설정 
				cstmt.registerOutParameter(1, Types.INTEGER);
				//인자값 설정 
				cstmt.setString(2, userid);
				cstmt.setString(3, userpassword);

				//SQL 함수 실행
				cstmt.execute();
				
				//결과값 얻기 
				int result = cstmt.getInt(1);
				switch(result) {
				case 0:
					System.out.println("로그인 성공");
					break;
				case 1:
					System.out.println("비밀번호가 잘못되었습니다");
					break;
				case 2:
					System.out.println("아이디가 잘못되었습니다");
					break;
				}
			} 
			cstmt.close();
			
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