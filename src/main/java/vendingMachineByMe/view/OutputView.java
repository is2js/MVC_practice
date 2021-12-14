package vendingMachineByMe.view;

import vendingMachineByMe.domain.Amount;

public class OutputView {

	public static final String INPUT_MACHINE_AMOUNT_INSTRUCTION = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	public static final String ERROR_INSTRUCTION = "[ERROR] ";
	public static final String RESULT_OF_COIN_AMOUNT = "자판기가 보유한 동전";
	public static final String INPUT_SALE_INFO_INSTRUCTION = "상품명과 가격, 수량을 입력해 주세요.";
	public static final String INPUT_INSERT_AMOUNT_INSTRUCTION = "투입 금액을 입력해 주세요.";
	public static final String INPUT_SALE_INSTRUCTION = "구매할 상품명을 입력해 주세요.";

	public static void printInputMachineAmountInstruction() {
		System.out.println(INPUT_MACHINE_AMOUNT_INSTRUCTION);
	}

	public static void printError(String message) {
		System.out.println(ERROR_INSTRUCTION + message);
	}

	public static void printResultOfCoinAmount(String randomCoins) {
		System.out.println(RESULT_OF_COIN_AMOUNT);
		System.out.println(randomCoins);
	}

	public static void printInputSaleInfoInstruction() {
		System.out.println(INPUT_SALE_INFO_INSTRUCTION);
	}

	public static void printInputInsertAmountInstruction() {
		System.out.println(INPUT_INSERT_AMOUNT_INSTRUCTION);
	}

	public static void printResultOfInsertAmount(Amount insertedAmount) {
		System.out.println();
		System.out.printf("투입 금액: %d원", insertedAmount.toInt());
		System.out.println();
	}

	public static void printInputSaleInstruction() {
		System.out.println(INPUT_SALE_INSTRUCTION);
	}
}
