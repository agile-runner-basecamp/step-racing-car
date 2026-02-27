import { BadRacer } from './badRacer';

describe('BadRacer', () => {
    it('테스트1', () => {
        const r = new BadRacer();
        expect(r.doM(4)).toBe(true);
    });

    it('테스트2', () => {
        const r = new BadRacer();
        expect(r.doM(3)).toBe(false);
    });

    it('테스트3', () => {
        const r = new BadRacer();
        expect(r.chk('kim')).toBe(true);
    });

    it('테스트4', () => {
        const r = new BadRacer();
        expect(r.chk('abcdef')).toBe(false);
    });

    it('테스트5', () => {
        const r = new BadRacer();
        const c = new Map<string, number>();
        c.set('kim', 3);
        c.set('lee', 2);
        c.set('park', 3);
        const w = r.doW(c);
        expect(w).toEqual(expect.arrayContaining(['kim', 'park']));
        expect(w).toHaveLength(2);
    });
});
