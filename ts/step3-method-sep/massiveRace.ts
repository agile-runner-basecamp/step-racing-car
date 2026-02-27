export class MassiveRace {
    public race(namesInput: string, rounds: number, randomValues: number[]): string {
        if (!namesInput || namesInput.length === 0) {
            throw new Error('자동차 이름을 입력해 주세요.');
        }
        const nameArray = namesInput.split(',');
        if (nameArray.length < 2) {
            throw new Error('자동차는 2대 이상이어야 합니다.');
        }
        for (const name of nameArray) {
            if (name.trim().length === 0) {
                throw new Error('자동차 이름은 비어있을 수 없습니다.');
            }
            if (name.trim().length > 5) {
                throw new Error('자동차 이름은 5자를 초과할 수 없습니다.');
            }
        }
        if (rounds <= 0) {
            throw new Error('시도 횟수는 1 이상이어야 합니다.');
        }
        const names = nameArray.map(n => n.trim());
        const positions = new Array(names.length).fill(0);
        let randomIndex = 0;
        for (let round = 0; round < rounds; round++) {
            for (let i = 0; i < names.length; i++) {
                if (randomValues[randomIndex] >= 4) {
                    positions[i]++;
                }
                randomIndex++;
            }
        }
        const maxPosition = Math.max(...positions);
        const winners: string[] = [];
        for (let i = 0; i < names.length; i++) {
            if (positions[i] === maxPosition) {
                winners.push(names[i]);
            }
        }
        return winners.join(', ') + '가 최종 우승했습니다.';
    }
}
