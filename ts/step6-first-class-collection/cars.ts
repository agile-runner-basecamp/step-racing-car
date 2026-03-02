// cars.ts
import { Car } from './car';

export class Cars {
    private readonly cars: Car[];

    constructor(cars: Car[]) {
        if (cars.length === 0) {
            throw new Error('자동차는 최소 1대 이상이어야 합니다.');
        }
        this.cars = cars;
    }

    public moveAll(randomValues: number[]): void {
        if (randomValues.length !== this.cars.length) {
            throw new Error('랜덤 값의 개수가 자동차 수와 일치해야 합니다.');
        }

        this.cars.forEach((car, index) => {
            car.move(randomValues[index]);
        });
    }

    public findWinners(): string[] {
        const maxPosition = Math.max(...this.cars.map(c => c.getPosition()));

        return this.cars
            .filter(c => c.getPosition() === maxPosition)
            .map(c => c.getName());
    }

    public getCars(): readonly Car[] {
        return this.cars;
    }
}