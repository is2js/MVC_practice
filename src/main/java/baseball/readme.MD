# 미션 - 숫자 야구 게임

## 기능 구현 목록

- [x] 3스트라이크가 나올때까지 반복해서 입력받기 - Controller#runGame()
    - [x] 3스트라이크 나올시 안내멘트 및 새로 시작여부 입력받기
        - [x] 새로 시작시 서로 다른 임의의 수 3개 새로 생성하기
- [x] 1에서 9까지 서로 다른 임의의 수 3개(컴퓨터 수)를 생성하기 - Computer#createRandomNumbers()
- [x] 1에서 9까지 서로 다른 임의의 수 3개(플레이어 수)를 입력받기 - Refree#askNumbers()
    - [x] 플레이어 수를 검증하고 변환하는 기능 - Refree#validAndConvertNumbers()
        - [x] 물어본 숫자 검증해서 유효하지 않으면 에러 내기, 그렇지 않으면 숫자로 변환하기
            - 검증내역: 3글자 입력인지 / 숫자 입력인지 / 서로 다른수 맞는지
- [x] 컴퓨터수 와 플레이어 수 비교하기 - Refree#compare()
    - [x] 같은 수 있는지 파악하기 - Refree#countSameNumber()
        - [x] 같은 수가 전혀 없으면(countSameNumber() = 0) 낫싱 출력
    - [x] 같은 수가 있을 때(countSameNumber() >= 1) 몇 개가 같은 위치인지 파악하기 - Refree#countStrike()
        - [x] 같은 자리에 있으면 스트라이크로 갯수로 파악
        - [x] 다른 자리에 있으면 볼 갯수로 파악
        - [x] n볼 m스트라이크 출력하기

## 그외 요구사항 체크리스트

- [ ] 자바의 버전이 8버전인가
- [ ] 자바 코드 컨벤션을 지키는가
- [ ] 3항 연산자를 쓰지 않는가
- [ ] **indent depth가 2이하인가**
- [ ] **하나의 함수가 한 가지 일만 하도록 하였는가**
- [ ] **(추가) 하드코딩 하진 않았는가**
- [ ] `Random`의 `pickNumberInRange()`를 사용했는가
- [ ] `Console`의 `readLine()`을 사용했는가
- [ ] `gradlew.bat clean test`의 모든 테스트가 통과하는가

