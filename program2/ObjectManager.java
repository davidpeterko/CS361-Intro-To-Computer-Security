import java.util.*;

public class ObjectManager{
	TreeMap<String, SystemObject> objectMap = new TreeMap<String, SystemObject>(Collections.reverseOrder());
	
	public ObjectManager(){
	}
	
	public void write(String objName, int value){
		SystemObject temp = getObject(objName);
		temp.setValue(value);
	}
	
	public int read(String objName){
		SystemObject temp = getObject(objName);
		return temp.getValue();
	}

	public SystemObject getObject(String name){
		return objectMap.get(name.toUpperCase());
	}

	public void printSystemObject(){
		for(Map.Entry<String, SystemObject> entry: objectMap.entrySet()){
			String objName = entry.getKey();
			SystemObject sysObj = entry.getValue();

			System.out.println("   " + sysObj.getName() + " has value: " + sysObj.getValue());
		}
	}

	public void createObject(String objName, int level){
		objectMap.put(objName.toUpperCase(), new SystemObject(objName, level, 0));
	}

	public void destroyObject(String objName){
		objectMap.remove(objName.toUpperCase());

	}
}