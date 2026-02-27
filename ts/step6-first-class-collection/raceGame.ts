import { Car } from './car';

export class RaceGame {
    private cars: Car[];

    constructor(names: string[]) {
        this.cars = names.map(name => new Car(name));
    }

    // 😱 내부 컬렉션을 그대로 반환 — 외부에서 직접 조작 가능!
    public getCars(): Car[] {
        return this.cars;
    }

    public moveAll(randomValues: number[]): void {
        for (let i = 0; i < this.cars.length; i++) {
            this.cars[i].move(randomValues[i]);
        }
    }

    public findWinners(): string[] {
        const maxPosition = Math.max(...this.cars.map(c => c.getPosition()));
        return this.cars
            .filter(c => c.getPosition() === maxPosition)
            .map(c => c.getName());
    }
}
