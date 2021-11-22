package trade.view;

import java.util.Locale;

public class OutputView {

    public void printTitle() {
        System.out.println("중고나라에 오신 것을 환영합니다.");
    }
    public void printNameInstruction() {
        System.out.println("등록할 물품명을 입력해주세요.");
    }
    public void printMoneyInstruction() {
        System.out.println("등록할 금액을 입력해주세요.");
    }
    public void printRegisterInstruction() {
        System.out.println("해당 아이템을 해당 금액에 등록하시겠습니까? (Y, N)");
    }

    public void printRegisterResult(String answer) {
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("정상 등록 되었습니다.");
            return;
        }
        if (answer.equalsIgnoreCase("n")) {
            System.out.println("등록 취소 되었습니다.");
            return;
        }
        System.out.println("y나 n을 입력해주세요.");
    }
}
