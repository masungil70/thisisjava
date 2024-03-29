package ch09.sec06.exam03;

import ch09.sec06.exam03.Button.ClickListener;

public class ButtonExample5 {
	
	String foo() {
		return "aaa";
	}
	public static void main(String[] args) {

		String s1 = "문자열";
		String s2 = new String("문자열");
		
		if (s1.equals(s2)) {
			System.out.println("두 문자열이 같음");
		}
		if (s1 == s2) {
			System.out.println("두 문자열이 같음");
		}

		//Ok 버튼 객체 생성
		Button btnOk = new Button();
		btnOk.click();

		//Ok 버튼 클릭 이벤트를 처리할 ClickListener 구현 클래스(로컬 클래스)
		class OkListener implements Button.ClickListener {
			@Override
			public void onClick() {
				System.out.println("Ok 버튼을 클릭했습니다.");
			}
		}
		
		ClickListener listner = new Button.ClickListener() {
			@Override
			public void onClick() {
				System.out.println("Ok 버튼을 클릭했습니다.");
			}
		};

		//Ok 버튼 객체에 ClickListener 구현 객체 주입
		btnOk.setClickListener(() -> System.out.println("Ok 버튼을 클릭했습니다."));
		
		//Ok 버튼 클릭하기
		btnOk.click();

		//-----------------------------------------------------------------------------
		
		//Cancel 버튼 객체 생성
		Button btnCancel = new Button();

		//Cancel 버튼 클릭 이벤트를 처리할 ClickListener 구현 클래스(로컬 클래스)
		class CancelListener implements Button.ClickListener {
			@Override
			public void onClick() {
				System.out.println("Cancel 버튼을 클릭했습니다.");
			}
		}
		
		//Cancel 버튼 객체에 ClickListener 구현 객체 주입
		btnCancel.setClickListener(new CancelListener());
		
		//Cancel 버튼 클릭하기
		btnCancel.click();
	}
}