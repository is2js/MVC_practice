package vendingMachineByMe.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class CoinCounter {
	//TODO:사용법 new xxxxCounter(); -> key:Enum, value: count = 0 내부 자동 초기화됨.
	//  1) CountOf(ENUM), plusCount, plusCountFromList, minusCount, minusCountFromList
	//  2) @toString(기본 key -> value 형식), convertToString, calculate , createReadOnlyCounter

	private SortedMap<Coin, Integer> counterMap;

	public CoinCounter() {
		counterMap = new TreeMap<>(Comparator.comparingInt(Coin::ordinal));
		Arrays.stream(Coin.values())
			.forEach(enumObj -> counterMap.put(enumObj, 0));
	}

	//3. put은 plusCount(Enum객체 1개)를 받아서, 그놈의 counter를 +1
	// -> 이미 모드 ENum에 대해 value를 0으로 초기화 했으니 가능
	public void plusCount(Coin coin) {
		counterMap.put(coin, counterMap.get(coin) + 1);
	}

	public void minusCount(Coin coin) {
		counterMap.put(coin, counterMap.get(coin) - 1);
	}

	//4. list로 받아서 증가!!
	public void plusCountFromList(List<Coin> list) {
		list.stream()
			.forEach(enumObj -> counterMap.put(enumObj, counterMap.get(enumObj) + 1));
	}

	public void minusCountFromList(List<Coin> list) {
		list.stream()
			.forEach(enumObj -> counterMap.put(enumObj, counterMap.get(enumObj) - 1));
	}

	//5. 갯수 반환 get대신 CountOf ( 해당Enum상수객체 )
	public Integer CountOf(Coin coin) {
		return counterMap.get(coin);
	}

	//6. print시, 모든 key + value가 나오도록 with keySet()
	@Override
	public String toString() {
		return counterMap.keySet()
			.stream()
			// .filter( key -> key.ordinal()>=3)
			.map(enumObjAsKey -> enumObjAsKey.toAmount() + "원 - " + counterMap.get(enumObjAsKey) + "개")
			.collect(Collectors.joining(System.lineSeparator())); // 줄바꿈 단위로 붙임.
	}

	//7. 읽기전용map만 반환할때
	public Map<Coin, Integer> createReadOnlyCounter() {
		return Collections.unmodifiableMap(counterMap);
	}

	public String calcuateChargeCoin(int amount) {
		String result = "";
		int coinCount = 0;
		for (Coin coin : counterMap.keySet()) {
			if (amount > coin.toAmount() &&
				// TODO 26 :coin도 반환전에 갯수를 체크해야한다...
				counterMap.get(coin) > 0
			) {
				int coinAmount = coin.toAmount();
				// int coinCount = amount / coinAmount;
				// 코인갯수를 구하더라도.. 그만큼 남아있어야 갯수를 다 쓴다..
				while (amount > 0 && counterMap.get(coin) > 0) {
					amount -= coinAmount;
					minusCount(coin); // TODO 33 내부에서 minus전에 검사해야할듯?
					coinCount++;
				}

				// amount = amount - (coinCount * coinAmount);

				//TODO 28.. 여기에서 쓰인 코인갯수를 원하고 있어서
				// 동전바뀔때마다 stirng으로 넘기자.
				result += coin.toAmount() + "원 - " + coinCount + "개\n";
			}

			// 다음동전으로 넘어갈때마다, 사용갯수0으로 초기화..
			coinCount = 0;
		}

		return result;

	}

	public SortedMap<Coin, Integer> toMap() {
		return this.counterMap;
	}

	public void forEach(BiConsumer<Coin, Integer> action) {
		this.counterMap.forEach(action);
	}
}
