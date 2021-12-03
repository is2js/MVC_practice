## 기능 구현 목록

- [x] 자동차 이름 및 시행 횟수 입력 받기
    - [x] 자동차 이름 검증하여 입력받기 InputView#getNames()
    - [x] 시행 횟수 검증하여 입력 받기 InputView#getCounts()
    - [x] 자동차 이름 5자 이하 검증하기 InputView#checkValidation()
- [ ] 자동차 전진 또는 멈춤 판단하기 Car#isMovable
    - [ ] 무작위 값을 구한 후 무작위 값이 4 이상인지 확인하기
- [ ] 자동차 경주 게임을 완료한 후 누가 우승했는지 확인하기 Cars#findWinners()
    - [ ] 단독 우숭자 vs 여러명 우숭자 판단하기 Cars#isWinnerMoreThanTwo()
        - 우승자가 여러 명일 경우 쉼표(,)를 이용하여 구분
- [ ] 자동차 결과 출력하기 OutputView#printResult()
- [ ] 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생 시킨 뒤 "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받기
  OutputView#printException
    - [ ] 에러발생 상황시 게임 재시작하기 RacingGame#runGame()

## 그외 요구사항 체크리스트(과제수행간 계속 추가예정)

### 1주차

- [ ] 자바의 버전이 8버전인가
- [ ] 자바 코드 컨벤션을 지키는가
- [ ] 3항 연산자를 쓰지 않는가
- [ ] indent depth가 2이하인가
- [ ] 하나의 함수가 한 가지 일만 하도록 하였는가
- [ ] **(추가) 하드코딩 하진 않았는가**
- [ ] `Randoms`의 `pickNumberInRange()`를 사용했는가
- [ ] `Console`의 `readLine()`을 사용했는가
- [ ] `gradlew.bat clean test`의 모든 테스트가 통과하는가

### 2주차(추가)

- [ ] **함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다.**
- [ ] **else 예약어를 쓰지 않는다**

#### 프로그래밍 요구사항(추가)

- [ ] Car 기본 생성자를 추가할 수 없다.

- [ ] name, position 변수의 접근 제어자인 private을 변경할 수 없다.

- [ ] **가능하면 setPosition(int position) 메소드를 추가하지 않고 구현한다.**

  ```java
  public class Car {
      private final String name;
      private int position = 0;
  
      public Car(String name) {
          this.name = name;
      }
  
      // 추가 기능 구현
  }
  ```

## 그외 추가 학습사항 및 과제수행시 키포인트

- **[java 기본 문법 정리하기](https://github.com/is2js/exampleStudy01)**
    - [x]  1코테~1주차 과제기간 자바의 정석 등 기본문법
    - [x] **2주차 과제기간 Enum(day10), Interface(day11) 추가
      학습** : [설명](https://github.com/is2js/exampleStudy01/blob/master/readme.md)
      ,  [코드](https://github.com/is2js/exampleStudy01/tree/master/src/main/java)
- **[백준 python 기본 구현 문제](https://github.com/is2js/python_algorithm) -> java 로 풀어보기(~ing)**
    - [ ] **1~2주차 [문제풀이](https://github.com/is2js/boj_java)
      및 [개념정리](https://github.com/is2js/boj_java/blob/master/concept.md) 계속 진행중**
- **[java로 콘솔 게시판 만들어보기](https://github.com/is2js/exampleStudy01/blob/master/board.md) (~ing)**
    - [x] 1주차 콘솔 게시판까지 완성
    - [ ] **2주차 콘솔 게시판 정렬기능까지 완성**
- **지난 과제**를 지원동기들 코드 참고하여 **다시 작성해보기**
    - [x] [1주차과제 복습](https://github.com/is2js/MVC_practice/tree/master/src/main/java/baseball2Youngyooon) : 숫자야구게임 **MVC
        + 일급콜렉션 적용** 위주로 코드 새로 짜기
    - [ ] 2주차과제 복습 :
- **1주차 과제 수행시 키포인트**
    - 개발 시작전에 요구사항 충분히 파악하기
    - 기본 프로젝트 구조 및 뼈대 코드 작성하고 시작하기
    - 라이브러리 확인 및 컨벤션 포맷터 적용, 버전 확인 등 기본적인 협업환경 확인하기
    - Application에 개발하지 않고 각 class에서 개발하여 구동하기
    - scanner 등 input, outview은 static으로 작성해서 재활용하기
    - 문자열type의 숫자입력시 검증 여러가지 해보기(길이-> 포맷-> 범위-> 중복여부 등 기타)
    - else 안쓰고, 반복문안에 if는 method로 빼서 method내에서 하여 indent줄이기
    - 무한반복시 조건변수를 결과로 업데이트 하면 됬으나, 재시작 등 추가분기가 필요한 경우, status변수 추가 도입하기
    - 상수도 반복되니 따로 class로 빼기
    - 중복확인은 list길이로 하는 데, stream으로 편하게 하기
        - list 중복확인 : list.stream().distinct().count()
        - 입력받은 문자열배열 -> 정수 list변환 :  Arrays.stream( ).mapToint(Integer::parseInt).boxed().collect(Collectors.toList());







