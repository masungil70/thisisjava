package ch16.sec02.exam01;

class PersonWork implements Workable {
	@Override
	public void work() {
		System.out.println("회사에 출근하여 일을 하고 있습니다");
	}
}

public class LambdaExample {
	public static void main(String[] args) {
		Person person = new Person();
		
//		person.action(new PersonWork());
		person.action(new Workable() {
			@Override
			public void work() {
				System.out.println("익명의 클래스로 일할 수 있게 구현함");
			}
		});
		
//		int i=10;
//		if (i == 10) i = 100;
		
//		person.action(() -> System.out.println("익명의 클래스로 일할 수 있게 구현함"));		

		//실행문이 두 개 이상인 경우 중괄호 필요
		person.action(() -> {
			System.out.println("출근을 합니다.");
			System.out.println("프로그래밍을 합니다.");
		});

		//실행문이 한 개일 경우 중괄호 생략 가능
		person.action(() -> System.out.println("퇴근합니다."));
	}
}