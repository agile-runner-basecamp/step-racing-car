export class BadRacer {
    public doM(r: number): boolean {
        return r >= 4;
    }

    public chk(n: string | null): boolean {
        if (n === null) return false;
        if (n.length > 5) return false;
        if (n.length === 0) return false;
        return true;
    }

    public doW(c: Map<string, number>): string[] {
        let m = 0;
        for (const [, v] of c) {
            if (v > m) m = v;
        }
        const w: string[] = [];
        for (const [k, v] of c) {
            if (v === m) w.push(k);
        }
        return w;
    }
}
