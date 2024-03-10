package ch20.oracle.sec12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BoardExample10 {
	//Field
	private Scanner scanner = new Scanner(System.in);
	//할일 : 게시물 정보를 저장 할 수 있는 배열을 선언한다 
	//List<Board> boardList = new ArrayList<Board>();
	//set/map 으로 변경
	//Set<Board> boardSet = new HashSet<Board>();
	//Map<Integer, Board> boardMap = new HashMap<Integer, Board>();
	
	//Constructor
	
	//Method		
	public void list() {
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------------------------------------------");
		// 아래 구문이 동작할 수 있게 기능 추가 
		// select * from boards
		
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
			
			PreparedStatement pstmt = conn.prepareStatement("select * from boards");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//찾고자 하는 자료가 있음 
				String bno = rs.getString("bno");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String bwriter = rs.getString("bwriter");
				String bdate = rs.getString("bdate");
				
				System.out.println("bno : " + bno);
				System.out.println("btitle : " + btitle);
				System.out.println("bcontent : " + bcontent);
				System.out.println("bwriter : " + bwriter);
				System.out.println("bdate : " + bdate);
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
		mainMenu();
	}
	
	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			case "1" -> create();
			case "2" -> read();
			case "3" -> clear();
			case "4" -> exit();
		}
	}	
	
	public void create() {
		System.out.println("[새 게시물 입력]");
		System.out.print("제목: ");
		//할일 : 제목 입력
		String title = scanner.nextLine();
		System.out.print("내용: "); 	
		//할일 : 내용 입력
		String content = scanner.nextLine();
		System.out.print("작성자: "); 	
		//할일 : 작성자 입력
		String writer = scanner.nextLine();
		
		//할일 : 보조 메뉴 출력
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		if(menuNo.equals("1")) {
			
			// 아래 구문이 동작할 수 있게 기능 추가 
			//  insert into boards (
			//    bno, btitle, bcontent, bwriter, bdate
			//  ) values (
			//    seq_bno.nextval, ?, ?, ?, sysdate
			//  ) 

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
				
				PreparedStatement pstmt = conn.prepareStatement("insert into boards (bno, btitle, bcontent, bwriter, bdate) values (seq_bno.nextval, ?, ?, ?, sysdate)");
		
				//입력 값을 설정 한다
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, writer);
					
				int updated = pstmt.executeUpdate();
				//변경된 건 수 
				System.out.println("변경 건수  : " + updated);
				
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
		
		//게시물 목록 출력
		list();
	}
	
	public void read() {
		System.out.println("[게시물 읽기]");
		System.out.print("bno: ");
		//게시물 번호 입력 
		int bno = Integer.parseInt(scanner.nextLine());
		
		// 아래 구문이 동작할 수 있게 기능 추가 
		//select * from boards where bno = ?
		
		
//		if (findBoard != null) {
//			System.out.printf("%-6s%-12s%-16s%-40s \n"
//			, board.getBno()
//			, board.getBtitle()
//			, board.getBdate()
//			, board.getBcontent());
//			findBoard.printDetail();
	
//			//보조 메뉴 출력
//			System.out.println("-------------------------------------------------------------------");
//			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
//			System.out.print("메뉴 선택: ");
//			String menuNo = scanner.nextLine();
//			System.out.println();
//			
//			switch(menuNo) {
////			case "1" -> update(findBoard);
////			case "2" -> delete(findBoard);
//			case "3" -> list(); 
//			}
//		}
	}
	
	public void update(Board board) {
		//수정 내용 입력 받기
		System.out.println("[수정 내용 입력]");
		System.out.print("제목: "); 	
		//할일 : 제목 입력
		String title = scanner.nextLine();
		System.out.print("내용: "); 	
		//할일 : 내용 입력
		String content = scanner.nextLine();
		System.out.print("작성자: "); 	
		//할일 : 작성자 입력
		String writer = scanner.nextLine();
		
		//보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		// 아래 구문이 동작할 수 있게 기능 추가 
		// update 구문 완성해서 구현 해주세요
		
		//할일 : 입력된 정보를 기준으로 게시물 정보 수정한다
//		if ("1".equals(menuNo)) {
//			board.setBtitle(title);
//			board.setBcontent(content);
//			board.setBwriter(writer);
//		}
		
		//게시물 목록 출력
		list();
	}
	
	public void delete(Board board) {
		// 아래 구문이 동작할 수 있게 기능 추가 
		// delete 구문 완성해서 구현 해주세요 

		//할일 : 게시물 정보 삭제
		//boardList.remove(board);
		//boardSet.remove(board);
		//boardMap.remove(board.getBno());
		
		//게시물 목록 출력		
		list();
	}
		
	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		// 아래 구문이 동작할 수 있게 기능 추가 
		// delete 구문 완성해서 구현 해주세요 

		//할일 : 게시물 전체 삭제 기능 구현
		//boardList.clear();
		//boardSet.clear();
		//boardMap.clear();
		
		//게시물 목록 출력
		list();
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		BoardExample10 boardExample = new BoardExample10();
		boardExample.list();
	}
}


/*
트렌젝션 동작 할 수 있게 추가 할 것

setAutoCommit(false);

insert, delete, update 구문 실 행 후 commit() 실행 할 것 
  
*/ 

