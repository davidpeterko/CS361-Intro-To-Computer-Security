import java.util.*;

public class ReferenceMonitor{
	private ObjectManager objMan;
	private HashMap<String, SystemSubject> subjectList;
	
	public ReferenceMonitor(){
		objMan = new ObjectManager();
		subjectList = new HashMap<String, SystemSubject>();
	}
	
	public void executeWrite(String subjName, String objName, int value){
		if(subjectList.get(subjName.toUpperCase()).getSecurityLevel() <= objMan.getObject(objName).getSecurityLevel()){
			objMan.write(objName, value);
		}
		//System.out.println(subjName + " writes value " + value + " to " + objName);
		//printState();
	}
	
	public void executeRead(String subjName, String objName){
		SystemSubject currSubj = subjectList.get(subjName.toUpperCase());
		if(currSubj.getSecurityLevel() >= objMan.getObject(objName).getSecurityLevel()){
			currSubj.setTemp(objMan.read(objName));
		}
		else{
			currSubj.setTemp(0);
		}
		//System.out.println(subjName + " reads " + objName);
		//printState();		
	}
	
	public void createSubject(String subjName, String level){
		subjectList.put(subjName.toUpperCase(), new SystemSubject(subjName, level));
	}

	public void printState(){
		System.out.println("The current state is:");
		objMan.printSystemObject();
		for(Map.Entry<String, SystemSubject> entry: subjectList.entrySet()){
			String subjName = entry.getKey();
			SystemSubject sysSubj = entry.getValue();
			System.out.println("   " + sysSubj.getName() + " has recently read: " + sysSubj.lastRead());
		}
		System.out.println();
	}

	public void printBad(BadInstruction badInst){
		System.out.println("Bad Instruction");
		//printState();
	}


	public void createObject(String name, int level){
		objMan.createObject(name, level);
	}

	public void executeCreate(String subjName, String objName, int value){
		//check that object does not exist
		if(objMan.getObject(objName) == null){
			//create the object with the name and level
			createObject(objName, subjectList.get(subjName.toUpperCase()).getSecurityLevel());
		}
	}


	public void executeDestroy(String subjName, String objName, int value){
		//check if object exists
		if(objMan.getObject(objName) != null){
			//check that object level > than subject so can right
			if(subjectList.get(subjName.toUpperCase()).getSecurityLevel() <= objMan.getObject(objName).getSecurityLevel()){

				objMan.destroyObject(objName);
			}
		}
	}

	public String executeRun(String subjName){
		//grab subject name from map, and then run run ont he SystemSubject
		return subjectList.get(subjName).run();

	}

}