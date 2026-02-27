import { DeepRaceValidator } from './deepRaceValidator';

describe('DeepRaceValidator', () => {
    it('랜덤 값이 4 이상이면 전진한다', () => {
        const validator = new DeepRaceValidator();
        expect(validator.move('kim', 4, 0)).toBe(1);
    });

    it('랜덤 값이 3 이하이면 정지한다', () => {
        const validator = new DeepRaceValidator();
        expect(validator.move('kim', 3, 0)).toBe(0);
    });

    it('이름이 null이면 예외가 발생한다', () => {
        const validator = new DeepRaceValidator();
        expect(() => validator.move(null, 4, 0)).toThrow();
    });

    it('이름이 5자를 초과하면 예외가 발생한다', () => {
        const validator = new DeepRaceValidator();
        expect(() => validator.move('abcdef', 4, 0)).toThrow();
    });

    it('랜덤 값이 범위를 벗어나면 예외가 발생한다', () => {
        const validator = new DeepRaceValidator();
        expect(() => validator.move('kim', 10, 0)).toThrow();
    });
});
