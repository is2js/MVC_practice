package vendingMachineByMe.domain;

import java.util.List;
import java.util.stream.Collectors;

public class SaleInfos {
	private List<Sale> saleInfos;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private생성자에 배정한다.
	// -> 외부list를 stream()map()에서 객체로 변환하여 list로 collect한다.
	public SaleInfos(List<String> list) { //TODO: from와 동일한 파라미터로 변경해주기
		// this.saleInfos = list

		//TODO: convert (포맷 ->) 외부 list.stream().map(  element ->  *객체 List가 되도록 단일객체생성Car.of(  element  )*     ).collect(Collectors.toList())
		// -> map속에서 단일객체.of( )를 또 정리하러 가자.
		this.saleInfos = list.stream()
			.map(element -> {
				element = element.replaceAll("\\[|\\]", "");
				//TODO 10. replace는 안되고, substring으로 제거하는 건 되네..
				// -> replaceAll만 정규식을 받고, 대괄호는 앞에 \\ 2개 붙혀줘야된다.
				// element = element.substring(1, element.length() - 1);
				// System.out.println("[]삭제 안됫나?;" + element);
				String[] split = element.split(",");
				Sale sale = Sale.of(split[0], split[1], split[2]);
				return sale;
			}) //단일객체 생성시 여러 파라미터가 들어갈 수 도, Ball.of(i, integerBalls.get(i)
			// -> IntStream.range(0, list.size()).mapToObj(i ->     Ball.of( i ,   list.get(i)  ))
			.collect(Collectors.toList());

		// cf) map 속 단일객체생성시 index도 필요하면, IntStream + mapToObj  -> IntStream.range(0, list.size()).mapToObj(   i -> Ball.of(i, list.get(i))    )
		// List<Integer> integerBalls = Util.ConvertStrToIntList(stringBalls);
		// this.balls = IntStream.range(0, integerBalls.size())
		// 	.mapToObj(i -> Ball.of(i, integerBalls.get(i)))
		// 	.collect(Collectors.toList());
	}

	//1) 객체List 매핑한 일급컬렉션 -> 스태틱생성메서드에서 검증은 똑같은데, 외부 파라미터도 List로 들어와야한다.
	// -> 객체 매핑 : of( String or Int)  / 객체 List매핑 : from ( List<> xxx ) -> 여긴 아마 바깥 파라미터로 인하 자동 생성될듯싶다.
	// -> 특별값을 반환해야하는 경우만, 들어갈 경우 if분기 return new 클래스( 특정값 );
	public static SaleInfos from(List<String> list) {
		//TODO: 일급컬렉션) 파라미터 검증(필요시)
		// checkSaleInfosValidation(list);
		return new SaleInfos(list);
	}

	public boolean isContains(Sale saleInfo) {
		return this.saleInfos.contains(saleInfo);
	}

	//TODO: 쓰는 것을 지양할뿐이지, 일급컬렉션도, 더 상위 일급컬렉션이 받아가서 내부에서 사용한다면
	// ex> Cars.from( Names ) -> 내부에서 Names -> getter로 -> List<Names>으로 받와야야.. -> name -> Car.of (name) 생성
	//getter 대용
	public List<Sale> toList() {
		return this.saleInfos;
	}

	public Sale findByName(String saleName) {
		return this.saleInfos.stream()
			.filter(sale -> sale.isSameName(saleName))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public Sale findMinAmount() {
		return this.saleInfos.stream()
			.min(Sale::compareTo) // TODO 19: 최소단위객체도 특정변수를 기준을 정해주러 가좁자.
			.orElseThrow(IllegalArgumentException::new);

	}

	public boolean findIsOrLessThan(Amount insertedAmount) {
		return this.saleInfos.stream()
			.anyMatch(sale -> sale.isOrLessThan(insertedAmount));
	}

	// 집계===========
	// // 단일객체가 comparable -> compareTo 오버라이딩하여, 정렬/집계/비교의 기준이 정해졌을 때, 집계의 기준에 넣는다.
	// public int findMax() {
	// 	return this.cars.stream()
	// 		.max(SaleInfo::compareTo) // 집계함수의 결과도 reduce처럼 Optional이다. -> get보다는.. orElseThrow()로 가야할듯?
	// 		.get()
	// 		.getPosition();
	// }

	//TODO: 기존 List<결과값>을 대신하는 것이라면, 해당 list자리에
	//===========================
	// this.stream()... .collect(Collectors.toLit()) 자리에
	// -> SaleInfos.from(   ) 를 씌워서, 일급컬렉션 객체로 만들어줘야하다.!!!
	//===========================

}
