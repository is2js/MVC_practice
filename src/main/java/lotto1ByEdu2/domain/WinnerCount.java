package lotto1ByEdu2.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WinnerCount {
	//TODO:사용법 new xxxxCounter(); -> key: string, value: count = 0 내부 자동 초기화됨.
	//  1) plusCount, plusCountFromList, minusCount
	//  2) CountOf(ENUM), printCount, toString오버라이딩, createReadOnlyCounter

	// StringList별, 0초기화 count를 기록 treemap을 wrapping
	private SortedMap<LottoWinRank, Integer> counterMap;

	public WinnerCount() {
		//TODO: 1 - 들어온 순서대로(LinkedHashMap) vs 정렬된체로 저장(TreeMap);
		counterMap = new TreeMap<>();

		// TODO2: 정렬기준이 oridnal( 이넘 선언한 순서가 아니라면, 커스텀 Comparator)
		// 1) Enum나열 역순:
		// counterMap = new TreeMap<>(Comparator.comparingInt(LottoWinRank::ordinal).reversed());
		// 2) 특정 Enum변수.getter() 순:
		// counterMap = new TreeMap<>(Comparator.comparingInt(LottoWinRank::getPrize));
		// 3) enumx 문자열이었따면 -> 길이순: counterMap = new TreeMap<>(   (s1, s2) -> s1.length() - s2.length()   );

		// ->  enum처럼 이미 정해진 게 없으므로.. put할때마다 초기화해줘야할듯?
		// Arrays.stream(LottoWinRank.values())
		// 	.forEach(enumObj -> counterMap.put(enumObj, 0));
	}

	//3. put은 plusCount(Enum객체 1개)를 받아서, 그놈의 counter를 +1
	// -> 이미 모드 ENum에 대해 value를 0으로 초기화 했으니 가능
	public void plusCount(LottoWinRank value) {
		counterMap.put(value, counterMap.getOrDefault(value, 0) + 1);
	}

	public void minusCount(LottoWinRank value) {
		counterMap.put(value, counterMap.getOrDefault(value, 0) - 1);
	}

	//4. list로 받아서 증가!!
	public void plusCountFromList(List<LottoWinRank> list) {
		list.stream()
			.forEach(value -> counterMap.put(value, counterMap.getOrDefault(value, 0) + 1));
	}

	//5. 갯수 반환 get대신 CountOf ( 해당Enum상수객체 )
	public Integer CountOf(LottoWinRank value) {
		return counterMap.get(value);
	}

	//6. 이제.. 순서대로 print with keySet()
	public void printCount() {
		counterMap.keySet()
			// .stream().filter()
			.forEach(key -> {
				System.out.println(key + " -> " + counterMap.get(key) + "개");
			});
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

	//7. 읽기전용map만 반환할때
	public Map<LottoWinRank, Integer> createReadOnlyCounter() {
		return Collections.unmodifiableMap(counterMap);
	}
}
