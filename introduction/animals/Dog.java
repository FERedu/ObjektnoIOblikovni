package animals;

import javax.annotation.Nonnull;

public class Dog extends ASisavac{
	
	private final @Nonnull Breed breed;
	
	public Dog(String name, int age, Breed breed) {
		super(name,age);
		this.breed = breed;
	}
	
	@Override
	public String makeNoise() {
		return "Vau vau";
	}
	
	public String makeNoise(int numRepeat) {
		String noise = "";
		for(int i=0; i<numRepeat; i++) {
			noise += "Vau vau ";
		}
		return noise;
	}
	
	
	public String makeNoise(double num) {
		return "Vau " + num;
	}
	
}
