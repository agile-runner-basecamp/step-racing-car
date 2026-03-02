export interface RaceRoundResult {
    round: number;
    positions: number[];
}

export interface RaceResult {
    rounds: RaceRoundResult[];
    winners: string[];
}

export class RaceEngine {
    private static readonly MIN_CAR_COUNT = 2;
    private static readonly MAX_NAME_LENGTH = 5;
    private static readonly MOVE_THRESHOLD = 4;

    public run(
        names: string[],
        rounds: number,
        randomValues: number[]
    ): RaceResult {

        this.validateNames(names);

        const positions = new Array(names.length).fill(0);
        const roundResults: RaceRoundResult[] = [];

        let randomIndex = 0;

        for (let round = 0; round < rounds; round++) {
            for (let i = 0; i < names.length; i++) {
                if (randomValues[randomIndex] >= RaceEngine.MOVE_THRESHOLD) {
                    positions[i]++;
                }
                randomIndex++;
            }

            roundResults.push({
                round: round + 1,
                positions: [...positions], // 불변성 유지
            });
        }

        const winners = this.findWinners(names, positions);

        return {
            rounds: roundResults,
            winners,
        };
    }

    private validateNames(names: string[]): void {
        if (!names || names.length < RaceEngine.MIN_CAR_COUNT) {
            throw new Error('자동차는 2대 이상이어야 합니다.');
        }

        for (const name of names) {
            if (name.length > RaceEngine.MAX_NAME_LENGTH) {
                throw new Error(`자동차 이름은 5자를 초과할 수 없습니다: ${name}`);
            }
        }
    }

    private findWinners(names: string[], positions: number[]): string[] {
        const max = Math.max(...positions);
        return names.filter((_, i) => positions[i] === max);
    }
}