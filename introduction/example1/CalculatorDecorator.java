package example1;

public class CalculatorDecorator implements ICalculator{

	
	private ICalculator calculator;

	public CalculatorDecorator(ICalculator calculator) {
		this.calculator = calculator;
	}
	
	@Override
	public int calculate(int[] numbers) {
		int result = calculator.calculate(numbers);
		System.out.println("Result: " + result);
		return result;
	}

	@Override
	public String getResultName() {
		return calculator.getResultName();
	}

}
