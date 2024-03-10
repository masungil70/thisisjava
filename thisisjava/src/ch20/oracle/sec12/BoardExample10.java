package ch20.oracle.sec12;

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
	Set<Board> boardSet = new HashSet<Board>();
	Map<Integer, Board> boardMap = new HashMap<Integer, Board>();
	
	//Constructor
	
	//Method		
	public void list() {
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("-----------------------------------------------------------------------");
//		System.out.printf("%-6s%-12s%-16s%-40s \n", 
//				"1", "winter", "2024.01.27", "게시판에 오신 것을 환영합니다.");
//		System.out.printf("%-6s%-12s%-16s%-40s \n", 
//				"2", "winter", "2024.01.27", "올 겨울은 많이 춥습니다.");
		
		//할일 : 배열에 저장된 것을 출력할 수 있게 기능 추가한다 
		for (Board board : boardSet) {
		//for (Board board : boardList) {
//			System.out.printf("%-6s%-12s%-16s%-40s \n"
//					, board.getBno()
//					, board.getBtitle()
//					, board.getBdate()
//					, board.getBcontent());
			board.print();
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
			//할일 : 입력된 정보를 기준으로 객체를 생성하여 배열에 저장 하는 기능을 구현한다 
			//boardList.add(new Board(title, content, writer));
			Board board = new Board(title, content, writer);
			boardSet.add(board);
			boardMap.put(board.getBno(), board);
		}
		
		//게시물 목록 출력
		list();
	}
	
	public void read() {
		System.out.println("[게시물 읽기]");
		System.out.print("bno: ");
		//게시물 번호 입력 
		int bno = Integer.parseInt(scanner.nextLine());
		
		//할일 : 입력된 게시물 번호를 이용하여 게시물 목록에서 자료를 찾아 출력한다
		Board findBoard = boardMap.get(bno);
//		for (Board board : boardList) {
//			if (board.getBno() == bno) {
//				findBoard = board;
//				break;
//			}
//		}
		
		if (findBoard != null) {
//			System.out.printf("%-6s%-12s%-16s%-40s \n"
//			, board.getBno()
//			, board.getBtitle()
//			, board.getBdate()
//			, board.getBcontent());
			findBoard.printDetail();
	
			//보조 메뉴 출력
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴 선택: ");
			String menuNo = scanner.nextLine();
			System.out.println();
			
			switch(menuNo) {
			case "1" -> update(findBoard);
			case "2" -> delete(findBoard);
			case "3" -> list(); 
			}
		}
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
		//할일 : 입력된 정보를 기준으로 게시물 정보 수정한다
		if ("1".equals(menuNo)) {
			board.setBtitle(title);
			board.setBcontent(content);
			board.setBwriter(writer);
		}
		
		//게시물 목록 출력
		list();
	}
	
	public void delete(Board board) {
		//할일 : 게시물 정보 삭제
		//boardList.remove(board);
		boardSet.remove(board);
		boardMap.remove(board.getBno());
		
		//게시물 목록 출력		
		list();
	}
		
	public void clear() {
		System.out.println("[게시물 전체 삭제]");
		//할일 : 게시물 전체 삭제 기능 구현
		//boardList.clear();
		boardSet.clear();
		boardMap.clear();
		
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