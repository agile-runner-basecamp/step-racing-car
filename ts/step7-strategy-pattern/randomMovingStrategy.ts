import { MovingStrategy } from './movingSTrategy';

export const randomMovingStrategy: MovingStrategy = () => {
    const value = Math.floor(Math.random() * 10);
    return value >= 4;
};