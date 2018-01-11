package example1;

public abstract class ACalculator implements ICalculator{

	protected int result = 0;
	
	@Override
	public final int calculate(int[] numbers) {
		initialize();
		for(int hight:numbers) {
			update(hight);
		}
		return getResult();
	}

	protected abstract void initialize();
	protected abstract void update(int hight);
	
	private int getResult() {
		return result;
	}
}
