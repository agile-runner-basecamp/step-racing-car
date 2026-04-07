> **“Any fool can write code that a computer can understand. Good programmers write code that humans can understand.”**
— Martin Fowler
> 

이 문서는 팀원 온보딩을 위한 **읽기 좋은 코드 작성 가이드**입니다.
코드의 가독성이 곧 유지보수성이며, 유지보수성이 곧 팀의 생산성입니다.

---

## 목차

1. [왜 읽기 좋은 코드인가?](about:blank#1-%EC%99%9C-%EC%9D%BD%EA%B8%B0-%EC%A2%8B%EC%9D%80-%EC%BD%94%EB%93%9C%EC%9D%B8%EA%B0%80)
2. [추상 — 모든 원칙의 뿌리](about:blank#2-%EC%B6%94%EC%83%81--%EB%AA%A8%EB%93%A0-%EC%9B%90%EC%B9%99%EC%9D%98-%EB%BF%8C%EB%A6%AC)
3. [논리, 사고의 흐름](about:blank#3-%EB%85%BC%EB%A6%AC-%EC%82%AC%EA%B3%A0%EC%9D%98-%ED%9D%90%EB%A6%84)
4. [객체 지향 패러다임](about:blank#4-%EA%B0%9D%EC%B2%B4-%EC%A7%80%ED%96%A5-%ED%8C%A8%EB%9F%AC%EB%8B%A4%EC%9E%84)
5. [객체 지향 적용하기](about:blank#5-%EA%B0%9D%EC%B2%B4-%EC%A7%80%ED%96%A5-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
6. [코드 다듬기](about:blank#6-%EC%BD%94%EB%93%9C-%EB%8B%A4%EB%93%AC%EA%B8%B0)
7. [리팩토링 실전 전략](about:blank#7-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-%EC%8B%A4%EC%A0%84-%EC%A0%84%EB%9E%B5)
8. [기억하면 좋을 조언들](about:blank#8-%EA%B8%B0%EC%96%B5%ED%95%98%EB%A9%B4-%EC%A2%8B%EC%9D%84-%EC%A1%B0%EC%96%B8%EB%93%A4)

---

## 1. 왜 읽기 좋은 코드인가?

### 클린 코드를 추구하는 이유

> 코드가 잘 읽힌다 → 이해가 잘 된다 → 유지보수가 수월하다 → **시간과 자원이 절약된다**
> 
- **코드가 잘 읽힌다 → 이해가 잘 된다**: 메서드명, 변수명, 구조가 명확하면 코드를 처음 보는 동료도 “이 코드가 뭘 하는지” 빠르게 파악할 수 있다.
- **이해가 잘 된다 → 유지보수가 수월하다**: 코드를 이해해야 수정할 수 있다. 이해하기 어려운 코드는 수정 시 의도치 않은 버그를 만들기 쉽다.
- **유지보수가 수월하다 → 시간과 자원이 절약된다**: 버그 수정, 기능 추가에 드는 시간이 줄어들면 팀 전체의 생산성이 올라간다.

클린 코드는 단순히 깔끔한 코드가 아닙니다.

**“**Clean code ***that works*”** — ***잘 동작해야***  비로소 ******클린 코드입니다.

### 핵심 용어 정의

| 용어 | 정의 |
| --- | --- |
| **도메인** | 해결하고자 하는 문제 영역. 예: 카페 키오스크를 만든다면 “음료 주문, 재고 관리, 결제” 등이 도메인 |
| **도메인 지식** | 도메인을 이해하고 해결하는 데 필요한 지식. 코드를 작성하기 전에 미리 설계하는 것이 아니라, 코드를 작성하고 요구사항을 분석하는 과정에서 자연스럽게 **발견**되는 것. 예: “음료에는 HANDMADE, BOTTLE, BAKERY 타입이 있고, BOTTLE과 BAKERY만 재고를 관리해야 한다”는 지식은 요구사항을 코드로 구현하면서 드러남 |
| **레거시(유산)** | 우리가 현재 가지고 있는 코드. 꼭 “나쁜 코드”라는 뜻이 아니라, 과거에 작성되어 지금 운영 중인 코드 전체를 의미 |
| **조상** | 과거의 나, 과거의 동료, 코드를 작성했던 개발자들 |
| **후손** | 미래의 나, 동료, 개발자들 |

### 프로그램의 구성

```
프로그램 = 데이터 + 코드
```

- **데이터 관점 → 추상** (섹션 2): 변수명, 메서드명, 클래스명을 어떻게 지을 것인가? 어떤 정보를 드러내고 어떤 정보를 숨길 것인가? — 이것이 **추상화**의 영역
- **코드 관점 → 논리** (섹션 3): 조건문, 반복문, 예외 처리 등 코드의 흐름을 어떻게 구성해야 읽는 사람의 뇌에 부담을 덜 줄 것인가? — 이것이 **논리적 사고 흐름**의 영역

---

## 2. 추상 — 모든 원칙의 뿌리

### 추상이란?

> **중요한 정보는 가려내어 남기고, 덜 중요한 정보는 생략하여 버리는 것**
> 

일상의 예를 들면, 지하철 노선도는 실제 지리적 거리와 곡선을 생략하고 “어떤 역에서 어떤 역으로 갈 수 있는가”라는 핵심 정보만 남긴 **추상화**입니다.
프로그래밍에서도 동일합니다. `sendEmail(to, subject, body)`라는 메서드명은 내부적으로 SMTP 연결, 소켓 통신, 인코딩 등의 복잡한 과정을 **생략**하고 “이메일을 보낸다”라는 핵심만 드러냅니다.

컴퓨터 과학 자체가 추상의 연속입니다.

- 1 bit → 자료형 → 고수준 언어 → 프레임워크: 모두 추상화 레벨의 차이
- 고수준 언어와 저수준 언어는 추상화 레벨을 말하는 것

### 잘못된 추상화의 원인

1. **중요한 정보를 거르지 못함** — 메서드 안에 핵심 로직과 부가 처리(로깅, 검증 등)가 섞여 있어 “이 메서드가 주로 뭘 하는지” 한눈에 파악하기 어려운 경우
2. **해석자가 동일하게 공유하는 문맥(context)이 없음** — 예를 들어, 변수명 `data`는 코드를 작성한 본인에게는 “API 응답 데이터”이지만, 다른 팀원이 읽으면 “DB 조회 결과”로 해석할 수 있다. 같은 이름이 팀원마다 다르게 해석되면 추상화가 실패한 것
3. **도메인이 명확하지 않음** — 어떤 문제를 해결하는 코드인지 도메인이 불명확하면, 이름을 지어도 의미가 모호해진다

> **도메인 안에서 추상화하는 것이 중요합니다.**
같은 `Status`라는 이름이라도, 주문 도메인에서는 `OrderStatus`, 결제 도메인에서는 `PaymentStatus`처럼 도메인 문맥을 함께 담아야 합니다.
> 

---

### 2.1 이름 짓기 — 가장 단순하면서도 중요한 추상화

이름 짓기는 고도의 추상화 행위입니다.

### 이름 짓기 4대 원칙

| 원칙 | 설명 | 예시 |
| --- | --- | --- |
| **단수와 복수 구분하기** | 컬렉션인지 단일 값인지 이름으로 표현 | `user` vs `users` |
| **이름 줄이지 않기** | 축약어는 혼란을 초래 | `cnt` → `count` |
| **은어/방언 사용하지 않기** | 보편적 용어 사용 | 팀 내 합의된 용어 사전 운영 |
| **좋은 코드를 보고 습득하기** | 업계 관용 표현 익히기 | `pool`(재사용 가능한 자원 묶음, 예: ConnectionPool), `candidate`(조건을 만족하는 후보, 예: 검색 결과 후보), `threshold`(판단 기준이 되는 임계값, 예: 재고 경고 기준선) |

### 실전 팁

- `boolean` 변수는 `is`, `has`, `can` 접두어로 타입을 명확히 → `isAllOpened`, `hasPermission`
- `i`, `j` 같은 의미 불분명한 변수는 `row`, `col` 등 의미를 부여
- 협업 시 **도메인 용어 사전** 을 만들어 “이 프로젝트에서 이 단어는 이렇게 정의한다”를 통일

---

### 2.2 메서드와 추상화

> **한 문단의 주제는 반드시 하나다.** 메서드도 마찬가지.
> 

글을 쓸 때 한 문단에 여러 주제를 섞으면 읽기 어려운 것처럼, 하나의 메서드가 “입력 검증 + 비즈니스 계산 + 결과 저장”을 모두 하면 읽는 사람이 한 번에 여러 맥락을 기억해야 합니다.

```java
// ❌ 하나의 메서드에 여러 주제가 섞여 있음
public void processOrder(Order order) {
    if (order.getItems().isEmpty()) throw new IllegalArgumentException();
    int total = order.getItems().stream().mapToInt(Item::getPrice).sum();
    if (total > 50000) total = (int)(total * 0.9);
    orderRepository.save(order);
    emailService.sendConfirmation(order);
}

// ✅ 각 메서드가 하나의 주제만 담당
public void processOrder(Order order) {
    validateOrder(order);
    int total = calculateTotalWithDiscount(order);
    saveOrder(order, total);
    notifyOrderConfirmation(order);
}
```

- 메서드의 이름으로 내용을 추상화
- 너무 많은 일을 하면 **잘게 쪼개기** — 각각이 하나의 주제를 가져야 함
- 코드가 한 줄이라도, 의미 전달을 위해 추상화하는 것이 좋은 경우라면 추상화한다

### 메서드 선언부의 구성

```
반환타입  메서드명  (파라미터)  { 구현부 }
└───── 메서드 선언부 ──────┘
         └── 메서드 시그니처 ──┘
```

### 파라미터 설계

- 파라미터의 **타입, 개수, 순서** 를 통해 의미를 전달
- 파라미터는 **외부 세계와 소통하는 창**: 메서드를 호출하는 쪽(외부)이 메서드 내부(내부 세계)에 정보를 전달하는 유일한 통로이므로, 파라미터만 보고도 “이 메서드에 무엇을 넘겨야 하는지”가 명확해야 한다
- 메서드명과 파라미터를 **전치사로 연결** 지어 추상화하면 좋음
    - 예: `findProductsBy(Category category)` → “카테고리로 상품을 찾는다”고 자연어처럼 읽힘

### 반환 타입 설계

- `void` 대신 충분히 반환할 만한 값이 있는지 고민 → 반환값이 있으면 `assertEquals(expected, actual)` 같은 단언이 가능하므로 **테스트도 용이**
- `check`로 시작하는 메서드는 보통 `void` 반환 — 검증에 실패하면 **예외를 던지는** 관례. 반환값 없이 “문제가 없으면 조용히 통과, 문제가 있으면 예외 발생”이라는 의미를 이름으로 전달

---

### 2.3 추상화 레벨 ⭐

> **메서드로 추출하면 “경계”가 생긴다 — 외부 세계(높은 추상화)와 내부 세계(낮은 추상화)**
> 

`calculateTotalPrice()`를 호출하는 쪽(외부)은 “총 금액을 계산한다”라는 높은 추상화 수준에서 이해하고,
메서드 내부(내부 세계)에서는 `stream().mapToInt().sum()` 같은 낮은 추상화 수준의 구현이 숨어 있습니다.

```
외부 세계 ──(파라미터)──▶ 내부 세계 ──(반환값)──▶ 외부 세계
                     메서드명이 중계
```

| 레벨 | 외부 세계 | 내부 세계 |
| --- | --- | --- |
| **메서드** | 메서드가 사용된 코드 | 메서드 구현 로직 |
| **객체** | `public` 메서드로 소통 | `private` 메서드는 블랙박스 |
| **모듈/시스템** | API(프로토콜)로 소통 | 내부 구현 |

### 핵심 규칙: 하나의 메서드(하나의 세계) 안에서는 추상화 레벨이 동등해야 한다

왜 동등해야 할까? — 높은 추상화 수준의 코드 사이에 갑자기 낮은 수준의 코드가 끼어 있으면,
읽는 사람이 “사고 모드를 전환”해야 합니다. 전체 흐름을 파악하다가 갑자기 구현 세부사항을 읽어야 하면 뇌에 부담이 생깁니다.

```java
// ❌ 추상화 레벨 불일치 — 읽는 사람이 멈칫하게 됨
public static void main(String[] args) {
    showGameStartComments();   // 추상화 레벨: 높음
    initializeGame();          // 추상화 레벨: 높음
    showBoard();               // 추상화 레벨: 높음

    if (gameStatus == 1) {     // 추상화 레벨: 낮음 ← 여기서 멈칫!
        System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!!");
        break;
    }
}
```

```java
// ✅ 추상화 레벨 통일 — 부드럽게 읽힘
public static void main(String[] args) {
    showGameStartComments();
    initializeGame();
    showBoard();

    if (doesUserWinTheGame()) {  // 동일한 추상화 레벨
        System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!!");
        break;
    }
}

private boolean doesUserWinTheGame() {
    return gameStatus == 1;
}
```

---

### 2.4 매직 넘버, 매직 스트링

> **의미를 갖고 있으나 상수로 추출되지 않은 숫자, 문자열**
> 

상수 추출 = 이름을 짓는 것 = **추상화**

```java
// ❌ 매직 넘버
if (age >= 19) { ... }

// ✅ 상수 추출
private static final int ADULT_AGE = 19;
if (age >= ADULT_AGE) { ... }
```

신경 쓰지 않으면 놓치기 쉽지만, 상수로 추출했을 때 가독성과 유지보수성 향상 효과가 큽니다.

---

## 3. 논리, 사고의 흐름

### 핵심 원리: 뇌 메모리 적게 쓰기 (인지적 경제성)

> **최소의 인지적 노력으로 최대의 정보를 전달하라.**
> 
- 뇌는 한 번에 한 가지 일만 처리할 수 있다 (멀티태스킹은 실제로는 빠르게 전환하는 저글링일 뿐)
- 코드를 읽을 때도 마찬가지다. 예를 들어 20줄 위에서 선언된 변수의 의미를 기억하면서, 동시에 현재 줄의 조건문을 해석하고, 다음 분기가 어디로 갈지 추적하는 것은 뇌에 큰 부담이다.
- 작업 전환마다 뇌를 재설정하는 비용이 발생하므로, **읽는 사람이 한 번에 추적해야 할 정보를 최소화**하는 것이 핵심이다

---

### 3.1 Early Return

```java
// ❌ 뇌가 앞의 조건을 계속 기억해야 함
if (condition1) {
    // ...
} else if (condition2) {
    // ...
} else {
    // ...
}

// ✅ Early Return — 조건을 즉시 해소
if (!condition1) {
    return;
}
if (!condition2) {
    return;
}
// 핵심 로직만 남음
```

- `else` 사용을 지양 — early return으로 조건을 즉시 해소
- `switch-case`도 동일 원칙 적용

---

### 3.2 사고의 depth 줄이기

### 중첩 분기문, 중첩 반복문

```java
// ❌ 3단계 depth — 조건을 계속 기억해야 다음 코드를 읽을 수 있음
for (int i = 0; i < 20; i++) {
    for (int j = 20; j < 30; j++) {
        if (i >= 10 && j < 25) {
            doSomething();
        }
    }
}
```

> 무조건 1 depth로 만들어라가 아니다.
**보이는 depth가 아니라, 사고 과정의 depth를 줄이자.**
> 

“보이는 depth”란 코드의 들여쓰기 단계를 말하고, “사고의 depth”란 읽는 사람이 머릿속에 동시에 기억해야 하는 조건의 수를 말합니다.
예를 들어, 같은 2중 반복이라도 `board[row][col]`처럼 행-열 구조가 명확한 경우는 사고 부담이 적지만,
위의 예시처럼 `i >= 10 && j < 25` 같은 조건이 중첩 안에 숨어 있으면 “지금 i는 몇이고, j는 몇이며, 이 조건은 왜 필요한가?”를 동시에 추적해야 해서 사고 부담이 큽니다.

때로는 2중 중첩이 의미 전달에 더 나을 수 있으며, 기계적 분리가 오히려 혼란을 줄 수 있습니다.

### Stream 활용

```java
// ❌ 중첩 반복문 + 플래그 변수
private static boolean isAllCellOpened() {
    boolean isAllOpened = true;
    for (int row = 0; row < BOARD_ROW_SIZE; row++) {
        for (int col = 0; col < BOARD_COL_SIZE; col++) {
            if (BOARD[row][col].equals(CLOSED_CELL_SIGN)) {
                isAllOpened = false;
            }
        }
    }
    return isAllOpened;
}

// ✅ Stream — 선언적으로 의도를 드러냄
private static boolean isAllCellOpened() {
    return Arrays.stream(BOARD)
            .flatMap(Arrays::stream)
            .noneMatch(cell -> cell.equals(CLOSED_CELL_SIGN));
}
```

> **참고**: Stream은 `parallelStream()`으로 병렬 처리도 가능
(내부적으로 Fork/Join Framework, Spliterator 사용)
> 

### 실전 팁

- 사용할 변수는 **가깝게 선언** — 20줄 위에서 선언한 변수를 아래에서 쓰면 뇌가 고생
- 파라미터를 줄일 수 있다면 줄이기 (단, 의미 전달에 방해되면 안 됨)

---

### 3.3 공백 라인의 의미

공백 라인도 정보를 전달합니다. 복잡한 로직의 **의미 단위** 를 나누어 읽는 사람에게 구조적 힌트를 줍니다.

---

### 3.4 부정어를 대하는 자세

부정어는 **2단계 사고**를 요구하므로 비효율적입니다.
예: `!isLeftSide()`를 읽으면 뇌는 ① “왼쪽인가?”를 먼저 이해한 뒤 ② “그것의 반대”를 다시 계산해야 합니다.
`isRightSide()`라고 쓰면 ① “오른쪽인가?” 한 단계로 끝납니다.

| 전략 | 예시 |
| --- | --- |
| 부정어 없이 표현 가능한지 체크 | `!isLeftSide()` → `isRightSide()` |
| 부정의 의미를 담은 단어 사용 | `isInvalid()`, `isNotEmpty()` → `hasContent()` |

---

### 3.5 해피 케이스와 예외 처리

사람은 해피 케이스(정상 동작)에 몰두하는 경향이 있습니다.
예외 상황을 꼼꼼히 처리할수록 **견고한 소프트웨어** 가 되며, 이것이 개발자의 역량입니다.

### 예외 처리 3대 원칙

1. **예외 발생 가능성 낮추기** — 외부 입력값은 항상 의심 (사용자 입력, 생성자, 외부 서버 요청)
2. **의도한 예외 vs 예상하지 못한 예외 구분하기**
    - **의도한 예외**: 비즈니스 규칙 위반 시 개발자가 **의도적으로 발생**시키는 예외. 커스텀 Exception (`AppException`, `BusinessException`)을 사용하여 사용자에게 “주문 수량은 1개 이상이어야 합니다” 같은 안내 메시지를 보여줌
    - **의도하지 못한 예외**: 네트워크 장애, DB 커넥션 끊김 등 **예측하지 못한 상황**에서 발생하는 예외. 로그를 남겨 개발자가 확인해야 함 (`e.printStackTrace()`는 콘솔에만 출력되므로 **실무에서는 안티패턴** — 로깅 프레임워크 사용)
3. **Null을 대하는 자세**

### Null 안전하게 다루기

모든 참조 타입 객체는 Null이 될 가능성이 있기에 `NullPointerException`을 유의해야 합니다.

| 타입 | 저장 방식 | Null 가능 여부 |
| --- | --- | --- |
| **기본 타입** (`int`, `boolean` 등) | 실제 값 저장 | 불가 |
| **참조 타입** (클래스, 인터페이스, 배열) | 메모리 주소 저장 | 가능 |

**Null 방어 전략:**

1. 참조 타입은 항상 NPE 발생 가능성 인지하기
2. `return null` 자제 — 어렵다면 `Optional` 사용

**Optional 사용 규칙:**

| 규칙 | 이유 |
| --- | --- |
| `Optional` 자체가 비싼 객체 | 래핑으로 참조가 한 단계 추가됨 |
| 파라미터로 받지 말 것 | 분기 케이스가 3개(Optional null / 데이터 null / 값 있음) |
| 반환받았다면 최대한 빠르게 해소 | 코드 복잡도 증가 방지 |

**Optional 해소 방법:**

```java
// ❌ isPresent() → get() : 분기문이 생김
if (optional.isPresent()) {
    return optional.get();
}

// ✅ orElse / orElseGet / orElseThrow 사용
optional.orElse(defaultValue);          // 항상 실행
optional.orElseGet(() -> createNew());  // null일 때만 실행
optional.orElseThrow(() -> new Exception());
```

**NPE 방어 관용구:**

```java
// ❌ 사용자 입력이 null이면 NPE 발생
userInput.equals("EXIT");

// ✅ 상수를 앞에 배치 — 상수는 null이 아니므로 안전
"EXIT".equals(userInput);
```

---

## 4. 객체 지향 패러다임

### 프로그래밍 패러다임 비교

| 패러다임 | 핵심 철학 |
| --- | --- |
| **절차 지향** | 컴퓨터 처리 과정과 비슷하게 순차적 프로그래밍 |
| **객체 지향** | 객체들 간의 **협력** 으로 프로그래밍 |
| **함수형** | 외부 요인을 없애 동일 입력 → 동일 출력 보장 |

### 객체란?

> **추상화된 [데이터 + 코드]**
> 
- 데이터: 객체가 가진 필드(변수)
- 코드: 데이터를 조작/가공하는 메서드

---

### 4.1 캡슐화·상속·추상화·다형성

| 특성 | 설명 | 주의사항 |
| --- | --- | --- |
| **캡슐화** | 데이터와 로직을 숨기고, 공개 메서드로만 소통 | getter/setter를 남발하면 객체 내부의 데이터 구조가 외부에 노출되어, 내부 구조를 변경할 때 이 getter를 사용하는 모든 코드를 함께 수정해야 함 |
| **상속** | 부모의 특성을 물려받아 확장 | 남발 시 결합도 증가 → **조합 선호** |
| **추상화** | 핵심만 뽑아내어 표현 | 너무 이른 시점의 추상화 주의 |
| **다형성** | 상위 타입 인터페이스로 여러 구현체 교체 가능 | 인터페이스 분리 원칙(ISP) 함께 고려 |

---

### 4.2 관심사의 분리 (Separation of Concerns)

> **특정 관심사에 따라 객체를 만들고, 조합으로 프로그램을 구성하라.**
> 
- **응집도**: 하나의 객체(클래스) 안에 있는 요소들이 얼마나 밀접하게 관련되어 있는가. 응집도가 높으면 클래스 안의 필드와 메서드가 모두 같은 목적을 위해 존재함
- **결합도**: 서로 다른 객체(클래스)가 얼마나 강하게 연결되어 있는가. 결합도가 높으면 하나를 바꿀 때 다른 것도 함께 바꿔야 함

```
관심사 분리 → 작업/개념을 묶어 이름 짓기 → 역할 부여
                    ↓
         높은 응집도 + 낮은 결합도
```

- 관심사를 정하면 그 관심사로만 객체를 구성 → **응집도 높임** (예: `GameBoard` 클래스에는 보드 관련 필드와 메서드만 존재)
- 서로 다른 관심사끼리는 관계가 적어야 → **결합도 낮춤** (예: `GameBoard`가 `InputHandler`의 내부 구현을 몰라도 동작해야 함)

---

### 4.3 객체 설계 원칙

### 객체를 만들 때 체크리스트

1. **1개의 관심사** 로 명확한 책임이 정의되었는가?
2. **생성자/정적 팩토리 메서드** 에서 유효성 검증을 하고 있는가?
3. **setter 사용을 자제** 하고 있는가? (데이터는 불변이 최고, 변경이 필요하면 `update~` 처럼 의도를 담은 이름)
4. **getter도 처음에는 자제** — 필요할 때만 추가 (getter 남발 = 캡슐화 파괴)
5. **필드의 수는 적을수록 좋은가?** (필드로 만들지 말고 메서드로 만들 수 있다면 메서드 선호)

### getter 남발의 문제

```java
// ❌ getter 체이닝 — 캡슐화 파괴, 무례한 코드
if (person.get지갑().get신분증().findAge() >= 19) {
    pass();
}

// ✅ 객체에게 물어보기 — Tell, Don't Ask
if (person.isAgeGreaterThanOrEqualTo(19)) {
    pass();
}
```

### 정적 팩토리 메서드

생성자에 이름을 붙여 **의미를 명확히** 하는 패턴입니다.

```java
// 생성자 — 어떤 Cell인지 불명확
Cell cell = new Cell(0, false);

// 정적 팩토리 메서드 — 의도가 드러남
Cell cell = Cell.ofEmpty();
Cell cell = Cell.ofLandMine();
```

### 필드 수 줄이기의 판단 기준

```
리스크 줄이기 (필드 최소화) ←──→ 성능 고려 (계산 비용이 크면 필드로 캐싱)
```

```java
// 메서드로 대체 가능하면 필드 불필요
// int totalPrice;  ← 제거
public int calculateTotalPrice() {
    return items.stream().mapToInt(Item::getPrice).sum();
}

// 단, 계산이 복잡하고 빈번하면 필드로 캐싱
private final int totalPrice;  // 성능을 위해 유지
```

---

### 4.4 SOLID 원칙

### SRP — 단일 책임 원칙 (Single Responsibility Principle)

> **하나의 클래스는 단 한 가지의 변경 이유만을 가져야 한다.**
> 
- 변경 이유 = 책임
- 공개 메서드, 필드, 상수는 해당 객체의 단일 책임에 의해서만 변경되어야 함
- **책임을 볼 줄 아는 눈은 경험으로 키워진다** → 시작점은 “이 클래스를 변경해야 하는 이유가 몇 가지인지 세어 보는 것”이다. 예를 들어 `Minesweeper` 클래스를 바꿔야 하는 이유가 “게임 규칙 변경”, “화면 출력 방식 변경”, “사용자 입력 방식 변경” 3가지라면, 이미 3가지 책임이 섞여 있다는 신호이다. 많이 나눠보고, 메타인지하고, 코드 리뷰에서 피드백을 받으며 감각을 키우자.

**실전 분리 예시 (지뢰찾기 게임):**

| 분리 단계 | 분리 기준 |
| --- | --- |
| 1단계 | 프로그램 실행(`main`) ↔︎ 게임 로직(`Minesweeper`) |
| 2단계 | 핵심 로직 ↔︎ 입출력(`InputHandler`, `OutputHandler`) |
| 3단계 | 게임 로직(`Minesweeper`) ↔︎ 보드 관리(`GameBoard`) |

### OCP — 개방-폐쇄 원칙 (Open-Closed Principle)

> **확장에는 열려있고, 수정에는 닫혀있어야 한다.**
> 

**추상화와 다형성** 을 활용해 OCP를 지킵니다.

```java
// 인터페이스로 확장 포인트 제공
public interface GameLevel {
    int getRowSize();
    int getColSize();
    int getLandMineCount();
}

// 구현체 추가만으로 기능 확장 — 기존 코드 수정 없음
public class Beginner implements GameLevel { ... }
public class Intermediate implements GameLevel { ... }
public class Advanced implements GameLevel { ... }
```

### LSP — 리스코프 치환 원칙 (Liskov Substitution Principle)

> **자식 클래스는 부모 클래스를 대체할 수 있어야 한다.**
> 
- 부모의 행동을 변경하지 않고, 기능을 확장해야 함
- 위반 시: 오동작, 예상 밖의 예외, 불필요한 타입 체크 동반

```java
// ❌ LSP 위반 — 자식에서 사용하지 않는 추상 메서드 강제 구현
abstract class Cell {
    abstract void turnOnLandMine();  // EmptyCell에서는 불필요
}

// ✅ LSP 준수 — 필요한 기능만 자식에서 확장
abstract class Cell {
    // 공통 기능만 추상 메서드로
}
class LandMineCell extends Cell {
    // 지뢰 관련 기능 확장
}
```

### ISP — 인터페이스 분리 원칙 (Interface Segregation Principle)

> **인터페이스를 기능 단위로 잘게 쪼개라.**
> 

```java
// ❌ 하나의 큰 인터페이스
interface Worker { void work(); void eat(); }

// ✅ 분리된 인터페이스
interface Workable { void work(); }
interface Eatable { void eat(); }

class Robot implements Workable { ... }        // eat() 구현 불필요
class Human implements Workable, Eatable { ... }
```

- 인터페이스 이름은 **형용사** 로 짓는 것이 관례 (`Comparable`, `Serializable`, `Runnable`)
- 메서드는 **동사 + 목적어**, 변수는 **명사**

### DIP — 의존 역전 원칙 (Dependency Inversion Principle)

> **상위 수준의 모듈이 하위 수준의 모듈에 직접적으로 의존해서는 안 된다.**
> 
- 의존: 한 모듈이 다른 모듈의 구체적인 정보에 접근할 수 있는 것
- 추상(인터페이스)에 의존하도록 설계

```java
// ❌ 상위가 하위 구현에 직접 의존
class Minesweeper {
    private ConsolePrinter printer = new ConsolePrinter();
}

// ✅ 추상에 의존 → 구현체 교체 자유
class Minesweeper {
    private final OutputHandler outputHandler;

    public Minesweeper(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }
}
```

---

## 5. 객체 지향 적용하기

### 5.1 상속보다 조합을 선호하라

> **상속은 시멘트처럼 굳어지는 구조다.**
> 
> 
> 왜 굳어지는가? — 자식 클래스는 부모의 **모든 필드와 메서드를 알고 있어야** 하므로, 부모를 수정하면 모든 자식에 영향이 발생한다.
> 또한 상속 계층이 깊어질수록 “이 메서드가 어느 부모께서 온 것인지” 추적하기 어려워져서, 나중에 구조를 바꿨려 해도 수정 범위가 너무 커진다.
> 

| 비교 | 상속 | 조합 |
| --- | --- | --- |
| 결합도 | 높음 (자식이 부모의 모든 것을 알아야) | 낮음 (필요한 기능만 위임) |
| 캡슐화 | 깨짐 (부모 내부 접근 필요) | 유지 |
| 유연성 | 낮음 (부모 변경 → 자식 전체 영향) | 높음 |

```java
// ❌ 상속
class NumberCell extends Cell { ... }

// ✅ 조합 + 인터페이스
interface Cell { ... }
class NumberCell implements Cell {
    private final CellState state;  // 조합
}
```

---

### 5.2 Value Object (값 객체)

기본 타입을 객체로 감싸 **의미를 부여하고 추상화** 한 객체입니다.

### Value Object 3대 특성

| 특성 | 구현 |
| --- | --- |
| **불변성** | `final` 필드, setter 금지 |
| **동등성** | `equals()`, `hashCode()` 재정의 — 내부 값이 같으면 같은 객체 |
| **유효성 검증** | 생성자에서 값의 유효성 보장 |

```java
public class CellPosition {
    private final int row;
    private final int col;

    public CellPosition(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("인덱스는 0 이상이어야 합니다.");
        }
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) { ... }

    @Override
    public int hashCode() { ... }
}
```

### Value Object vs Entity

| 구분 | Value Object | Entity |
| --- | --- | --- |
| 식별자 | 없음 (모든 필드가 함께 식별자 역할) | 있음 (ID 등) |
| 동등성 | 모든 값이 같아야 동등 | 식별자만 같으면 동등 |
| 가변성 | 불변 | 가변 가능 |

---

### 5.3 일급 컬렉션 (First-Class Collection)

> 컬렉션을 객체로 감싸 **컬렉션 관련 로직을 캡슐화**
> 

```java
public class Cells {
    private final List<Cell> cells;

    public Cells(List<Cell> cells) {
        this.cells = new ArrayList<>(cells);  // 방어적 복사
    }

    public List<Cell> getCells() {
        return Collections.unmodifiableList(cells);  // 불변 반환
    }

    public int size() {
        return cells.size();
    }
}
```

> **주의**: 반환 시 원본 컬렉션을 그대로 반환하면 외부에서 참조(메모리 주소)로 조작 가능 → **방어적 복사 또는 불변 뷰 반환** 필수
> 

---

### 5.4 Enum의 특성과 활용

- 변경이 잦은 개념은 Enum보다 DB 관리가 나을 수 있음
- 변경이 드물고 **상태가 고정된 집합** 에 적합

---

### 5.5 다형성으로 조건문 제거하기

반복되는 `if-else`를 **다형성 패턴** 으로 대체합니다.

```java
// ❌ 조건문 나열
if (status == EMPTY) return " ";
else if (status == FLAG) return "⚑";
else if (status == MINE) return "✱";

// ✅ 다형성 패턴 — supports() + provide()
public enum CellSignProvider {
    EMPTY(CellSnapshotStatus.EMPTY, " "),
    FLAG(CellSnapshotStatus.FLAG, "⚑"),
    MINE(CellSnapshotStatus.MINE, "✱");

    private final CellSnapshotStatus status;
    private final String sign;

    public boolean supports(CellSnapshotStatus status) {
        return this.status == status;
    }

    public String provide() {
        return sign;
    }
}

// 사용
String sign = Arrays.stream(CellSignProvider.values())
    .filter(provider -> provider.supports(snapshot.getStatus()))
    .map(CellSignProvider::provide)
    .findFirst()
    .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 상태입니다."));
```

---

### 5.6 숨겨진 도메인 개념 도출하기

1. **도메인 지식은 만드는 것이 아니라 발견하는 것** — 코드를 작성하면서 드러남. 예를 들어, 지뢰찾기 게임을 만들다 보면 “셀의 상태(EMPTY, FLAG, MINE, NUMBER)”라는 도메인 개념이 자연스럽게 드러난다. 처음부터 설계한 것이 아니라, 코드를 쓰다 보니 “이건 별도의 개념으로 분리해야겠다”라는 필요성이 보인 것이다.
2. **객체 지향은 현실을 100% 반영하는 게 아니라 흉내내는 것** — 현실에서 보이지 않던 개념도 도출해야 할 때가 있음
3. **최선으로 설계하되, 틀렸다면 언제든 돌아올 수 있는 코드를 작성** — 과도하면 오버 엔지니어링
4. 매개변수가 너무 많아지면 **설정 객체** 로 묶는 것을 고려 (예: `GameConfig`)

---

## 6. 코드 다듬기

### 6.1 주석의 양면성

> **“주석이 많다는 것은 비즈니스 요구사항을 코드에 잘 녹이지 못했다는 의미”**
> 

예를 들어 `// 성인인지 확인한다`라는 주석을 달아야 했다면, 메서드명을 `isAdult()`로 지었으면 주석이 필요 없었을 것입니다.
주석이 많다는 것은 “코드만으로는 의도를 전달하지 못하고 있다”는 신호이므로, 먼저 코드 자체를 개선하는 것이 우선입니다.

### 나쁜 주석 vs 좋은 주석

| 나쁜 주석 | 좋은 주석 |
| --- | --- |
| 코드를 설명하는 주석 | **의사 결정의 히스토리** |
| 코드와 동기화되지 않는 주석 | 코드로 설명할 수 없는 맥락 |
| 당연한 것을 설명하는 주석 | 비즈니스 규칙의 근거 |

```java
/**
 * 이 객체가 가지고 있는 정책은,
 * A안과 B안 중 AB 테스트를 통해 결정된 사항을 기반으로 작성된 것이다.
 * 관련 문서: https://wiki.readable-code.com?pageId=123
 */
```

**절차:**

1. 모든 방법을 동원해 읽기 좋은 코드를 만든다
2. 그럼에도 전달할 수 없는 사항을 주석으로 남긴다
3. 주석도 코드와 함께 업데이트한다 (관리 대상이 하나 늘어나는 것)

---

### 6.2 변수와 메서드의 나열 순서

### 변수

**사용하는 순서대로 나열** → 인지적 경제성

### 메서드 나열 전략

**공개 메서드 우선도:**

```
상태 변경 >> 판별(boolean) ≥ 조회
```

**비공개 메서드:**

- 공개 메서드에서 언급된 순서대로 배치
- 공통 사용 메서드는 하단에 배치

**스타일 선택:**

```
스타일 1 (인라인)              스타일 2 (분리)
─────────────────            ─────────────────
public method A              public method A
  └ private A-1              public method B
  └ private A-2              ─────────────────
public method B              private A-1
  └ private B-1              private A-2
  └ private B-2              private B-1
                             private B-2
```

> 나열 순서로도 **의도와 정보를 전달** 할 수 있습니다.
> 

---

### 6.3 패키지 나누기

- 패키지는 **문맥(context)** 으로써 정보를 제공
    - `gameLevel.VeryBeginner` — 패키지명이 의미를 보충
- 너무 안 쪼개면 관리 어려움, 너무 잘게 쪼개도 관리 어려움
- **처음 만들 때부터 잘 고민** 하는 것이 최선
- 대규모 패키지 변경은 **팀원과 합의 후** 진행 (conflict 위험)
- 하위 개념은 상위 패키지의 **하위 패키지** 로 구성

---

### 6.4 기능 유지보수

- **버그 잡기**: 추상화가 잘 되어있으면 버그 원인 추적이 쉬움
- **알고리즘 교체**: 추상화된 구조에서는 교체가 용이
    - 예: 재귀(DFS, Stack 기반) → 명시적 Stack으로 교체 → StackOverflow 방지

---

### 6.5 IDE 도구 활용

| 도구 | 단축키/설명 |
| --- | --- |
| **코드 포맷 정렬** | `Ctrl + Alt + L` — 습관적으로 사용 |
| **코드 품질 체크** | SonarLint — 잠재적 문제를 미리 경고 |
| **포맷 규칙 통일** | `.editorconfig` — 팀 전체 스타일 통일 |
| **코드 인라인** | `Ctrl + Alt + N` — 중복 코드 정리 |
| **동일 코드 선택** | `Alt + J` (하나씩), `Ctrl + Alt + Shift + J` (전체) |

> 스타일은 혼자 결정하지 않고 **팀 내 합의** 로 도출하며, 지속적으로 개선/반영합니다.
> 

---

## 7. 리팩토링 실전 전략

### 7.1 안전한 리팩토링 절차

```
1. 리팩토링 대상 범위 확인
2. 기능 보장을 위한 테스트 코드 작성 (없으면 먼저 작성!)
3. 리팩토링 수행
4. 테스트 코드로 검증
```

> 실제 상황에서는 테스트 코드가 없는 환경이 더 많습니다. 리팩토링 전에 **테스트부터 작성** 하세요.
> 

### 7.2 점진적 리팩토링 기법

메서드가 여러 군데에서 사용되고 있다면:

```
1. 기존 메서드를 복사하여 "메서드2"로 생성
2. 위에서부터 하나씩 메서드2로 교체
3. 교체할 때마다 동작 확인
4. 모두 교체 완료 후 기존 메서드 삭제
5. 메서드2를 원래 이름으로 변경
```

> 한꺼번에 바꾸지 않고 **점진적으로 교체** 하면 안전합니다.
> 

### 7.3 추상화 기반 리팩토링 체크리스트

- [ ]  매직 넘버/스트링이 상수로 추출되었는가?
- [ ]  메서드가 하나의 주제만 다루는가?
- [ ]  같은 세계의 추상화 레벨이 동등한가?
- [ ]  early return으로 불필요한 else를 제거했는가?
- [ ]  부정어구를 긍정 표현으로 바꿀 수 있는가?
- [ ]  getter를 제거하고 객체에게 메시지를 보내는 방식으로 바꿀 수 있는가?
- [ ]  변수는 사용하는 곳 가까이에 선언되었는가?

---

## 8. 기억하면 좋을 조언들

### 8.1 능동적 읽기

다른 사람의 코드를 읽을 때, “원본을 망가뜨릴까 봐” 걸정하지 마세요.
Git을 사용하고 있다면 `git reset --hard`로 언제든 원래 상태로 돌릴 수 있으니까,
마음 편하게 코드를 **수정하면서** 읽으세요.

1. **공백/줄바꿈** 으로 의미 단위 구분
2. **주석** 으로 이해한 내용 적어가며 읽기
3. **한글 메서드명** 으로 추출하면서 이해 → 이해 후 `git reset --hard`로 원상 복구

---

### 8.2 오버 엔지니어링 경계

### 구현체가 하나인 인터페이스

- 인터페이스 형태가 아키텍처 이해에 도움 되거나, 근시일 내 구현체 추가 예정이면 OK
- 아니라면: 구현체 수정 시 인터페이스도 수정해야 하고, 코드 탐색에 방해

### 너무 이른 추상화

- 과도하거나 이른 시점의 추상화는 복잡도를 높임
- 후대 개발자가 선대의 의도를 파악하기 어려움

---

### 8.3 은탄환은 없다 (No Silver Bullet)

> **모든 기술과 방법론은 적정 기술의 범위 내에서 사용되어야 한다.**
> 

```
지속 가능한 소프트웨어 품질  ←──→  기술 부채를 안고 가는 빠른 결과물
```

- 클린 코드도 은탄환이 아님 — 한 번 쓰고 버릴 코드에 과도한 투자는 불필요
- 기술 부채를 안을 때는 `TODO` 주석으로 표시

> **“도구라는 것은, 그것을 한계까지 사용할 줄 아는 사람이 사용하지 말아야 할 때도 아는 법이다.”**
> 

극단적으로 시도해보고 → 어디서 한계에 부딪히는지 느끼고 → 적정 수준을 체화하세요.

---

### 8.4 전문가의 사고 방식

> **전문가는 탑다운 방식만 고수하지 않는다.**
> 

추상과 구체를 넘나들며 **탑다운과 바텀업을 반복** 합니다.

예를 들어 지뢰찾기 게임을 설계할 때:
- **탑다운**: “전체 게임 흐름은 ’입력 → 셸 처리 → 표시 → 종료 판단’이다”라고 큰 그림을 그림
- **바텀업**: “셸이 열릴 때 주변 지뢰 개수는 어떻게 계산하지?” 하고 구체적인 구현을 작성
- **다시 탑다운**: “이 계산 로직을 `CellSnapshot`이라는 개념으로 추상화하면 더 깔끔하겠다” 하고 설계를 조정

이처럼 전환이 일어나는 순간마다 **“아하 모먼트”** 가 발생하고, 이것이 성장의 핵심입니다.

---

### 8.5 추천 도서

| 도서 | 저자 | 핵심 주제 |
| --- | --- | --- |
| **함께 자라기** | 김창준 | 애자일, 함께 성장하기 |
| **Five Lines of Code** | Christian Clausen | 메서드는 5줄 이내로 |
| **Clean Code** | Robert C. Martin | 클린 코드 원칙 |
| **리팩토링** | Martin Fowler | 코드 구조 개선 체계 |
| **이펙티브 자바** | Joshua Bloch | Java 모범 사례 87가지 |
| **오브젝트** | 조영호 | 객체 지향의 본질 |

---

## 부록: 유용한 IntelliJ 단축키

| 단축키 | 기능 |
| --- | --- |
| `Ctrl + Alt + L` | 코드 포맷 정렬 |
| `Ctrl + Alt + N` | 인라인 (중복 제거) |
| `Ctrl + Alt + M` | 메서드 추출 |
| `Ctrl + Alt + V` | 변수 추출 |
| `Ctrl + Alt + C` | 상수 추출 |
| `Alt + J` | 동일 코드 하나씩 선택 |
| `Ctrl + Alt + Shift + J` | 동일 코드 전체 선택 |
| `Shift + F6` | 이름 변경 (Rename) |
| `F2` | 다음 에러/경고로 이동 |

---

> **“읽기 좋은 코드는 ’잘 쓴 글’과 같습니다. 독자(후손)를 배려하는 마음으로 코드를 작성합시다.”**
>