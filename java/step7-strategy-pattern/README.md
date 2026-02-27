# Step 7: 전략 패턴 (Strategy Pattern) — 테스트 가능한 설계

이 코드는 **기능적으로 완벽하게 동작**합니다.
하지만 `playRound()` 안에 `new Random().nextInt(10)` / `Math.random()`이 **하드코딩**되어 있어, 테스트에서 결과를 예측할 수 없습니다.

## 🎯 학습 목표
- 제어할 수 없는 영역(랜덤)을 **인터페이스로 격리**하는 전략 패턴을 체득합니다.
- 테스트에서는 고정 값, 프로덕션에서는 랜덤 값을 주입하는 **DIP(의존 역전 원칙)**를 적용합니다.

## 📝 요구 사항
1. `MovingStrategy` 인터페이스(Java) / 타입(TS)을 만드세요.
2. `RandomMovingStrategy` — 실제 랜덤 값을 생성하는 구현체
3. `FixedMovingStrategy` — 테스트용 고정 값을 반환하는 구현체
4. `HardcodedRace`가 생성자에서 `MovingStrategy`를 주입받도록 변경하세요.
5. 테스트에서 `FixedMovingStrategy`를 주입하여 결과를 **확정적으로 검증**하세요.

## ✅ 달성 기준
- [ ] `MovingStrategy` 인터페이스가 존재하는가?
- [ ] `HardcodedRace` (또는 리네이밍한 클래스)에서 `new Random()`이 사라졌는가?
- [ ] 테스트에서 고정 값을 주입하여 이동 결과를 **확정적으로 검증**할 수 있는가?
- [ ] 기존의 `playRound_isNotPredictable` 테스트가 예측 가능한 테스트로 바뀌었는가?
- [ ] 모든 테스트가 Green인가?

## 📚 참고 자료
- [BE] 읽기 좋은 코드 작성법 → §4.4 SOLID (DIP), §4.3 OCP
- [FE] 읽기 좋은 코드 작성법 → §4.3 OCP — 확장에 열린 컴포넌트
- [BE] 테스트 코드 가이드 → §5 테스트하기 어려운 영역 분리하기
- [FE] 테스트 코드 가이드 → §5 테스트하기 어려운 영역 분리하기
