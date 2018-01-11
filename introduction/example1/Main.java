package example1;

import java.util.Arrays;
import java.util.List;

public class Main {

	
	public static void main(String[] args) {
		
		int[] numbers = new int[] {123,150,170,180};
		
		List<ICalculator> calculators = Arrays.asList(
				new MaxCalculator(), 
				new CalculatorDecorator(new MinCalculator()));
		
		for(ICalculator calculator:calculators) {
			int result = calculator.calculate(numbers);
			System.out.println(calculator.getResultName()+": "+result);
		}
		
		
	}
}
