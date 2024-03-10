package jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
	private int    empno;
	private String ename;
	private String job;
	private int    mgr;
	private String hiredate;
	private int    sal;
	private int    comm;
	private int    deptno;
}
