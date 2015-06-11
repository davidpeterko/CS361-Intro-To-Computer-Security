public class SystemObject{
	private String objectName;
	private SecurityLevel level;
	private int value;
	
	public SystemObject(String objName, String lvl, int val){
		objectName = objName;
		level = new SecurityLevel(lvl);
		value = val;
	}
	
	public String getName(){
		return objectName;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int writeValue){
		value = writeValue;
	}
	
	public int getSecurityLevel(){
		return level.getLevel();
	}
}