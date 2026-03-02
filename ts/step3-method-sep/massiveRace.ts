export class MassiveRace {
    private static readonly MIN_CAR_COUNT = 2;
    private static readonly MAX_NAME_LENGTH = 5;
    private static readonly MOVE_THRESHOLD = 4;
    private static readonly MIN_ROUND = 1;

    public race(namesInput: string, rounds: number, randomValues: number[]): string {
        const names = this.parseAndValidateNames(namesInput);
        this.validateRounds(rounds);

        const positions = this.initializePositions(names.length);
        this.playRounds(names, rounds, randomValues, positions);

        const winners = this.findWinners(names, positions);
        return this.formatResult(winners);
    }


    private parseAndValidateNames(namesInput: string): string[] {
        if (!namesInput || namesInput.length === 0) {
            throw new Error('자동차 이름을 입력해 주세요.');
        }

        const names = namesInput.split(',').map(name => name.trim());

        if (names.length < MassiveRace.MIN_CAR_COUNT) {
            throw new Error('자동차는 2대 이상이어야 합니다.');
        }

        for (const name of names) {
            if (name.length === 0) {
                throw new Error('자동차 이름은 비어있을 수 없습니다.');
            }

            if (name.length > MassiveRace.MAX_NAME_LENGTH) {
                throw new Error('자동차 이름은 5자를 초과할 수 없습니다.');
            }
        }

        return names;
    }

    private validateRounds(rounds: number): void {
        if (rounds < MassiveRace.MIN_ROUND) {
            throw new Error('시도 횟수는 1 이상이어야 합니다.');
        }
    }


    private initializePositions(count: number): number[] {
        return new Array(count).fill(0);
    }

    private playRounds(
        names: string[],
        rounds: number,
        randomValues: number[],
        positions: number[]
    ): void {
        let randomIndex = 0;

        for (let round = 0; round < rounds; round++) {
            for (let i = 0; i < names.length; i++) {
                if (randomValues[randomIndex] >= MassiveRace.MOVE_THRESHOLD) {
                    positions[i]++;
                }
                randomIndex++;
            }
        }
    }

    private findWinners(names: string[], positions: number[]): string[] {
        const maxPosition = Math.max(...positions);
        const winners: string[] = [];

        for (let i = 0; i < names.length; i++) {
            if (positions[i] === maxPosition) {
                winners.push(names[i]);
            }
        }

        return winners;
    }

    private formatResult(winners: string[]): string {
        return winners.join(', ') + '가 최종 우승했습니다.';
    }
}