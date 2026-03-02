// race.ts
import { RaceCar } from './raceCar';
import { MovingStrategy } from './movingSTrategy';

export class Race {
    private cars: RaceCar[];
    private movingStrategy: MovingStrategy;

    constructor(names: string[], movingStrategy: MovingStrategy) {
        this.cars = names.map(name => new RaceCar(name));
        this.movingStrategy = movingStrategy;
    }

    public playRound(): void {
        for (const car of this.cars) {
            if (this.movingStrategy()) {
                car.move(4); 
            }
        }
    }

    public findWinners(): string[] {
        const maxPosition = Math.max(...this.cars.map(c => c.getPosition()));
        return this.cars
            .filter(c => c.getPosition() === maxPosition)
            .map(c => c.getName());
    }

    public getCars(): readonly RaceCar[] {
        return this.cars;
    }
}