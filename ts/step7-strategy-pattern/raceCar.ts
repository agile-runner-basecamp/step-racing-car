export class RaceCar {
    private name: string;
    private position: number;

    constructor(name: string) {
        this.name = name;
        this.position = 0;
    }

    public move(randomValue: number): void {
        if (randomValue >= 4) {
            this.position++;
        }
    }

    public getName(): string { return this.name; }
    public getPosition(): number { return this.position; }
}
