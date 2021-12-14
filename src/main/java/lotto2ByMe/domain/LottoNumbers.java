package lotto2ByMe.domain;

import java.util.List;

public class LottoNumbers {
	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<LottoNumber> lottoNumbers;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private생성자에 배정한다.
	// -> 외부list를 stream()map()에서 객체로 변환하여 list로 collect한다.
	public LottoNumbers(List<LottoNumber> list) {
		this.lottoNumbers = list;
	}

	//1) 객체List 매핑한 일급컬렉션 -> 스태틱생성메서드에서 검증은 똑같은데, 외부 파라미터도 List로 들어와야한다.
	// -> 객체 매핑 : of( String or Int)  / 객체 List매핑 : from ( List<> xxx ) -> 여긴 아마 바깥 파라미터로 인하 자동 생성될듯싶다.
	// -> 특별값을 반환해야하는 경우만, 들어갈 경우 if분기 return new 클래스( 특정값 );
	public static LottoNumbers from(List<LottoNumber> list) {
		//TODO: 일급컬렉션) 파라미터 검증(필요시)
		// checkLottoNumbersValidation(list);
		return new LottoNumbers(list);
	}

	public boolean isContains(LottoNumber lottoNumber) {
		return this.lottoNumbers.contains(lottoNumber);
		// return this.lottoNumbers.stream()
		// 	.anyMatch(num -> num.compareTo(lottoNumber) == 0);
	}

	//TODO: 쓰는 것을 지양할뿐이지, 일급컬렉션도, 더 상위 일급컬렉션이 받아가서 내부에서 사용한다면
	// ex> Cars.from( Names ) -> 내부에서 Names -> getter로 -> List<Names>으로 받와야야.. -> name -> Car.of (name) 생성
	//getter 대용
	public List<LottoNumber> toList() {
		return this.lottoNumbers;

		//TODO: 기존 List<결과값>을 대신하는 것이라면, 해당 list자리에
		//===========================
		// this.stream()... .collect(Collectors.toLit()) 자리에
		// -> LottoNumbers.from(   ) 를 씌워서, 일급컬렉션 객체로 만들어줘야하다.!!!
		//===========================

	}

	@Override
	public String toString() {
		return this.lottoNumbers.toString();
	}

	// 일급 -> stream 객 vs 일급 ==> 결과값리스트( ->맞춘 갯수 sum)의 리스트
	// public List<Integer> match(LottoNumbers winNumbers) {
	// -> 여기서도 일급컬렉션..
	// public int match(LottoNumbers winNumbers) {
	public int match(LottoNumbers winNumbers) {
		// return (int)this.lottoNumbers.stream()
		// .map( inputNumber -> inputNumber.match(winNumbers))
		// .mapToInt(winNumbers::match)
		// .filter(inputNumber -> winNumbers.isContains(inputNumber))
		// .count();
		return this.lottoNumbers.stream()
			// filter + isContains (or filter + has -> anyMatch + is )
			// 1) stream객체vs일급 -> filter + isContains이후, 해당객체의 수를 바로 count()
			// 2) stream객체v일급 ->자리바꿔 일급vs stream객체 -> stream객체vs stream객체
			//    시에는.. .filter가 아닌  map + 결과물return함수로 받아와야한다.
			.mapToInt(inputNumber -> winNumbers.match(inputNumber))
			.sum();
		// 각 객체별 종합이 온다. 이것을 더하면.. 일급의 카운트..
		// .sum());
		// TODO : 추가.. 1단위 결과값은 어떻게는 enum으로 저장
		// .collect(Collectors.toList());
		// .collect(toList());
		// .mapToObj( count -> LottoPrize.findByCount(count)) // 결과값 1개 아직도 아님..

	}

	// 일급-> stream객 vs 객 => 결과값 리스트 -> 리스트의 리스트 되기전에 여기서 합산.
	private int match(LottoNumber inputNumber) {
		return this.lottoNumbers.stream()
			.mapToInt(winNumber -> winNumber.match(
				inputNumber)) // 여기서 결과값1개임.. 숫자-숫자 0or1 -> List-숫자-sum() -> list-list - enum1개 결과값 1개
			.sum();
	}

}
