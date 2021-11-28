package trade;
// 사용자가 상품명과 금액을 입력할 수 있어야 한다. - InputView#inputItem() -> InputView#getInput()
// 상품이 정상적으로 등록 될 때 결과를 확인할 수 있어야 한다. - OutputView#printRegisterResult()
// 상품과 금액을 Item으로 등록할 수 있어야 된다. - Register#registerItem()

import trade.domain.Item;
import trade.domain.Money;
import trade.view.InputView;
import trade.view.OutputView;

public class Application {
  public static void main(String[] args) {
    // 1.main에 일단 몰아서 한다고 치면, class도 여기 내부에서 만들어줘도 된다.
    // -> 일단은 나누서 작성해보려고함.

    // 2. 컨트롤러로 옮길 예정인데, 테스트도 여기서 해보자.

    // 3. 안내문들은 OutputView의 메서드로 다 빼논다.
//     -> 객체를 굳이 이용할필요도, 상태(private)도 없으니, static메소드로 정해서 사용하자.
//            OutputView outputView = new OutputView();
    OutputView.printTitle();
    // 4. InputView에선 static scanner객체를 건네주는 유틸메소드를 이용해서 String만 넘겨받는다.
    OutputView.printNameInstruction();
    String name = InputView.getInput();

    OutputView.printMoneyInstruction();
    // 5. 기본적으로 nextLine()의 문자열만 입력받는데, 숫자를 받아서 parseInt해야한다면?
//    int itemMoney = Integer.parseInt(InputView.getInput());
    // -> **생성자에서 문자열로 입력받는 Wrapper클래스를 만든 뒤, 생성자에서 예외처리르 다 해준다.**
    Money money = new Money(InputView.getInput());
    // 6. 문자열숫자 입력처리 끝난 자료형은, 전체자료형중에 1개이다.
    // -> 래퍼클래스 객체를 생성하고, 그놈도 전체Item중 1개로서 대입하여 데이터를 만들자.
    // -> **전체 자료형 만들 때, 문자열숫자 래퍼클래스가 들어올 것인지 안들어오는지 생각하고 만들어야겠네**
    Item item = new Item(name, money);

    // 7. 등록된 Item(사용자마자 달라지는 부분)을 받아서, print하는 것도
    // -> [domain, model] Item 클래스가 아닌, [View] OutputView의 static메소드로 정의하자.
    OutputView.printItem(item);
    OutputView.printRegisterInstruction();
    String answerRegister = InputView.getInput();
    OutputView.printRegisterItemInstruction(answerRegister);
  }
}
