package example1;

import java.util.Arrays;

public class MaxCalculator extends ACalculator{


	@Override
	protected void initialize() {
		result = 0;
	}

	@Override
	protected void update(int hight) {
		if(hight>result)
			result = hight;
	}
	
	@Override
	public String getResultName() {
		return "Max";
	}
}
