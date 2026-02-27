# Step 2: Early Return (else 제거와 depth 줄이기)

이 코드는 **기능적으로 완벽하게 동작하며 모든 테스트를 통과**합니다.
하지만 입력값을 검증하고 전진/정지를 판정하는 과정에서 `if-else`가 6단계까지 중첩되어 있습니다.

## 🎯 학습 목표
- `Early Return` 패턴으로 조건을 즉시 해소하는 연습을 합니다.
- `else` 예약어를 코드에서 완전히 제거합니다.
- 부정 조건(`!condition`)을 긍정 표현으로 바꾸는 감각을 익힙니다.

## 📝 요구 사항
1. `DeepRaceValidator.java` / `deepRaceValidator.ts`의 중첩 `if-else`를 평탄화하세요.
2. 모든 `else`를 지우고, 유효하지 않은 경우 **빠르게 throw** 하도록 수정하세요.
3. 결과적으로 메서드 내부의 최대 depth를 **1단계**로 유지하세요.
4. 테스트를 실행하여 동작이 깨지지 않았는지 확인하세요!

## ✅ 달성 기준
- [ ] 코드에 `else` 키워드가 하나도 없는가?
- [ ] 메서드의 최대 depth가 1인가?
- [ ] 부정 조건이 자연스러운 표현으로 바뀌었는가?
- [ ] 모든 테스트가 Green인가?

## ⌨️ IntelliJ 리팩토링 단축키

| 기능 | Windows/Linux | macOS |
|------|--------------|-------|
| **조건 반전** (Invert Condition) | `Alt + Enter` → 선택 | `⌥ + Enter` → 선택 |
| **인라인 변수** (Inline Variable) | `Ctrl + Alt + N` | `⌘ + ⌥ + N` |

## 📚 참고 자료
- [BE] 읽기 좋은 코드 작성법 → §3.1 Early Return, §3.4 부정어를 대하는 자세
- [FE] 읽기 좋은 코드 작성법 → §3.1 Early Return, §3.4 부정어를 대하는 자세
