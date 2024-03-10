
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Computer {
    public static void main(String[] args) {
        String[] students = {
        		  "강연규"
        		, "백승찬"
        		, "김혜진"
        		, "안아영"
        		, "박경덕"
        		, "안윤빈"
        		, "박상준"
        		, "박상훈"
        		, "이민영"
        		, "임소연"
        		, "박시은"
        		, "박종성"
        		, "정혜림"
        		, "조성희"
        		, "최정민"
        		, "배자현"};
        
        List<String> list = new ArrayList<>();
        for (String name : students) {
        	list.add(name);
        }
        Collections.shuffle(list);
        
        int i = 0;
        for (String name : list) {
        	System.out.println((i++ + 1) + "번 : " + name);
        }
    }
}
