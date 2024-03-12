package ch20.oracle.sec12.step3;

import java.util.List;
import java.util.Scanner;

public class UsersService {

	private Scanner scanner = new Scanner(System.in);
	UsersDAO usersDAO;

	public UsersService(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
//		this.mainMenu();
	}

//secession
	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1" -> join();
		case "2" -> detail();
		case "3" -> clear();
		case "4" -> exit();
		}
	}

	public void join() {
		System.out.println("[새 회원 가입]");
		System.out.print("아이디: ");
		String id = scanner.nextLine();
		// validation 시작
		// 1. 아이디가 중복되는지 확인
		// 불러온다. 아이디를 read() 사용하여. 아이디가 존재하면, "이미 존재하는 아이디입니다" 출력
		if (usersDAO.validationId(id)) {
			System.out.println("어림도 없다!");
		} else {
			System.out.println("이름:");
			String name = scanner.nextLine();
			System.out.print("비밀번호: ");
			String password = scanner.nextLine();
			// password validatin 시작
			// 1. 비밀번호가 8자리 이상인지 확인
			// 2. 비밀번호가 영문자, 숫자, 특수문자를 포함하는지 확인
			if (password == null) {
				System.out.println("비밀번호가 null일 수 없습니다.");
			} else {
				if (password.length() < 8) {
					System.out.println("비밀번호가 8자리 이상이어야 합니다.");
				}
				if (!password.matches(".*[a-zA-Z].*")) {
					System.out.println("비밀번호에는 영문자가 포함되어야 합니다.");
				}
				if (!password.matches(".*[0-9].*")) {
					System.out.println("비밀번호에는 숫자가 포함되어야 합니다.");
				}
				if (!password.matches(".*[!@#$%^&*].*")) {
					System.out.println("비밀번호에는 특수문자가 포함되어야 합니다.");
				}
			}
			System.out.println("나이:");
			int age = Integer.parseInt(scanner.nextLine());
			System.out.print("이메일: ");
			String email = scanner.nextLine();

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
			System.out.print("메뉴 선택: ");
			String menuNo = scanner.nextLine();
			if (menuNo.equals("1")) {
				int updated = usersDAO.insert(new Users(id, name, password, age, email));
				System.out.println("변경 건수  : " + updated);
			}
			mainMenu();
		}

	}

	public void detail() {
		System.out.println("아이디를 입력하세요");
		String id = scanner.nextLine();
		Users users = usersDAO.read(id);
		if (users == null) {
			System.out.println("[" + id + "] 해당하는 사용자가 존재하지 않습니다.");
			return;
		}
		System.out.println("아이디: " + users.getUserid());
		System.out.println("이름: " + users.getUsername());
		System.out.println("나이: " + users.getUserage());
		System.out.println("이메일: " + users.getUseremail());

		if (id != null) {
			// 보조 메뉴 출력
			System.out.println("-------------------------------------------------------------------");
			System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
			System.out.print("메뉴 선택: ");

			String menuNo = scanner.nextLine();
			System.out.println();

			switch (menuNo) {
			case "1" -> update(id);
			case "2" -> delete(id);
			case "3" -> mainMenu();
			}
		}

	}

	public void update(String id) {
		Users updateUsers = usersDAO.read(id);
		if (updateUsers == null) {
			System.out.println("[" + id + "] 에 대한 자료가 존재하지않습니다 ");
			id = null;

		}
		System.out.println("[수정 내용 입력]");
		System.out.print("이름: ");
		String username = scanner.nextLine();
		System.out.print("비밀번호: ");
		String password = scanner.nextLine();
		System.out.print("나이: ");
		int age = Integer.parseInt(scanner.nextLine());
		System.out.print("이메일: ");
		String email = scanner.nextLine();

		// 보조 메뉴 출력
		System.out.println("-------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menNo = scanner.nextLine();
		// commit
		if (menNo.equals("1")) {
			usersDAO.update(new Users(id, username, password, age, email));
		}

		// 아래 구문이 동작할 수 있게 기능 추가
		// update 구문 완성해서 구현 해주세요

		// 게시물 목록 출력
		mainMenu();
	}

	public void delete(String id) {
		System.out.println("삭제할 id :");
		id = scanner.nextLine();
		int updated = usersDAO.delete(id);
		System.out.println("삭제 내용 : " + updated);
		mainMenu();
	}

	public void clear() {
		System.out.println("게시물 전체 삭제");

		int clear = usersDAO.clear();
		System.out.println("삭제 건수 : " + clear);

		mainMenu();

	}

	public void exit() {

		System.out.println("프로그램 종료");
		System.exit(0);
	}

    public void list(){
 
        List<Users> list = usersDAO.list();
        for (Users users : list) {
            users.print();
        }
        if (list.size() == 0) {
            System.out.println("회원정보가 존재하지 않습니다");
        }
        mainMenu();
    }
	public static void main(String[] args) {
		UsersService usersExample = new UsersService(new UsersDAO());
		usersExample.list();

	}
}