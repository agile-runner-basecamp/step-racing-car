import { Car } from './rawCar';
import { CarName } from './carName';
import { Position } from './position';

describe('Car', () => {

    it('자동차는 CarName 객체를 가진다', () => {
        const car = new Car(new CarName('kim'));
        expect(car.getName().value).toBe('kim');
    });

    it('랜덤 값이 4 이상이면 전진한다', () => {
        const car = new Car(new CarName('kim'));

        car.move(4);

        expect(car.getPosition().value).toBe(1);
    });

    it('랜덤 값이 3 이하이면 정지한다', () => {
        const car = new Car(new CarName('kim'));

        car.move(3);

        expect(car.getPosition().value).toBe(0);
    });
});

describe('CarName (Value Object)', () => {

    it('5자를 초과하면 예외가 발생한다', () => {
        expect(() => new CarName('abcdef')).toThrow();
    });

    it('빈 문자열이면 예외가 발생한다', () => {
        expect(() => new CarName('')).toThrow();
    });

    it('값이 같으면 동등하다', () => {
        const name1 = new CarName('kim');
        const name2 = new CarName('kim');

        expect(name1.equals(name2)).toBe(true);
    });

    it('값이 다르면 동등하지 않다', () => {
        const name1 = new CarName('kim');
        const name2 = new CarName('lee');

        expect(name1.equals(name2)).toBe(false);
    });
});
describe('Position (Value Object)', () => {

    it('0 이상만 허용한다', () => {
        expect(() => new Position(-1)).toThrow();
    });

    it('increase(move)는 새로운 Position을 반환한다', () => {
        const position = new Position(0);
        const moved = position.move();

        expect(position.value).toBe(0);  
        expect(moved.value).toBe(1);     
    });

    it('같은 값이면 동등하다', () => {
        const p1 = new Position(2);
        const p2 = new Position(2);

        expect(p1.equals(p2)).toBe(true);
    });
});