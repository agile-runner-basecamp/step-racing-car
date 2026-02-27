import { ConsoleRace } from './consoleRace';

// TODO: 이 테스트를 어떻게 짜야 할지 막막할 것입니다!
// ConsoleRace가 값을 반환하지 않고 console.log만 하고 있기 때문입니다.
// 도메인 로직을 분리하여, 반환값으로 검증할 수 있도록 리팩토링하세요.
describe('ConsoleRace', () => {
    it('출력(console.log)에 의존하는 코드는 테스트하기 어렵다', () => {
        const race = new ConsoleRace();
        const names = ['kim', 'lee', 'park'];
        const randomValues = [4, 3, 5, 4, 4, 3];

        // 실행은 되지만 결과를 검증할 방법이 없습니다!
        race.run(names, 2, randomValues);

        // TODO: 도메인 로직을 분리하여 아래와 같은 검증이 가능해져야 합니다.
        // const result = race.play(names, 2, randomValues);
        // expect(result.winners).toEqual(expect.arrayContaining(['kim', 'park']));
    });
});
