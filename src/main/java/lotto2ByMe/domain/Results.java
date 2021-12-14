package lotto2ByMe.domain;

import java.util.List;

public class Results {
	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	// -> 단일객체 없이 원시를 포장하는 상황이라면, List<원시>의 원시타입만 알고있으면 된다.
	// -> convert되어야한다면, 원시타입 변경해야함.
	private List<LottoPrize> results;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private생성자에 배정한다.
	//TODO: from와 동일한 파라미터로 변경해주기
	private Results(List<LottoPrize> list) {
		// TODO: case1) 그냥 List<원시>만 포장 만약,
		// 외부input으로 인한 재료가 아니라, 일반 List<결과값> 를 씌워주는 거면, stream필요없다
		// TODO: List<원시> convert시 여기서 <-------
		this.results = list;

		// TODO: case2) 재료 List가 온경우 -> stream을 이용해 변환
		// this.results = list.stream()
		// 	.map(element -> 원소을 원하는 타입으로 변환메서드(element))
		////.map(element -> Car.of(element)) // <- 단일객체 존재버전시
		// 	.collect(Collectors.toList())

		// TODO: case3) 재료 + size, length를 통한 index  이용해야할 때
		// this.results = IntStream.range(0, list.size())
		// 	.mapToObj(i -> Ball.of(i, list.get(i)))
		// 	.collect(Collectors.toList());
	}

	//1)  외부 파라미터 맞춰주기
	// -> 객체 래핑 : of( String or Int)  / 객체List 래핑 : from ( List<> xxx )
	// -> 특별값을 반환해야하는 경우만, 들어갈 경우 if분기 return new 클래스( 특정값 );
	public static Results from(List<LottoPrize> list) {
		//TODO: 일급컬렉션) 파라미터 검증(필요시)
		// checkResultsValidation(list, delimeter);
		return new Results(list);
	}

	public boolean isContains(LottoPrize element) {
		return this.results.contains(element);
	}

	//TODO: 쓰는 것을 지양할뿐이지, 일급컬렉션도, 더 상위 일급컬렉션이 받아가서 내부에서 사용한다면
	// ex> Cars.from( Names ) -> 내부에서 Names -> getter로 -> List<Names>으로 받와야야.. -> name -> Car.of (name) 생성
	//getter 대용
	public List<LottoPrize> toList() {
		return this.results;
	}

	public void record() {

	}

	public void reportToMap(WinnerCounter winnerCounter) {

	}

	public void toCounterMap(WinnerCounter winnerCounter) {
		winnerCounter.plusCountFromList(this.results);
	}

	//TODO: 기존 List<결과값>을 대신하는 것이라면, 해당 list자리에
	//===========================
	// this.stream()... .collect(Collectors.toLit()) 자리에
	// -> Results.from(   ) 를 씌워서, 일급컬렉션 객체로 만들어줘야하다.!!!
	//===========================

}
