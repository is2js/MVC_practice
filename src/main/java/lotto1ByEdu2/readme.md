## 1차

- [x] 금액 입력시 구입 가능한 `로또의 수`를 알 수 있다.
    - [x] 입력된 금액에서 구매가능한 `로또의 수를` `출력`한다. #Output
    - [x] 로또 머신은 구입 가능한 로또의 수 만큼 로또를 발급한다.
    - [x] 로또 생성시 로또 번호 리스트의 길이는 항상 6이다.
    - [x] 로또를 생성할때 입력되는 정수는 1~45의 서로다른 번호이다.
    - [x] 로또에서 로또 번호 리스트를 가져올 수 있다.
    - [x] 구매된 로또들의 `로또번호를 각각 출력`한다. #Output
    - [x] 입력된 `당첨번호`는 6개의 당첨번호와 1개의 보너스 볼이다.
    - [x] 지난 주 `당첨 번호`와 보너스 볼을 동시에 `입력`받는다. #Input
    - [x] 당첨번호 입력시 발급 된 `로또와 같은 수`가 있는지 `비교` 할 수있다. #Lotto
    - [x] 당첨번호와 로또번호 `비교`시 총 몇개의 같은 숫자가 일치하는지 알 수있다.
    -[x] 당첨번호화 로또번호의 일치된 숫자를 가지고 당첨금을 알 수있다.
    - [x] 당첨 통계를 `출력`한다. #Output
    - [x] 일치된 숫자에 따라 수익률을 `알 수있다`. #LottoLogic
    - [x] `수익률`은 당첨금액 / 구매금액 이다. #LottoLogic
    - [x] `수익률`에 따른 손익 메세지를 `확인할 수`있다. #LottoLogic

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다. 로또 1장의 가격은 1000원이다.

```
구입금액을 입력해 주세요. 
14000 
14개를 구매했습니다.

[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6

당첨 통계
---------
3개 일치 (5000원)- 1개 
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
6개 일치 (2000000000원)- 0개
총 수익률은 35.7%입니다.
```

## 2차

```
구입금액을 입력해 주세요. 14000

수동으로 구매할 로또 수를 입력해 주세요. 3

수동으로 구매할 번호를 입력해 주세요. 8, 21, 23, 41, 42, 43 3, 5, 11, 16, 32, 38 7, 11, 16, 35, 36, 44

수동으로 3장, 자동으로 11개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요. 1, 2, 3, 4, 5, 6 보너스 볼을 입력해 주세요. 7

당첨 통계
---------
3개 일치 (5000원)- 1개 4개 일치 (50000원)- 0개 5개 일치 (1500000원)- 0개 5개 일치, 보너스 볼 일치(30000000원) - 0개 6개 일치 (2000000000원)- 0개 총 수익률은
35.7%입니다.
```
### 4단계 - 로또(자동)

### 기능 요구사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

### 3단계 추가 요구사항

- 2등을 위해 추가 번호를 하나 더 추첨한다.
- 당첨 통계에 2등도 추가해야 한다.

### 4단계 추가 요구사항

- 현재 로또 생성기는 자동 생성 기능만 제공한다. 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.

```
구입금액을 입력해 주세요.`
14000`

수동으로 구매할 로또 수를 입력해 주세요.
3

수동으로 구매할 번호를 입력해 주세요.
8, 21, 23, 41, 42, 43
3, 5, 11, 16, 32, 38
7, 11, 16, 35, 36, 44

수동으로 3장, 자동으로 11개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

## 힌트

- 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
- Collections.sort() 메소드를 활용해 정렬 가능하다.
- ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

## 역할과 책임 분류

1) 구매할 로또 금액, 수동 개를을 입력 (InputView)

- 로또 개수(자동, 수동) 생성(LottoQuantity)

2) 로또머신으로 티켓생성 (LottoMachine)

- 일급 컬렉션
    - 로또 티켓들 생성 (LottoTickets)
        - 로또 티켓 (LottoTicket)

3) 당첨번호 확인(입력) (LottoWinners)

- 당첨정보 생성(LottoNumbers)
- 보너스볼 생성(LottoNumber)

4) 로또 당첨 정보(LottoRank)
5) 로또 수익률 계산 (LottoYield)
6) 결과 출력 (ResultView)

## 구현 목록

- [X] 구입금액 입력
- [X] 천원 단위로 구입개수 도출
    - [X] 1개 이상인지 확인
- [X] 구입개수로 로또티켓 발행(일급컬렉션)
    - [X] 로또 생성, shuffle 후 앞에서 6개 빼오기
- [X] 당첨번호 입력
- [X] 보너스 번호 입력
- [X] 당첨정보 반환
    - [X] 당첨 내역
- [X] 수익률 계산
- [X] 로또 번호 출력