import { Racer } from './badRacer';

describe('Racer', () => {
    let racer: Racer;

    beforeEach(() => {
        racer = new Racer();
    });

    describe('canMove', () => {
        it('랜덤 값이 4 이상이면 전진한다', () => {
            expect(racer.canMove(4)).toBe(true);
        });

        it('랜덤 값이 4 미만이면 전진하지 않는다', () => {
            expect(racer.canMove(3)).toBe(false);
        });
    });

    describe('isValidName', () => {
        it('이름이 1자 이상 5자 이하이면 유효하다', () => {
            expect(racer.isValidName('kim')).toBe(true);
        });

        it('이름이 6자 이상이면 유효하지 않다', () => {
            expect(racer.isValidName('abcdef')).toBe(false);
        });
    });

    describe('findWinners', () => {
        it('가장 많이 이동한 레이서를 모두 반환한다 (공동 우승 포함)', () => {
            const distanceByRacer = new Map<string, number>();
            distanceByRacer.set('kim', 3);
            distanceByRacer.set('lee', 2);
            distanceByRacer.set('park', 3);

            const winners = racer.findWinners(distanceByRacer);

            expect(winners).toEqual(expect.arrayContaining(['kim', 'park']));
            expect(winners).toHaveLength(2);
        });
    });
});