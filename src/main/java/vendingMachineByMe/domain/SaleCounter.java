package vendingMachineByMe.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SaleCounter {
	//TODO:사용법 new xxxxCounter(); -> key: string, value: count = 0 내부 자동 초기화됨.
	//  1) CountOf(ENUM), plusCount, plusCountFromList, minusCount, minusCountFromList
	//  2) @toString(기본 key -> value 형식), convertToString, calculate , createReadOnlyCounter
	// -> 그외에 결과값List를 받는다거나.. or 메서드 정의 하지말고, [[[싱글톤내에서 inesrted된 변수활용]]]하도록,  plusCount( 개별 결과값 )정도만 쓴다.

	// StringList별, 0초기화 count를 기록 treemap을 wrapping
	private SortedMap<Sale, Integer> counterMap;

	public SaleCounter() {
		// 판매가격 역순으로 정렬해보자..
		counterMap = new TreeMap<>(Comparator.comparingInt(Sale::toAmountInt).reversed());
	}

	public void plusCount(Sale value) {
		counterMap.put(value, counterMap.getOrDefault(value, 0) + 1);
	}

	public void minusCount(Sale value) {
		counterMap.put(value, counterMap.getOrDefault(value, 0) - 1);
	}

	public void plusCountFromList(List<Sale> list) {
		list.stream()
			.forEach(obj -> IntStream.range(0, obj.toCount())
				.forEach(i -> counterMap.put(obj, counterMap.getOrDefault(obj, 0) + 1)));
	}

	public void minusCountFromList(List<Sale> list) {
		list.stream()
			.forEach(value -> counterMap.put(value, counterMap.getOrDefault(value, 0) - 1));
	}

	//5. 갯수 반환 get대신 CountOf ( 해당Enum상수객체 )
	public Integer CountOf(Sale value) {
		return counterMap.getOrDefault(value, 0);
	}

	//6. 프린트용
	@Override
	public String toString() {
		return counterMap.keySet()
			.stream()
			// .filter()
			.map(key -> key + " -> " + counterMap.get(key) + "개")
			.collect(Collectors.joining(System.lineSeparator())); // 줄바꿈 단위로 붙임.
	}

	// //8. key + value를 사용한 계산식
	// //TODO: 추후 나누기 들어갈시 double로 반환
	// public double calculate() {
	// 	return this.counterMap.keySet()
	// 		.stream()
	// 		.mapToDouble(key -> {
	// 			// Integer counter = this.counterMap.get(key);
	// 			// int prize = enumValue.toPrize();
	// 			// return counter * prize;
	// 		})
	// 		// .peek( key-> System.out.println("등수별 상금들 : " + this.counterMap.get(key)))
	// 		.sum();
	// }

	//7. 읽기전용map만 반환할때
	public Map<Sale, Integer> createReadOnlyCounter() {
		return Collections.unmodifiableMap(counterMap);
	}

	public void insert(SaleInfos sales) {
		this.plusCountFromList(sales.toList());
	}

	public boolean isAnyAvailable() {
		return this.counterMap.keySet()
			.stream()
			.anyMatch(key -> counterMap.getOrDefault(key, 0) > 0);
	}

	// isExist() 검증 이후에 사용하기 -> 없으면 thr함.(null안하는 이유는 거의 검증용에서 사용)
	// -> 객체가 아니라 원시값으로 넣는다면,, contains(key)로 찾는 대안도.
	public Sale findBy(String inputValue) {
		return this.counterMap.keySet()
			.stream()
			.filter(sale -> sale.toString().equals(inputValue))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다. 다시 입력해주세요."));
	}

	// 내부에 findBy활용함. -> count를 확인하려면, key에 들어간 객체부터 찾아놔야함.
	public boolean isAvailable(String inputValue) {
		Sale searchedSale = findBy(inputValue);
		return this.counterMap.getOrDefault(searchedSale, 0) > 0;
	}

	public boolean isExist(String inputValue) {
		return this.counterMap.keySet()
			.stream()
			.anyMatch(key -> key.isSame(inputValue)); // 조건에 맞게 함수 작성
	}
}
