package transit.people;

import java.util.Random;

import transit.core.*;

public class Passenger 
{
	private String name;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	private Stop destination;
	public Stop getDestination()
	{
		return this.destination;
	}
	public void setDestination(Stop destination)
	{
		this.destination = destination;
	}
	
	// thanks bizarre pumpkin method!
	public static String getRandomName()
	{
		String[] names = new String[] {"John","William","James","Charles","George","Frank","Joseph","Thomas","Henry","Robert","Edward","Harry","Walter","Arthur","Fred","Albert","Samuel","David","Louis","Joe","Charlie","Clarence","Richard","Andrew","Daniel","Ernest","Will","Jesse","Oscar","Lewis","Peter","Benjamin","Frederick","Willie","Alfred","Sam","Roy","Herbert","Jacob","Tom","Elmer","Carl","Lee","Howard","Martin","Michael","Bert","Herman","Jim","Francis","Harvey","Earl","Eugene","Ralph","Ed","Claude","Edwin","Ben","Charley","Paul","Edgar","Isaac","Otto","Luther","Lawrence","Ira","Patrick","Guy","Oliver","Theodore","Hugh","Clyde","Alexander","August","Floyd","Homer","Jack","Leonard","Horace","Marion","Philip","Allen","Archie","Stephen","Chester","Willis","Raymond","Rufus","Warren","Jessie","Milton","Alex","Leo","Julius","Ray","Sidney","Bernard","Dan","Jerry","Calvin","Perry","Dave","Anthony","Eddie","Amos","Dennis","Clifford","Leroy","Wesley","Alonzo","Garfield","Franklin","Emil","Leon","Nathan","Harold","Matthew","Levi","Moses","Everett","Lester","Winfield","Adam","Lloyd","Mack","Fredrick","Jay","Jess","Melvin","Noah","Aaron","Alvin","Norman","Gilbert","Elijah","Victor","Gus","Nelson","Jasper","Silas","Christopher","Jake","Mike","Percy","Adolph","Maurice","Cornelius","Felix","Reuben","Wallace","Claud","Roscoe","Sylvester","Earnest","Hiram","Otis","Simon","Willard","Irvin","Mark","Jose","Wilbur","Abraham","Virgil","Clinton","Elbert","Leslie","Marshall","Owen","Wiley","Anton","Morris","Manuel","Phillip","Augustus","Emmett","Eli","Nicholas","Wilson","Alva","Harley","Newton","Timothy","Marvin","Ross","Curtis","Edmund","Jeff","Elias","Harrison","Stanley","Columbus","Lon","Ora","Ollie","Russell","Pearl","Solomon","Arch","Asa","Clayton","Enoch","Irving","Mathew","Nathaniel","Scott","Hubert","Lemuel","Andy","Ellis","Emanuel","Joshua","Millard","Vernon","Wade","Cyrus","Miles","Rudolph","Sherman","Austin","Bill","Chas","Lonnie","Monroe","Byron","Edd","Emery","Grant","Jerome","Max","Mose","Steve","Gordon","Abe","Pete","Chris","Clark","Gustave","Orville","Jocelynn","Litzy","Makena","Abagail","Giuliana","Joyce","Libby","Lillianna","Thalia","Tia","Sarahi","Zaniyah","Kristin","Lorelai","Mattie","Taniya","Jaslyn","Gemma","Valery","Lailah","Mckinley","Micah","Deja","Frida","Brynlee","Jewel","Krista","Mira","Yamilet","Adison","Carina","Karli","Magdalena","Stephany","Charlize","Raelynn","Aliana","Cassie","Mina","Karley","Shirley","Marlie","Alani","Taniyah","Cloe","Sanai","Lina","Nola","Anabella","Dalia","Raina","Mariela","Ariella","Bria","Kamari","Monique","Ashleigh","Reina","Alia","Ashanti","Lara","Lilia","Justine","Leia","Maribel","Abigayle","Tiara","Alannah","Princess","Sydnee","Kamora","Paityn","Payten","Naima","Gretchen","Heidy","Nyasia","Livia","Marin","Shaylee","Maryjane","Laci","Nathalia","Azaria","Anabel","Chasity","Emmy","Izabelle","Denisse","Emelia","Mireya","Shea","Amiah","Dixie","Maren","Averi","Esperanza","Micaela","Selina","Alyvia","Chana","Avah","Donna","Kaylah","Ashtyn","Karsyn","Makaila","Shayna","Essence","Leticia","Miya","Rory","Desirae","Kianna","Laurel","Neveah","Amaris","Hadassah","Dania"};
		return names[(int) (Math.random()*names.length)];
	}

	public Passenger(String name, Stop currentStop) 
	{
		//implement list of random names later
		if(name == null)
		{
			 name = getRandomName();
		}
		else 
		{
			this.name = name;
		}
		
		int totalStops = 1;
		Stop tempStop = currentStop.nextStop;
		
		// Count number of stops
		while(tempStop != currentStop)
		{
			totalStops++;
			tempStop = tempStop.nextStop;
		}
		
		Random rng = new Random();
		int destinationSteps = rng.nextInt(totalStops - 1) + 1; // -1 + 1 ensures we don't pick current stop
		
		Stop destinationStop = currentStop;
		for(int i = 0; i < destinationSteps; i++)
		{
			destinationStop = destinationStop.nextStop;
		}
		
		this.destination = destinationStop;
	}
	
	public Passenger(Stop currentStop)
	{
		this(null, currentStop);
	}

}
