import { CarName } from './carName';
import { Position } from './position';

export class Car {
    private readonly name: CarName;
    private position: Position;

    private static readonly MOVE_THRESHOLD = 4;

    constructor(name: CarName) {
        this.name = name;
        this.position = new Position(0);
    }

    public move(randomValue: number): void {
        if (randomValue >= Car.MOVE_THRESHOLD) {
            this.position = this.position.move();
        }
    }

    public getName(): CarName {
        return this.name;
    }

    public getPosition(): Position {
        return this.position;
    }
}