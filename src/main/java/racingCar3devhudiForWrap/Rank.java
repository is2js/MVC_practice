package racingCar3devhudiForWrap;

// 외부사용 : Group.findGroupByElement("그룹리스트에 속한 문자열 리스트 원소 중 1개");

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum Rank {
	EDUCATION("교육용 프로그램", Arrays.asList("우아한 테크코스", "삼성 소프트웨어", "서울42", "패스트 캠퍼스")),
	FOR_JOB("취업용 프로그램", Arrays.asList("국비지원 아카데미", "우아한 테크캠프", "프로그래머스"));

	private final String group; // 문자열 그룹명
	private final List<String> list; // 문자열리스트의 list명칭

	Rank(String group, List<String> list) {
		this.group = group;
		this.list = list;
	}

	public static String findGroupByElement(String element) { // <- 문자열리스트 중 1개 원소 변수명
		return Arrays.stream(Rank.values())
			.filter(enumVariable -> enumVariable.list.stream()
				.anyMatch(elementInStringList -> elementInStringList.equals(element)))
			.filter(hasElementInGroup(element))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("포함되는 문자열 리스트 그룹이 없습니다."))
			.getgroup();
	}

	public String getgroup() {
		return this.group;
	}

	private static Predicate<Rank> hasElementInGroup(String element) {
		return e -> e.list.stream().anyMatch(elementInStringList -> elementInStringList.equals(element));
	}

}
