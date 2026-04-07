> **“Any fool can write code that a computer can understand. Good programmers write code that humans can understand.”**
— Martin Fowler
> 

이 문서는 **TypeScript 기반 React** 프로젝트에서 읽기 좋은 코드를 작성하기 위한 팀원 온보딩 가이드입니다.
코드의 가독성이 곧 유지보수성이며, 유지보수성이 곧 팀의 생산성입니다.

---

## 목차

1. [왜 읽기 좋은 코드인가?](about:blank#1-%EC%99%9C-%EC%9D%BD%EA%B8%B0-%EC%A2%8B%EC%9D%80-%EC%BD%94%EB%93%9C%EC%9D%B8%EA%B0%80)
2. [추상 — 모든 원칙의 뿌리](about:blank#2-%EC%B6%94%EC%83%81--%EB%AA%A8%EB%93%A0-%EC%9B%90%EC%B9%99%EC%9D%98-%EB%BF%8C%EB%A6%AC)
3. [논리, 사고의 흐름](about:blank#3-%EB%85%BC%EB%A6%AC-%EC%82%AC%EA%B3%A0%EC%9D%98-%ED%9D%90%EB%A6%84)
4. [컴포넌트 설계 패러다임](about:blank#4-%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8-%EC%84%A4%EA%B3%84-%ED%8C%A8%EB%9F%AC%EB%8B%A4%EC%9E%84)
5. [TypeScript 활용하기](about:blank#5-typescript-%ED%99%9C%EC%9A%A9%ED%95%98%EA%B8%B0)
6. [코드 다듬기](about:blank#6-%EC%BD%94%EB%93%9C-%EB%8B%A4%EB%93%AC%EA%B8%B0)
7. [리팩토링 실전 전략](about:blank#7-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-%EC%8B%A4%EC%A0%84-%EC%A0%84%EB%9E%B5)
8. [기억하면 좋을 조언들](about:blank#8-%EA%B8%B0%EC%96%B5%ED%95%98%EB%A9%B4-%EC%A2%8B%EC%9D%84-%EC%A1%B0%EC%96%B8%EB%93%A4)

---

## 1. 왜 읽기 좋은 코드인가?

### 클린 코드를 추구하는 이유

> 코드가 잘 읽힌다 → 이해가 잘 된다 → 유지보수가 수월하다 → **시간과 자원이 절약된다**
> 
- **코드가 잘 읽힌다 → 이해가 잘 된다**: 컴포넌트명, 훅명, Props 구조가 명확하면 코드를 처음 보는 동료도 “이 컴포넌트가 뭘 하는지” 빠르게 파악할 수 있다.
- **이해가 잘 된다 → 유지보수가 수월하다**: 코드를 이해해야 수정할 수 있다. 이해하기 어려운 코드는 수정 시 의도치 않은 버그를 만들기 쉽다.
- **유지보수가 수월하다 → 시간과 자원이 절약된다**: 버그 수정, 기능 추가에 드는 시간이 줄어들면 팀 전체의 생산성이 올라간다.

클린 코드는 단순히 깔끔한 코드가 아닙니다.

**“**Clean code ***that works*”** — ***잘 동작해야 비로소*** 클린 코드입니다.

### 핵심 용어 정의

| 용어 | 정의 |
| --- | --- |
| **도메인** | 해결하고자 하는 문제 영역. 예: 카페 키오스크를 만든다면 “음료 주문, 재고 관리, 결제” 등이 도메인 |
| **도메인 지식** | 도메인을 이해하고 해결하는 데 필요한 지식. 코드를 작성하기 전에 설계하는 것이 아니라, 요구사항을 구현하는 과정에서 자연스럽게 **발견**되는 것. 예: “상품에는 HANDMADE, BOTTLE, BAKERY 타입이 있고, 각 타입마다 재고 처리가 다르다”는 지식은 코드로 구현하면서 드러남 |
| **레거시(유산)** | 우리가 현재 가지고 있는 코드. 꼭 “나쁜 코드”라는 뜻이 아니라, 과거에 작성되어 지금 운영 중인 코드 전체를 의미 |
| **후손** | 미래의 나, 동료, 개발자들 — 코드의 **진짜 독자** |

---

## 2. 추상 — 모든 원칙의 뿌리

### 추상이란?

> **중요한 정보는 가려내어 남기고, 덜 중요한 정보는 생략하여 버리는 것**
> 

일상의 예를 들면, 지하철 노선도는 실제 지리적 거리와 곡선을 생략하고 “어떤 역에서 어떤 역으로 갈 수 있는가”라는 핵심 정보만 남긴 **추상화**입니다.
프로그래밍에서도 동일합니다. `useAuth()`라는 훅은 내부적으로 토큰 저장, API 호출, 리프레시 로직 등의 복잡한 과정을 **생략**하고 “인증 상태를 관리한다”라는 핵심만 드러냅니다.

React 개발에서 추상은 **컴포넌트, 훅, 유틸 함수, 타입** 등 모든 곳에 적용됩니다.

### 잘못된 추상화의 원인

1. **중요한 정보를 거르지 못함** — 컴포넌트 안에 데이터 fetching, 필터링, 정렬, 렌더링이 모두 섞여 있어 “이 컴포넌트가 주로 뭘 하는지” 파악하기 어려운 경우
2. **해석자가 동일하게 공유하는 문맥(context)이 없음** — 예를 들어, 변수명 `data`는 코드를 작성한 본인에게는 “API 응답 데이터”이지만, 다른 팀원이 읽으면 “상태 데이터”로 해석할 수 있다. 같은 이름이 팀원마다 다르게 해석되면 추상화가 실패한 것
3. **도메인이 명확하지 않음** — 어떤 문제를 해결하는 코드인지 도메인이 불명확하면, 이름을 지어도 의미가 모호해진다

---

### 2.1 이름 짓기 — 가장 단순하면서도 중요한 추상화

### 이름 짓기 4대 원칙

| 원칙 | 설명 | 예시 |
| --- | --- | --- |
| **단수와 복수 구분하기** | 배열인지 단일 값인지 표현 | `user` vs `users` |
| **이름 줄이지 않기** | 축약어는 혼란을 초래 | `btn` → `button`, `msg` → `message` |
| **은어/방언 사용하지 않기** | 보편적 용어 사용 | 팀 내 합의된 용어 사전 운영 |
| **좋은 코드를 보고 습득하기** | 업계 관용 표현 익히기 | `payload`(액션이나 이벤트에 담기는 데이터), `handler`(이벤트를 처리하는 함수), `provider`(하위 컴포넌트에 값을 제공하는 래퍼) |

### React/TS 실전 이름 짓기

```tsx
// ❌ 불명확한 이름
const data = useFetch('/api/users');
const flag = useState(false);
const cb = () => {};

// ✅ 의미가 드러나는 이름
const { users, isLoading, error } = useUsers();
const [isModalOpen, setIsModalOpen] = useState(false);
const handleSubmit = () => {};
```

**컴포넌트 이름 규칙:**

| 유형 | 네이밍 패턴 | 예시 |
| --- | --- | --- |
| 페이지 컴포넌트 | `~Page` | `UserProfilePage` |
| 레이아웃 | `~Layout` | `DashboardLayout` |
| 목록 | `~List` | `UserList` |
| 목록 항목 | `~Item` / `~Card` | `UserItem`, `UserCard` |
| 폼 | `~Form` | `SignUpForm` |
| 모달/다이얼로그 | `~Modal` / `~Dialog` | `ConfirmModal` |
| Provider | `~Provider` | `AuthProvider` |
| 커스텀 훅 | `use~` | `useAuth`, `useLocalStorage` |

**이벤트 핸들러 이름 규칙:**

```tsx
// Props로 전달되는 콜백 — on 접두어
interface Props {
  onSubmit: (data: FormData) => void;
  onChange: (value: string) => void;
  onClose: () => void;
}

// 컴포넌트 내부 핸들러 — handle 접두어
const handleSubmit = (e: FormEvent) => { ... };
const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => { ... };
const handleModalClose = () => { ... };
```

**boolean 변수/Props 이름 규칙:**

```tsx
// ❌ 불명확
const open = true;
const data = false;

// ✅ is, has, can, should 접두어
const isOpen = true;
const hasPermission = false;
const canEdit = true;
const shouldRender = items.length > 0;
```

---

### 2.2 함수/컴포넌트와 추상화

> **한 문단의 주제는 반드시 하나다.** 함수와 컴포넌트도 마찬가지.
> 

```tsx
// ❌ 여러 주제가 혼합된 컴포넌트
function UserDashboard() {
  // 데이터 fetching 로직 30줄...
  // 권한 체크 로직 20줄...
  // 폼 검증 로직 25줄...
  // 렌더링 50줄...
}

// ✅ 추상화로 분리 — 각각 하나의 주제
function UserDashboard() {
  const { user, isLoading } = useUser();
  const { canEdit } = usePermissions(user);
  const { formState, handleSubmit } = useProfileForm(user);

  if (isLoading) return <Skeleton />;

  return (
    <DashboardLayout>
      <UserProfile user={user} />
      {canEdit && <ProfileEditForm {...formState} onSubmit={handleSubmit} />}
    </DashboardLayout>
  );
}
```

---

### 2.3 추상화 레벨 ⭐

> **하나의 컴포넌트(하나의 세계) 안에서는 추상화 레벨이 동등해야 한다.**
> 
> 
> 왜 동등해야 할까? — 높은 추상화 수준의 코드(`<OrderSummary />`, `<OrderList />`) 사이에 갑자기 낮은 수준의 코드(`orders.reduce(...)`, 인라인 스타일)가 끼어 있으면,
> 읽는 사람이 “사고 모드를 전환”해야 합니다. 전체 흐름을 파악하다가 갑자기 구현 세부사항을 읽어야 하면 뇌에 부담이 생깁니다.
> 

```tsx
// ❌ 추상화 레벨 불일치 — 고수준과 저수준이 혼재
function OrderPage() {
  const orders = useOrders();

  return (
    <div>
      <OrderSummary orders={orders} />           {/* 고수준 */}
      <OrderList orders={orders} />               {/* 고수준 */}
      {orders.length > 0 && (                     {/* 저수준 — 여기서 멈칫! */}
        <div style={{ marginTop: 16, padding: '8px 12px', backgroundColor: '#f0f0f0' }}>
          <span>총 {orders.reduce((sum, o) => sum + o.price, 0)}원</span>
        </div>
      )}
    </div>
  );
}

// ✅ 추상화 레벨 통일 — 모두 같은 레벨
function OrderPage() {
  const orders = useOrders();

  return (
    <div>
      <OrderSummary orders={orders} />
      <OrderList orders={orders} />
      <OrderTotalPrice orders={orders} />
    </div>
  );
}
```

**React에서의 추상화 레벨 계층:**

| 레벨 | React 대응 | 예시 |
| --- | --- | --- |
| **시스템/모듈** | 페이지, 라우트 | `UserPage`, `OrderPage` |
| **컴포넌트** | 재사용 가능한 UI 단위 | `Button`, `DataTable`, `UserCard` |
| **훅** | 로직의 캡슐화 | `useAuth()`, `useDebounce()` |
| **유틸 함수** | 순수 로직 | `formatPrice()`, `validateEmail()` |

---

### 2.4 매직 넘버, 매직 스트링

```tsx
// ❌ 매직 넘버/스트링
if (userAge >= 19) { ... }
if (status === 'active') { ... }
setTimeout(callback, 3000);

// ✅ 상수로 추출 — 의미 부여
const ADULT_AGE = 19;
const USER_STATUS = { ACTIVE: 'active', INACTIVE: 'inactive' } as const;
const DEBOUNCE_DELAY_MS = 3000;

if (userAge >= ADULT_AGE) { ... }
if (status === USER_STATUS.ACTIVE) { ... }
setTimeout(callback, DEBOUNCE_DELAY_MS);
```

---

## 3. 논리, 사고의 흐름

### 핵심 원리: 뇌 메모리 적게 쓰기 (인지적 경제성)

> **최소의 인지적 노력으로 최대의 정보를 전달하라.**
> 

뇌는 한 번에 한 가지 일만 처리할 수 있습니다. 코드를 읽을 때도 마찬가지입니다.
예를 들어, 컴포넌트 상단에 선언된 여러 state 변수의 의미를 기억하면서, 동시에 JSX 안의 조건부 렌더링을 해석하고, `useEffect`의 의존성 배열을 추적하는 것은 뇌에 큰 부담입니다.
**읽는 사람이 한 번에 추적해야 할 정보를 최소화**하는 것이 핵심입니다.

---

### 3.1 Early Return / Early Exit

```tsx
// ❌ 중첩 조건 — 뇌가 모든 조건을 기억해야 함
function UserProfile({ user }: Props) {
  if (user) {
    if (user.isActive) {
      if (user.profile) {
        return <Profile data={user.profile} />;
      } else {
        return <EmptyProfile />;
      }
    } else {
      return <InactiveUser />;
    }
  } else {
    return <NotFound />;
  }
}

// ✅ Early Return — 조건을 즉시 해소
function UserProfile({ user }: Props) {
  if (!user) return <NotFound />;
  if (!user.isActive) return <InactiveUser />;
  if (!user.profile) return <EmptyProfile />;

  return <Profile data={user.profile} />;
}
```

---

### 3.2 사고의 depth 줄이기

### 중첩 삼항 연산자 지양

```tsx
// ❌ 중첩 삼항 — 읽기 극도로 어려움
return (
  <div>
    {isLoading ? <Spinner /> : error ? <Error /> : data ? <DataView data={data} /> : <Empty />}
  </div>
);

// ✅ early return 또는 명시적 분기
if (isLoading) return <Spinner />;
if (error) return <Error />;
if (!data) return <Empty />;

return <DataView data={data} />;
```

### 선언적 코드 선호

```tsx
// ❌ 명령형 — 어떻게(how) 하는지 서술
const activeUsers: User[] = [];
for (const user of users) {
  if (user.isActive) {
    activeUsers.push({
      ...user,
      displayName: `${user.firstName} ${user.lastName}`,
    });
  }
}

// ✅ 선언적 — 무엇을(what) 하는지 서술
const activeUsers = users
  .filter((user) => user.isActive)
  .map((user) => ({
    ...user,
    displayName: `${user.firstName} ${user.lastName}`,
  }));
```

---

### 3.3 공백 라인의 의미

```tsx
function useCheckout(cart: Cart) {
  // 1. 상태 관리
  const [isProcessing, setIsProcessing] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // 2. 파생 데이터
  const totalPrice = useMemo(() => calculateTotal(cart), [cart]);
  const isValid = cart.items.length > 0 && totalPrice > 0;

  // 3. 사이드 이펙트
  useEffect(() => {
    trackCartView(cart.id);
  }, [cart.id]);

  // 4. 핸들러
  const handleCheckout = async () => { ... };

  return { isProcessing, error, totalPrice, isValid, handleCheckout };
}
```

공백 라인으로 **의미 단위** 를 나누면 훅의 구조가 한눈에 보입니다.

---

### 3.4 부정어를 대하는 자세

```tsx
// ❌ 이중 부정 — 뇌가 2단계 처리 필요
// 예: `!isNotValid`를 읽으면 ① "유효하지 않은가?"를 먼저 이해한 뒤 ② "그것의 반대"를 계산해야 함
if (!isNotValid) { ... }
{!isHidden && <Component />}

// ✅ 긍정 표현 — 한 단계로 이해 가능
if (isValid) { ... }
{isVisible && <Component />}
```

---

### 3.5 예외 처리와 에러 바운더리

### 방어적 프로그래밍

```tsx
// 외부 입력은 항상 의심
function UserCard({ user }: { user: User | null | undefined }) {
  if (!user) return <Fallback />;

  return <div>{user.name}</div>;
}
```

### Error Boundary 패턴

```tsx
// 의도하지 않은 에러를 우아하게 처리
function App() {
  return (
    <ErrorBoundary fallback={<ErrorFallback />}>
      <Suspense fallback={<Loading />}>
        <UserDashboard />
      </Suspense>
    </ErrorBoundary>
  );
}
```

### Null/Undefined 안전하게 다루기

```tsx
// ❌ 위험 — 런타임 에러 가능
const userName = user.profile.name;

// ✅ Optional Chaining + Nullish Coalescing
const userName = user?.profile?.name ?? '알 수 없는 사용자';

// ✅ TypeScript로 컴파일 타임에 방어
interface User {
  id: string;
  profile?: {          // 명시적 optional
    name: string;
    bio?: string;
  };
}
```

---

## 4. 컴포넌트 설계 패러다임

### 4.1 관심사의 분리 (Separation of Concerns)

React에서 관심사 분리는 **컴포넌트 분리, 커스텀 훅, 유틸 함수** 로 실현합니다.

```
관심사 분리 → 높은 응집도 + 낮은 결합도
```

- **응집도**: 하나의 컴포넌트/훅 안에 있는 요소들이 얼마나 밀접하게 관련되어 있는가. 응집도가 높으면 컴포넌트 안의 state, 핸들러, JSX가 모두 같은 목적을 위해 존재함
- **결합도**: 서로 다른 컴포넌트/훅이 얼마나 강하게 연결되어 있는가. 결합도가 높으면 하나를 바꿀 때 다른 것도 함께 바껴야 함

```tsx
// ❌ 모든 관심사가 한 컴포넌트에 혼재
function UserPage() {
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    fetch('/api/user')
      .then((res) => res.json())
      .then((data) => { setUser(data); setIsLoading(false); });
  }, []);

  const formattedDate = user
    ? new Intl.DateTimeFormat('ko-KR').format(new Date(user.createdAt))
    : '';

  if (isLoading) return <Spinner />;

  return <div>{user.name} — {formattedDate}</div>;
}

// ✅ 관심사별 분리
// 1. 데이터 fetching → 커스텀 훅
function useUser() {
  return useQuery({ queryKey: ['user'], queryFn: fetchUser });
}

// 2. 포맷팅 → 유틸 함수
function formatDate(dateString: string): string {
  return new Intl.DateTimeFormat('ko-KR').format(new Date(dateString));
}

// 3. 렌더링 → 컴포넌트
function UserPage() {
  const { data: user, isLoading } = useUser();

  if (isLoading) return <Spinner />;

  return <div>{user.name} — {formatDate(user.createdAt)}</div>;
}
```

---

### 4.2 단일 책임 원칙 (SRP) — 컴포넌트 편

> **하나의 컴포넌트는 하나의 역할만 수행해야 한다.**
> 

| 분리 기준 | 예시 |
| --- | --- |
| 데이터 로직 ↔︎ UI | `useUsers()` ↔︎ `UserList` |
| 레이아웃 ↔︎ 컨텐츠 | `PageLayout` ↔︎ `OrderDetail` |
| 제네릭 ↔︎ 도메인 | `DataTable` ↔︎ `OrderTable` |
| 상태 관리 ↔︎ 표현 | Container 컴포넌트 ↔︎ Presentational 컴포넌트 |

```tsx
// ❌ 하나의 컴포넌트가 여러 책임
function UserManagement() {
  // 사용자 목록 fetch, 필터링, 정렬, 페이지네이션,
  // 사용자 생성 폼, 삭제 확인 모달, 권한 관리...
  // 300줄의 JSX...
}

// ✅ 책임별 분리
function UserManagementPage() {
  return (
    <PageLayout title="사용자 관리">
      <UserFilters />
      <UserTable />
      <CreateUserButton />
    </PageLayout>
  );
}
```

---

### 4.3 OCP — 확장에 열린 컴포넌트

> **Props와 합성(Composition)으로 수정 없이 확장 가능하게**
> 

```tsx
// ❌ 새로운 variant 추가할 때마다 컴포넌트 내부 수정 필요
function Alert({ type }: { type: 'success' | 'error' | 'warning' }) {
  if (type === 'success') return <div className="green">...</div>;
  if (type === 'error') return <div className="red">...</div>;
  if (type === 'warning') return <div className="yellow">...</div>;
}

// ✅ variant map + 합성 — 확장에 열림
const ALERT_STYLES = {
  success: { className: 'alert-success', icon: CheckIcon },
  error: { className: 'alert-error', icon: XIcon },
  warning: { className: 'alert-warning', icon: AlertIcon },
} as const;

type AlertVariant = keyof typeof ALERT_STYLES;

function Alert({ variant, children }: { variant: AlertVariant; children: ReactNode }) {
  const { className, icon: Icon } = ALERT_STYLES[variant];
  return (
    <div className={className}>
      <Icon />
      {children}
    </div>
  );
}
```

### children과 Composition 패턴

```tsx
// ❌ Props로만 확장 — 유연성 부족
<Card title="주문 상세" subtitle="2024-01-01" footer={<Button>확인</Button>} />

// ✅ Composition — 자유롭게 조합 가능
<Card>
  <Card.Header>
    <h2>주문 상세</h2>
    <Badge>신규</Badge>
  </Card.Header>
  <Card.Body>
    <OrderDetail order={order} />
  </Card.Body>
  <Card.Footer>
    <Button onClick={handleConfirm}>확인</Button>
  </Card.Footer>
</Card>
```

---

### 4.4 컴포넌트 Props 설계

### 캡슐화 — 필요한 데이터만 넘기기 (Tell, Don’t Ask)

```tsx
// ❌ 객체 전체를 넘기면 결합도 증가
interface Props {
  user: User;  // User의 모든 필드를 알아야 함
}

// ✅ 필요한 데이터만 Props로 선언
interface Props {
  userName: string;
  avatarUrl: string;
  isOnline: boolean;
}
```

> **단, 도메인 객체 전체를 넘기는 것이 맞는 경우도 있습니다.** `UserProfile`처럼 User 전체가 관심사인 컴포넌트라면 `User` 타입을 그대로 받는 것이 자연스럽습니다. 맹목적으로 쪼개지 마세요.
> 

### 인터페이스 vs 타입 일관성

```tsx
// 팀 내 합의로 통일 — 일반적으로:
// Props, 객체 형태 → interface
interface UserCardProps {
  name: string;
  role: UserRole;
}

// Union, 유틸리티 타입 → type
type UserRole = 'admin' | 'editor' | 'viewer';
type UserWithProfile = User & { profile: Profile };
```

---

### 4.5 커스텀 훅으로 로직 캡슐화

> **커스텀 훅 = 로직의 캡슐화 단위 (Java의 Service 클래스와 유사)**
> 

```tsx
// ❌ 컴포넌트에 로직이 직접 노출
function SearchPage() {
  const [query, setQuery] = useState('');
  const [debouncedQuery, setDebouncedQuery] = useState('');
  const [results, setResults] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => setDebouncedQuery(query), 300);
    return () => clearTimeout(timer);
  }, [query]);

  useEffect(() => {
    if (!debouncedQuery) return;
    setIsLoading(true);
    searchApi(debouncedQuery)
      .then(setResults)
      .finally(() => setIsLoading(false));
  }, [debouncedQuery]);

  return ( ... );
}

// ✅ 커스텀 훅으로 캡슐화
function useSearch(initialQuery = '') {
  const [query, setQuery] = useState(initialQuery);
  const debouncedQuery = useDebounce(query, 300);
  const { data: results = [], isLoading } = useQuery({
    queryKey: ['search', debouncedQuery],
    queryFn: () => searchApi(debouncedQuery),
    enabled: debouncedQuery.length > 0,
  });

  return { query, setQuery, results, isLoading };
}

function SearchPage() {
  const { query, setQuery, results, isLoading } = useSearch();
  return ( ... );  // 렌더링만 담당
}
```

---

### 4.6 상태 관리 원칙

### 최소한의 상태만 유지

```tsx
// ❌ 파생 가능한 값을 상태로 관리 — 동기화 위험
const [items, setItems] = useState<Item[]>([]);
const [totalPrice, setTotalPrice] = useState(0);     // items에서 계산 가능!
const [itemCount, setItemCount] = useState(0);        // items.length로 충분!

// ✅ 파생 값은 계산으로 도출
const [items, setItems] = useState<Item[]>([]);
const totalPrice = useMemo(() => items.reduce((sum, item) => sum + item.price, 0), [items]);
const itemCount = items.length;
```

### 상태의 위치 선정

```
state가 필요한 범위 → 가장 가까운 공통 부모에 배치

컴포넌트 로컬 → useState / useReducer
형제 간 공유 → 부모로 끌어올리기 (Lifting State Up)
앱 전역       → Context / Zustand / Redux
서버 상태     → TanStack Query / SWR
```

---

## 5. TypeScript 활용하기

### 5.1 타입으로 의도 전달하기 (추상화)

TypeScript의 타입 시스템은 **코드에 의미를 부여하는 강력한 추상화 도구** 입니다.

```tsx
// ❌ 원시 타입만 사용 — 의미 불명확
function createUser(name: string, age: number, role: string) { ... }

// ✅ 도메인 타입으로 의미 부여
type UserName = string;
type Age = number;
type UserRole = 'admin' | 'editor' | 'viewer';

interface CreateUserParams {
  name: UserName;
  age: Age;
  role: UserRole;
}

function createUser(params: CreateUserParams) { ... }
```

---

### 5.2 Union Type으로 안전한 분기 처리

```tsx
// ❌ string으로 상태 관리 — 오타 위험, 자동완성 불가
const [status, setStatus] = useState('loading');

// ✅ Union Type — 컴파일 타임 안전
type RequestStatus = 'idle' | 'loading' | 'success' | 'error';
const [status, setStatus] = useState<RequestStatus>('idle');
```

### Discriminated Union으로 상태 모델링

```tsx
// ❌ 모든 상태를 nullable로 관리 — 불가능한 상태 조합 존재
interface State {
  isLoading: boolean;
  data: Data | null;
  error: Error | null;
  // isLoading: true, data: Data, error: Error ← 이 조합은 불가능한데 타입이 허용함
}

// ✅ Discriminated Union — 불가능한 상태를 타입으로 제거
type State =
  | { status: 'idle' }
  | { status: 'loading' }
  | { status: 'success'; data: Data }
  | { status: 'error'; error: Error };

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case 'FETCH_SUCCESS':
      return { status: 'success', data: action.payload };  // data가 보장됨
  }
}
```

---

### 5.3 any 금지, unknown 활용

```tsx
// ❌ any — 타입 안전성 포기
function parseJSON(json: string): any {
  return JSON.parse(json);
}

// ✅ unknown + 타입 가드 — 안전한 파싱
function parseJSON(json: string): unknown {
  return JSON.parse(json);
}

function isUser(value: unknown): value is User {
  return (
    typeof value === 'object' &&
    value !== null &&
    'id' in value &&
    'name' in value
  );
}

const data = parseJSON(response);
if (isUser(data)) {
  console.log(data.name);  // 타입 안전
}
```

---

### 5.4 제네릭으로 재사용성 확보

```tsx
// ❌ 타입별 중복 함수
function getFirstUser(items: User[]): User | undefined { return items[0]; }
function getFirstOrder(items: Order[]): Order | undefined { return items[0]; }

// ✅ 제네릭으로 추상화
function getFirst<T>(items: T[]): T | undefined {
  return items[0];
}

// 제네릭 컴포넌트 예시
interface DataListProps<T> {
  items: T[];
  renderItem: (item: T) => ReactNode;
  keyExtractor: (item: T) => string;
}

function DataList<T>({ items, renderItem, keyExtractor }: DataListProps<T>) {
  return (
    <ul>
      {items.map((item) => (
        <li key={keyExtractor(item)}>{renderItem(item)}</li>
      ))}
    </ul>
  );
}

// 사용 — 타입 자동 추론
<DataList
  items={users}
  renderItem={(user) => <UserCard user={user} />}
  keyExtractor={(user) => user.id}
/>
```

---

### 5.5 as const와 satisfies

```tsx
// as const — 리터럴 타입으로 좁히기
const ROUTES = {
  HOME: '/',
  USERS: '/users',
  SETTINGS: '/settings',
} as const;
// typeof ROUTES.HOME → '/' (string이 아닌 리터럴)

// satisfies — 타입 체크와 추론을 동시에
const THEME = {
  primary: '#3B82F6',
  secondary: '#10B981',
  danger: '#EF4444',
} satisfies Record<string, string>;
// THEME.primary의 타입은 '#3B82F6' (리터럴 유지 + 타입 안전)
```

---

## 6. 코드 다듬기

### 6.1 주석의 양면성

> **“주석이 많다는 것은 요구사항을 코드에 잘 못 녹였다는 의미”**
> 

예를 들어 아래 ❌ 예시처럼 `// 사용자가 성인인지 확인`이라는 주석을 달아야 했다면, `user.isAdult()`로 작성했으면 주석이 필요 없었을 것입니다.
주석이 많다는 것은 “코드만으로는 의도를 전달하지 못하고 있다”는 신호이므로, 먼저 코드 자체를 개선하는 것이 우선입니다.

```tsx
// ❌ 코드를 설명하는 주석
// 사용자가 성인인지 확인
if (user.age >= 19) { ... }

// ✅ 코드 자체로 의미 전달
if (user.isAdult()) { ... }

// ✅ 좋은 주석 — 의사 결정 히스토리
/**
 * 디바운스 시간을 500ms → 300ms로 줄임.
 * UX 테스트 결과 300ms가 최적 타이핑 체감이었음.
 * 관련 실험: https://wiki.team.com/search-debounce-test
 */
const DEBOUNCE_MS = 300;
```

---

### 6.2 컴포넌트 내부 나열 순서

일관된 순서로 코드를 배치하면 인지적 경제성이 높아집니다.

```tsx
function MyComponent({ initialValue }: Props) {
  // 1. 타입/상수 (컴포넌트 스코프)

  // 2. 상태 (useState, useReducer)
  const [value, setValue] = useState(initialValue);

  // 3. 파생 값 (useMemo)
  const computed = useMemo(() => transform(value), [value]);

  // 4. Ref
  const inputRef = useRef<HTMLInputElement>(null);

  // 5. 사이드 이펙트 (useEffect)
  useEffect(() => { ... }, []);

  // 6. 이벤트 핸들러
  const handleChange = (e: ChangeEvent<HTMLInputElement>) => { ... };
  const handleSubmit = () => { ... };

  // 7. 조건부 렌더링 (early return)
  if (!value) return <Empty />;

  // 8. JSX 반환
  return ( ... );
}
```

---

### 6.3 파일/디렉토리 구조

```
src/
├── components/           # 공통 UI 컴포넌트
│   ├── Button/
│   │   ├── Button.tsx
│   │   ├── Button.test.tsx
│   │   ├── Button.styles.ts
│   │   └── index.ts
│   └── ...
├── features/             # 도메인(기능) 단위 모듈
│   ├── auth/
│   │   ├── components/
│   │   ├── hooks/
│   │   ├── types/
│   │   ├── utils/
│   │   └── index.ts
│   └── order/
├── hooks/                # 공통 커스텀 훅
├── utils/                # 공통 유틸리티 함수
├── types/                # 공통 타입 정의
├── constants/            # 공통 상수
└── pages/                # 페이지 컴포넌트 (라우트 단위)
```

> 패키지(디렉토리) 경로 자체가 **문맥** 을 제공합니다.
`features/auth/hooks/useLogin.ts` — 경로만으로 “인증 도메인의 로그인 훅”임을 알 수 있습니다.
> 

---

### 6.4 IDE 도구 활용

| 도구 | 용도 |
| --- | --- |
| **ESLint** | 코드 품질 규칙 자동 검사 |
| **Prettier** | 코드 포맷 자동 정리 |
| **TypeScript strict mode** | `"strict": true` 필수 — 타입 안전성 극대화 |
| **Husky + lint-staged** | 커밋 전 자동 검사 |
| **.editorconfig** | 편집기 간 스타일 통일 |

> 스타일은 **팀 내 합의** 로 결정하고, `.eslintrc` / `.prettierrc`로 자동화하세요.
개인 취향을 도구에 위임하면 코드 리뷰에서 스타일 논쟁이 사라집니다.
> 

---

## 7. 리팩토링 실전 전략

### 7.1 안전한 리팩토링 절차

```
1. 리팩토링 대상 범위 확인
2. 테스트 코드로 기존 동작 보장 (없으면 먼저 작성!)
3. 리팩토링 수행
4. 테스트로 검증
5. 브라우저에서 수동 검증
```

### 7.2 점진적 리팩토링

```tsx
// 1단계: 새로운 버전을 별도로 생성
function UserCardV2({ user }: Props) { ... }

// 2단계: 사용처를 하나씩 교체하며 확인
// <UserCard user={user} />  →  <UserCardV2 user={user} />

// 3단계: 모든 교체 완료 후 기존 컴포넌트 삭제
// 4단계: V2를 원래 이름으로 변경
```

### 7.3 React/TS 리팩토링 체크리스트

- [ ]  매직 넘버/스트링이 상수로 추출되었는가?
- [ ]  컴포넌트가 하나의 관심사만 다루는가?
- [ ]  `any` 타입이 사용되고 있지 않은가?
- [ ]  커스텀 훅으로 분리 가능한 로직이 컴포넌트에 남아있지 않은가?
- [ ]  early return으로 중첩 조건이 정리되었는가?
- [ ]  파생 가능한 값이 상태(`useState`)로 관리되고 있지 않은가?
- [ ]  Props 인터페이스가 명확히 정의되어 있는가?
- [ ]  불필요한 `useEffect`가 없는가? (이벤트 핸들러로 대체 가능한 것은 아닌지)
- [ ]  변수/함수 이름이 의도를 명확히 드러내는가?
- [ ]  `key` prop이 안정적인 값으로 설정되어 있는가? (index 사용 지양)

---

## 8. 기억하면 좋을 조언들

### 8.1 능동적 읽기

다른 사람의 코드를 읽을 때, “원본을 망가뜨릴까 봐” 걸정하지 마세요.
Git을 사용하고 있다면 `git stash` 또는 `git reset --hard`로 언제든 원래 상태로 돌릴 수 있으니까,
마음 편하게 코드를 **수정하면서** 읽으세요.

1. **주석** 으로 이해한 내용 적기
2. **함수/컴포넌트를 한글명으로 추출** 하며 이해 → 이해 후 `git reset --hard`로 원상 복구
3. **console.log** 로 데이터 흐름 추적 → 이해 후 제거

---

### 8.2 오버 엔지니어링 경계

### 너무 이른 추상화

```tsx
// ❌ 사용되는 곳이 1개인데 제네릭 컴포넌트를 만듦
function GenericDataFetcherWithCacheAndRetry<T>(...) { ... }

// ✅ 구체적으로 시작 → 패턴이 반복되면 추상화
function useUsers() { ... }     // 먼저 이것만 만들기
function useOrders() { ... }    // 유사 패턴 발견!
// → 이 시점에서 공통 훅 추출 고려
```

> **“Rule of Three”** — 3번 반복될 때 추상화를 고려
> 

### 불필요한 상태 관리 라이브러리

```
useState로 충분한가? → 충분하면 useState 사용
컴포넌트 간 공유가 필요한가? → Lifting State Up 또는 Context
서버 상태인가? → TanStack Query / SWR
복잡한 클라이언트 상태인가? → 이때 Zustand / Redux 고려
```

---

### 8.3 은탄환은 없다 (No Silver Bullet)

> **모든 기술과 방법론은 적정 기술의 범위 내에서 사용되어야 한다.**
> 
- 클린 코드도 은탄환이 아님 — 프로토타입이나 실험에는 빠른 코드가 낫다
- 기술 부채를 안을 때는 `// TODO:` 주석으로 표시
- 극단적으로 시도 → 한계를 느끼고 → 적정 수준을 체화

> **“도구라는 것은, 그것을 한계까지 사용할 줄 아는 사람이 사용하지 말아야 할 때도 아는 법이다.”**
> 

---

### 8.4 전문가의 사고 방식

> **전문가는 탑다운 방식만 고수하지 않는다.**
> 

추상과 구체를 넘나들며 **탑다운과 바텀업을 반복** 합니다.

예를 들어 주문 관리 페이지를 설계할 때:
- **탑다운**: “페이지 구조는 `필터 → 목록 → 상세`이다”라고 큰 그림을 그림
- **바텀업**: `주문 목록에서 필터링할 때 날짜 범위 선택은 어떻게 구현하지?` 하고 `useDateRangePicker` 훅을 작성
- **다시 탑다운**: “이 훅을 필터 컴포넌트에 합성하면 더 깔끔하겠다” 하고 설계 조정

이처럼 전환이 일어나는 순간마다 **“아하 모먼트”** 가 발생하고, 이것이 성장의 핵심입니다.

---

### 8.5 추천 리소스

| 리소스 | 유형 | 핵심 주제 |
| --- | --- | --- |
| **함께 자라기** (김창준) | 📕 도서 | 애자일, 함께 성장하기 |
| **Clean Code** (Robert C. Martin) | 📕 도서 | 클린 코드 원칙 |
| **리팩토링** (Martin Fowler) | 📕 도서 | 코드 구조 개선 체계 |
| **이펙티브 타입스크립트** (Dan Vanderkam) | 📕 도서 | TypeScript 62가지 모범 사례 |
| **React 공식 문서** | 📖 문서 | [react.dev](https://react.dev/) |
| **Patterns.dev** | 📖 문서 | [patterns.dev](https://patterns.dev/) — React 패턴 모음 |
| **Total TypeScript** (Matt Pocock) | 🎓 강의 | TypeScript 심화 |
| **Five Lines of Code** (Christian Clausen) | 📕 도서 | 함수는 5줄 이내로 |

---

## 부록: 유용한 VS Code 단축키

| 단축키 | 기능 |
| --- | --- |
| `Ctrl + Shift + P` | 명령 팔레트 |
| `F2` | 심볼 이름 변경 (Rename) |
| `Ctrl + .` | Quick Fix (자동 import, 리팩토링 제안) |
| `Ctrl + Shift + R` | 리팩토링 메뉴 |
| `Alt + Shift + O` | import 정리 |
| `Ctrl + D` | 동일 단어 하나씩 선택 |
| `Ctrl + Shift + L` | 동일 단어 전체 선택 |
| `Ctrl + Shift + F` | 전체 프로젝트 검색 |
| `Alt + ↑ / ↓` | 줄 이동 |
| `Ctrl + Shift + K` | 줄 삭제 |

---

> **“읽기 좋은 코드는 ’잘 쓴 글’과 같습니다. 독자(후손)를 배려하는 마음으로 코드를 작성합시다.”**
>