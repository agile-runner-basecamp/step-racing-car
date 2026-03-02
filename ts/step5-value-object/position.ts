export class Position {
    public readonly value: number;

    constructor(value: number) {
        if (value < 0) {
            throw new Error('위치는 0 이상이어야 합니다.');
        }

        this.value = value;
    }

    public move(): Position {
        return new Position(this.value + 1);
    }

    public equals(other: Position): boolean {
        return this.value === other.value;
    }
}