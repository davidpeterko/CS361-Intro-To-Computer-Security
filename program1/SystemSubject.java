public class SystemSubject{
	private String subjectName;
	private SecurityLevel level;
	private int temp;
	
	public SystemSubject(String subjName, String lvl){
		subjectName = subjName;
		level = new SecurityLevel(lvl);
		temp = 0;
	}
	
	public String getName(){
		return subjectName;
	}
	
	public int lastRead(){
		return temp;
	}
	
	public void setTemp(int lastRead){
		temp = lastRead;
	}
	
	public int getSecurityLevel(){
		return level.getLevel();
	}
}