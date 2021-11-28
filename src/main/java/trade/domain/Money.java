package trade.domain;

public class Money {


  // 5-1. 일단 최종적으로 필요한 변수를 private 숫자형 선언한다.
  // -> 생성자에서, 외부(nextLine()) 문자열을 받아서, 예외처리까지 한 parse까지 다 하도록
  // -> 다 처리한 뒤 초기화 되어서, 객체 생성만 해주면 외부접근불가한
  // -> [객체.순수숫자]가 나오게 한다.
  private int amount;

  // 5-2. 생성자에서 본격적으로 다 처리해본다. -> alt+insert로 생성
  // 일단 String을 파라미터 수정받도록 한다.
  public Money(String amount) {
    int amountParsed;
    // 5-3 parseInt( string )한다. -> 여기선 안나는데..
    // 1) 기본적으로 숫자문자열 받을 때 숫자가아닌 아닌 문자열 들어올때 대비-> NumberFormatException -> try 캐취 해준다.
    try{
      amountParsed = Integer.parseInt(amount);
    } catch (NumberFormatException e ) {
      throw new IllegalArgumentException("잘못된 형식을 입력하셨습니다.");
    }
    // 2) 이제 숫자의 범위(0, 음수) 필터링을 해준다.
    if (amountParsed <= 0) {
      throw new IllegalArgumentException("잘못된 금액을 입력하셨습니다.");
    }
    // 4) 문자열종료필터링, 범위종료필터링이 끝나면, private한 인스턴스변수에 넣어준다.
    // -> 꺼낼 땐.. moeney객체.amount로 꺼낼 듯.
    this.amount = amountParsed;
  }

  public int getAmount() {
    return amount;
  }

  // 5-3.  Item이든 Money든 toString을 오버라이드 해주는 버릇?
  @Override
  public String toString() {
    return Integer.toString(amount);
  }
}