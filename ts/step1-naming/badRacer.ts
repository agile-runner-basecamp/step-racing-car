export class Racer {

    private static readonly MOVE_THRESHOLD = 4;
    private static readonly MAX_NAME_LENGTH = 5;


    public canMove(randomValue: number): boolean {
        return randomValue >= Racer.MOVE_THRESHOLD;
    }

    public isValidName(name: string | null): boolean {
        if (name === null) return false;
        if (name.length === 0) return false;
        if (name.length > Racer.MAX_NAME_LENGTH) return false;
        return true;
    }


    public findWinners(distanceByRacer: Map<string, number>): string[] {
        let maxDistance = 0;

        for (const distance of distanceByRacer.values()) {
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        const winners: string[] = [];

        for (const [name, distance] of distanceByRacer) {
            if (distance === maxDistance) {
                winners.push(name);
            }
        }

        return winners;
    }
}