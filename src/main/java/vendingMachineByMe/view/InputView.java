package vendingMachineByMe.view;

import static vendingMachineByMe.utils.Util.*;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import vendingMachineByMe.utils.Util;

public class InputView {

	public static final String DELIMETER = ";";

	private static String getInput() {
		return Console.readLine();
	}

	public static int getAmount() {
		try {
			String inputValue = InputView.getInput();
			Util.checkAmountValidation(inputValue);
			return Integer.parseInt(inputValue);
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getAmount();
		}
	}

	//상품명, 가격, 수량은 쉼표로, 개별 상품은 대괄호([])로 묶어 세미콜론(;)으로 구분한다.
	public static List<String> getSaleInfos() {
		try {
			String inputValue = removeSpace(InputView.getInput());

			Util.checkSaleInfoValidation(inputValue, DELIMETER);
			return Arrays.asList(inputValue.split(DELIMETER));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.getSaleInfos();
		}
	}

	public static String getSaleName() {
		try {
			String inputValue = removeSpace(InputView.getInput());
			checkSaleNameValidation(inputValue);
			return inputValue.trim();
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getSaleName();
		}
	}

}

// 자판기 로직
// 자판기 기본 준비
// 1) 보유금액 -> 보유동전map
// 2) 보유상품(입력) -> 보유상품Map
//
// 구매전준비 자판기조건
// 1) 남겨줄 보유금액이 충분히 있나? (문제 예외사항)
// 2) 상품 anyMatch 최소 1개 존재하나? in counterMap
// 구매전준비 사용자조건
// 1) 투입금액 충분한가( 젤싼거보다 많은 금액인가)
// anyMatch 나보다  같거나 작은 금액의 물건이 있는지  물건탐색
//
// 구매 조건->  (상품이름 입력시)검증에서 다 처리
// 1) input검증시 <존재유무>구매상품이 존재하는지 in counterMap
// 2) input검증시 <갯수유무>구매상품이 존재하지만, 1개이상있는지 in counterMap
//3) 남은돈(업데이트된 투입금액) 해당상품보다 큰지..
