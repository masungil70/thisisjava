package ch20.oracle.sec12.step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*
 * java, jdbc를 분리하려고 함 
 * 
 */
public class BoardExample14 {
	// Field
	private Scanner scanner = new Scanner(System.in);
	//참조 변수 선언 
	BoardDAO boardDAO;
	
	
	// Constructor
	public BoardExample14(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	// Method
	public void list() {

		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------------------------------------------");

		List<Board> list = boardDAO.list();
		for (Board board : list) {
			board.print();
		}
		
		if (list.size() == 0) {
			System.out.println("게시물의 자료가 존재하지 않습니다");
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

		switch (menuNo) {
		case "1" -> create();
		case "2" -> read();
		case "3" -> clear();
		case "4" -> exit();
		}
	}

	public void create() {
		System.out.println("[새 게시물 입력]");
		System.out.print("제목: ");
		// 할일 : 제목 입력
		String title = scanner.nextLine();
		System.out.print("내용: ");
		// 할일 : 내용 입력
		String content = scanner.nextLine();
		System.out.print("작성자: ");
		// 할일 : 작성자 입력
		String writer = scanner.nextLine();

		// 할일 : 보조 메뉴 출력
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		if (menuNo.equals("1")) {
			int updated = boardDAO.insert(new Board(title, content, writer));
			// 변경된 건 수
			System.out.println("변경 건수  : " + updated);
		}

		// 게시물 목록 출력
		list();
	}

	public void read() {
		System.out.println("[게시물 읽기]");
		System.out.print("bno: ");
		// 게시물 번호 입력
		int bno = Integer.parseInt(scanner.nextLine());

		// 아래 구문이 동작할 수 있게 기능 추가
		// select * from boards where bno = ?

		Board board = boardDAO.read(bno);
		if (board != null) {
			board.printDetail();
			
			// 보조 메뉴 출력
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴 선택: ");
			String menuNo = scanner.nextLine();
			System.out.println();

			switch (menuNo) {
			case "1" -> update(bno);
			case "2" -> delete(bno);
			case "3" -> list();
			}
		} else {
			// 찾고자 하는 자료가 없음
			System.out.println("[" + bno + "] 에 대한 자료가 존재하지않습니다 ");
			bno = -1;
		}

	}

	public void update(int bno) {
		// 수정 내용 입력 받기
		System.out.println("[수정 내용 입력]");
		System.out.print("제목: ");
		// 할일 : 제목 입력
		String title = scanner.nextLine();
		System.out.print("내용: ");
		// 할일 : 내용 입력
		String content = scanner.nextLine();
		System.out.print("작성자: ");
		// 할일 : 작성자 입력
		String writer = scanner.nextLine();

		// 보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		// 아래 구문이 동작할 수 있게 기능 추가
		// update 구문 완성해서 구현 해주세요
		if (menuNo.equals("1")) {
			boardDAO.update(new Board(bno, title, content, writer));
		}
		// 게시물 목록 출력
		list();
	}

	public void delete(int bno) {
		// 아래 구문이 동작할 수 있게 기능 추가
		// delete 구문 완성해서 구현 해주세요
		int updated = boardDAO.delete(bno);

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		list();
	}

	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		// 아래 구문이 동작할 수 있게 기능 추가
		// delete 구문 완성해서 구현 해주세요
		int updated = boardDAO.clear();

		// 변경된 건 수
		System.out.println("삭제 건수  : " + updated);

		// 게시물 목록 출력
		list();
	}

	public void exit() {
		
//		if (conn != null) {
//			try {
//				// 연결 끊기
//				conn.close();
//				System.out.println("연결 끊기");
//			} catch (SQLException e) {
//			}
//		}
		
		System.exit(0);
	}

	public static void main(String[] args) {
		BoardExample14 boardExample = new BoardExample14(new BoardDAO());
		boardExample.list();
	}
}

