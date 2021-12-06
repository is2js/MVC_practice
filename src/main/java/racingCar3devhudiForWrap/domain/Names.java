package racingCar3devhudiForWrap.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Names {
	private List<Name> names;

	public Names(List<String> list) { //TODO: from와 동일한 파라미터로 변경해주기 
		this.names = list.stream()
			.map(element -> Name.of(element)) //단일객체 생성시 여러 파라미터가 들어갈 수 도, Ball.of(i, integerBalls.get(i) 
			// -> IntStream.range(0, list.size()).mapToObj(i ->     Ball.of( i ,   list.get(i)  ))
			.collect(Collectors.toList());
	}

	public static Names from(List<String> list) {
		return new Names(list);
	}

	public List<Name> getNames() {
		return this.names;
	}
}
