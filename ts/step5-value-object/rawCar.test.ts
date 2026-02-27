import { RawCar } from './rawCar';

describe('RawCar', () => {
    it('자동차는 이름을 가진다', () => {
        const car = new RawCar('kim');
        expect(car.getName()).toBe('kim');
    });

    it('자동차 이름이 5자를 초과하면 예외가 발생한다', () => {
        expect(() => new RawCar('abcdef')).toThrow();
    });

    it('자동차 이름이 비어있으면 예외가 발생한다', () => {
        expect(() => new RawCar('')).toThrow();
    });

    it('랜덤 값이 4 이상이면 전진한다', () => {
        const car = new RawCar('kim');
        car.move(4);
        expect(car.getPosition()).toBe(1);
    });

    it('랜덤 값이 3 이하이면 정지한다', () => {
        const car = new RawCar('kim');
        car.move(3);
        expect(car.getPosition()).toBe(0);
    });

    // TODO: 원시값을 포장(Value Object)한 뒤 아래 테스트를 추가하세요:
    // - CarName의 동등성 검증: 같은 이름이면 같은 객체
    // - Position의 increase() 동작 검증
    // - Position이 음수가 될 수 없다는 검증
});
