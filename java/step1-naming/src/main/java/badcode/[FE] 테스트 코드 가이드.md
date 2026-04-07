> **테스트 코드를 잘 작성하기 위한 종합 가이드**
> 
> 
> TypeScript 기반 React 프로젝트에서 실전 테스트 코드를 작성하기 위한 원칙, 기법, 구체적 조언을 정리한 문서입니다.
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
9. [컴포넌트 테스트](about:blank#9-%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8)
10. [커스텀 훅 테스트](about:blank#10-%EC%BB%A4%EC%8A%A4%ED%85%80-%ED%9B%85-%ED%85%8C%EC%8A%A4%ED%8A%B8)
11. [API 통합 테스트 (MSW)](about:blank#11-api-%ED%86%B5%ED%95%A9-%ED%85%8C%EC%8A%A4%ED%8A%B8-msw)
12. [Mock을 마주하는 자세](about:blank#12-mock%EC%9D%84-%EB%A7%88%EC%A3%BC%ED%95%98%EB%8A%94-%EC%9E%90%EC%84%B8)
13. [더 나은 테스트를 위한 구체적 조언](about:blank#13-%EB%8D%94-%EB%82%98%EC%9D%80-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A5%BC-%EC%9C%84%ED%95%9C-%EA%B5%AC%EC%B2%B4%EC%A0%81-%EC%A1%B0%EC%96%B8)
14. [E2E 테스트](about:blank#14-e2e-%ED%85%8C%EC%8A%A4%ED%8A%B8)
15. [테스트를 작성하는 마음가짐](about:blank#15-%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A5%BC-%EC%9E%91%EC%84%B1%ED%95%98%EB%8A%94-%EB%A7%88%EC%9D%8C%EA%B0%80%EC%A7%90)
16. [부록: 추가 학습 키워드](about:blank#16-%EB%B6%80%EB%A1%9D-%EC%B6%94%EA%B0%80-%ED%95%99%EC%8A%B5-%ED%82%A4%EC%9B%8C%EB%93%9C)

---

## 1. 테스트는 왜 필요한가?

### 수동 테스트의 한계

- 기능이 추가될수록 **기존 기능과 새 기능이 서로 영향을 주는 영역**이 증가한다.
예를 들어, 장바구니 컴포넌트를 수정했더니 주문 페이지나 결제 흐름에까지 영향이 갈 수 있다.
이런 영역을 매번 브라우저에서 직접 클릭하며 확인하는 것은 현실적으로 불가능하다.
- 브라우저에서 직접 클릭하고 값을 입력하는 수동 테스트는 **비용과 시간**이 기하급수적으로 늘어난다.
- React 앱은 **상태, 라우팅, 비동기 로직**이 복잡하게 얽혀 있어 수동 검증의 누락이 빈번하다.

### 테스트를 통해 얻고자 하는 것

| 목표 | 설명 |
| --- | --- |
| **빠른 피드백** | 코드 변경 직후 문제를 즉시 발견 |
| **자동화** | 반복 가능한 검증을 기계에 위임 |
| **안정감** | 리팩토링과 기능 추가 시 기존 기능의 정상 동작을 보장 |

### 테스트 코드를 작성하지 않으면?

1. 컴포넌트 하나를 수정할 때마다 **관련된 모든 화면을 직접 클릭**해봐야 한다.
2. Props나 상태 구조가 변경될 때 **어디가 깨지는지 알 수 없다.**
3. 빠르게 변화하는 프론트엔드 프로젝트의 **안정성을 보장할 수 없다.**

### 테스트 코드가 병목이 되면?

> 테스트 코드도 코드이므로, **잘못 작성하면 오히려 발목을 잡는다.**
> 
1. 테스트가 불안정하거나 자주 깨지면 “테스트 깨져도 무시”하는 문화가 생겨, 프로덕션 코드의 안정성을 유지하기 **힘들어진다.**
2. 프로덕션 코드를 조금만 바꿔도 수십 개의 테스트를 수정해야 하면, 테스트 코드 자체가 유지보수하기 어려운, **새로운 짐**이 된다.
3. 구현 세부사항(state 이름, CSS 클래스 등)에 의존하는 **잘못된 검증**이 리팩토링을 방해한다.
코드 구조는 올바르게 바꿨는데 테스트가 깨지면 “그냥 테스트 삭제하자”라는 유혹에 빠진다.

### 올바른 테스트 코드란?

1. 자동화 테스트로 **코드 변경 직후 몇 초 만에 버그를 발견**하고, 매번 손으로 확인하는 비용을 크게 절약한다.
2. 기존 기능이 깨지지 않는다는 보장이 있으므로, **새로운 기능을 과감하게 추가하거나 리팩토링할 수 있다.**
3. 한 팀원이 “이 경우에는 어떻게 동작해야 하지?”를 고민한 결과가 테스트 코드로 남으면,
다른 팀원은 그 테스트를 읽는 것만으로 동일한 맥락을 공유할 수 있다. — **개인의 지식이 팀의 자산**이 된다.
4. **가까이 보면 느리지만, 멀리 보면 가장 빠르다.**

---

## 2. 테스트 피라미드

```
        ╱  E2E  ╲          ← Playwright / Cypress
       ╱──────────╲
      ╱ Integration ╲      ← Component + MSW
     ╱────────────────╲
    ╱    Unit Tests     ╲   ← 유틸 / 훅 / 순수 함수
   ╱────────────────────╲
```

| 구분 | 범위 | 속도 | 도구 | 권장 비율 |
| --- | --- | --- | --- | --- |
| **Unit** | 유틸 함수, 커스텀 훅, 리듀서 | 매우 빠름 | Vitest / Jest | 가장 많이 |
| **Integration** | 컴포넌트 + 훅 + API 연동 | 보통 | Testing Library + MSW | 적절히 |
| **E2E** | 전체 사용자 흐름 | 느림 | Playwright / Cypress | 최소한 |

> **풍부한 단위 테스트 + 사용자 관점의 통합 테스트** 두 가지 관점으로 접근하라.
> 

### React 테스트 도구 생태계

| 도구 | 역할 |
| --- | --- |
| **Vitest** | 빠른 테스트 러너 (Vite 기반, Jest 호환 API) |
| **Jest** | 가장 널리 쓰이는 테스트 러너 |
| **React Testing Library (RTL)** | 사용자 관점의 컴포넌트 테스트 |
| **MSW (Mock Service Worker)** | 네트워크 레벨 API 모킹 |
| **Playwright / Cypress** | E2E 브라우저 테스트 |
| **@testing-library/react-hooks** | 커스텀 훅 단독 테스트 (RTL에 통합됨) |

---

## 3. 단위 테스트

### 정의

- **작은** 코드 단위(유틸 함수, 커스텀 훅, 리듀서)를 API 호출이나 DOM 렌더링 없이 **독립적**으로 검증하는 테스트
- 외부 의존이 없으므로 **밀리초 단위로 빠르게** 실행되며, 환경에 따라 결과가 달라지지 않아 **안정적**이다.

### 유틸 함수 테스트

```tsx
// utils/formatPrice.ts
export function formatPrice(price: number): string {
  return new Intl.NumberFormat('ko-KR').format(price) + '원';
}

// utils/formatPrice.test.ts
import { formatPrice } from './formatPrice';

describe('formatPrice', () => {
  it('숫자를 한국 원화 형식으로 변환한다', () => {
    expect(formatPrice(1000)).toBe('1,000원');
    expect(formatPrice(12500)).toBe('12,500원');
  });

  it('0원을 올바르게 표시한다', () => {
    expect(formatPrice(0)).toBe('0원');
  });
});
```

### 리듀서 테스트

```tsx
// store/cartReducer.ts
interface CartState {
  items: CartItem[];
}

type CartAction =
  | { type: 'ADD_ITEM'; payload: CartItem }
  | { type: 'REMOVE_ITEM'; payload: string }
  | { type: 'CLEAR' };

export function cartReducer(state: CartState, action: CartAction): CartState {
  switch (action.type) {
    case 'ADD_ITEM':
      return { ...state, items: [...state.items, action.payload] };
    case 'REMOVE_ITEM':
      return { ...state, items: state.items.filter(item => item.id !== action.payload) };
    case 'CLEAR':
      return { ...state, items: [] };
  }
}

// store/cartReducer.test.ts
describe('cartReducer', () => {
  const initialState: CartState = { items: [] };

  it('아이템을 추가하면 목록에 담긴다', () => {
    const item: CartItem = { id: '1', name: '아메리카노', price: 4000, quantity: 1 };
    const result = cartReducer(initialState, { type: 'ADD_ITEM', payload: item });

    expect(result.items).toHaveLength(1);
    expect(result.items[0]).toEqual(item);
  });

  it('전체 삭제하면 목록이 비워진다', () => {
    const state: CartState = {
      items: [{ id: '1', name: '아메리카노', price: 4000, quantity: 1 }],
    };
    const result = cartReducer(state, { type: 'CLEAR' });

    expect(result.items).toHaveLength(0);
  });
});
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
- **구간**: 시작점, 끝점 (페이지네이션 첫 페이지 / 마지막 페이지)
- **문자열**: 빈 문자열, 공백만 있는 문자열, 최대 길이

```tsx
// 경계값 테스트 예시: 음료는 1잔 이상 주문해야 함
describe('validateOrderQuantity', () => {
  it('0개를 주문하면 유효하지 않다', () => {
    expect(validateOrderQuantity(0)).toBe(false);
  });

  it('1개를 주문하면 유효하다', () => {
    expect(validateOrderQuantity(1)).toBe(true);
  });

  it('음수를 주문하면 유효하지 않다', () => {
    expect(validateOrderQuantity(-1)).toBe(false);
  });
});
```

---

## 5. 테스트하기 어려운 영역 분리하기

### 테스트하기 어려운 영역

1. **관측할 때마다 다른 값에 의존하는 코드**
    - 현재 날짜/시간, `Math.random()`, `window.innerWidth`
2. **외부 세계에 영향을 주는 코드**
    - API 호출, `localStorage`, `console.log`, `window.location`

### 핵심 원칙: 외부로 분리하라

```tsx
// ❌ BAD — 테스트하기 어려움
function createGreeting(): string {
  const hour = new Date().getHours();
  if (hour < 12) return '좋은 아침입니다';
  if (hour < 18) return '좋은 오후입니다';
  return '좋은 저녁입니다';
}

// ✅ GOOD — 시간을 매개변수로 분리
function createGreeting(currentHour: number): string {
  if (currentHour < 12) return '좋은 아침입니다';
  if (currentHour < 18) return '좋은 오후입니다';
  return '좋은 저녁입니다';
}

// 테스트 — 제어 가능
it('12시 이전에는 아침 인사를 반환한다', () => {
  expect(createGreeting(9)).toBe('좋은 아침입니다');
});
```

### React에서의 분리 패턴

```tsx
// ❌ BAD — 컴포넌트 내부에 현재 시간 의존
function OrderTimer() {
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    const hour = new Date().getHours();
    setIsOpen(hour >= 10 && hour < 22);
  }, []);

  return <div>{isOpen ? '주문 가능' : '영업시간이 아닙니다'}</div>;
}

// ✅ GOOD — 시간을 주입받도록 분리
function useShopStatus(getCurrentHour: () => number = () => new Date().getHours()) {
  const hour = getCurrentHour();
  return { isOpen: hour >= 10 && hour < 22 };
}

// 테스트 — 시간 제어
it('영업시간이면 isOpen이 true이다', () => {
  const { result } = renderHook(() => useShopStatus(() => 14));
  expect(result.current.isOpen).toBe(true);
});
```

### 순수 함수 (Pure Function)

- 같은 입력에는 항상 같은 결과
- 외부 세상과 **단절된 형태**
- 테스트하기 쉬운 코드

> **테스트하기 어려운 영역을 발견한다면, 외부로 분리하라.**
React에서는 **커스텀 훅**이나 **Props를 통한 의존성 주입**으로 분리할 수 있다.
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

### React에서의 TDD 실전 예시

**장바구니 총 금액 계산 기능을 TDD로 구현:**

```tsx
// 🔴 Red — 실패하는 테스트 먼저 작성
describe('calculateTotalPrice', () => {
  it('상품 리스트의 총 금액을 계산한다', () => {
    const items: CartItem[] = [
      { id: '1', name: '아메리카노', price: 4000, quantity: 2 },
      { id: '2', name: '카페라떼', price: 4500, quantity: 1 },
    ];

    expect(calculateTotalPrice(items)).toBe(12500);
  });
});

// 🟢 Green — 테스트 통과를 위한 최소한의 구현
export function calculateTotalPrice(items: CartItem[]): number {
  return items.reduce((sum, item) => sum + item.price * item.quantity, 0);
}

// 🔵 Refactor — 필요 시 개선 (이 경우 이미 깔끔)
```

### 선 테스트 작성, 후 기능 구현의 장점

1. 테스트를 먼저 작성하면 “이 함수를 어떻게 쓸 것인가?”를 먼저 고민하게 되어,
자연스럽게 **의존성이 적고, 역할이 명확한 코드**로 구현하게 된다.
2. 기능을 먼저 구현하면 “잘 되는 경우”만 생각하기 쉽지만, 테스트를 먼저 작성하면
“빈 배열이 오면?”, “0개 수량이면?” 같은 엣지 케이스를 **놓치지 않게** 해준다.
3. 코드를 작성할 때마다 테스트를 실행해서 **즉시 맞았는지 틀렸는지 확인**할 수 있다.
4. 기존 테스트가 안전망 역할을 하므로, 코드 구조를 **과감하게 개선**할 수 있다.

### TDD의 핵심 가치

> **TDD의 핵심 가치는 피드백이다.**
> 
> 
> TDD는 **관점의 변화**를 일으키는 도구다.
> 테스트를 먼저 작성한다는 것은 “이 함수·컴포넌트를 사용하는 쪽”의 입장에서 코드를 설계한다는 뜻이다.
> 예를 들어, `useCart()`의 테스트를 먼저 작성하면 “장바구니에 아이템을 추가하려면 어떤 인터페이스가 자연스러운가?”를
> **사용하는 쪽의 시각**에서 먼저 고민하게 된다.
> 

---

## 7. 테스트는 문서다

### 왜 문서인가?

1. 새 팀원이 “이 컴포넌트가 정확히 뭘 하는 거지?”라고 궁금할 때, 테스트 코드의 `it` 설명과 Given/When/Then을 읽으면 **프로덕션 기능의 동작을 빠르게 파악**할 수 있다.
2. 해피 케이스뿐 아니라 예외 케이스, 경계값 테스트까지 있으면 **“이런 경우에는 어떻게 동작하지?”라는 궁금증**이 테스트 코드만으로 해결된다.
3. 선배 개발자가 “장바구니가 비어있을 때 주문하면?”을 고민해서 작성한 테스트는,
후배 개발자가 같은 고민을 반복하지 않게 해준다. — **개인의 경험이 팀 전체의 자산**이 된다.

> **우리는 항상 팀으로 일한다.** 다른 팀원에게 어떻게 비칠지 고민하며 작성하자.
> 

### 테스트 이름을 섬세하게

| 원칙 | Bad Example | Good Example |
| --- | --- | --- |
| 명사 나열보다 **문장**으로 | 상품 추가 테스트 | 상품을 장바구니에 추가하면 목록에 담긴다 |
| 테스트 행위에 대한 **결과까지** 기술 | 상품을 추가할 수 있다 | 상품을 추가하면 수량이 1 증가한다 |
| **도메인 용어** 사용 | 버튼 클릭 시 실패 | 영업시간 외에는 주문을 생성할 수 없다 |
| 현상이 아닌 **정책** 관점 | ~하면 에러가 발생한다 | ~할 수 없다 |

```tsx
// ❌ 불명확한 테스트 이름
it('test add', () => { ... });
it('에러 테스트', () => { ... });

// ✅ 의도가 드러나는 테스트 이름
it('상품을 장바구니에 추가하면 총 금액이 갱신된다', () => { ... });
it('재고가 부족한 상품은 장바구니에 추가할 수 없다', () => { ... });
```

---

## 8. BDD 스타일로 작성하기

### BDD (Behavior Driven Development)

- TDD에서 파생된 개발 방법
- `calculateTotalPrice()` 같은 **함수 단위**가 아니라, “고객이 상품을 담고 주문하면 총 금액이 계산된다” 같은 **사용자 시나리오 단위**로 테스트를 작성
- 기획자나 QA 담당자가 읽어도 “이 테스트가 무엇을 검증하는지” 이해할 수 있을 정도의 **자연어에 가까운 표현** 권장

### Given / When / Then (Arrange / Act / Assert)

| 절 | 역할 | React에서의 대응 |
| --- | --- | --- |
| **Given (Arrange)** | 준비 | 컴포넌트 렌더링, 초기 상태 설정 |
| **When (Act)** | 행동 | 사용자 이벤트 발생 (click, type, submit) |
| **Then (Assert)** | 검증 | 화면에 기대하는 결과가 표시되는지 확인 |

```tsx
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

describe('CartPage', () => {
  it('상품을 추가하면 장바구니 목록에 표시된다', async () => {
    // given — 장바구니 페이지를 렌더링한다
    const user = userEvent.setup();
    render(<CartPage />);

    // when — 아메리카노 추가 버튼을 클릭한다
    const addButton = screen.getByRole('button', { name: '아메리카노 추가' });
    await user.click(addButton);

    // then — 장바구니에 아메리카노가 표시된다
    expect(screen.getByText('아메리카노')).toBeInTheDocument();
    expect(screen.getByText('4,000원')).toBeInTheDocument();
  });
});
```

> **언어가 사고를 제한한다.**
테스트 자체는 올바르게 동작하더라도, `it` 설명이 모호하면
나중에 그 테스트를 읽는 팀원이 “이게 대체 뭘 검증하는 거지?”라는 혼란에 빠진다.
혼란을 주는 테스트는 오히려 팀의 사고를 제한하는 장애물이 될 수 있다.
> 

---

## 9. 컴포넌트 테스트

### 9.1 React Testing Library의 철학

> **“The more your tests resemble the way your software is used,
the more confidence they can give you.”**
— Testing Library 공식 슬로건
> 
- **구현 세부사항**이 아닌 **사용자 행위**를 테스트한다
- DOM 노드를 state 이름이나 CSS 클래스로 찾지 않고, **사용자가 보는 것**으로 찾는다

### 9.2 쿼리 우선순위

| 우선순위 | 쿼리 | 사용 시점 |
| --- | --- | --- |
| 1 ⭐ | `getByRole` | 접근성 역할 (button, heading, textbox 등) |
| 2 | `getByLabelText` | form 요소 |
| 3 | `getByPlaceholderText` | placeholder만 있는 input |
| 4 | `getByText` | 화면에 보이는 텍스트 |
| 5 | `getByDisplayValue` | input의 현재 값 |
| 6 | `getByAltText` | 이미지 alt 텍스트 |
| 7 | `getByTestId` | 최후의 수단 ⚠️ |

```tsx
// ❌ 구현 세부사항에 의존 — 리팩토링 시 깨짐
const button = container.querySelector('.submit-btn');
const input = container.querySelector('#email-input');

// ✅ 사용자 관점 — 리팩토링에 강건
const button = screen.getByRole('button', { name: '주문하기' });
const input = screen.getByLabelText('이메일');
```

### 9.3 사용자 이벤트: userEvent vs fireEvent

```tsx
// ❌ fireEvent — 단순 이벤트 디스패치 (실제 사용자 행동과 다름)
fireEvent.click(button);
fireEvent.change(input, { target: { value: 'hello' } });

// ✅ userEvent — 실제 사용자 행동 시뮬레이션 (focus, keydown 등 포함)
const user = userEvent.setup();
await user.click(button);
await user.type(input, 'hello');
```

> `userEvent`는 실제 사용자의 행동과 동일한 이벤트 시퀀스를 발생시킨다.
> 

### 9.4 컴포넌트 테스트 실전 예시

```tsx
// components/Counter.tsx
interface CounterProps {
  initialCount?: number;
  onCountChange?: (count: number) => void;
}

export function Counter({ initialCount = 0, onCountChange }: CounterProps) {
  const [count, setCount] = useState(initialCount);

  const handleIncrement = () => {
    const newCount = count + 1;
    setCount(newCount);
    onCountChange?.(newCount);
  };

  const handleDecrement = () => {
    if (count <= 0) return;
    const newCount = count - 1;
    setCount(newCount);
    onCountChange?.(newCount);
  };

  return (
    <div>
      <button onClick={handleDecrement} aria-label="감소">-</button>
      <span role="status">{count}</span>
      <button onClick={handleIncrement} aria-label="증가">+</button>
    </div>
  );
}
```

```tsx
// components/Counter.test.tsx
describe('Counter', () => {
  it('초기값이 0으로 렌더링된다', () => {
    // given & when
    render(<Counter />);

    // then
    expect(screen.getByRole('status')).toHaveTextContent('0');
  });

  it('증가 버튼을 클릭하면 카운트가 1 증가한다', async () => {
    // given
    const user = userEvent.setup();
    render(<Counter initialCount={5} />);

    // when
    await user.click(screen.getByRole('button', { name: '증가' }));

    // then
    expect(screen.getByRole('status')).toHaveTextContent('6');
  });

  it('카운트가 0이면 감소 버튼을 눌러도 0 이하로 내려가지 않는다', async () => {
    // given
    const user = userEvent.setup();
    render(<Counter initialCount={0} />);

    // when
    await user.click(screen.getByRole('button', { name: '감소' }));

    // then
    expect(screen.getByRole('status')).toHaveTextContent('0');
  });

  it('카운트가 변경되면 onCountChange 콜백이 호출된다', async () => {
    // given
    const user = userEvent.setup();
    const handleCountChange = vi.fn();
    render(<Counter onCountChange={handleCountChange} />);

    // when
    await user.click(screen.getByRole('button', { name: '증가' }));

    // then
    expect(handleCountChange).toHaveBeenCalledWith(1);
    expect(handleCountChange).toHaveBeenCalledTimes(1);
  });
});
```

### 9.5 비동기 컴포넌트 테스트

```tsx
describe('UserProfile', () => {
  it('로딩 중에는 스켈레톤이 표시된다', () => {
    render(<UserProfile userId="1" />);
    expect(screen.getByRole('progressbar')).toBeInTheDocument();
  });

  it('데이터 로딩이 완료되면 사용자 이름이 표시된다', async () => {
    render(<UserProfile userId="1" />);

    // waitFor — 비동기 상태 변화를 기다림
    expect(await screen.findByText('홍길동')).toBeInTheDocument();
  });

  it('에러가 발생하면 에러 메시지가 표시된다', async () => {
    // MSW로 에러 응답 설정 (11장 참고)
    server.use(
      http.get('/api/users/:id', () => HttpResponse.error())
    );

    render(<UserProfile userId="1" />);
    expect(await screen.findByText('데이터를 불러올 수 없습니다')).toBeInTheDocument();
  });
});
```

### 9.6 waitFor vs findBy

| 메서드 | 역할 | 용도 |
| --- | --- | --- |
| `findByText` | 요소가 나타날 때까지 자동 대기 | 비동기 렌더링 결과 확인 |
| `waitFor` | 콜백 내 assertion이 통과할 때까지 재시도 | 복잡한 비동기 검증 |
| `waitForElementToBeRemoved` | 요소가 사라질 때까지 대기 | 로딩 스피너 제거 확인 |

```tsx
// findBy = getBy + waitFor의 축약
expect(await screen.findByText('완료')).toBeInTheDocument();

// waitFor = 복잡한 비동기 assertion
await waitFor(() => {
  expect(screen.getByText('총 금액')).toHaveTextContent('12,500원');
  expect(screen.queryByRole('progressbar')).not.toBeInTheDocument();
});
```

---

## 10. 커스텀 훅 테스트

### renderHook 사용

```tsx
import { renderHook, act } from '@testing-library/react';

// hooks/useCounter.ts
export function useCounter(initialValue = 0) {
  const [count, setCount] = useState(initialValue);
  const increment = () => setCount((prev) => prev + 1);
  const decrement = () => setCount((prev) => Math.max(0, prev - 1));
  const reset = () => setCount(initialValue);
  return { count, increment, decrement, reset };
}

// hooks/useCounter.test.ts
describe('useCounter', () => {
  it('초기값으로 시작한다', () => {
    // given & when
    const { result } = renderHook(() => useCounter(10));

    // then
    expect(result.current.count).toBe(10);
  });

  it('increment를 호출하면 1 증가한다', () => {
    // given
    const { result } = renderHook(() => useCounter(0));

    // when
    act(() => {
      result.current.increment();
    });

    // then
    expect(result.current.count).toBe(1);
  });

  it('0에서 decrement를 호출하면 0 이하로 내려가지 않는다', () => {
    // given
    const { result } = renderHook(() => useCounter(0));

    // when
    act(() => {
      result.current.decrement();
    });

    // then
    expect(result.current.count).toBe(0);
  });
});
```

### Provider가 필요한 훅 테스트

```tsx
// React Query 등 Context가 필요한 경우
const wrapper = ({ children }: { children: ReactNode }) => (
  <QueryClientProvider client={new QueryClient({
    defaultOptions: { queries: { retry: false } }
  })}>
    {children}
  </QueryClientProvider>
);

it('사용자 데이터를 불러온다', async () => {
  const { result } = renderHook(() => useUser('1'), { wrapper });

  await waitFor(() => {
    expect(result.current.data?.name).toBe('홍길동');
  });
});
```

---

## 11. API 통합 테스트 (MSW)

### MSW (Mock Service Worker) 란?

- **네트워크 레벨**에서 API 요청을 가로채서 모킹
- 실제 `fetch` / `axios`가 동작하므로 **프로덕션 코드를 수정하지 않아도 됨**
- 테스트, 개발, 디버깅 모두에서 활용 가능

### MSW 설정

```tsx
// mocks/handlers.ts
import { http, HttpResponse } from 'msw';

export const handlers = [
  http.get('/api/products', () => {
    return HttpResponse.json([
      { id: '1', name: '아메리카노', price: 4000, status: 'SELLING' },
      { id: '2', name: '카페라떼', price: 4500, status: 'SELLING' },
    ]);
  }),

  http.post('/api/orders', async ({ request }) => {
    const body = await request.json();
    return HttpResponse.json({
      id: 'order-1',
      items: body.items,
      totalPrice: 12500,
      status: 'CREATED',
    }, { status: 201 });
  }),
];

// mocks/server.ts
import { setupServer } from 'msw/node';
import { handlers } from './handlers';

export const server = setupServer(...handlers);

// vitest.setup.ts (또는 jest.setup.ts)
beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());
```

### MSW를 활용한 통합 테스트

```tsx
describe('OrderPage', () => {
  it('상품 목록을 불러와서 표시한다', async () => {
    // given & when
    render(<OrderPage />);

    // then — API 응답이 화면에 렌더링됨
    expect(await screen.findByText('아메리카노')).toBeInTheDocument();
    expect(await screen.findByText('카페라떼')).toBeInTheDocument();
  });

  it('주문 버튼을 클릭하면 주문이 생성된다', async () => {
    // given
    const user = userEvent.setup();
    render(<OrderPage />);
    await screen.findByText('아메리카노'); // 상품 로딩 대기

    // when
    await user.click(screen.getByRole('button', { name: '주문하기' }));

    // then
    expect(await screen.findByText('주문이 완료되었습니다')).toBeInTheDocument();
  });

  it('API 에러 시 에러 메시지를 표시한다', async () => {
    // given — 특정 테스트에서만 에러 응답
    server.use(
      http.get('/api/products', () => {
        return new HttpResponse(null, { status: 500 });
      })
    );

    // when
    render(<OrderPage />);

    // then
    expect(await screen.findByText('상품을 불러올 수 없습니다')).toBeInTheDocument();
  });
});
```

> **MSW는 Java Spring 테스트에서의 @MockBean과 유사한 역할을 한다.**
차이점은 MSW가 **네트워크 레벨**에서 동작하므로 프로덕션 코드 변경이 필요없다는 것.
> 

---

## 12. Mock을 마주하는 자세

### 12.1 모킹의 종류

| 종류 | 도구 | 용도 |
| --- | --- | --- |
| **함수 Mock** | `vi.fn()` / `jest.fn()` | 콜백, 이벤트 핸들러 검증 |
| **모듈 Mock** | `vi.mock()` / `jest.mock()` | 외부 모듈 전체 교체 |
| **네트워크 Mock** | MSW | API 응답 제어 |
| **타이머 Mock** | `vi.useFakeTimers()` | setTimeout, setInterval 제어 |

### 12.2 함수 Mock 활용

```tsx
// 콜백 검증
it('폼 제출 시 onSubmit이 입력값과 함께 호출된다', async () => {
  // given
  const user = userEvent.setup();
  const handleSubmit = vi.fn();
  render(<LoginForm onSubmit={handleSubmit} />);

  // when
  await user.type(screen.getByLabelText('이메일'), 'test@test.com');
  await user.type(screen.getByLabelText('비밀번호'), 'password123');
  await user.click(screen.getByRole('button', { name: '로그인' }));

  // then
  expect(handleSubmit).toHaveBeenCalledWith({
    email: 'test@test.com',
    password: 'password123',
  });
  expect(handleSubmit).toHaveBeenCalledTimes(1);
});
```

### 12.3 모듈 Mock

```tsx
// 외부 라이브러리 전체 모킹
vi.mock('next/navigation', () => ({
  useRouter: () => ({
    push: vi.fn(),
    back: vi.fn(),
  }),
  useSearchParams: () => new URLSearchParams(),
}));

// 특정 함수만 모킹
vi.mock('@/utils/analytics', () => ({
  trackEvent: vi.fn(),
}));
```

### 12.4 타이머 Mock

```tsx
describe('Debounce', () => {
  beforeEach(() => {
    vi.useFakeTimers();
  });

  afterEach(() => {
    vi.useRealTimers();
  });

  it('300ms 후에 검색 API가 호출된다', async () => {
    // given
    const user = userEvent.setup({ advanceTimers: vi.advanceTimersByTime });
    render(<SearchInput />);

    // when
    await user.type(screen.getByRole('searchbox'), '아메리카노');
    vi.advanceTimersByTime(300);

    // then
    await waitFor(() => {
      expect(screen.getByText('검색 결과')).toBeInTheDocument();
    });
  });
});
```

### 12.5 Classicist vs Mockist — React에서의 적용

| 관점 | 주장 | React 적용 |
| --- | --- | --- |
| **Classicist** | 가능하면 실제로 렌더링하여 테스트, Mocking은 외부 API만 | 실제 컴포넌트 렌더링 + MSW로 API만 모킹 |
| **Mockist** | 테스트 대상 외 모든 의존성을 Mock으로 격리 | 모든 의존성을 vi.mock()으로 교체 |

> **실무 권장**: 외부 API는 MSW로 모킹하되, **컴포넌트 간의 통합은 가능한 실제로 테스트**하라.
하위 컴포넌트까지 모두 vi.mock()으로 교체하면, 실제 조합에서 발생하는 문제를 잡지 못하고
리팩토링 시 테스트가 깨지는 **취약한 테스트**가 된다.
> 

---

## 13. 더 나은 테스트를 위한 구체적 조언

### 13.1 한 테스트에 한 주제

- 테스트 안에 `for`문이나 `if`문이 있다면, 하나의 테스트가 **여러 조건을 동시에 검증**하고 있다는 신호다.
→ 테스트가 실패했을 때 “정확히 어떤 케이스가 실패한 건지” 파악하기 어려워진다.
- 여러 입력값으로 같은 로직을 검증하고 싶다면 → **`it.each`**로 케이스를 분리하라.

```tsx
// ❌ 하나의 테스트에 여러 검증
it('유효성 검사', () => {
  expect(validateEmail('')).toBe(false);
  expect(validateEmail('invalid')).toBe(false);
  expect(validateEmail('test@test.com')).toBe(true);
  // 어디서 실패했는지 파악하기 어려움
});

// ✅ 케이스별 분리 — it.each 활용
it.each([
  ['', false],
  ['invalid', false],
  ['test@test.com', true],
  ['user@domain.co.kr', true],
])('이메일 "%s"의 유효성 검사 결과는 %s이다', (email, expected) => {
  expect(validateEmail(email)).toBe(expected);
});
```

### 13.2 완벽하게 제어하기

- 현재 시간, 랜덤 값 → **고정된 값이나 Mock으로 제어**
- `Date.now()` → `vi.setSystemTime(new Date('2024-01-01'))`
- `Math.random()` → `vi.spyOn(Math, 'random').mockReturnValue(0.5)`

```tsx
it('현재 날짜를 기준으로 D-day를 계산한다', () => {
  // given — 시간을 고정
  vi.setSystemTime(new Date('2024-03-01'));

  // when
  const dDay = calculateDDay('2024-03-10');

  // then
  expect(dDay).toBe(9);

  vi.useRealTimers();
});
```

### 13.3 구현 세부사항이 아닌 행위를 테스트하라

```tsx
// ❌ 구현 세부사항 테스트 — 내부 state를 직접 확인
expect(component.state.count).toBe(1);
expect(component.instance().handleClick).toHaveBeenCalled();

// ✅ 사용자 행위 테스트 — 화면에 보이는 결과를 확인
await user.click(screen.getByRole('button', { name: '증가' }));
expect(screen.getByRole('status')).toHaveTextContent('1');
```

> 이것이 **React Testing Library**의 핵심 철학이다.
구현을 테스트하면 **리팩토링할 때마다 테스트가 깨진다.**
> 

### 13.4 테스트 간 독립성 보장

- **전역 상태를 공유하지 마라** — 테스트 간 순서에 따라 결과가 달라질 수 있다
- `afterEach`에서 cleanup을 철저히 하라

```tsx
afterEach(() => {
  // Testing Library는 자동 cleanup
  // 하지만 MSW, 타이머, 전역 상태는 수동으로!
  server.resetHandlers();
  vi.useRealTimers();
  localStorage.clear();
});
```

### 13.5 Test Fixture 구성 원칙

### 팩토리 함수 패턴

```tsx
// ❌ 매번 긴 객체를 반복 작성
const user: User = {
  id: '1',
  name: '홍길동',
  email: 'hong@test.com',
  role: 'admin',
  isActive: true,
  createdAt: '2024-01-01',
};

// ✅ 팩토리 함수 — 필요한 속성만 오버라이드
function createUser(overrides: Partial<User> = {}): User {
  return {
    id: '1',
    name: '홍길동',
    email: 'hong@test.com',
    role: 'viewer',
    isActive: true,
    createdAt: '2024-01-01',
    ...overrides,
  };
}

// 사용
const admin = createUser({ role: 'admin' });
const inactiveUser = createUser({ isActive: false });
```

### 렌더 헬퍼 패턴

```tsx
// Provider가 많은 경우 — 렌더 헬퍼로 감싸기
function renderWithProviders(ui: ReactElement, options?: RenderOptions) {
  const queryClient = new QueryClient({
    defaultOptions: { queries: { retry: false } },
  });

  return render(
    <QueryClientProvider client={queryClient}>
      <MemoryRouter>
        <ThemeProvider>{ui}</ThemeProvider>
      </MemoryRouter>
    </QueryClientProvider>,
    options
  );
}

// 사용
it('프로필 페이지가 렌더링된다', () => {
  renderWithProviders(<ProfilePage />);
  expect(screen.getByText('프로필')).toBeInTheDocument();
});
```

### 13.6 @ParameterizedTest → it.each

```tsx
// Vitest / Jest의 파라미터화 테스트
describe('상품 타입 확인', () => {
  it.each([
    ['HANDMADE', false],
    ['BOTTLE', true],
    ['BAKERY', true],
  ])('상품 타입 %s의 재고 관련 여부는 %s이다', (type, expected) => {
    expect(isStockType(type as ProductType)).toBe(expected);
  });
});

// 객체 형태도 가능
it.each([
  { input: 0, expected: '0원' },
  { input: 1000, expected: '1,000원' },
  { input: 12500, expected: '12,500원' },
])('$input을 "$expected"로 포맷한다', ({ input, expected }) => {
  expect(formatPrice(input)).toBe(expected);
});
```

### 13.7 테스트 수행 환경 통합하기

> **테스트 실행 시간은 비용이다.**
> 

```tsx
// vitest.config.ts — 최적화 설정
export default defineConfig({
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: ['./vitest.setup.ts'],
    css: false,  // CSS 파싱 비용 제거
    pool: 'forks',  // 병렬 실행
    coverage: {
      provider: 'v8',
      reporter: ['text', 'html'],
    },
  },
});
```

### 13.8 private 함수의 테스트

> **결론: 직접 테스트할 필요가 없다.**
> 
- 컴포넌트 내부 함수나 훅 내부 로직은 **공개된 인터페이스(Props, 반환값, 화면 출력)를 통해 간접 검증**된다.
예를 들어, `Counter` 컴포넌트의 테스트로 증가 버튼을 클릭하면 내부의 `handleIncrement` 함수도 자연스럽게 검증되는 것이다.
- 내부 로직이 복잡해서 **별도로 테스트해야 할 것 같다면?** → 그 로직을 **별도 유틸 함수로 분리하라는 신호**다.

```tsx
// ❌ 내부 헬퍼를 직접 테스트하고 싶다면
// → 별도 유틸로 분리하라는 신호
function useCart() {
  // 이 함수를 직접 테스트하고 싶다면?
  const calculateDiscount = (items: CartItem[]) => { ... };

  // ...
}

// ✅ 유틸 함수로 분리 후 독립 테스트
// utils/calculateDiscount.ts
export function calculateDiscount(items: CartItem[]): number { ... }

// utils/calculateDiscount.test.ts
describe('calculateDiscount', () => { ... });
```

---

## 14. E2E 테스트

### Playwright 기본 구조

```tsx
// e2e/order.spec.ts
import { test, expect } from '@playwright/test';

test.describe('주문 흐름', () => {
  test('상품을 선택하고 주문을 완료할 수 있다', async ({ page }) => {
    // given — 주문 페이지에 접속한다
    await page.goto('/order');

    // when — 상품을 선택하고 주문한다
    await page.getByRole('button', { name: '아메리카노 추가' }).click();
    await page.getByRole('button', { name: '카페라떼 추가' }).click();
    await page.getByRole('button', { name: '주문하기' }).click();

    // then — 주문 완료 화면이 표시된다
    await expect(page.getByText('주문이 완료되었습니다')).toBeVisible();
    await expect(page.getByText('총 금액: 8,500원')).toBeVisible();
  });

  test('영업시간 외에는 주문을 할 수 없다', async ({ page }) => {
    // given
    await page.goto('/order');

    // then — 영업시간 안내가 표시된다
    await expect(page.getByText('현재 영업시간이 아닙니다')).toBeVisible();
    await expect(page.getByRole('button', { name: '주문하기' })).toBeDisabled();
  });
});
```

### E2E 테스트 원칙

| 원칙 | 설명 |
| --- | --- |
| **최소한으로** | 핵심 사용자 흐름만 E2E로, 나머지는 Unit/Integration |
| **안정적으로** | 타이밍 이슈를 `waitFor` / `expect` auto-retry로 해결 |
| **독립적으로** | 각 테스트가 독립적인 데이터와 환경에서 실행 |
| **빠르게** | 불필요한 대기 없이, 병렬 실행 활용 |

---

## 15. 테스트를 작성하는 마음가짐

### 요약 체크리스트

| 섹션 | 핵심 포인트 |
| --- | --- |
| 테스트의 필요성 | 빠른 피드백, 자동화, 안정감 |
| 단위 테스트 | 순수 함수, 유틸, 리듀서부터 시작 |
| TDD | 빠른 피드백, 엣지 케이스 발견, 과감한 리팩토링 |
| 테스트는 문서 | 팀 자산, 의도가 드러나는 이름 |
| 컴포넌트 테스트 | 사용자 관점 (Testing Library), 구현 세부사항 X |
| 커스텀 훅 테스트 | renderHook + act |
| API 테스트 | MSW로 네트워크 레벨 모킹 |
| Mock | 외부 시스템만 Mocking, 내부 통합은 실제 테스트 |
| 구체적 조언 | 한 주제, 완벽한 제어, 독립성, Fixture 관리 |

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

## 16. 부록: 추가 학습 키워드

### 테스트 도구

| 도구 | 용도 |
| --- | --- |
| **Vitest** | Vite 기반 빠른 테스트 러너 |
| **Jest** | 범용 테스트 러너 |
| **React Testing Library** | 사용자 관점 컴포넌트 테스트 |
| **MSW** | 네트워크 레벨 API 모킹 |
| **Playwright** | 크로스 브라우저 E2E 테스트 |
| **Cypress** | E2E + Component 테스트 |
| **Storybook** | 컴포넌트 시각적 테스트 + 문서화 |

### 테스트 패턴 & 방법론

- **Testing Trophy** — Kent C. Dodds가 제안한 프론트엔드 테스트 비율
    - Static (TypeScript, ESLint) > Unit > Integration(가장 많이) > E2E
- **Snapshot Testing** — 컴포넌트 렌더링 결과의 스냅샷 비교 (남용 주의)
- **Visual Regression Testing** — Chromatic, Percy 등으로 UI 회귀 테스트
- **Contract Testing** — 프론트엔드/백엔드 간 API 계약 테스트
- **Accessibility Testing** — jest-axe, @axe-core/playwright

### TDD & 방법론

- 애자일(Agile) vs 폭포수(Waterfall) 방법론
- 익스트림 프로그래밍 (XP) — TDD는 XP의 실천 방법 중 하나
- 스크럼(Scrum), 칸반(Kanban)

### 기타

- **코드 커버리지** — V8 Coverage, Istanbul (높다고 좋은 것은 아님, 기준선으로 활용)
- **CI/CD 통합** — GitHub Actions에서 자동 테스트 실행
- **Test Driven CSS** — Storybook + Chromatic으로 스타일 회귀 방지

---

> 📝 이 문서는 Java Spring 기반 [Practical Testing 가이드](practical-testing-guide.md)의 원칙을 React + TypeScript 생태계에 맞게 재구성한 팀 온보딩 가이드입니다.
>