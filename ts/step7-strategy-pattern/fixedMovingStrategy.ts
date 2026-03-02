import { MovingStrategy } from './movingSTrategy';

export const fixedMovingStrategy = (shouldMove: boolean): MovingStrategy =>
    () => shouldMove;