package baseball3streamAndEnum.domain;

import java.util.List;

public class Results {
	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<Result> results;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private생성자에 배정한다.
	// -> 외부list를 stream()map()에서 객체로 변환하여 list로 collect한다.
	public Results(List<Result> list) {
		this.results = list;
	}

	public static Results from(List<Result> list) {
		return new Results(list);
	}

	public List<Result> getResults() {
		return results;
	}

	public boolean hasStrike() {
		// 아직 결과값List 의 일급컬렉션이라면
		// -> has를 contains가 아닌 stream타고 또물어볼 수 있다.
		return this.results.stream()
			.anyMatch(result -> result.isStrike());

	}

	public int getStrikeCount() {
		return (int)this.results.stream()
			.filter(result -> result.isStrike())
			.count();
	}

	public boolean hasBall() {
		return this.results.stream()
			.anyMatch(result -> result.isBall());
	}

	public int getBallCount() {
		return (int)this.results.stream()
			.filter(result -> result.isBall())
			.count();
	}
}
