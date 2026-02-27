export class ConsoleRace {
    public run(names: string[], rounds: number, randomValues: number[]): void {
        if (!names || names.length < 2) {
            console.log('[ERROR] 자동차는 2대 이상이어야 합니다.');
            return;
        }
        for (const name of names) {
            if (name.length > 5) {
                console.log(`[ERROR] 자동차 이름은 5자를 초과할 수 없습니다: ${name}`);
                return;
            }
        }

        const positions = new Array(names.length).fill(0);
        let randomIndex = 0;

        console.log('\n실행 결과');

        for (let round = 0; round < rounds; round++) {
            for (let i = 0; i < names.length; i++) {
                if (randomValues[randomIndex] >= 4) {
                    positions[i]++;
                }
                randomIndex++;
            }
            for (let i = 0; i < names.length; i++) {
                console.log(`${names[i]} : ${'-'.repeat(positions[i])}`);
            }
            console.log();
        }

        const maxPosition = Math.max(...positions);
        const winners: string[] = [];
        for (let i = 0; i < names.length; i++) {
            if (positions[i] === maxPosition) {
                winners.push(names[i]);
            }
        }
        console.log(`${winners.join(', ')}가 최종 우승했습니다.`);
    }
}
