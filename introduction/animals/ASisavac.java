package animals;

public abstract class ASisavac implements IAnimal{

	
	private String name;
	private int age;

	public ASisavac(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

}
