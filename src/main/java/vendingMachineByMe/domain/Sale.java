package vendingMachineByMe.domain;

import vendingMachineByMe.utils.Util;

public class Sale implements Comparable<Sale> {
	//TODO 1: 내부 구성된 원시변수들을 -> 래핑처리 하고온다.
	private final Name name;
	private Amount amount;
	private Count count;

	//TODO 5: of/from 스태틱객체생성메서드와 파라미터가 동일한지 확인한다.
	public Sale(String name, String amount, String count) {
		//TODO 6 : 만약 문자열 -> 숫자 convert시 여기다 넣어준다.
		this.name = Name.of(name);
		this.amount = Amount.of(amount);
		this.count = Count.of(count);
	}

	// 살리면 Util로 옮기기
	private int convertStringToInt(String value) {
		return Integer.parseInt(value);
	}

	// TODO 3: 숫자래핑변수의 default값 없이  둘다 정보를 받으면, 파라미터를 수정한다. -> 생성자 파라미터도 수정한다.
	public static Sale of(String name, String amount, String count) {
		// TODO 9패턴까진 확인했지만, 그 이후 검증은 여기서 해주자.
		//amout와 count를 생성해본 경험으로.. 그 검증도 이미 있다?
		// -> util에 옮겨놓고 쓰면 될듯?
		Util.checkAmountValidation(amount);
		Util.checkCountValidation(count);

		return new Sale(name, amount, count);
	}

	//TODO 7) 객체끼리 비교시, 2개를 다 사용한다??
	// 1) 3개 변수 모두 같을 때용 ->  equals 오버라이딩  (필수) for 모두같 + isContains

	// 3) 숫자로 객체 비교(정렬,집계)용 -> [크기비교]나 [max집계+정렬 사용]시 -> compareTo 오버라이딩후, 숫자래핑변수만 사용
	// (1) implements Comparable<Sale> 이후
	// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  Sale otherSale  ) 로 수정
	// (3) return Integer.compare( this.amount.toInt() , otherSale.amount.toInt());    으로  비교하게 하기
	//  혹은  return Integer.compare( this.count.toInt() , otherSale.count.toInt());    으로  비교하게 하기

	// TODO 숫자중에는 택1해야할듯..비교/집계/정렬의 기준이 될 놈으로

	@Override
	public String toString() {
		return name.toString();
	}

	public boolean isSameName(String saleName) {
		return this.name.toString().equals(saleName);
	}

	public Amount toAmount() {
		return this.amount;
	}

	@Override
	public int compareTo(Sale o) {
		return Integer.compare(this.amount.toInt(), o.amount.toInt());
	}

	public int toAmountInt() {
		return amount.toInt();
	}

	public boolean isOrLessThan(Amount insertedAmount) {
		return this.amount.compareTo(insertedAmount) <= 0;
	}

	public boolean isSame(String inputValue) {
		return this.name.toString().equals(inputValue);
	}

	public int toCount() {
		return this.count.toInt();
	}
}
