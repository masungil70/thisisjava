package moviebooking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MovieBookingSystem {
	private static Seat[][] seatLayout = new Seat[4][5]; // 4행 5열의 좌석 배열
	private static Scanner scanner = new Scanner(System.in);
	private static Map<Integer, Seat> resvervedNumberMap = new HashMap<Integer, Seat>();
	private static Set<Integer> resvervedGenSet = new HashSet<>();
	
	
	public static void menuOutput() {
        System.out.println("\n======== 영화관 좌석 예매 시스템 ========");
        System.out.println("1. 좌석 예매");
        System.out.println("2. 예매 조회");
        System.out.println("3. 예매 취소");
        System.out.println("4. 종료");
        System.out.println("메뉴를 선택하세요: ");
	}
	
    public static void main(String[] args) {
        // 좌석 배열 초기화
        initialize();
        
        int choice; // 사용자 선택
        
        do {
            // 메뉴 표시
        	menuOutput();
        	choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    bookSeat(); // 좌석 예매 기능 호출
                    break;
                case 2:
                    checkBooking(); // 예매 조회 기능 호출
                    break;
                case 3:
                    cancelBooking(); // 예매 취소 기능 호출
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        } while (choice != 4);
        
        scanner.close();
    }
    
    // 좌석 배열을 초기화하는 메서드
    private static void initialize() {
        for (int i = 0; i < seatLayout.length; i++) {
            for (int j = 0; j < seatLayout[i].length; j++) {
                seatLayout[i][j] = new Seat(i+1, j+1);
            }
        }
    }
    
    private static int genResvervedNumber() {
    	while(true) {
        	int resvervedNumber = (int)(Math.random() * 100000) % 900000 + 100000;
        	//생성된 예약 번호가 존재하지 않으면 사용할 수 있음 
        	if (!resvervedGenSet.contains(resvervedNumber)) {
        		resvervedGenSet.add(resvervedNumber);
        		return resvervedNumber;
        	}
    	}
    }
    // 좌석 예매 기능
    private static void bookSeat() {
        
        System.out.println("*********좌석 현황*********");
    	for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (seatLayout[i][j].isReserved()) {
                    System.out.print("[예매]");
                } else {
                    System.out.print("[" + (i + 1) + "-" + (j + 1) + "]");
                }
            }
            System.out.println();
        }
        
        System.out.println("좌석을 선택하세요  예)4-5");
        System.out.println("이미 예매된 좌석은 \"예매\"라고 표시됩니다.");
        String input = scanner.next(); // 사용자 입력
        String[] parts = input.split("-"); // 입력을 행과 열로 분리
        
        if (parts.length != 2) {
            System.out.println("유효하지 않은 좌석 선택입니다.");
            return;
        }
        
        int row = Integer.parseInt(parts[0]) - 1; // 행 번호
        int column = Integer.parseInt(parts[1]) - 1; // 열 번호
        
        // 예매 가능한 좌석인지 확인 후 예매 처리
        if (row > 0 && row < seatLayout.length && column > 0 && column < seatLayout[row].length) {
        	final Seat seat = seatLayout[row][column];
            if (!seat.isReserved()) {
                System.out.println("예매 가능합니다. 예매하시겠습니까?");
                System.out.println("네(1), 아니오(2), 초기화면(0)중 하나를 입력해주세요");
                switch(scanner.nextInt()) {
                case 1:
                	seat.reserve(genResvervedNumber());
                    System.out.println("예매가 완료되었습니다.");
                    System.out.println("예매한 좌석 번호 : [" + input + "]/예약번호:[" + seat.getResvervedNumber() + "]");
                    System.out.println("감사합니다.");

                    //예약 번호를 키로 해서 좌석객체를 저장한다
                    resvervedNumberMap.put(seat.getResvervedNumber(), seat);
                	break;
                case 0:
                	return;
                default:
                    System.out.println("예매가 취소 되었습니다");
                	break;
                }
            } else {
                System.out.println("이미 예매된 좌석입니다.");
            }
        } else {
            System.out.println("유효하지 않은 좌석 선택입니다.");
        }
    }
    
    // 예매 조회 기능
    private static void checkBooking() {
        System.out.println("======== 예매 조회 ========");
        
        System.out.println("예매번호를 입력해주세요");
        int reservedNumber = scanner.nextInt(); //예매번호 입력  
        Seat seat = resvervedNumberMap.get(reservedNumber);
        if (seat != null) {
          seat.output();
        } else {
        	System.out.println("예약번호를 다시 확인해주세요.");
        }
        
//        exit:
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 5; j++) {
//                if (seatLayout[i][j].isReserved() && reservedNumber == seatLayout[i][j].getResvervedNumber()) {
//                    System.out.println("고객님이 예매하신 좌석은 " + (i+1) + "-" + (j+1) + "입니다.");
//                    break exit;
//                }
//            }
//        }
        
    }
    
    // 예매 취소 기능
    private static void cancelBooking() {
        System.out.println("예매번호를 입력해주세요");
        int reservedNumber = scanner.nextInt(); //예매번호 입력  
        Seat seat = resvervedNumberMap.get(reservedNumber);
        if (seat != null) {
          seat.output();
          
          System.out.println("예매를 취소하시겠습니까?");
          System.out.println("네(1), 아니오(2) 중 하나를 입력해주세요");
          
          int input = scanner.nextInt();
          if(input == 1) {
              System.out.println("예매가 취소되었습니다. 감사합니다.");
              seat.cancelReservation();

              //예매번호를 이용하여 예약 정보를 관리하는 맵에 있는 키를 제거한다
              resvervedNumberMap.remove(reservedNumber);
              
          } else {
              System.out.println("예매를 취소...");
          }
          
        } else {
        	System.out.println("예약번호를 다시 확인해주세요.");
        }
        
//        exit:
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 5; j++) {
//                if (seatLayout[i][j].isReserved() && reservedNumber == seatLayout[i][j].getResvervedNumber()) {
//                    System.out.println("고객님이 예매하신 좌석은 " + i + "-" + j + "입니다.\n");
//                    
//                    System.out.println("예매를 취소하시겠습니까?");
//                    System.out.println("네(1), 아니오(2) 중 하나를 입력해주세요");
//                    
//                    int input = scanner.nextInt();
//                    if(input == 1) {
//                        System.out.println("예매가 취소되었습니다. 감사합니다.");
//                        seatLayout[i][j].cancelReservation();
//                    } else {
//                        System.out.println("예매를 취소...");
//                    }
//                    break exit;
//                }
//            }
//        }
    }
}
