package lotto1ByEdu2.domain;

import java.util.List;

public class LottoNumbers {
	private List<Integer> lottoNumbers;
	//TODO 각 일급컬렉션별 관리변수 -> 객체 인스턴스로 생성한다.
	// -> 객체v객체 결과값으로 return받아서 여기에 넣어줘야할듯.
	private int winCount;

	public LottoNumbers(List<Integer> list) { //TODO: from와 동일한 파라미터로 변경해주기
		this.lottoNumbers = list;

		//추가된 변수로서...winCount는 외부에서 안받고 결과로서.. 알게 된다?
		// -> 외부에서 안받는 것은 자체 초기화.
		this.winCount = 0;
	}

	public static LottoNumbers from(List<Integer> list) {
		return new LottoNumbers(list);
	}

	public List<Integer> toList() {
		return this.lottoNumbers;
	}

	// 일급vs일급 -stream->  객체vs일급
	public int compare(LottoNumbers winnerNumbers) {
		// TODO:
		// 싱글톤 변수로 결과값을 관리한다면,
		// -> 일급v일급이 끝나고나서도 초기화해줘야한다....
		// -> 여러개의 일급 vs 일급이 비교할 때까지 다 차기 때문..
		// --> 그것보다.. 여기서는 일급컬렉션(로또 1개) 단위로 관리변수가 필요하다.
		// -> ~~ 단위로 관리변수 -> 인스턴스변수!!!!!!!!
		// -> 아니면, stream()을 역으로 계속 sum해주면서 return int

		// return this.lottoNumbers.stream()
		// 	.mapToInt(winnerNumbers::compare)
		// 	.sum();
		// this.winCount = this.lottoNumbers.stream()
		// 	.mapToInt(winnerNumbers::compare)
		// 	.sum();

		// TODO: 최종: 분기별 결과값이라면, 객vs객의 결과값을 enum상수객체로 반환(값 안씀) -> enum리스트로 모은다.
		// return this.lottoNumbers.stream()
		// 	.mapToInt(winnerNumbers::compare)
		// 	.sum();
		return this.lottoNumbers.stream()
			.mapToInt(number -> winnerNumbers.compare(number))
			// .peek(sum -> System.out.println("여긴 아직 마찬가지 sum = [일급(로또1개) vs 숫자1개] : " + sum))
			// 객체vs객체를 겪고 올라온, 0~6 count
			//TODO ㅠㅠ: 일급vs일급의 결과까지 계속 sum해야한다.
			//TODO : 일급vs객체의 결과값(객vs객) List가 나왔을때, 합산할 수 있음 미리 해서- > 결과값List의 List를 미리 막자.
			// .mapToObj(count -> LottoWinRank.findBycount(count))
			// .collect(Collectors.toList());
			.sum();

	}

	// 일급vs객체 -stream-> 객체vs객체
	private int compare(Integer number2) {
		int sum = this.lottoNumbers.stream()
			.mapToInt(number1 -> getWinCount(number2, number1))
			// .peek(x -> System.out.println("[객체vs객체 결과]여기선 1 or 0을 받는다. : " + x))
			.sum();
		// System.out.println("[객체vs객체]의 sum = [일급(로또1개) vs 숫자1개] -> sum" + sum);
		return sum;
		// .boxed()
		// .collect(Collectors.toList());
		//TODO: 객체vs객체의 결과값을 분기별 결과값이면 enum으로 반환 -> enum리스트로 모은다.
		// -> 객체vs객체 -> count -> sum해야 enum조회됨... 역 다음 단계에서 enum + finder로

	}

	private int getWinCount(Integer number2, Integer number1) {
		if (number1 == number2) {
			return 1;
		}
		return 0;
	}

	public boolean isContains(Integer bonus) {
		return this.lottoNumbers.contains(bonus);
	}
}
