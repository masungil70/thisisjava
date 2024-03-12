package ch20.oracle.sec12.step4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
    private String userid;
    private String username;
    private String userpassword;
    private  int  userage;
    private String useremail;

    public Users(String userid, int age, String email) {
        this.userid = userid;
        this.userage = age;
        this.useremail = email;
    }
    
    public void print() {
        System.out.printf("%-6s%-12s%-16s%-40s \n"
                , userid
                , username
                , userage
                , useremail);
    }

    public void printDetail() {
        System.out.println("번호 : " + userid);
        System.out.println("이름 : " + username);
        System.out.println("나이 : " + userage);
        System.out.println("이메일 : " + useremail);
    }

}