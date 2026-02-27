# Step 6: 일급 컬렉션 (First-Class Collection)

이 코드는 **기능적으로 완벽하게 동작하며 테스트를 통과**합니다.
하지만 `RaceGame`이 내부의 `Car[]`를 `getCars()`로 **그대로 노출**하고 있어, 외부에서 직접 조작할 수 있습니다. 컬렉션 관련 로직(우승자 찾기, 전체 이동 등)도 `RaceGame`에 흩어져 있습니다.

## 🎯 학습 목표
- 컬렉션을 객체로 감싸 **컬렉션 관련 로직을 캡슐화**하는 일급 컬렉션 패턴을 체득합니다.
- 외부에서 내부 컬렉션을 조작할 수 없도록 보호합니다.

## 📝 요구 사항
1. `Car[]`를 감싸는 `Cars` 클래스를 만드세요.
2. `moveAll()`, `findWinners()` 같은 컬렉션 로직을 `Cars` 내부로 이동하세요.
3. `getCars()`를 호출해도 내부 컬렉션을 직접 조작할 수 없도록 보호하세요.
4. `exposedInternalList` 테스트가 더 이상 내부 상태를 망가뜨리지 못하도록 수정하세요.

### 💡 TypeScript 힌트
Java의 `Collections.unmodifiableList()`에 해당하는 TypeScript 방어 기법:
```typescript
// 방법 1: 스프레드 연산자로 복사본 반환
getCars(): Car[] {
  return [...this.cars];
}

// 방법 2: readonly 배열 타입 반환
getCars(): readonly Car[] {
  return this.cars;
}
```

## ✅ 달성 기준
- [ ] `Cars` 일급 컬렉션 클래스가 존재하는가?
- [ ] 외부에서 `getCars()`로 내부 배열을 직접 수정할 수 없는가?
- [ ] `moveAll()`, `findWinners()` 로직이 `Cars` 안에 있는가?
- [ ] `RaceGame`에서 `Car[]`에 대한 직접 조작 코드가 없는가?
- [ ] 모든 테스트가 Green인가?

## ⌨️ VSCode 리팩토링 단축키

| 기능 | Windows/Linux | macOS |
|------|--------------|-------|
| **새 파일 생성** | `Ctrl + N` | `⌘ + N` |
| **함수 추출** (Extract Function) | `Ctrl + .` → 선택 | `⌘ + .` → 선택 |
| **참조 찾기** (Find All References) | `Shift + F12` | `Shift + F12` |
| **이름 변경** (Rename Symbol) | `F2` | `F2` |

> 💡 `getCars()`의 모든 호출부를 `Shift + F12`로 먼저 확인한 뒤, 어떤 메서드를 `Cars`로 옮겨야 할지 판단하세요.

## 📚 참고 자료
- [FE] 읽기 좋은 코드 작성법 → §4.5 커스텀 훅으로 로직 캡슐화 (유사 개념)
- racing-car README → "일급 컬렉션을 사용한다"
