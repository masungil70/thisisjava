package ch16.sec05.exam02;

public class MethodReferenceExample {
	public static void main(String[] args) {
		Person person = new Person();
//		person.ordering((a, b) -> {
//			return a.compareTo(b);
//		} );
		//person.ordering((a, b) -> a.compareTo(b));
		person.ordering(String::compareTo);
		//person.ordering(String :: compareToIgnoreCase);
	}
}