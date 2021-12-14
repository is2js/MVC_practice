package lotto2ByMe.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbersList {
	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<LottoNumbers> lottoNumbersList;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private생성자에 배정한다.
	// -> 외부list를 stream()map()에서 객체로 변환하여 list로 collect한다.
	public LottoNumbersList(List<LottoNumbers> list) { //TODO: from와 동일한 파라미터로 변경해주기
		this.lottoNumbersList = list;
	}

	//1) 객체List 매핑한 일급컬렉션 -> 스태틱생성메서드에서 검증은 똑같은데, 외부 파라미터도 List로 들어와야한다.
	// -> 객체 매핑 : of( String or Int)  / 객체 List매핑 : from ( List<> xxx ) -> 여긴 아마 바깥 파라미터로 인하 자동 생성될듯싶다.
	// -> 특별값을 반환해야하는 경우만, 들어갈 경우 if분기 return new 클래스( 특정값 );
	public static LottoNumbersList from(List<LottoNumbers> list) {
		//TODO: 일급컬렉션) 파라미터 검증(필요시)
		// checkLottoNumbersListValidation(list);
		return new LottoNumbersList(list);
	}

	public boolean isContains(LottoNumbers lottoNumbers) {
		return this.lottoNumbersList.contains(lottoNumbers);
	}

	//TODO: 쓰는 것을 지양할뿐이지, 일급컬렉션도, 더 상위 일급컬렉션이 받아가서 내부에서 사용한다면
	// ex> Cars.from( Names ) -> 내부에서 Names -> getter로 -> List<Names>으로 받와야야.. -> name -> Car.of (name) 생성
	//getter 대용
	public List<LottoNumbers> toList() {
		return this.lottoNumbersList;

		//TODO: 기존 List<결과값>을 대신하는 것이라면, 해당 list자리에
		//===========================
		// this.stream()... .collect(Collectors.toLit()) 자리에
		// -> LottoNumbersList.from(   ) 를 씌워서, 일급컬렉션 객체로 만들어줘야하다.!!!
		//===========================

	}

	public Results match(LottoNumbers winNumbers, LottoNumber bonusNumber) {
		return Results.from(this.lottoNumbersList.stream()
			.map(inputNumbers -> {

				LottoPrize prize = LottoPrize.findByCount(inputNumbers.match(winNumbers));
				if (inputNumbers.isContains(bonusNumber)) {
					System.out.println("보너스 포함중.");
					prize = LottoPrize.BONUS;
				}
				return prize;
			})
			.collect(Collectors.toList()));
	}
}
