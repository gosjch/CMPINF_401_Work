package lab11;

public class FactoryRunner
{
	public static void main(String[] args)
{
		Clock clock = new Clock();
		Boss boss = new Boss();
		Worker worker = new Worker("Joe");
		clock.registerObserver(boss);
		clock.registerObserver(worker);

		while(true)
		{
			clock.tick();
			System.out.println(clock.getTime());
			System.out.println(boss);
			System.out.println(worker);
			System.out.println();	
		}
	}
}

