public class SystemSubject{
	private String subjectName;
	private SecurityLevel level;
	private int temp;
	private String byteString;
	
	public SystemSubject(String subjName, String lvl){
		subjectName = subjName;
		level = new SecurityLevel(lvl);
		temp = 0;
		byteString = "";
	}

	public String run(){
		if(temp == 1){
			byteString += "1";
		}
		else if(temp == 0){
			byteString += "0";
		}

		if(byteString.length() == 8){
			String temp = byteString;
			byteString = "";
			return temp;
		}

		return byteString;

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