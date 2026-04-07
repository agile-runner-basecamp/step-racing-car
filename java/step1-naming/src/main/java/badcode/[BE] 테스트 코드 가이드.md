> **테스트 코드를 잘 작성하기 위한 종합 가이드**
> 
> 
> Java Spring 기반 프로젝트에서 실전 테스트 코드를 작성하기 위한 원칙, 기법, 구체적 조언을 정리한 문서입니다.
> 

---

## 목차

1. [테스트는 왜 필요한가?](about:blank#1-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%8A%94-%EC%99%9C-%ED%95%84%EC%9A%94%ED%95%9C%EA%B0%80)
2. [테스트 피라미드](about:blank#2-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%94%BC%EB%9D%BC%EB%AF%B8%EB%93%9C)
3. [단위 테스트](about:blank#3-%EB%8B%A8%EC%9C%84-%ED%85%8C%EC%8A%A4%ED%8A%B8)
4. [테스트 케이스 세분화](about:blank#4-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BC%80%EC%9D%B4%EC%8A%A4-%EC%84%B8%EB%B6%84%ED%99%94)
5. [테스트하기 어려운 영역 분리하기](about:blank#5-%ED%85%8C%EC%8A%A4%ED%8A%B8%ED%95%98%EA%B8%B0-%EC%96%B4%EB%A0%A4%EC%9A%B4-%EC%98%81%EC%97%AD-%EB%B6%84%EB%A6%AC%ED%95%98%EA%B8%B0)
6. [TDD: Test Driven Development](about:blank#6-tdd-test-driven-development)
7. [테스트는 문서다](about:blank#7-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%8A%94-%EB%AC%B8%EC%84%9C%EB%8B%A4)
8. [BDD 스타일로 작성하기](about:blank#8-bdd-%EC%8A%A4%ED%83%80%EC%9D%BC%EB%A1%9C-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0)
9. [Spring & JPA 기반 테스트](about:blank#9-spring--jpa-%EA%B8%B0%EB%B0%98-%ED%85%8C%EC%8A%A4%ED%8A%B8)
10. [Mock을 마주하는 자세](about:blank#10-mock%EC%9D%84-%EB%A7%88%EC%A3%BC%ED%95%98%EB%8A%94-%EC%9E%90%EC%84%B8)
11. [더 나은 테스트를 위한 구체적 조언](about:blank#11-%EB%8D%94-%EB%82%98%EC%9D%80-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A5%BC-%EC%9C%84%ED%95%9C-%EA%B5%AC%EC%B2%B4%EC%A0%81-%EC%A1%B0%EC%96%B8)
12. [Spring REST Docs](about:blank#12-spring-rest-docs)
13. [테스트를 작성하는 마음가짐](about:blank#13-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A5%BC-%EC%9E%91%EC%84%B1%ED%95%98%EB%8A%94-%EB%A7%88%EC%9D%8C%EA%B0%80%EC%A7%90)
14. [부록: 추가 학습 키워드](about:blank#14-%EB%B6%80%EB%A1%9D-%EC%B6%94%EA%B0%80-%ED%95%99%EC%8A%B5-%ED%82%A4%EC%9B%8C%EB%93%9C)

---

## 1. 테스트는 왜 필요한가?

### 수동 테스트의 한계

- 기능이 추가될수록 **기존 기능과 새 기능이 서로 영향을 주는 영역**이 증가한다.
예를 들어, 주문 기능을 수정했더니 결제나 재고 차감 로직에까지 영향이 갈 수 있다.
이런 영역을 매번 사람이 직접 클릭하며 확인하는 것은 현실적으로 불가능하다.
- 인력을 늘려 수동으로 테스트하더라도 **사람은 실수**를 하기 마련이다.
같은 시나리오를 반복 검증하다 보면 집중력이 떨어지고, 놓치는 케이스가 생긴다.
- 수동 테스트(QA)만으로는 **비용과 시간**이 기하급수적으로 늘어난다.

### 테스트를 통해 얻고자 하는 것

| 목표 | 설명 |
| --- | --- |
| **빠른 피드백** | 코드 변경 직후 문제를 즉시 발견 |
| **자동화** | 반복 가능한 검증을 기계에 위임 |
| **안정감** | 리팩토링과 기능 추가 시 기존 기능의 정상 동작을 보장 |

### 테스트 코드를 작성하지 않으면?

1. 코드를 수정할 때마다 “이 변경이 다른 곳에 영향을 주진 않을까?”를 **개발자가 머릿속으로 전부 추적**해야 한다.
2. 새 팀원이 코드를 수정할 때, 기존 팀원이 이미 했던 고민(“이 케이스는 괜찮은가?”)을 **처음부터 다시 반복**해야 한다.
3. 기능이 빠르게 추가·변경되는 상황에서, 기존 기능이 여전히 정상 동작하는지 **확신할 수 없다.**

### 테스트 코드가 병목이 되면?

> 테스트 코드도 코드이므로, **잘못 작성하면 오히려 발목을 잡는다.**
> 
1. 테스트가 불안정하거나 자주 깨지면 “테스트가 깨져도 무시”하는 문화가 생겨, 프로덕션 코드의 **신뢰 안전망이 무너진다.**
2. 프로덕션 코드를 조금만 바꿔도 수십 개의 테스트를 수정해야 하는 상황이 되면, 테스트 코드 자체가 유지보수하기 어려운 **새로운 짐**이 된다.
3. 잘못된 테스트가 통과하면서 “테스트가 통과했으니 괜찮다”는 **거짓 안심**을 줄 수 있다.

### 올바른 테스트 코드란?

1. 자동화 테스트로 **코드 변경 직후 몇 초 만에 버그를 발견**하고, 매번 손으로 확인하는 비용을 크게 절약한다.
2. 기존 기능이 깨지지 않는다는 보장이 있으므로, **새로운 기능을 과감하게 추가하거나 리팩토링할 수 있다.**
3. 한 팀원이 “이 경우에는 어떻게 동작해야 하지?”를 고민한 결과가 테스트 코드로 남으면,
다른 팀원은 그 테스트를 읽는 것만으로 동일한 맥락을 공유할 수 있다. — **개인의 지식이 팀의 자산**이 된다.
4. **가까이 보면 느리지만, 멀리 보면 가장 빠르다.**

> 테스트는 귀찮지만 **해야 한다.**
> 

---

## 2. 테스트 피라미드

```
        ╱  E2E  ╲          ← 느리고 비용 높음, 최소한으로
       ╱──────────╲
      ╱ Integration ╲      ← 모듈 간 협력 검증
     ╱────────────────╲
    ╱    Unit Tests     ╲   ← 빠르고 안정적, 가장 풍부하게
   ╱────────────────────╲
```

| 구분 | 범위 | 속도 | 비용 | 권장 비율 |
| --- | --- | --- | --- | --- |
| **Unit** | 클래스/메서드 단위 | 매우 빠름 | 낮음 | 가장 많이 |
| **Integration** | 여러 모듈 협력 | 보통 | 중간 | 적절히 |
| **E2E** | 전체 시스템 흐름 | 느림 | 높음 | 최소한 |

> **풍부한 단위 테스트 + 큼직한 기능/시나리오 단위를 검증하는 통합 테스트** 두 가지 관점으로 접근하라.
> 

---

## 3. 단위 테스트

### 정의

- **작은** 코드 단위(하나의 클래스 또는 하나의 메서드)를, 데이터베이스나 외부 API 없이 **독립적**으로 검증하는 테스트
- 외부 의존이 없으므로 **밀리초 단위로 빠르게** 실행되며, 환경에 따라 결과가 달라지지 않아 **안정적**이다.

### 사용 도구

| 도구 | 역할 |
| --- | --- |
| **JUnit 5** | 단위 테스트를 위한 테스트 프레임워크 (Kent Beck의 XUnit 시리즈) |
| **AssertJ** | 풍부한 API, 메서드 체이닝을 지원하는 테스트 라이브러리 |

> `spring-boot-starter-test`에 JUnit 5와 AssertJ가 모두 포함되어 있다.
> 

### AssertJ 핵심 활용법

```java
// 기본 검증
assertThat(actual).isEqualTo(expected);
assertThat(actual).isNotNull();
assertThat(actual).isTrue();

// List 검증 — extracting + tuple 조합이 강력
assertThat(products).hasSize(3)
    .extracting("productNumber", "name", "sellingStatus")
    .containsExactlyInAnyOrder(
        tuple("001", "아메리카노", SELLING),
        tuple("002", "카페라떼", HOLD),
        tuple("003", "팥빙수", STOP_SELLING)
    );

// 예외 검증
assertThatThrownBy(() -> stock.deductQuantity(quantity))
    .isInstanceOf(IllegalArgumentException.class)
    .hasMessage("차감할 재고 수량이 없습니다.");
```

---

## 4. 테스트 케이스 세분화

### 요구사항이 추가됐을 때 반드시 질문하라

> **“암묵적이거나 아직 드러나지 않은 요구사항이 있는가?”**
> 

모든 요구사항은 아래 두 가지로 나눠서 생각한다:

1. **해피 케이스** — 정상적으로 동작하는 경우
2. **예외 케이스** — 실패하거나 비정상적인 경우

### 경계값 테스트

특히 주의해야 하는 영역:

- **범위**: 이상, 이하, 초과, 미만
- **구간**: 시작점, 끝점
- **날짜/시간**: 경계 시점

```java
// 경계값 테스트 예시: 음료는 1잔 이상 주문해야 함
@Test
void addZeroBeverages() {
    // 0개를 추가할 경우 — 경계값
    assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
}
```

---

## 5. 테스트하기 어려운 영역 분리하기

### 테스트하기 어려운 영역

1. **관측할 때마다 다른 값에 의존하는 코드**
    - 현재 날짜/시간, 랜덤 값, 전역 변수/함수, 사용자 입력
2. **외부 세계에 영향을 주는 코드**
    - 표준 출력, 메시지 발송, 데이터베이스 기록

### 핵심 원칙: 외부로 분리하라

```java
// ❌ BAD — 테스트하기 어려움
public Order createOrder() {
    LocalDateTime now = LocalDateTime.now();
    if (now.isBefore(SHOP_OPEN_TIME) || now.isAfter(SHOP_CLOSE_TIME)) {
        throw new IllegalArgumentException("영업시간이 아닙니다.");
    }
    return new Order(now, beverages);
}

// ✅ GOOD — 시간을 매개변수로 분리
public Order createOrder(LocalDateTime currentDateTime) {
    if (currentDateTime.isBefore(SHOP_OPEN_TIME) || currentDateTime.isAfter(SHOP_CLOSE_TIME)) {
        throw new IllegalArgumentException("영업시간이 아닙니다.");
    }
    return new Order(currentDateTime, beverages);
}
```

> 프로덕션 코드에서는 `LocalDateTime.now()`를, 테스트 코드에서는 **고정된 시간**을 넣어주면 된다.
> 

### 순수 함수 (Pure Function)

- 같은 입력에는 항상 같은 결과
- 외부 세상과 **단절된 형태**
- 테스트하기 쉬운 코드

> **테스트하기 어려운 영역을 발견한다면, 외부로 분리하라.**
> 

---

## 6. TDD: Test Driven Development

### 정의

프로덕션 코드보다 **테스트 코드를 먼저 작성**하여, 테스트가 구현 과정을 **주도**하도록 하는 방법론

### Red → Green → Refactor

```
┌─────────────────────────────────────────────┐
│                                             │
│   🔴 Red        실패하는 테스트 작성          │
│       ↓                                     │
│   🟢 Green      테스트 통과 (최소한의 코딩)    │
│       ↓                                     │
│   🔵 Refactor   구현 코드 개선 (테스트 통과 유지)│
│       ↓                                     │
│   🔴 Red        다시 실패하는 테스트 작성 …     │
│                                             │
└─────────────────────────────────────────────┘
```

### 선 기능 구현, 후 테스트 작성의 문제

1. 테스트 자체의 **누락 가능성** (빠르게 구현해야 하는 상황)
2. **특정 테스트 케이스**(해피 케이스)만 검증할 가능성
3. 잘못된 구현을 **다소 늦게 발견**할 가능성

### 선 테스트 작성, 후 기능 구현의 장점

1. 테스트를 먼저 작성하면 “이 코드를 어떻게 테스트하지?”를 먼저 고민하게 되어,
자연스럽게 **의존성이 적고, 역할이 명확한 코드**로 구현하게 된다.
2. 기능을 먼저 구현하면 “잘 되는 경우”만 생각하기 쉽지만, 테스트를 먼저 작성하면
“0개를 입력하면?”, “null이 오면?” 같은 **엣지 케이스**를 자연스럽게 떠올리게 된다.
3. 코드를 작성할 때마다 테스트를 실행해서 **즉시 맞았는지 틀렸는지 확인**할 수 있다.
4. 기존 테스트가 안전망 역할을 하므로, 코드 구조를 **과감하게 개선**할 수 있다.

### TDD의 핵심 가치

> **TDD의 핵심 가치는 피드백이다.**
> 
> 
> TDD는 **관점의 변화**를 일으키는 도구다.
> 테스트를 먼저 작성한다는 것은 “이 클래스를 사용하는 쪽(=클라이언트)”의 입장에서 코드를 설계한다는 뜻이다.
> 예를 들어, `OrderService`의 테스트를 먼저 작성하면 “주문을 생성하려면 어떤 파라미터가 필요한가?”
> “반환값은 무엇이 자연스러운가?”를 **사용하는 쪽의 시각**에서 먼저 고민하게 된다.
> 

---

## 7. 테스트는 문서다

### 왜 문서인가?

1. 새 팀원이 “이 서비스가 정확히 뭘 하는 거지?”라고 궁금할 때, 테스트 코드의 `@DisplayName`과 Given/When/Then을 읽으면 **프로덕션 기능의 동작을 빠르게 파악**할 수 있다.
2. 해피 케이스뿐 아니라 예외 케이스, 경계값 테스트까지 있으면 **“이런 경우에는 어떻게 동작하지?”라는 궁금증**이 테스트 코드만으로 해결된다.
3. 선배 개발자가 “재고가 0일 때 주문하면 어떻게 해야 하지?”를 고민해서 작성한 테스트는,
후배 개발자가 같은 고민을 반복하지 않게 해준다. — **개인의 경험이 팀 전체의 자산**이 된다.

> 프로덕션 코드는 **공유 자산**이고, 테스트 코드는 이를 **설명하는 문서**다.
> 
> 
> **우리는 항상 팀으로 일한다.** 다른 팀원에게 어떻게 비칠지 고민하며 작성하자.
> 

### @DisplayName을 섬세하게

| 원칙 | Bad Example | Good Example |
| --- | --- | --- |
| 명사 나열보다 **문장**으로 | 음료 1개 추가 테스트 | 음료를 1개 추가하면 주문 목록에 담긴다 |
| 테스트 행위에 대한 **결과까지** 기술 | 음료를 1개 추가할 수 있다 | 음료를 1개 추가하면 주문 목록에 담긴다 |
| **도메인 용어** 사용, 추상화된 내용 | 특정 시간 이전에 주문을 생성하면 실패한다 | 영업 시작 시간 이전에는 주문을 생성할 수 없다 |
| 현상(실패/성공)이 아닌 **정책** 관점 | ~하면 실패한다 | ~할 수 없다 |

> **“~테스트”라는 이름은 지양하기**
> 

---

## 8. BDD 스타일로 작성하기

### BDD (Behavior Driven Development)

- TDD에서 파생된 개발 방법
- `calculateTotalPrice()` 같은 **함수 단위**가 아니라, “고객이 상품을 담고 주문하면 총 금액이 계산된다” 같은 **사용자 시나리오 단위**로 테스트를 작성
- 기획자나 QA 담당자가 읽어도 “이 테스트가 무엇을 검증하는지” 이해할 수 있을 정도의 **자연어에 가까운 표현** 권장

### Given / When / Then

| 절 | 역할 | 설명 |
| --- | --- | --- |
| **Given** | 준비 | 시나리오에 필요한 모든 준비 과정 (객체, 값, 상태) |
| **When** | 행동 | 시나리오 행동 진행 |
| **Then** | 검증 | 시나리오에 대한 결과 명시, 검증 |

```java
@DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
@Test
void calculateTotalPrice() {
    // given
    CafeKiosk cafeKiosk = new CafeKiosk();
    cafeKiosk.add(new Americano());
    cafeKiosk.add(new Latte());

    // when
    int totalPrice = cafeKiosk.calculateTotalPrice();

    // then
    assertThat(totalPrice).isEqualTo(8500);
}
```

> **IntelliJ Live Templates**로 Given/When/Then 템플릿을 등록해두면 편리하다.
> 

### 참고: JUnit vs Spock

Spock은 BDD 프레임워크이므로 `given:`, `when:`, `then:` 블록을 자연스럽게 지원한다.

> **언어가 사고를 제한한다.**
테스트 자체는 올바르게 동작하더라도, `@DisplayName`이나 메서드 이름이 모호하면
나중에 그 테스트를 읽는 팀원이 “이게 대체 뭘 검증하는 거지?”라는 혼란에 빠진다.
혼란을 주는 테스트는 오히려 팀의 사고를 제한하는 장애물이 될 수 있다.
> 

---

## 9. Spring & JPA 기반 테스트

### 9.1 레이어드 아키텍처와 테스트

```
┌──────────────────────┐
│  Presentation Layer  │  ← 외부 요청 수신, 파라미터 유효성 검증
├──────────────────────┤
│   Business Layer     │  ← 비즈니스 로직, 트랜잭션 보장
├──────────────────────┤
│  Persistence Layer   │  ← 데이터 읽기/쓰기
└──────────────────────┘
```

- **관심사의 분리** → 테스트하고자 하는 영역에 **집중** 가능
- **핵심 질문**: 무엇을 테스트할 것인지? 어떻게 테스트할 것인지?

### 9.2 통합 테스트 (Integration Test)

- `OrderService`가 `ProductRepository`에서 상품을 조회하고, `StockRepository`에서 재고를 차감하고,
`OrderRepository`에 주문을 저장하는 **여러 모듈이 협력하는 흐름 전체**를 검증하는 테스트
- 각 클래스의 단위 테스트가 모두 통과하더라도, **실제로 조합했을 때** 트랜잭션이 안 걸려있거나
쿼리가 의도와 다르게 동작하는 문제를 잡으려면 통합 테스트가 필요하다.

### 9.3 Persistence Layer 테스트

```java
@SpringBootTest  // @DataJpaTest보다 권장 (환경 통합을 위해)
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> products = productRepository.findAllBySellingStatusIn(
            List.of(SELLING, HOLD)
        );

        // then
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", SELLING),
                tuple("002", "카페라떼", HOLD)
            );
    }
}
```

> **Query Method도 테스트를 작성해야 하는 이유:**
1. 작성한 코드가 의도한 쿼리를 날리는지 보장
2. 미래에 MyBatis 등 다른 기술로 변경될 수 있으므로 보장 필요
> 

### @DataJpaTest vs @SpringBootTest

| 구분 | @DataJpaTest | @SpringBootTest |
| --- | --- | --- |
| 범위 | JPA 관련 Bean만 | 전체 Bean |
| 속도 | 빠름 | 느림 |
| 트랜잭션 | 자동 롤백 (@Transactional 포함) | 수동 롤백 필요 |
| **권장** | — | ✅ (서버 띄우는 횟수를 줄이기 위해) |

### 9.4 Business Layer 테스트

- **비즈니스 로직**을 구현하는 역할
- Persistence Layer와의 상호작용으로 비즈니스 로직을 전개
- **트랜잭션**을 보장해야 한다

```java
@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @AfterEach
    void tearDown() {
        // 수동 클렌징 — FK 순서에 주의
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
    }

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.of(2023, 3, 5, 10, 0);
        // ... 상품 생성 및 저장

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 12500);
    }
}
```

### @Transactional 사용 시 주의

- 테스트에 `@Transactional`을 달면 테스트 종료 시 자동으로 DB가 롤백되어 편리하지만:
    - **함정**: 테스트 클래스에 `@Transactional`이 붙으면, 테스트 자체가 하나의 트랜잭션으로 감싸진다.
    이 때문에 **서비스 코드에 `@Transactional`을 깜빡해도 테스트가 통과**해버린다.
    배포 후에야 “왜 DB에 반영이 안 되지?”라는 문제를 발견하게 되는 위험이 있다.
    - 팀원들이 이 메커니즘을 이해하지 못하면 혼란이 생기므로, 도입 전에 팀 차원에서 **합의**가 필요하다.
- **대안**: `@AfterEach`에서 `deleteAllInBatch()`로 직접 데이터를 정리하는 수동 클렌징

### @Transactional(readOnly = true)와 CQRS

- 조회 전용 메서드에 `@Transactional(readOnly = true)` 적용
- JPA 성능 최적화: 엔티티의 변경 여부를 추적하기 위한 **스냅샷 저장과 변경감지(Dirty Checking)를 생략**하므로 메모리와 CPU를 절약
- **CQRS (Command Query Responsibility Segregation)**:
데이터를 변경하는 로직(Command)과 조회하는 로직(Query)을 서비스 계층에서 분리하는 패턴.
예를 들면, `ProductService`(CUD)와 `ProductReadService`(Read)를 나누는 것.
조회에서 장애가 나도 주문(커맨드)에는 영향이 없으므로 **장애 범위를 격리**할 수 있다.
- DB 엔드포인트를 Master(쓰기) / Slave(읽기)로 분리하면 `readOnly = true`가 자동으로 Slave DB로 라우팅된다.

### 9.5 Presentation Layer 테스트

- 외부 요청을 가장 먼저 받는 계층
- **파라미터에 대한 최소한의 검증** 수행

```java
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @DisplayName("신규 상품을 등록한다.")
    @Test
    void createProduct() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        // when // then
        mockMvc.perform(
                post("/api/v1/products/new")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("신규 상품을 등록할 때 상품 타입은 필수값이다.")
    @Test
    void createProductWithoutType() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                // type 누락
                .sellingStatus(SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        // when // then
        mockMvc.perform(
                post("/api/v1/products/new")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."));
    }
}
```

### Bean Validation 어노테이션 비교

| 어노테이션 | null | “” (빈문자열) | ” ” (공백) |
| --- | --- | --- | --- |
| `@NotNull` | ❌ | ✅ | ✅ |
| `@NotEmpty` | ❌ | ❌ | ✅ |
| `@NotBlank` | ❌ | ❌ | ❌ |

> **컨트롤러에서는 최소한의 검증만** 수행하고, 비즈니스 규칙(예: 20자 제한)은 **서비스 레이어에서 검증**하라.
> 

### Controller ↔︎ Service DTO 분리

```
Controller  →  ProductCreateRequest (+ @NotNull, @NotBlank 등 Bean Validation)
                      ↓ toServiceRequest()
Service     →  ProductCreateServiceRequest (Validation 없음, 순수 데이터)
```

- **왜 분리하는가?**
서비스 계층이 컨트롤러의 요청 DTO를 그대로 사용하면, 서비스가 컨트롤러에 **의존**하게 된다.
나중에 키오스크 주문 외에 바코드 주문이 추가되면, 새로운 컨트롤러가 동일한 서비스를 호출해야 하는데
기존 컨트롤러의 Request DTO에 묶여 있으면 확장이 어려워진다.
- **분리하면?**
각 컨트롤러가 자신만의 Request DTO를 가지고, `toServiceRequest()`로 변환해서 서비스에 전달한다.
서비스는 어떤 경로로 요청이 왔는지 몰라도 되므로, **확장 시 영향이 최소화**된다.

---

## 10. Mock을 마주하는 자세

### 10.1 Test Double

> 영화에서 위험한 장면을 대신 수행하는 대역 배우를 “Stunt Double”이라 하듯,
테스트에서 실제 객체 대신 사용하는 가짜 객체를 **Test Double**이라 부른다. (마틴 파울러 정의)
> 

| 종류 | 설명 |
| --- | --- |
| **Dummy** | 아무것도 하지 않는 깡통 객체 |
| **Fake** | 단순한 형태로 동일 기능 수행, 프로덕션에선 부적합 (ex. FakeRepository) |
| **Stub** | 요청에 대해 미리 준비한 결과를 제공, 그 외에는 응답 X |
| **Spy** | Stub이면서 호출 내역을 기록. 일부는 실제처럼, 일부만 Stubbing 가능 |
| **Mock** | 행위에 대한 기대를 명세하고, 그에 따라 동작하도록 만든 객체 |

### Stub vs Mock 핵심 차이

- **Stub** → **상태 검증** (State Verification): “메일을 보내면 true를 반환해라”처럼 **결과값에만 집중**
- **Mock** → **행위 검증** (Behavior Verification): “메일 전송 메서드가 정확히 1번 호출되었는가?”처럼 **호출 여부·횟수에 집중**

### 10.2 Mockito로 Stubbing하기

```java
// Mockito 스타일
Mockito.when(mailSendClient.sendEmail(
    any(String.class), any(String.class), any(String.class), any(String.class)
)).thenReturn(true);

// BDDMockito 스타일 (given절에 when이 오는 어색함 해결)
BDDMockito.given(mailSendClient.sendEmail(
    anyString(), anyString(), anyString(), anyString()
)).willReturn(true);
```

### 10.3 @Mock, @Spy, @InjectMocks

```java
@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        BDDMockito.given(mailSendClient.sendEmail(
            anyString(), anyString(), anyString(), anyString()
        )).willReturn(true);

        // when
        boolean result = mailService.sendMail("from", "to", "subject", "content");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}
```

| 어노테이션 | 용도 |
| --- | --- |
| `@Mock` | 가짜 객체 생성 |
| `@Spy` | 실제 객체 기반, 일부만 Stubbing |
| `@InjectMocks` | Mock/Spy를 주입받는 대상 |
| `@MockitoBean` | Spring 컨테이너 환경에서 Mock 주입 |

> **@Spy 사용 시 Stubbing 문법이 다르다:**`doReturn(true).when(spyObject).method()`
> 

### 10.4 Classicist vs Mockist

| 관점 | 주장 | 예시 |
| --- | --- | --- |
| **Classicist** | 가능하면 실제 객체·실제 DB로 테스트, Mocking은 정말 필요한 곳만 | Service 테스트 시 실제 Repository를 사용 |
| **Mockist** | 테스트 대상 외의 모든 의존성을 Mock으로 격리 | Service 테스트 시 Repository도 Mock |

> **실무 권장**: 외부 시스템(메일 발송, PG 결제 등 **내가 제어할 수 없는 것**)에만 Mocking 적용.
내부 모듈까지 Mocking하면 “내가 Stubbing한 행위가 실제 런타임과 정말 같은가?”를 보장할 수 없고,
프로덕션에서 실제 모듈을 조합했을 때 발생하는 문제를 테스트에서 잡지 못하는 위험이 있다.
> 

> **메일 전송 같은 긴 네트워크 호출 로직에는 `@Transactional`을 붙이지 않는 것이 좋다.**
DB 커넥션을 계속 점유하게 되기 때문이다.
> 

---

## 11. 더 나은 테스트를 위한 구체적 조언

### 11.1 한 문단에 한 주제

- 테스트 안에 `for`문이나 `if`문이 있다면, 하나의 테스트가 **여러 조건을 동시에 검증**하고 있다는 신호다.
→ 테스트가 실패했을 때 “정확히 어떤 케이스가 실패한 건지” 파악하기 어려워진다.
- 여러 입력값으로 같은 로직을 검증하고 싶다면 → **@ParameterizedTest**로 케이스를 분리하라.

> **자가 검증 기준**: “이 테스트의 `@DisplayName`을 한 문장으로 자연스럽게 쓸 수 있는가?”
한 문장으로 표현하기 어렵다면 테스트를 분리해야 할 시점이다.
> 

### 11.2 완벽하게 제어하기

- 현재 시간, 랜덤 값, 외부 시스템 → **고정된 값이나 Mocking으로 제어**
- 제어할 수 없는 변수를 **처음부터 사용하지 말자**

### 11.3 테스트 환경의 독립성 보장 (단일 테스트 내)

```java
// ❌ BAD — given절에서 다른 행위(deductQuantity)를 호출
Stock stock = Stock.create("001", 2);
stock.deductQuantity(1);  // ← 이 메서드에 버그가 있으면, 테스트 준비 단계에서 실패
                          //    实際 검증하려는 when절과 무관한 이유로 테스트가 깨짐

// ✅ GOOD — 테스트하려는 상태를 생성자로 직접 만든다
Stock stock = Stock.create("001", 1);  // "재고가 1인 상태"를 직접 생성
```

> **given절은 최대한 순수한 생성자·빌더로 구성하라.**
given절에서 다른 비즈니스 메서드를 호출하면, 그 메서드의 버그로 인해 **엉뚱한 곳에서 테스트가 실패**할 수 있다.
팩토리 메서드(`Stock.createWithDeduction()` 등)도 내부에 로직이 포함되어 있으므로 given절에서는 지양한다.
> 

### 11.4 테스트 간 독립성 보장 (다중 테스트)

- **전역 변수를 공유하지 마라** — 테스트 간 순서에 따라 결과가 달라질 수 있다
- 각 테스트는 독립적으로 실행 가능해야 한다

### 11.5 Test Fixture 구성 원칙

### Fixture란?

> **Fixture**(고정물) = 테스트의 given절에서 만드는 모든 준비 데이터.
예: `Product`, `Order`, `Stock` 등의 테스트용 객체, DB에 미리 넣어둔 데이터 등.
테스트마다 **동일한 초기 상태**를 보장하기 위해 고정시켜 놓는다는 의미에서 Fixture라 부른다.
> 

### @BeforeEach 사용 시 주의점

- `setUp()`에 공동 Fixture를 두는 것은 **전역 변수를 선언하는 것과 같은 효과**
- 매번 `setUp()`을 확인해야 해서 **가독성 저하**
- **사용 기준**: 각 테스트가 setUp()을 몰라도 **이해에 문제가 없는가?**, 수정해도 **모든 테스트에 영향이 없는가?**

### data.sql 같은 외부 데이터의 문제

- 테스트 코드를 읽는 사람이 given절에 아무것도 없으면 “이 테스트의 데이터는 어디서 오는 거지?”라고 혼란을 겪게 된다.
데이터가 `data.sql`이라는 **별도 파일에 흩어져(파편화)** 있기 때문이다.
- 테스트 코드만 읽어도 무엇을 검증하는지 알 수 있어야 하는 **“문서로서의 테스트”** 가치가 사라진다.

### createProduct 같은 헬퍼 메서드

- 빌더 코드를 반복 작성하지 않도록 `createProduct(String name, int price)` 같은 **헬퍼 메서드**를 만들되,
테스트에서 중요한 파라미터만 매개변수로 받고, 나머지는 기본값으로 처리하라.
- 여러 테스트 클래스에서 공유하려고 **추상 클래스를 만들어 상속하는 것은 지양**한다.
테스트 클래스 간의 결합도가 높아져서, 공통 메서드를 수정하면 수많은 테스트에 영향을 줄 수 있다.

### 11.6 Test Fixture 클렌징

### deleteAll() vs deleteAllInBatch()

| 메서드 | 동작 | 성능 |
| --- | --- | --- |
| `deleteAll()` | SELECT → 건건이 DELETE | 느림 (테스트 비용 증가) |
| `deleteAllInBatch()` | DELETE 쿼리 한 방 | 빠름 ✅ |

> **FK가 있는 경우 중간 테이블(조인 테이블)을 먼저 삭제**해야 한다.
> 

```java
@AfterEach
void tearDown() {
    orderProductRepository.deleteAllInBatch();  // 중간 테이블 먼저
    orderRepository.deleteAllInBatch();
    productRepository.deleteAllInBatch();
    stockRepository.deleteAllInBatch();
}
```

### 11.7 @ParameterizedTest

여러 값으로 동일 로직을 검증할 때 사용:

```java
// @CsvSource 활용
@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
@CsvSource({"HANDMADE,false", "BOTTLE,true", "BAKERY,true"})
@ParameterizedTest
void containsStockType(ProductType productType, boolean expected) {
    boolean result = ProductType.containsStockType(productType);
    assertThat(result).isEqualTo(expected);
}

// @MethodSource 활용 — 복잡한 객체가 필요할 때
private static Stream<Arguments> provideProductTypesForCheckingStockType() {
    return Stream.of(
        Arguments.of(ProductType.HANDMADE, false),
        Arguments.of(ProductType.BOTTLE, true),
        Arguments.of(ProductType.BAKERY, true)
    );
}

@DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
@MethodSource("provideProductTypesForCheckingStockType")
@ParameterizedTest
void containsStockType(ProductType productType, boolean expected) {
    boolean result = ProductType.containsStockType(productType);
    assertThat(result).isEqualTo(expected);
}
```

기타 소스: `@ValueSource`, `@NullSource`, `@EmptySource`, `@NullAndEmptySource`, `@EnumSource`

### 11.8 @DynamicTest

**시나리오 기반** 테스트 — 하나의 환경에서 상태를 변화시키며 단계별 검증:

```java
@DisplayName("재고 차감 시나리오")
@TestFactory
Collection<DynamicTest> stockDeductionDynamicTest() {
    // given — 공유 환경
    Stock stock = Stock.create("001", 1);

    return List.of(
        dynamicTest("재고를 주어진 개수만큼 차감할 수 있다.", () -> {
            // given
            int quantity = 1;
            // when
            stock.deductQuantity(quantity);
            // then
            assertThat(stock.getQuantity()).isZero();
        }),
        dynamicTest("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.", () -> {
            // given
            int quantity = 1;
            // when // then
            assertThatThrownBy(() -> stock.deductQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 재고 수량이 없습니다.");
        })
    );
}
```

### 11.9 테스트 수행 환경 통합하기

> Spring Boot 테스트는 실행 시 **실제 서버(ApplicationContext)를 띄운다.**
테스트 클래스마다 환경 설정(`@ActiveProfiles`, `@MockitoBean` 등)이 다르면 서버를 **매번 새로 시작**하게 되는데,
서버 한 번 띄우는 데 수 초~십수 초가 걸리므로, 횟수가 많아지면 **전체 테스트 시간이 급증**한다.
> 

### 통합 테스트 환경 통합

```java
// IntegrationTestSupport — 공통 상위 클래스
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestSupport {

    @MockitoBean
    protected MailSendClient mailSendClient;  // 공통 Mock이 필요하면 여기에 선언
}

// 각 테스트 클래스에서 상속
class OrderServiceTest extends IntegrationTestSupport { ... }
class OrderStatisticsServiceTest extends IntegrationTestSupport { ... }
```

### Controller 테스트 환경 통합

```java
// ControllerTestSupport — @WebMvcTest 전용
@WebMvcTest(controllers = {
    ProductController.class,
    OrderController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockitoBean
    protected ProductService productService;

    @MockitoBean
    protected OrderService orderService;
}
```

> 이렇게 하면 **Spring Boot 서버를 최소 2번**(통합 / 컨트롤러)만 띄워서 테스트 시간을 크게 줄일 수 있다.
> 

### 11.10 private 메서드의 테스트

> **결론: 할 필요가 없다. 하려고 해서도 안 된다.**
> 
- private 메서드는 public 메서드의 내부 구현이므로, **public 메서드를 테스트하면 private 메서드도 자연스럽게 검증**된다.
- 예를 들어, `OrderService.createOrder()`를 테스트하면 그 안에서 호출되는 `calculateTotalPrice()`(private)도 함께 검증되는 것이다.
- 만약 private 메서드의 로직이 너무 복잡해서 **별도로 테스트해야 할 것 같다면?**
→ 그 로직을 **별도 클래스로 분리하라는 신호**다. 분리하면 public 메서드가 되므로 자연스럽게 테스트할 수 있다.

### 11.11 테스트에서만 필요한 메서드

> **결론: 만들어도 된다. 하지만 보수적으로 접근하라.**
> 
- 예를 들어, `ProductCreateRequest`의 Builder를 프로덕션 코드에서는 쓰지 않지만 테스트 편의를 위해 만드는 경우:
getter, 기본 생성자, Builder 등 **객체가 마땅히 가져도 자연스러운 메서드**이면서
미래에 프로덕션 코드에서도 충분히 **사용될 가능성이 있는 메서드**라면 만들어도 괜찮다.
- 반대로, 테스트만을 위해 내부 구현을 노출하는 메서드(예: `setInternalState()`)를 만드는 것은 지양한다.

---

## 12. Spring REST Docs

### REST Docs vs Swagger

| 구분 | Spring REST Docs | Swagger |
| --- | --- | --- |
| **신뢰도** | 테스트 통과 시에만 문서 생성 ✅ | 테스트와 무관 |
| **침투성** | 프로덕션 코드에 비침투적 ✅ | 프로덕션 코드에 어노테이션 필요 |
| **편의성** | 설정 복잡, 코드량 많음 | 적용 쉬움, API 바로 호출 가능 |

> 팀 상황에 맞게 선택. **REST Docs가 테스트 기반이라 신뢰도가 더 높다.**
> 

### 핵심 설정 (build.gradle)

```groovy
plugins {
    id 'org.asciidoctor.jvm.convert' version '3.3.2'
}

configurations {
    asciidoctorExt
}

dependencies {
    asciidoctorExt 'org.springframework.restdocs:spring-restdocs-asciidoctor'
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
}

ext {
    snippetsDir = file('build/generated-snippets')
}

test {
    outputs.dir snippetsDir
}

asciidoctor {
    inputs.dir snippetsDir
    configurations 'asciidoctorExt'
    sources { include("**/index.adoc") }
    baseDirFollowsSourceFile()
    dependsOn test
}

bootJar {
    dependsOn asciidoctor
    from("${asciidoctor.outputDir}") { into 'static/docs' }
}
```

### RestDocsSupport (스프링 서버 없이 문서 생성)

```java
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
                .apply(documentationConfiguration(provider))
                .build();
    }

    protected abstract Object initController();
}
```

> **standaloneSetup**을 사용하면 Spring 서버를 띄우지 않고 문서를 생성할 수 있다.
> 

### 실무 권장 워크플로우

1. Controller까지 빠르게 구현
2. DocsTest 작성 (+ Bean Validation)
3. **문서를 보면서 개발** 진행
4. 배포 후 `localhost:8080/docs/index.html`에서 API 명세 확인

---

## 13. 테스트를 작성하는 마음가짐

### 요약 체크리스트

| 섹션 | 핵심 포인트 |
| --- | --- |
| 테스트의 필요성 | 빠른 피드백, 자동화, 안정감 |
| 단위 테스트 | 테스트하기 어려운 영역 분리 |
| TDD | 빠른 피드백, 엣지 케이스 발견, 과감한 리팩토링 |
| 테스트는 문서 | 팀 자산, BDD, 언어가 사고를 제한 |
| Spring & JPA | 레이어드 아키텍처, 통합 테스트, 환경 통합 |
| Mock | Test Double, Classicist 관점, 외부만 Mocking |
| 구체적 조언 | 한 주제, 논리구조 제거, 독립성, Fixture 관리 |

### 가장 중요한 것

> 기술을 사용하고 방법론이 발전해나가는 데는 그 **이유**가 더 중요하다.
> 
> 
> 테스트 도구에 집중하기보단 테스트를 **왜** 해야 하는가가 훨씬 중요하다.
> 
> 테스트 작성을 방해하는 것은 **시간**이다.
> 
> 테스트 케이스를 추론하고 구체화해 보는 **연습이 많이 필요**하다.
> 

> **🔥 타협하지 않는 마음**
> 
> 
> **가까이 보면 느리지만, 멀리 보면 가장 빠르다.**
> 

---

## 14. 부록: 추가 학습 키워드

### 테스트 기초

- Lombok 사용 가이드
    - `@Data`, `@Setter`, `@AllArgsConstructor` 지양
    - 양방향 연관관계 시 `@ToString` 순환 참조 주의
- 해피 케이스 / 예외 케이스 / 경계값 테스트
- 순수 함수 (Pure Function)

### TDD & 방법론

- 애자일(Agile) vs 폭포수(Waterfall) 방법론
- 익스트림 프로그래밍 (XP) — TDD는 XP의 실천 방법 중 하나
- 스크럼(Scrum), 칸반(Kanban)

### 아키텍처 & 패턴

- Hexagonal Architecture — 레이어드 아키텍처의 단점 극복
- QueryDSL — 타입 안전, 동적 쿼리
- CQRS (Command Query Responsibility Segregation)
- Optimistic Lock / Pessimistic Lock — 동시성 제어

### 테스트 도구

- JUnit 5 vs Spock (BDD 프레임워크)
- REST Assured — 인수 테스트
- Testcontainers — 실제 DB로 통합 테스트
- ArchUnit — 아키텍처 규칙 검증 테스트
- JaCoCo — 코드 커버리지 측정

---

> 📝 이 문서는 [Practical Testing: 실용적인 테스트 가이드](https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C) 강의 내용을 기반으로 정리·보완한 팀 온보딩 가이드입니다.
>