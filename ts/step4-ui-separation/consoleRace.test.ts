import { ConsoleRace } from './consoleRace';

describe('ConsoleRace', () => {
    it('최종 우승자를 반환값으로 검증할 수 있다', () => {
        const race = new ConsoleRace();
        const names = ['kim', 'lee', 'park'];
        const randomValues = [4, 3, 5, 4, 4, 3];

        const result = race.play(names, 2, randomValues);

        expect(result.winners).toEqual(['kim']);
    });

    it('공동 우승자를 올바르게 반환한다', () => {
        const race = new ConsoleRace();
        const names = ['kim', 'lee'];
        const randomValues = [4, 4];

        const result = race.play(names, 1, randomValues);

        expect(result.winners).toEqual(['kim', 'lee']);
    });
});