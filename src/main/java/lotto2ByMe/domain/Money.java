package lotto2ByMe.domain;

public class Money {
	public static final int LOTTO_PRICE = 1000;
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private Integer money;

	// 다시요약) 생성자 파라미터에 포함되지 않는 인스턴스변수(래핑객체포함)는 생성자위 변수선언부에서 미리 초기화해두자.
	// -> 만약, 래핑된 변수면, new 래핑Class();로 초기화해야하니, -> 필수) 래핑클래스 내부에 파라미터 없는 생성자에서 값을 넣어주도록 만들어줘야한다.
	// TODO Wrap Integer(숫자) 2+) 외부에서 매핑변수를 초기화안하고 사용했다? -> 변수선언시 파라미터 없는 생성자로 초기화 내가 해줘야한다.
	// **단일 래핑 클래스는, 외부입력없이 생성되서 사용될 수 있으니(최소단위객체안에서 생성되는데, 객체 생성시 해당래핑변수의 값을 생성자 파라미터로 값을 안넣어줌), default값을 파라미터 없는 기본 생성자 만들어서 -> 생성자말고 선언부에 초기화해 주자.**
	// public Money() {
	// 	this.money = 0; // 사용처에서 외부입력없으면, <관리 인스턴스변수 자리에  private = new Money()의 기본생성자로 내가 초기화해줘야한다.>
	// }
	public Money(Integer money) {
		//TODO wrap3): convert
		this.money = money;
	}
	//TODO wrap 4) 생성자는 여러개(파라미터 받을 갯수 정해줄 수 있음)일 수 도 있다.

	//TODO wrap 1): 파라미터 검증(필요시) + 파라미터 추가 및 수정(default name)
	public static Money of(Integer money) {
		// TODO: 검증필요시 추가
		// checkMoneyValidation(money);
		return new Money(money);
	}

	//TODO: wrap 5) 단일객체부터는 비교를 해야하므로, equals && hashCode && toString 오버라이딩
	// ===> 같은객체끼리 비교 &&  this == [타.getter() 대체]를 위해  =====> equals 정의

	// getter대신 toInt로 정의
	public int toInt() {
		return this.money;
	}

	public int toLottoCount() {
		return this.money / LOTTO_PRICE;
	}

	// ===> 같은객체끼리 비교 && 부등호 > < 비교를 위해 1) getter 2) Comparable 정의
	//TODO: wrap 6) 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
	// -> 사실 객체끼리 비교보단, 객체List.stream(). 집계 ( 단일::비교함수compareTo   ) .get(). toInt(); 시 필요해서 그렇다.
	// (1) implements Comparable<Money> 이후
	// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  Money otherMoney  ) 로 수정
	// (3) return Integer.compare( this.money , otherMoney.getMoney());    으로  비교하게 하기s

	//TODO: wrap 7) 단일변수 매핑이라면 getter 거의 필수 -> equals 오버라이딩으로, 같은 객체 비교시의  ==는 대신할 수 있다!
	//TODO: wrap 8) 숫자라면, compareTo뿐만 아니라, increase, decrease 같은 내부숫자 증감 로직도 메소드로 짜야됨.
	// -> 수정된 값을 <생성자>에 넣어서 다시 호출하여 new 객체를 만들어 반환한다.
	// 데브후디: return new Position(this.position + MOVE_DISTANCE);
	// --> 증가후, Car가 들고 있는 Position객체인 this.position에
	// public void moveForward() {
	// 		if (willMove()) {
	// 			this.position = this.position.increase();
	// 		}
	// 	}
	// --> 증가된 숫자매핑객체 반환 -> 그걸 대입해줌..

	// 교육2: return new Distance(distance + 1);
	// 교육1: 그냥 this.변수++;

	// ==> 래핑클래스로 변환(상위객체 선언부에서 type변환후 정의)후에는,,
	// ==> 상위 객체의 생성자로 가서

	// TODO: 꼭!!!!! -> this.변수 = 생성파라; 해줬던 것을
	// -> this.변수 = new WrapClass( 생성파라 );

}
