export class RawCar {
    private name: string;
    private position: number;

    constructor(name: string) {
        if (!name || name.length === 0) {
            throw new Error('자동차 이름은 비어있을 수 없습니다.');
        }
        if (name.length > 5) {
            throw new Error('자동차 이름은 5자를 초과할 수 없습니다.');
        }
        this.name = name;
        this.position = 0;
    }

    public move(randomValue: number): void {
        if (randomValue >= 4) {
            this.position++;
        }
    }

    public getName(): string {
        return this.name;
    }

    public getPosition(): number {
        return this.position;
    }
}
