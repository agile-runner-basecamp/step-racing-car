import { RaceEngine, RaceResult } from './raceEngine';
import { ConsoleRaceView } from './consoleRaceView';

export class ConsoleRace {
    private readonly engine = new RaceEngine();
    private readonly view = new ConsoleRaceView();

    public run(names: string[], rounds: number, randomValues: number[]): void {
        const result = this.play(names, rounds, randomValues);
        this.view.print(names, result);
    }

    public play(
        names: string[],
        rounds: number,
        randomValues: number[]
    ): RaceResult {
        return this.engine.run(names, rounds, randomValues);
    }
}