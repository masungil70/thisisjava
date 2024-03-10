package moviebooking;

public class Seat {
	private int row;
	private int col;
    private boolean reserved; // 좌석 예약 여부
    private int resvervedNumber; //예약번호 
    
    public Seat(int row, int col) {
    	this.row = row;
    	this.col = col;
    }
    
    // 좌석 예약 여부를 반환하는 메서드
    boolean isReserved() {
        return reserved;
    }
    
    // 좌석을 예약하는 메서드
    void reserve(int resvervedNumber)  {
    	this.resvervedNumber = resvervedNumber;  
        this.reserved = true;
    }
    
    // 좌석 예약을 취소하는 메서드
    void cancelReservation() {
        this.reserved = false;
    }
    
    //예약번호 리턴
    public int getResvervedNumber() {
    	return resvervedNumber;
    }
    
//    public int getRow() {
//    	return row;
//    }
//    
//    public int getCol() {
//    	return col;
//    }
//    
    public void output() {
        System.out.println("고객님이 예매하신 좌석은 " + row + "-" + col + "입니다.");
    }
}