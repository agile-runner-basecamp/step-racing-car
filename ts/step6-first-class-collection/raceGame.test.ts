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

    it('getCars()로 외부에서 자동차 목록을 직접 조작할 수 있다 — 위험!', () => {
        const game = new RaceGame(['kim', 'lee']);
        const cars = game.getCars();

        // 😱 외부에서 내부 배열을 직접 조작할 수 있음!
        cars.length = 0;

        // 내부 상태가 망가짐
        expect(game.getCars()).toHaveLength(0);
        // TODO: 일급 컬렉션으로 감싸서 이런 조작을 불가능하게 만드세요.
    });
});
