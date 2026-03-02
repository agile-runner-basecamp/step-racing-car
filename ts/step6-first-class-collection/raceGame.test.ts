import { RaceGame } from './raceGame';

describe('RaceGame', () => {
    it('자동차들이 이동한 후 우승자를 찾을 수 있다', () => {
        const game = new RaceGame(['kim', 'lee', 'park']);
        game.moveAll([4, 3, 5]);

        const winners = game.findWinners();
        expect(winners).toEqual(expect.arrayContaining(['kim', 'park']));
        expect(winners).toHaveLength(2);
    });

    it('모두 같은 위치이면 모두 우승자이다', () => {
        const game = new RaceGame(['kim', 'lee', 'park']);
        game.moveAll([4, 4, 4]);

        const winners = game.findWinners();
        expect(winners).toHaveLength(3);
    });

    it('getCars()는 내부 컬렉션을 보호한다', () => {
        const game = new RaceGame(['kim', 'lee']);
        const cars = game.getCars();
        expect(game.getCars()).toHaveLength(2);
    });
});