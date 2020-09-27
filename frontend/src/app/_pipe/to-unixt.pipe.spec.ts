import { ToUnixtPipe } from './to-unixt.pipe';

describe('ToUnixtPipe', () => {
  it('create an instance', () => {
    const pipe = new ToUnixtPipe();
    expect(pipe).toBeTruthy();
  });
});
