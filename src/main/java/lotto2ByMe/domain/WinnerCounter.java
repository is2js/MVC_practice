package lotto2ByMe.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WinnerCounter {
	//TODO:사용법 new xxxxCounter(); -> key:Enum, value: count = 0 내부 자동 초기화됨.
	//  1) plusCount, plusCountFromList, minusCount, minusCountFromList
	//  2) CountOf(ENUM), printCount, toString오버라이딩, createReadOnlyCounter

	// Enum상수객체별, 0초기화 count를 기록 treemap을 wrapping
	// 1. < key: Enum속 상수객체,   value: 0 초기화 갯수>
	// -> 쉬운변수 getter가 있는줄도 모르니..
	private SortedMap<LottoPrize, Integer> counterMap;

	public WinnerCounter() {
		//TODO: 1 - 들어온 순서대로(LinkedHashMap) vs 정렬된체로 저장(TreeMap);
		counterMap = new TreeMap<>();
		// counterMap = new LinkedHashMap<>();

		// TODO2: 정렬기준이 oridnal( 이넘 선언한 순서가 아니라면, 커스텀 Comparator)
		// 1) Enum나열 역순:
		// counterMap = new TreeMap<>(Comparator.comparingInt(LottoWinRank::ordinal).reversed());
		// 2) 특정 Enum변수.getter() 순:
		// counterMap = new TreeMap<>(Comparator.comparingInt(LottoWinRank::getPrize));
		// 3) enumx 문자열이었따면 -> 길이순: counterMap = new TreeMap<>(   (s1, s2) -> s1.length() - s2.length()   );
		Arrays.stream(LottoPrize.values())
			.forEach(enumObj -> counterMap.put(enumObj, 0));
	}

	//3. put은 plusCount(Enum객체 1개)를 받아서, 그놈의 counter를 +1
	// -> 이미 모드 ENum에 대해 value를 0으로 초기화 했으니 가능
	public void plusCount(LottoPrize lottoPrize) {
		counterMap.put(lottoPrize, counterMap.get(lottoPrize) + 1);
	}

	public void minusCount(LottoPrize lottoPrize) {
		counterMap.put(lottoPrize, counterMap.get(lottoPrize) - 1);
	}

	//4. list로 받아서 증가!!
	public void plusCountFromList(List<LottoPrize> list) {
		list.stream()
			.forEach(enumObj -> counterMap.put(enumObj, counterMap.get(enumObj) + 1));
	}

	public void minusCountFromList(List<LottoPrize> list) {
		list.stream()
			.forEach(enumObj -> counterMap.put(enumObj, counterMap.get(enumObj) - 1));
	}

	//5. 갯수 반환 get대신 CountOf ( 해당Enum상수객체 )
	public Integer CountOf(LottoPrize lottoPrize) {
		return counterMap.get(lottoPrize);
	}

	//6. 이제.. 순서대로 print with keySet()
	public void printCount() {
		// StringBuilder countString = new StringBuilder();
		counterMap.keySet()
			// .stream().filter()
			.forEach(enumObjAsKey -> {
				System.out.println(enumObjAsKey + " -> " + counterMap.get(enumObjAsKey) + "개");
			});
	}

	//6. 프린트용
	@Override
	public String toString() {
		return counterMap.keySet()
			.stream()
			// .filter( key -> key.ordinal()>=3)
			.map(enumObjAsKey -> enumObjAsKey + " -> " + counterMap.get(enumObjAsKey) + "개")
			.collect(Collectors.joining(System.lineSeparator())); // 줄바꿈 단위로 붙임.
	}

	//7. 읽기전용map만 반환할때
	public Map<LottoPrize, Integer> createReadOnlyCounter() {
		return Collections.unmodifiableMap(counterMap);
	}

	// public void insertResultFrom(Results results) {
	// 	// 기록을 위해, count -> enum객체로 변환해야, map에 Enum으로 기록된다.
	// 	results.toList().stream()
	// 		// .peek(count -> System.out.println("Map에 없는 값 찾아보기 : " + count.toInt()))
	// 		.map(count -> LottoPrize.findByCount(count.toInt())) //enum
	// 		.forEach(countEnum -> {
	// 			// if (countEnum == LottoPrize.FIVE && lottoNumbers.isContains(bonusNumber))
	// 				this.plusCount(countEnum);
	// 		});
	// }

	public String convertToString() {
		return this.counterMap.keySet()
			.stream()
			.filter(enumValue -> enumValue.ordinal() >= 3) // enum의 선언순서로 필터링한다.
			.map(enumValue -> {
				Integer counter = this.counterMap.get(enumValue);
				int prize = enumValue.toPrize();
				int count = enumValue.getCount();
				if (enumValue.isBonus()) {
					// System.out.println("보너스 발견됨..");
					return String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개", count, prize, counter);
				}
				return String.format("%d개 일치 (%d원)- %d개", count, prize, counter);
			})
			.collect(Collectors.joining(System.lineSeparator()));
	}

	public double calculate() {
		return this.counterMap.keySet()
			.stream()
			.filter(enumValue -> enumValue.ordinal() >= 3)
			.mapToDouble(enumValue -> {
				Integer counter = this.counterMap.get(enumValue);
				int prize = enumValue.toPrize();
				return counter * prize;
			})
			// .peek( k-> System.out.println("등수별 상금들 : " + k))
			.sum();
	}
}
