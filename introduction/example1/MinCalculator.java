package example1;

public class MinCalculator extends ACalculator{

	@Override
	protected void initialize() {
		result = Integer.MAX_VALUE;
	}

	@Override
	protected void update(int hight) {
		if(hight<result)
			result = hight;
	}
	
	@Override
	public String getResultName() {
		return "Min";
	}

}
