export class DeepRaceValidator {
    private static readonly MAX_NAME_LENGTH = 5;
    private static readonly MOVE_THRESHOLD = 4;
    private static readonly MAX_RANDOM_VALUE = 9;
    private static readonly MIN_RANDOM_VALUE = 0;

    public move(
        name: string | null,
        randomValue: number,
        currentPosition: number
    ): number {


        if (name === null) {
            throw new Error('자동차 이름은 null일 수 없습니다.');
        }

        if (name.length === 0) {
            throw new Error('자동차 이름은 비어있을 수 없습니다.');
        }

        if (name.length > DeepRaceValidator.MAX_NAME_LENGTH) {
            throw new Error('자동차 이름은 5자를 초과할 수 없습니다.');
        }


        if (randomValue < DeepRaceValidator.MIN_RANDOM_VALUE ||
            randomValue > DeepRaceValidator.MAX_RANDOM_VALUE) {
            throw new Error('랜덤 값은 0~9 사이여야 합니다.');
        }

        
        if (randomValue < DeepRaceValidator.MOVE_THRESHOLD) {
            return currentPosition;
        }

        return currentPosition + 1;
    }
}