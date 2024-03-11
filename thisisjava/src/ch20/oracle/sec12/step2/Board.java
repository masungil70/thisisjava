package ch20.oracle.sec12.step2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private String bdate;
	
	public Board(String title, String content, String writer) {
		this(0, title, content, writer);
	}
	
	public Board(int bno, String title, String content, String writer) {
		this(bno, title, content, writer, "");
	}

	
	public void print() {
		System.out.printf("%-6s%-12s%-16s%-40s \n"
				, bno
				, bwriter
				, bdate
				, btitle);
	}
	
	public void printDetail() {
		System.out.println("번호 : " + bno);
		System.out.println("작성자 : " + bwriter);
		System.out.println("작성일자 : " + bdate);
		System.out.println("제목 : " + btitle);
		System.out.println("내용 : " + bcontent);
	}





	
}