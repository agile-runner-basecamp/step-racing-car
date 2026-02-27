import { RaceCar } from './raceCar';

export class HardcodedRace {
    private cars: RaceCar[];

    constructor(names: string[]) {
        this.cars = names.map(name => new RaceCar(name));
    }

    // 😱 Math.random()이 메서드 내부에 하드코딩 — 테스트에서 결과를 예측할 수 없음!
    public playRound(): void {
        for (const car of this.cars) {
            const value = Math.floor(Math.random() * 10);
            car.move(value);
        }
    }

    public findWinners(): string[] {
        const maxPosition = Math.max(...this.cars.map(c => c.getPosition()));
        return this.cars
            .filter(c => c.getPosition() === maxPosition)
            .map(c => c.getName());
    }
}
