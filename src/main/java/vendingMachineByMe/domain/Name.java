package vendingMachineByMe.domain;

import java.util.Objects;

public class Name {
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private String name;

	public Name(String name) {
	    //TODO wrap3): convert 필요시 추가
	    this.name = name;
	}
	//TODO wrap 4) 생성자는 여러개(파라미터 받을 갯수 정해줄 수 있음)일 수 도 있다. 이 때, 안받는 파라미터는 초기화까지

	//TODO wrap 1): 파라미터 검증(필요시) + 파라미터 추가 및 수정(default name)
	public static Name of(String name) {
	    // TODO: 검증필요시 추가
	    // checkNameValidation(name);
	    return new Name(name);
	}

	//TODO: wrap 5)  equals && hashCode 오버라이딩 필수 for 객체.인변 비교 -> 객체끼리 비교 가능하게

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Name name1 = (Name)o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	//TODO wrap 6)  toStirng : 객체 대신 문자열은 toStirng오버라이딩!!필수!!

	@Override
	public String toString() {
		return name;
	}

	//TODO wrap 7) 여지껏
	// 1) this.name가 사용됬던 곳                  ---> this.name.toString();
	// 2) this.name = 파라;로 생성+초기화 해줬던 곳 --->  this.name =  Name.of( 생성파라 );

}
