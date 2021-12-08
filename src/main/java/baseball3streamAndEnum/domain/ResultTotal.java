package baseball3streamAndEnum.domain;

import java.util.List;

public class ResultTotal {
	private List<Results> resultTotal;

	public List<Results> getResultTotal() {
		return resultTotal;
	}

	public ResultTotal(List<Results> list) { // from와 동일한 파라미터로 변경해주기
		// this.resultTotal = list.stream()
		// 	.map(element -> Results.from(element.getResults())) //단일객체 생성시 여러 파라미터가 들어갈 수 도, Ball.of(i, integerBalls.get(i)
		// 	.collect(Collectors.toList());
		this.resultTotal = list;// 뭐야.. 재료가 아니므로,, 굳이 찢어서 넣지 앟ㄴ아도 된다...?
		// [개념]10 - 객체list재료로 만드는 일급컬렉션이 아니라면
		// -> stream으로 찢어서 map으로 return 받은 list..가 아니고
		// -> 그냥 list만 핟당시킨다!!!

	}

	public static ResultTotal from(List<Results> list) {
		return new ResultTotal(list);
	}

	public int getStrikeCount() {
		return this.resultTotal.stream()
			//  [개념]14
			// 숫자세기전! fiter부터 해볼 것?! -> 1개라도 있으면 -> mapToInt로 숫자로 return받을 것이ㅣㄴ.. 미리 조사해야된다!!ㄴ
			// filter를 걸어주면, 없으면 알아서 mapToInt-> sum()이 0으로 출력된다!!
			// .mapToInt(resultList -> resultList.getStrikeCount())
			.filter(resultList -> resultList.hasStrike())
			.mapToInt(resultList -> resultList.getStrikeCount())
			.sum();

	}

	public int getBallCount() {
		return this.resultTotal.stream()
			.filter(resultList -> resultList.hasBall())
			.mapToInt(resultList -> resultList.getBallCount())
			.sum();
	}
}
