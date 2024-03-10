package jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 
아래 쿼리를 자바로 구현한 코드 

SELECT 
	*
FROM emp e1
WHERE sal > (SELECT 
				min(sal)
			 FROM emp e2
			 WHERE e2.DEPTNO = e1.DEPTNO 
			 )

 */
public class EmpMain {

	public static void main(String[] args) {
		List<Emp> list = Arrays.asList(
		  new Emp(7369,"SMITH", "CLERK", 7902, "1980-12-17", 800, -1, 20)
		 ,new Emp(7499,"ALLEN", "SALESMAN", 7698, "1981-02-20", 1600, 300, 30)
		 ,new Emp(7521,"WARD", "SALESMAN", 7698, "1981-02-22", 1250, 200, 30)
		 ,new Emp(7566,"JONES", "MANAGER", 7839, "1981-04-02", 2975, 30, 20)
		 ,new Emp(7654,"MARTIN", "SALESMAN", 7698, "1981-09-28", 1250, 300, 30)
		 ,new Emp(7698,"BLAKE", "MANAGER", 7839, "1981-04-01", 2850, -1, 30)
		 ,new Emp(7782,"CLARK", "MANAGER", 7839, "1981-06-01", 2450, -1, 10)
		 ,new Emp(7788,"SCOTT", "ANALYST", 7566, "1982-10-09", 3000, -1, 20)
		 ,new Emp(7839,"KING", "PRESIDENT", -1, "1981-11-17", 5000, 3500, 10)
		 ,new Emp(7844,"TURNER", "SALESMAN", 7698, "1981-09-08", 1500, 0, 30)
		 ,new Emp(7876,"ADAMS", "CLERK", 7788, "1983-01-12", 1100, -1, 20)
		 ,new Emp(7900,"JAMES", "CLERK", 7698, "1981-10-03", 950, -1, 30)
		 ,new Emp(7902,"FORD", "ANALYST", 7566, "1981-10-03", 3000, -1, 20)
		 ,new Emp(7934,"MILLER", "CLERK", 7782, "1982-01-23", 1300, -1, 10)
		 ,new Emp(9000,"A_A KING", "MANAGER", 7902, "1981-12-17", 700, -1, 20)
		);

		//list.stream().forEach(System.out::println);
	
		List<Emp> result = deptMinSalaryList(list);
		result.stream().forEach(System.out::println);
		
	}

//	private static int deptMinSalary(List<Emp> list, int deptno) {
////		int min = Integer.MAX_VALUE;
////		for (Emp emp: list) {
////			if (emp.getDeptno() == deptno) {
////				min = Math.min(emp.getSal(), min);
////			}
////		}
////		return min;
//
////		스트림을 사용하여 구현한 코드 		
//		Optional<Emp> emp = list.stream()
//				.filter(e -> e.getDeptno() == deptno)
//				.min((a,b) -> a.getSal() - b.getSal());
//		return emp.isPresent() ? emp.get().getSal() : 0;
//	}
	
	//map을 사용하여 처리함 -> memoization 기법, caching 기법   
	static Map<Integer, Integer> deptMinSalaryMap = new HashMap<Integer, Integer>();
	private static int deptMinSalary(List<Emp> list, int deptno) {
		
		//map에 부서의 코드에 대한 결과 값이 존재하는지 확인하고 있으면 리턴 
		Integer result = deptMinSalaryMap.get(deptno);
		if (result != null) {
			return result.intValue(); 
		}
		
		Optional<Emp> emp = list.stream()
				.filter(e -> e.getDeptno() == deptno)
				.min((a,b) -> a.getSal() - b.getSal());
		result = emp.isPresent() ? emp.get().getSal() : 0;
		//새롭게 계산한 값을 map에 부서 번호를 키로해서 저장한다
		deptMinSalaryMap.put(deptno, result);
		return result;
	}
	
	//부서직원 중  최소급여 보다 많이 받는 직원목록얻기 
	private static List<Emp> deptMinSalaryList(List<Emp> list) {
		List<Emp> result = new ArrayList<>();
		
		//케싱하는 결과 집합을 초기화 한다 
		deptMinSalaryMap.clear();
		for (Emp emp: list) {
			int minSalary = deptMinSalary(list, emp.getDeptno());
			if (emp.getSal() > minSalary) {
				result.add(emp);
			}
		}
		return result;
	}
	
	

}
