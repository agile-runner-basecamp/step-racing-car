# Step 5: 원시값 포장 (Value Object)

이 코드는 **기능적으로 완벽하게 동작하며 테스트를 통과**합니다.
하지만 자동차의 이름(`string`)과 위치(`number`)가 **날것 그대로** 사용되고 있어, 검증 로직이 생성자에 몰려 있고 도메인의 의미가 드러나지 않습니다.

> 💡 **난이도 안내:** 이 스텝부터는 기존 코드를 리팩토링하는 것에 더해, **새로운 클래스를 직접 만들어야** 합니다. 한 단계 높은 도전이지만 차근차근 진행하면 충분히 해낼 수 있습니다!

## 🎯 학습 목표
- 원시 타입을 의미 있는 객체로 감싸는 **Value Object** 패턴을 체득합니다.
- "이름은 5자 이하", "위치는 0 이상" 같은 규칙을 **객체 스스로가 보장**하도록 만듭니다.

## 📝 요구 사항
1. `rawCar.ts`의 `string` 타입 이름을 `CarName` 클래스로 포장하세요.
2. `number` 타입 위치를 `Position` 클래스로 포장하세요.
3. Value Object의 3대 특성을 지키세요:
   - **불변성**: `readonly` 필드, setter 없음
   - **동등성**: 값 비교 메서드 구현 (예: `equals(other: CarName): boolean`)
   - **유효성 검증**: 생성자에서 값의 유효성 보장
4. 포장 후 새로운 테스트를 추가하세요:
   - CarName의 동등성 테스트
   - Position이 음수가 될 수 없다는 테스트

### 💡 TypeScript 힌트
Java와 달리 TypeScript에는 `equals()`/`hashCode()`가 없습니다. 대신:
```typescript
// 값 비교 메서드를 직접 구현
class CarName {
  readonly value: string;

  equals(other: CarName): boolean {
    return this.value === other.value;
  }
}
```

## ✅ 달성 기준
- [ ] `CarName` 클래스가 존재하고, 5자 초과 시 생성 단계에서 예외가 발생하는가?
- [ ] `Position` 클래스가 존재하고, 0 이상의 값만 허용하는가?
- [ ] Value Object가 불변(`readonly`)인가?
- [ ] `RawCar` 클래스에서 `string`과 `number`가 직접 사용되지 않는가?
- [ ] 모든 테스트가 Green인가?

## ⌨️ VSCode 리팩토링 단축키

| 기능 | Windows/Linux | macOS |
|------|--------------|-------|
| **새 파일 생성** | `Ctrl + N` | `⌘ + N` |
| **이름 변경** (Rename Symbol) | `F2` | `F2` |
| **빠른 수정** (Quick Fix) | `Ctrl + .` | `⌘ + .` |
| **자동 import** | `Ctrl + .` → 선택 | `⌘ + .` → 선택 |

> 💡 새 클래스를 만든 뒤, 기존 코드에서 타입 에러가 나는 곳을 `Ctrl + .`로 빠르게 수정할 수 있습니다.

## 📚 참고 자료
- [FE] 읽기 좋은 코드 작성법 → §5.1 타입으로 의도 전달하기 (추상화)
- racing-car README → "모든 원시 값과 문자열을 포장한다"
