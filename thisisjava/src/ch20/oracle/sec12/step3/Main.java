package ch20.oracle.sec12.step3;

import java.util.Scanner;

public class Main {
	// Field
	private Scanner scanner = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.사용자관리 | 2.게시판관리 | 3.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1" -> usersManager();
		case "2" -> boardManager();
		case "3" -> exit();
		}
	}

	private void usersManager() {
		UsersService usersExample = new UsersService(new UsersDAO());
		usersExample.list();
	}
	
	private void boardManager() {
		BoardService boardExample = new BoardService(new BoardDAO());
		boardExample.list();
	}
	
	private void exit() {
		System.exit(0);
	}

	public static void main(String [] args) {
		new Main().mainMenu(); 
	}
}
