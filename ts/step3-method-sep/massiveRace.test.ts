import { MassiveRace } from './massiveRace';

describe('MassiveRace', () => {
    it('테스트A', () => {
        const r = new MassiveRace();
        const result = r.race('kim,lee,park', 2, [4, 3, 4, 4, 3, 4]);
        expect(result).toBe('kim, park가 최종 우승했습니다.');
    });

    it('테스트B', () => {
        const r = new MassiveRace();
        const result = r.race('kim,lee,park', 1, [4, 4, 4]);
        expect(result).toBe('kim, lee, park가 최종 우승했습니다.');
    });

    it('테스트C', () => {
        const r = new MassiveRace();
        expect(() => r.race('kim', 1, [4])).toThrow();
    });

    it('테스트D', () => {
        const r = new MassiveRace();
        expect(() => r.race('kim,abcdef', 1, [4, 4])).toThrow();
    });
});
