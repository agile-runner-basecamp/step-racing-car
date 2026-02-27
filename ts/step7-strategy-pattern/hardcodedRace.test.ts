import { HardcodedRace } from './hardcodedRace';

describe('HardcodedRace', () => {
    it('playRound를 실행하면 자동차가 이동하지만 결과를 예측할 수 없다', () => {
        const race = new HardcodedRace(['kim', 'lee', 'park']);

        // 😱 이 테스트는 랜덤에 의존하므로 결과를 보장할 수 없습니다!
        race.playRound();

        // 검증이 불가능 — 랜덤이라 어떤 결과가 나올지 모름

        // TODO: MovingStrategy 인터페이스를 도입하여,
        // 테스트에서는 고정된 값을 주입할 수 있도록 리팩토링하세요.
        // 예:
        // const race = new Race(['kim', 'lee'], () => 4);
        // race.playRound();
        // expect(race.findWinners()).toEqual(['kim', 'lee']);
    });

    it('초기 상태에서는 모든 자동차가 우승자이다', () => {
        const race = new HardcodedRace(['kim', 'lee', 'park']);
        const winners = race.findWinners();

        expect(winners).toEqual(expect.arrayContaining(['kim', 'lee', 'park']));
        expect(winners).toHaveLength(3);
    });
});
