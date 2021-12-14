package lotto1ByEdu2.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerNumbers {
	private List<Integer> winnerNumbers;

	public WinnerNumbers(List<String> list) {
		this.winnerNumbers = list.stream()
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());;
	}

	public static WinnerNumbers from(List<String> list) {
		return new WinnerNumbers(list);
	}
}
