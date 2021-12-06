package racingCar1My.domain;

public class Train implements Comparable<Train>{
	//2) 단일객체가 가진 변수들  private 선언
	private final String name;
	private int position = 0;

	//3) 생성자는 public으로 해도될듯. for 테스트 등
	// but 파라미터를 of,from 스태틱 메서드에서 검증된 것을 그대로 사용한다.
	// -> 굳이 모든 인변을 다 초기화 안해도된다.
	// -> position같은 경우, 생성자 호출전에 애초에 0으로 초기화하는 경우도.
	public Train(String name) {
		//TODO: convert
		this.name = name;
	}
	//4) 생성자는 여러개(파라미터 받을 갯수 정해줄 수 있음)일 수 도 있다.

	//1)
	public static Train of(String name) {
		// TODO wrap 1) 필요시 검증
		// checkValidation(name);
		return new Train(name);
	}

	//TODO: wrap 5) 단일객체부터는 비교를 해야하므로, equals && hashCode && toString)
	// -> 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
	// (1) implements Comparable<Train> 이후 (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체로 수정
	// (3) return   Integer.compare( this. 인변 ,   들어오는< >객체. get인변() )    으로  비교하게 하기




	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int compareTo(Train o) {
		return 0;
	}
}
