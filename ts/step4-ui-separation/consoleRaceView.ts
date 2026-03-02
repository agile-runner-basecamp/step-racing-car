import { RaceResult } from './raceEngine';

export class ConsoleRaceView {

    public print(names: string[], result: RaceResult): void {
        console.log('\n실행 결과');

        for (const round of result.rounds) {
            for (let i = 0; i < names.length; i++) {
                console.log(`${names[i]} : ${'-'.repeat(round.positions[i])}`);
            }
            console.log();
        }

        console.log(`${result.winners.join(', ')}가 최종 우승했습니다.`);
    }
}