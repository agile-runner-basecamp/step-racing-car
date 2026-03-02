import { Race, MovingStrategy } from './hardcodedRace';

describe('Race - Strategy 적용', () => {

    it('전략이 항상 true이면 모든 자동차가 이동한다', () => {
        const alwaysMove: MovingStrategy = () => true;

        const race = new Race(['kim', 'lee', 'park'], alwaysMove);
        race.playRound();

        const winners = race.findWinners();

        expect(winners).toEqual(
            expect.arrayContaining(['kim', 'lee', 'park'])
        );
        expect(winners).toHaveLength(3);
    });

    it('전략이 항상 false이면 아무도 이동하지 않는다', () => {
        const neverMove: MovingStrategy = () => false;

        const race = new Race(['kim', 'lee', 'park'], neverMove);
        race.playRound();

        const winners = race.findWinners();

        expect(winners).toEqual(
            expect.arrayContaining(['kim', 'lee', 'park'])
        );
        expect(winners).toHaveLength(3);
    });

    it('초기 상태에서는 모든 자동차가 우승자이다', () => {
        const dummyStrategy: MovingStrategy = () => false;

        const race = new Race(['kim', 'lee', 'park'], dummyStrategy);
        const winners = race.findWinners();

        expect(winners).toEqual(
            expect.arrayContaining(['kim', 'lee', 'park'])
        );
        expect(winners).toHaveLength(3);
    });

});