package vendingMachineByMe.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import vendingMachineByMe.utils.Util;

public class VendingMachine {
	private static final VendingMachine vendingMachine = new VendingMachine();
	private Amount machineAmount;
	private SaleInfos sales;
	private CoinCounter coinMap;
	private SaleCounter saleMap;
	private Amount insertedAmount;

	private VendingMachine() {
	}

	//3) 외부에서 이미생성된 싱글톤을 부르는 스태틱메서드
	public static VendingMachine getInstance() {
		return vendingMachine;
	}

	//4) 관리하는 insert된 변수들, 결과값 변수들 초기화
	// TODO singleton2) 메서드에 필요 -> 관리변수 -> 매 로직시마다 초기화도 가능하도록 한다.
	public void init() {
		machineAmount = null;
		insertedAmount = null;
		coinMap = new CoinCounter();
		saleMap = new SaleCounter(); // TODO 21: 카운터 셀려고 관리하는 맵들은 객체로 초기화 해서 처음부터 빈 map이 계속 생성되어잇게 한다.
	}

	public String generateRandomCoins() {
		// TODO : 일단 제일 큰동전이 몇개까지 가능한지 봐야한다. -> 테스트 함수보고 바뀜.
		// -> 0~그 리스트가 있으면, 넣어서 1개를 뽑는다. / 해당금액으로 동전을 만들진 않는다.

		// int machineAmount = this.amount.toInt();
		// 1) 동전을 돌면서, amount보다 작으면서 제일 큰 것 찾기
		// -> stream으로 돌면.. 랜덤으로 갯수뽑고 -> 그 다음 작은 동전으로 반영이 안되는 것 같다?
		// -> 남은 amount랑 업데이트를 해줘야함..
		// 2) 가장 큰 것부터.. 가능한 범윈내에서 랜덤으로 1개뽑고 다음에 업데이트해줘야함.
		// -> 분기별 결과값(coin amount)에 대한 갯수도 저장하도록 map 먼저 만든다.
		//2-1) 가장 큰것의 가능범위 찾기

		// NEW 1) enum에 매핑된 변수들을 list로 가져온다.
		// List<Coin> coinList = Arrays.stream(Coin.values()) // values는 배열을 만들어낸다.
		// 	.sorted(Comparator.comparingInt(Coin::toAmount).reversed())
		// 	.collect(Collectors.toList());
		// NEW 2) 랜덤으로 이중에서 1개를 뽑아야하는데, 조건은..  자판기금액보다는 작아야한다.
		// int pickedAmount = Util.getPickRandomNumberInList(amountList);

		// new 3) 뽑힌 동전금액을 전체금액 빼고, 카운터는 더한다.
		// -> 매번 동전뽑고, 그만큼 보유금액 줄여서  [ 보유금액 -> 동전 ]으로 환산한다.
		// --> 실제 보유금액(this.amount)이 아니라, 갯수를 세기위한 금액 machineAmount을 이용해서
		// --> 복사한금액을 제하면서 동전을 카운팅한다.
		int firstMachineAmount = machineAmount.toInt();
		while (machineAmount.toInt() >= 10) {
			// 0) 갯수셀때는 원본은 그대로두고, 복사본을 제하면서 해당단위를 뺄때마다 +1
			// int finalMachineAmount = machineAmount;
			List<Integer> amountList = Arrays.stream(Coin.values())
				.mapToInt(Coin::toAmount) // 1) 꺼내올 매핑변수 (enum -> getter)
				.filter(amount -> amount < firstMachineAmount
					&& amount <= machineAmount.toInt()) // 2) 조건부: 투입금액과 같은 금액은 안쓴다고 함..
				.boxed()
				.peek(k -> System.out.println(k))
				.collect(Collectors.toList());
			// if (amountList.contains(null)) {
			// 	break;
			// }
			int pickedAmount = Util.getPickRandomNumberInList(amountList);
			// 1. 복사전체금액에서 제한 금액만큼 동전이 +1된다. -> 카운팅해야한다.
			// -> 흠.. Coin이넘객체를 넣어야하는데 ㅋ 일단 찾아서 넣어준다.
			this.coinMap.plusCount(Coin.findByAmount(pickedAmount));
			this.machineAmount.decrease(pickedAmount); // 조건변수 업데이트
		}

		// for (Coin currentCoin : coinList) {
		// 	// default로 count 0이 맵에 들어가있음 ㅋ
		// 	// TODO...  나누어질 거면.. 0을 가지는 enum은 제거하자.. 일단..
		// 	if (currentCoin.toAmount() < machineAmount && machineAmount >= 10) {
		// 		int possibleCount = machineAmount / currentCoin.toAmount();
		// 		List<Integer> possibleCountList = IntStream.rangeClosed(1, possibleCount)
		// 			.boxed()
		// 			.collect(Collectors.toList());
		// 		// System.out.println("possibleCountList : " + possibleCountList);
		// 		int pickedCount = Util.getPickRandomNumberInList(possibleCountList);
		// 		// System.out.println("pickedCount : " + pickedCount);
		// 		// TODO 4: map에 기록하려면, enumList를 돌고 있어야한다. 그래야 key로서 카운팅하지..
		// 		// TODO 5: pickedCount번.. 카운팅해야하는데..ㅠㅠ coin1개만 새고잇엉슴..
		// 		IntStream.range(0, pickedCount)
		// 			.forEach(i -> coinMap.plusCount(currentCoin));
		//
		// 		// 기록후 업데이트해서 토탈바꿔놓으면,, 다음 큰놈이 카운팅됨.
		// 		// this.amount.update(currentCoin.toAmount() * pickedCount);
		// 		// machineAmount를 쓸거며 걔속스던가..
		// 		// TODO 32: 순수 동전 랜덤 생성에만 이용하므로
		// 		// -> 원본 thi.amount는 건들지말고, machinAmount만 깍아나가자?
		// 		// -> 동전 다만들나서 this.amount를 machinAmount로 업데이트?
		// 		machineAmount -= (currentCoin.toAmount() * pickedCount);
		// 	}
		// 	// TODO 33: 동전세아리는 용도의  돈으로 맞바꿈해줌..
		// 	// -> 메번 this.amount를 업데이트해도 됬었다???
		// 	this.amount.update(machineAmount);
		// }

		// 카운팅확인
		// System.out.println(counterMap.toString());

		// map을 출력용으로 커스텀한 뒤, String으로 반환해서 메인로직에서 출력하도록
		return this.coinMap.toString();

		// Arrays.stream(Coin.values()) // values는 배열을 만들어낸다.
		// 	.sorted(Comparator.comparingInt(Coin::toAmount).reversed()) // default 선언순서(큰 순서대로 이미 나열)대로 해도되지만 정렬해보자.
		// 	.peek(coinEnum -> System.out.println("역순 정렬잘되었나 : " + coinEnum.toAmount()))
		// 	.mapToInt(coinEnum -> {
		// 		int coinAmount = coinEnum.toAmount();
		// 		int maxCount = 0;
		// 		if (coinAmount < machineAmount) {
		// 			maxCount = machineAmount / coinAmount;
		// 		}
		// 		return maxCount;
		// 	})
		// 	.boxed()
		// 	.collect(Collectors.toList());

	}

	public void insert(Amount machineAmount) {
		this.machineAmount = machineAmount;
	}

	public void play(Amount insertedAmount, String saleName) {
		// this.
	}

	public void insertSales(SaleInfos sales) {
		this.sales = sales;
		// TODO 31: this로 관리하면서 바로 countMap에도 동시에 등록..
		this.saleMap.insert(sales);
		System.out.println(saleMap);
	}

	// public Amount sale(Amount insertedAmount, String saleName) {
	// 	public Amount sale(String saleName) {
	// 		// TODO 13: 검색은 반복문 다 돌면서 검사하는 것..
	// 		Sale wantedSale = this.sales.findByName(saleName);
	//
	// 		// if (this.insertedAmount.compareTo(wantedSale.toAmount()) >= 0) {
	// 		// TODO 22: 이제 업데이트로 돈 깍기전을 때, 갯수도 같이 깍는다(>0 으로 통과한 상태이므로..?)
	// 		// -> 앜.. 모든상품소진만 검사한 상태... 박에서 추가해줘야한다.
	// 		//-> 구매하고 하고자하는.. 상품도 >0인지.. -> 입력시 검증으로 바굼
	// 		this.insertedAmount.discharge(wantedSale.toAmount()); // insertedAmount 깍이도록 업데이트
	// 	}
	//
	// 	//
	//
	// 		return insertedAmount;
	// }

	public Sale findMinSaleAmount() {
		return this.sales.findMinAmount();
	}

	public boolean isSalesAvailable() {
		Map<Sale, Integer> readOnlySaleCounter = this.saleMap.createReadOnlyCounter();

		//TODO 24: 구매하고 하고자하는.. 상품도 >0인지.. 추가 찾기..

		return readOnlySaleCounter.keySet()
			.stream()
			// .peek( key -> System.out.println(key.toString() + readOnlySaleCounter.get(key) + ""))
			// .anyMatch(key -> readOnlySaleCounter.getor(key) > 0);
			// TODO 31: enum아닌 일반맵은 무조건 getorDefault로 꺼내자..!
			.anyMatch(key -> readOnlySaleCounter.getOrDefault(key, 0) > 0);
	}

	public boolean checkWantedSaleAvailable(String saleName) {
		Sale wantedSale = this.sales.findByName(saleName);
		System.out.println("찾은 물품 : " + wantedSale.toString());
		return this.saleMap.CountOf(wantedSale) > 0;
	}

	public String calculateCharge() {
		// new)
		//1)
		//오.. 자판기 보유코인Map과 동일한 자료형으로 사용자 보유코인맵객체를 임의로 하나 생성한다.
		// -> 계산결과난오는 값이라. 따로 관리하진 않는다.
		CoinCounter userCoinMap = new CoinCounter();
		// userCoinMap.forEach()

		boolean isChargable = this.insertedAmount.toInt() > 0;
		// SortedMap<Coin, Integer> machineCoinCounter = this.coinMap.toMap();

		// System.out.println("동전으로 교환 전 내 돈 : " + this.insertedAmount.toInt());
		while (isChargable) {

			// Arrays.stream(Coin.values())
			// 	.sorted(Comparator.comparingInt(Coin::ordinal))
			// 	. for
			//new) 맵 -> 맵 교환은 .forEach로 둘다 같이꺼낼 수 있다!?!
			//-> toString도 업데이트
			//2) 보유동전Map을 forEach로 돌면서 [[[coin양만큼 + count횟수만큼 1개씩 차감]]]시킨다.
			// -> 이 떄, 해당 Coin차례에서는, [[동전교환조건 : 해당코인보다 <= 남은돈]]이 크거나 같을때만 가능하다는 조건문이 필요하다.
			this.coinMap // 카운트를 꺼내고, (조건만족동안은), 꺼낸 카운트만큼 반복한다
				// 일단 반복을 돌리고, 내부에서는 (if조건만족시만 작동)하도록 하면 된다.
				// -> 조건만족할때까지만 업데이트로직을 반복한다 -> 반복하는 동안 조건불만족해도 안멈춘다.
				// --> 일단 업데이트로직을 반복 돌려놓고, 업데이트로직 전체에 if를 건다. -> 알아서 조건만족안하는 것은 돌긴돌되 통과해버릴 것이다.
				.forEach((currentCoin, count) -> {
						IntStream.rangeClosed(1, count) // 1부터 시작으로 인해, 갯수>0 검사는 안해도된다!!
							.forEach(i -> {
								// 매 교환마다 동전교환조건확인
								if ((currentCoin.toAmount() <= this.insertedAmount.toInt())) {
									// System.out.println("차감라인으로 들어왔어요");
									// 1) 사용자 - 돈 차감 (업데이트로직)
									this.insertedAmount.decrease(currentCoin.toAmount());
									// 2) 사용자 - 동전갯수 증가
									userCoinMap.plusCount(currentCoin);
									// 3) 자판기 - 갯수 차감
									this.coinMap.minusCount(currentCoin);

								}
							});

					}

				);

			isChargable = this.insertedAmount.toInt() > 0; // 조건변수 업데이트
		}

		// return this.coinMap.calcuateChargeCoin(insertedAmount.toInt());
		return userCoinMap.toString();
	}

	public boolean canSale() {
		//1) 사용자 조건
		// 나) 물건최소값 -> 보다 큰 금액인지
		// 님) 물건들을 돌면서 -> 내 금액보다 작거나 같은 금액이 있는지. 탐색
		// boolean userAvailable = this.insertedAmount.toInt() >= this.sales.findMinAmount().toAmountInt();
		// boolean userAvailable = this.sales.toList().stream()
		// 	.anyMatch(sale -> sale.toAmountInt() <= this.insertedAmount.toInt());
		boolean userAvailable = this.sales.findIsOrLessThan(this.insertedAmount);
		if (userAvailable)
			System.out.println("사용자조건 만족");
		//2) 자판기 조건
		boolean machineAvailable = this.saleMap.isAnyAvailable();
		if (machineAvailable)
			System.out.println("자판기조건 만족");
		return userAvailable && machineAvailable;

	}

	public void insertUserAmount(Amount insertedAmount) {
		this.insertedAmount = insertedAmount;
	}

	public boolean searchSameName(String inputValue) {
		boolean b = this.saleMap.createReadOnlyCounter()
			.keySet()
			.stream()
			.anyMatch(sale -> sale.isSameName(inputValue));
		return b;
	}

	public boolean isAnyAvailable(String inputValue) {
		return this.saleMap.isAnyAvailable();
	}

	public boolean isAvailable(String inputValue) {
		return this.saleMap.isAvailable(inputValue);
	}

	public boolean isExist(String inputValue) {
		return this.saleMap.isExist(inputValue);
	}

	public boolean isPurchasable(String inputValue) {
		Sale want = this.saleMap.findBy(inputValue);
		return this.insertedAmount.isSameOrGreaterThan(want.toAmount());
	}

	public void sale(String saleName) {
		//0) 각자 입장에서 처리하기전에 객체부터 찾기
		Sale selledSale = this.saleMap.findBy(saleName);
		//1) 자판기- 갯수 차감
		this.saleMap.minusCount(selledSale);
		//2) 사용자- 투입금액 차감
		this.insertedAmount.discharge(selledSale.toAmount());

	}

	public Amount getInsertedAmount() {
		return this.insertedAmount;
	}

	public SortedMap<Coin, Integer> getCoinMap() {
		return this.coinMap.toMap();
	}

	// public boolean isEnd() {
	// 종료
	// if () {
	// 	return false;
	// }
	// 계속
	// true;
	// }
}
