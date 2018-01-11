package animals;

public class Cat extends ASisavac{

	public Cat(String name, int age) {
		super(name, age);
	}
	
	@Override
	public String makeNoise() {
		return "Mijau";
	}

}
