export class CarName {
    private static readonly MAX_LENGTH = 5;

    public readonly value: string;

    constructor(value: string) {
        if (!value || value.length === 0) {
            throw new Error('자동차 이름은 비어있을 수 없습니다.');
        }

        if (value.length > CarName.MAX_LENGTH) {
            throw new Error('자동차 이름은 5자를 초과할 수 없습니다.');
        }

        this.value = value;
    }

    public equals(other: CarName): boolean {
        return this.value === other.value;
    }
}