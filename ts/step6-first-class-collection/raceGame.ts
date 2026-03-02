
import { Car } from './car';
import { Cars } from './cars';

export class RaceGame {
    private readonly cars: Cars;

    constructor(names: string[]) {
        const carInstances = names.map(name => new Car(name));
        this.cars = new Cars(carInstances);
    }

    public moveAll(randomValues: number[]): void {
        this.cars.moveAll(randomValues);
    }

    public findWinners(): string[] {
        return this.cars.findWinners();
    }

    public getCars(): readonly Car[] {
        return this.cars.getCars();
    }
}