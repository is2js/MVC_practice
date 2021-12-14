package vendingMachineByMe.domain;

import java.util.Objects;

import vendingMachineByMe.utils.Util;

public class Amount implements Comparable<Amount> {
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private int amount;

	//TODO wrap2) 파라미터가 스태틱객체생성메서드(of, from)과 동일한지, 검증 추가 등으로 변경됬음 -> 여기도 변경
	public Amount(String amount) {
		// 이미 스태틱함수에서 검증된 String -> int로 바로 convert by 유틸메서드
		this.amount = convertStringToInt(amount); // 유틸로 이동시키기
	}

	private int convertStringToInt(String money) {
		return Integer.parseInt(money);
	}

	//TODO wrap 4) 숫자래핑객체가 default값을 가지고 태어날 경우 (파라미터 없이) ex> index, position
	// -> ex> 숫자+문자 2개를 래핑하는 Car최소단위객체; -> 시작 Position은 내부 position이 default 0으로 생성되어야함
	// --> cf) 초기값을 넣고 시작할거면, 선언부 =0;에서 하든 생성자에서 this.  = 0;을 하든 상관없음.
	// 실례: 2개이상래핑 최소단위객체에서는,
	// (1)외부leastobj : 선언부 그냥 default값 신경안쓰고, 파라미터 없이 객체 생성해서 초기화함.(생성자에서도 해줘도됨)
	// private Position position = new Position();
	// ======= 파라미터없이 default값으로 태어나는 숫자래핑객체면 여기서부터 남기기==========================================
	// (2)내부 숫자래핑class: 외부에서 쓰고있던 파라미터 없는 생성자 만들어서 -> default값 들어가게 해줌
	// -> 선언부에서 default값 넣고, 생성자는 비워도도될듯?
	// public Amount() {
	// 	this.amount = 0; // default값 넣어주기
	// }
	// ======= 파라미터없이 default값으로 태어나는 숫자래핑객체면 여기서부터 남기기==========================================

	//TODO: wrap 5) 숫자래핑의 객체vs객체 비교를 위해 택1
	//1)  @equals ->[인변.등호(==) 비교를 대신]할 예정이다 ->  ㅠ_ㅜ [일급.isContains() ]사용을 위해서라도, 반드시 오버라이딩  오버라이딩후 사용

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Amount amount1 = (Amount)o;
		return amount == amount1.amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	//2)   정렬이나 max집계가 사용된다.  or 부등호비교도 들어간다. -> 기준역할도 하는 @compareTo 정의
	//     -> Comparator자리에  [ max(Amount::compareTo) ]  / 객체 등호비교시 [ 객체1.compareTo(객체2) >= == <= 0 ]으로 사용
	// implements Comparable<Amount>
	// return Integer.compare( this.amount , o.amount);    으로  비교하게 하기

	//TODO wrap 1): 파라미터 검증(필요시) + reutrn 생성자 호출시 파라미터 추가 및 수정(default name)
	public static Amount of(String amount) {
		Util.checkAmountValidation(amount);
		return new Amount(amount);

	}

	//TODO: wrap6) 내부 숫자인변의 증가, 감소 -> 다 메소드로 작성해야한다.
	//1) 증가 사용
	// this.position.incraese()
	//2) 메소드 정의
	// public Amount increase() {
	// 	this.amount++;
	// }

	// getter대신 toInt로 정의
	public int toInt() {
		return this.amount;
	}

	@Override
	public int compareTo(Amount o) {
		return Integer.compare(this.amount, o.amount);
	}

	public void update(int i) {
		this.amount = i;
	}

	public void discharge(Amount toAmount) {
		this.amount -= toAmount.amount;
	}

	public boolean isSameOrGreaterThan(Amount o) {
		return this.compareTo(o) >= 0;
	}

	public void decrease(int pickedAmount) {
		this.amount -= pickedAmount;
	}

	// TODO wrap7) 여지껏
	// 1) this.amount가 사용됬던 곳                  ---> this.amount.toInt();
	// 2) this.amount = 파라;로 생성+초기화 해줬던 곳 --->  this.amount =  Amount.of( 생성파라 );
}
