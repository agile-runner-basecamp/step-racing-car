# Step 7: 전략 패턴 (Strategy Pattern) — 테스트 가능한 설계

이 코드는 **기능적으로 완벽하게 동작**합니다.
하지만 `playRound()` 안에 `Math.random()`이 **하드코딩**되어 있어, 테스트에서 결과를 예측할 수 없습니다.

## 🎯 학습 목표
- 제어할 수 없는 영역(랜덤)을 **인터페이스로 격리**하는 전략 패턴을 체득합니다.
- 테스트에서는 고정 값, 프로덕션에서는 랜덤 값을 주입하는 **DIP(의존 역전 원칙)**를 적용합니다.

## 📝 요구 사항
1. `MovingStrategy` 타입을 만드세요.
2. `RandomMovingStrategy` — 실제 랜덤 값을 생성하는 구현
3. `FixedMovingStrategy` — 테스트용 고정 값을 반환하는 구현
4. `HardcodedRace`가 생성자에서 `MovingStrategy`를 주입받도록 변경하세요.
5. 테스트에서 `FixedMovingStrategy`를 주입하여 결과를 **확정적으로 검증**하세요.

### 💡 TypeScript 힌트
Java에서는 `interface MovingStrategy`를 만들지만, TypeScript에서는 **함수 타입**으로 더 간결하게 표현할 수 있습니다:
```typescript
// 방법 1: 함수 타입 (TypeScript 관용적)
type MovingStrategy = () => boolean;

const randomMovingStrategy: MovingStrategy = () =>
  Math.floor(Math.random() * 10) >= 4;

const fixedMovingStrategy = (value: boolean): MovingStrategy =>
  () => value;

// 방법 2: 인터페이스 (Java 스타일)
interface MovingStrategy {
  shouldMove(): boolean;
}
```
두 방법 모두 가능하지만, TypeScript에서는 **방법 1(함수 타입)**이 더 관용적입니다.

## ✅ 달성 기준
- [ ] `MovingStrategy` 타입(또는 인터페이스)이 존재하는가?
- [ ] `HardcodedRace` (또는 리네이밍한 클래스)에서 `Math.random()`이 사라졌는가?
- [ ] 테스트에서 고정 값을 주입하여 이동 결과를 **확정적으로 검증**할 수 있는가?
- [ ] 기존의 `playRound는 결과를 예측할 수 없다` 테스트가 예측 가능한 테스트로 바뀌었는가?
- [ ] 모든 테스트가 Green인가?

## ⌨️ VSCode 리팩토링 단축키

| 기능 | Windows/Linux | macOS |
|------|--------------|-------|
| **새 파일 생성** | `Ctrl + N` | `⌘ + N` |
| **이름 변경** (Rename Symbol) | `F2` | `F2` |
| **빠른 수정** (Quick Fix) | `Ctrl + .` | `⌘ + .` |
| **정의로 이동** (Go to Definition) | `F12` | `F12` |

> 💡 `Math.random()`을 사용하는 곳을 `Ctrl + Shift + F`로 검색한 뒤, 하나씩 전략 주입으로 교체하세요.

## 📚 참고 자료
- [FE] 읽기 좋은 코드 작성법 → §4.3 OCP — 확장에 열린 컴포넌트
- [FE] 테스트 코드 가이드 → §5 테스트하기 어려운 영역 분리하기
