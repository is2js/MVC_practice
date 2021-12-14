package vendingMachineByMe.controller;

import java.util.Arrays;

import vendingMachineByMe.domain.Amount;
import vendingMachineByMe.domain.SaleInfos;
import vendingMachineByMe.domain.VendingMachine;
import vendingMachineByMe.view.InputView;
import vendingMachineByMe.view.OutputView;

public class Controller {
	public void runMachine() {
		//메인 로직
		VendingMachine vendingMachine = VendingMachine.getInstance();
		vendingMachine.init();

		OutputView.printInputMachineAmountInstruction();//자판기가 보유하고 있는 금액을 입력해 주세요.
		//TODO 1: 금액검증은 했지만, 래핑클래스에서, 동전으로 구성될 수 잇는 금액인지 확인해야할 듯.  -> 어떻게든 10원이상이면 가능할듯.
		// Amount machineAmount = Amount.of(String.valueOf(InputView.getAmount()));
		Amount machineAmount = Amount.of("500");
		vendingMachine.insert(machineAmount);
		// String randomCoins = vendingMachine.generateRandomCoins();
		String randomCoins = vendingMachine.generateRandomCoins();
		OutputView.printResultOfCoinAmount(randomCoins); //자판기가 보유한 동전

		OutputView.printInputSaleInfoInstruction(); //상품명과 가격, 수량을 입력해 주세요.
		// [가,5000,2];[나,200,5]

		// TODO 7: 쪼개고나서 추가검사 필요하면, list-> 단일객체없는상태의 래핑 일급컬렉션에서 한다?
		// -> 일급컬렉션 -> stream 내부 단일객체 만들면서 검사한다? -> 내부 단일객체에서 한다?
		// --> 도메인에서 하면.. 무한검사가 되려나
		// SalesInfo.from(saleInfo);
		// TODO 8: 개별 패턴검사까지만 했음....
		// -> 나머지 서로 다른 타입의 개별단어의 검증은.. 내부에서 해야할 듯?
		// SaleInfos sales = SaleInfos.from(InputView.getSaleInfos());
		SaleInfos sales = SaleInfos.from(Arrays.asList("[콜라,3000,3];[사이다,600,10];[노아이템,300,0]".split(";")));
		// sales.toList().stream().forEach(s -> System.out.println(s.toString()));
		vendingMachine.insertSales(sales);
		// //TODO 20: 코인뿐만 아니라, 상품정보가 들어오면, 상품카운터도 만들어놔야한다..
		// //
		// while (vendingMachine.isEnd())
		OutputView.printInputInsertAmountInstruction();//투입 금액을 입력해 주세요.
		Amount insertedAmount = Amount.of(String.valueOf(InputView.getAmount()));
		vendingMachine.insertUserAmount(insertedAmount);
		//
		// // TODO 15: 결과값변수 -> 넣은동니 계속 없데이트 되는 결과값  -> 조건변수로 쓸 수 잇음.
		// //TODO 18 : 종료조건을 위해.. 머신하테.. 최저가 물어봐야하네...
		// Sale minSale = vendingMachine.findMinSaleAmount();
		OutputView.printResultOfInsertAmount(insertedAmount);//투입 금액: 3000원

		// NEW) 조건변수 대신, [다 가진]싱글톤 + can메서드()의 boolean식을 변수 -> 업데이트해보기
		boolean canSale = vendingMachine.canSale();

		while ((
			canSale
			// // 사용자조건 )
			// insertedAmount.toInt() >= minSale.toAmount().toInt()
			// 	&&
			// 	//자판기조건)
			// 	vendingMachine.isSalesAvailable()
			// //19: 상품도 카운터로 관리해야한다...
		)
		) {
			// System.out.println("들어왔");
			OutputView.printInputSaleInstruction();//구매할 상품명을 입력해 주세요.
			String saleName = InputView.getSaleName();
			// 구매직전 특정상품검증 완료시
			vendingMachine.sale(saleName);

			// TODO 25: 모둔상품이 다 0인지 검사는 while문에서 할 수 있으나...
			// -> 구매하고자하는 상품이 >0 것은 입려이후 가능
			// TODO 30: .. 판매불가검사를.. map데이타에 판매상품 먼저 넣고 검사해야함...
			// -> new) InputVew내부에서 싱글톤으로 검증가능함. -> getSaleName()내부에서 처리한 뒤 주석처리함.
			// if (!(vendingMachine.checkWantedSaleAvailable(saleName))) {
			// 	System.out.println("뭐야.. 판매불가래..");
			// 	break;

			// new) 싱글톤관리자에게, 현재상태기반으로 계속 물어볼 수 있으니 ->
			//      판매로직은, 판매후 금액반환까지 일x ->  판매만 하면 된다
			// insertedAmount = vendingMachine.sale(insertedAmount, saleName);
			// vendingMachine.sale(saleName);
			//
			//TODO new ) 잔돈반환 로직 따로 작성해야함. 변수를 업데이트X -> 상태기반 boolean만 업데이트
			OutputView.printResultOfInsertAmount(vendingMachine.getInsertedAmount());//투입 금액: 3000원

			canSale = vendingMachine.canSale(); // 메인로직후 조건변수 업데이트(원래는 값return받아서 그값으로 업데이트하는 수고스러움)
		}

		//[콜라,3000,3];[사이다,600,10];[노아이템,300,0]

		// 빠져나온ㅅ ㅏㅇ황 -> 잔돈계산해야한다
		// String charge = vendingMachine.calculateCharge(insertedAmount);
		String charge = vendingMachine.calculateCharge();
		// System.out.println(charge);

		System.out.println("유저동전" + charge);
		System.out.println("자판기 남은 동전" + vendingMachine.getCoinMap());

	}
}


