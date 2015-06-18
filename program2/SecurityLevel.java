public class SecurityLevel{
	private static int HIGH = 1;
	private static int LOW = 0;
	private int level;
	
	public SecurityLevel(String lvl){
		if(lvl.toUpperCase().equals("HIGH"))
			level = HIGH;
		else 
			if(lvl.toUpperCase().equals("LOW"))
				level = LOW;
			else 
				level = -1;
	}
	
	public SecurityLevel(int lvl){
		level = lvl;
	}


	public int getLevel(){
		return level;
	}
}