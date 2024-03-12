package ch20.oracle.sec12.step4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersDAO {
    // 1. 회원가입 목록 만들기
    // 2. 삭제 구현
    // 3. 수정 구현
    // 4. 상세보기 구현
    // 5. 전체삭제 구현
    // 6. 등록 구현
    private Scanner scanner = new Scanner(System.in);
    private static Connection conn = null;
    private static PreparedStatement userListPstmt = null;
    private static PreparedStatement userInsertPstmt = null;
    private static PreparedStatement userDeletePstmt = null;
    private static PreparedStatement userDetailPstmt = null;

    private static PreparedStatement userUpdatePstmt = null;
    private static PreparedStatement userDeleteAllPstmt = null;

    private static PreparedStatement userValidationIdPstmt = null;
    private static PreparedStatement userValidationPasswordPstmt = null;

    static {

        try {
            // 1. JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/msa2024",
					"root", //계정이름 
					"1004" //계정비밀번호
					);
            // 3. SQL 실행 객체 준비
            // 4. SQL 실행
            System.out.println("연결 성공");
            conn.setAutoCommit(false);

            userListPstmt = conn.prepareStatement("select * from users");
            userInsertPstmt = conn.prepareStatement("insert into users (userid, username, userpassword, userage, useremail) values (?, ?, ?, ?,?)");
            userDetailPstmt = conn.prepareStatement("select * from users where userid=?");
            userValidationIdPstmt = conn.prepareStatement("select userid from users where userid=?  ");
            userValidationPasswordPstmt  = conn.prepareStatement("select userpassword from users whrere userpassword=? ");
            //delete 가 되지 않았던 이유: ? 개수에 맞춰서 setString() 을 해주어야 한다.
            userDeletePstmt = conn.prepareStatement("delete from users where userid=?");
            userDeleteAllPstmt = conn.prepareStatement("delete from users");
            userUpdatePstmt = conn.prepareStatement("update users set username=?, userpassword=?,userage=?, useremail=? where userid=?");
            // 5. 결과 처리
            // 6. 연결 해제
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Users> list() {
        List<Users> list = new ArrayList<>();
        try {
            ResultSet rs = UsersDAO.userListPstmt.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getString("userid")
                        , rs.getString("username")
                        , rs.getString("userpassword")
                        , rs.getInt("userage")
                        , rs.getString("useremail"));
                
                list.add(users);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int insert(Users users){
        int updated = 0;
        try{
            userInsertPstmt.setString(1, users.getUserid());
            userInsertPstmt.setString(2, users.getUsername());
            userInsertPstmt.setString(3, users.getUserpassword());
            userInsertPstmt.setInt(4, users.getUserage());
            userInsertPstmt.setString(5, users.getUseremail());
            updated = userInsertPstmt.executeUpdate();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return updated;
    }
    public Users read(String userid) {

        Users users = null;
        try {
            userDetailPstmt.setString(1, userid);

            ResultSet rs = userDetailPstmt.executeQuery();
            if (rs.next()) {
                users = new Users(rs.getString("userid")
                        , rs.getString("username")
                        , rs.getString("userpassword")
                        , rs.getInt("userage")
                        , rs.getString("useremail"));
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public int update(Users users) {
        int updated = 0;
        try {
            userUpdatePstmt.setString(1, users.getUsername());
            userUpdatePstmt.setString(2, users.getUserpassword());
            userUpdatePstmt.setInt(3, users.getUserage());
            userUpdatePstmt.setString(4, users.getUseremail());
            userUpdatePstmt.setString(5, users.getUserid());
            updated = userUpdatePstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;

    }

    public int delete(String userid) {
        int updated = 0;

        try {
            userDeletePstmt.setString(1, userid);
            updated = userDeletePstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public int clear() {
        int updated = 0;
        try {
            updated = userDeleteAllPstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean validationId(String userid){
        boolean result = false;
        try {
            userValidationIdPstmt.setString(1, userid);
            ResultSet rs = userValidationIdPstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
    }
        return result;
    }

    public boolean  validationPassword(String userpassword){
        boolean result = false;
        try {
            userValidationPasswordPstmt.setString(1, userpassword);
            ResultSet rs = userValidationPasswordPstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}