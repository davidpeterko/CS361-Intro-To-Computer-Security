import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class SecureSystem{
	
	ReferenceMonitor refMon;
	BadInstruction badInst;
	
	public static void main (String args[])throws IOException{
		SecureSystem sys = new SecureSystem();
		System.out.println("\nReading from file: " + args[0] + "\n");
				
		sys.createSubject("Lyle", "low");
		sys.createSubject("Hal", "high");

		sys.getRefMon().createObject("LObj", "low");
		sys.getRefMon().createObject("HObj", "high");
		
		sys.readInstructionFile(args[0]);
	}

	public SecureSystem(){
		badInst = new BadInstruction("BAD");
		refMon = new ReferenceMonitor();
	}
	
	public void readInstructionFile(String fileName) throws IOException{
		File instructionFile = new File(fileName + ".txt");
		Scanner fileScanner = new Scanner(instructionFile);
		
		while(fileScanner.hasNextLine()){
			Scanner lineScanner = new Scanner(fileScanner.nextLine());

			try{
				String instruction = lineScanner.next();
				String subjectName = lineScanner.next();
				String objectName = lineScanner.next();
				int value = 0; 
				
				if(instruction.toLowerCase().equals("write")){
					value = lineScanner.nextInt();	
	 				write(new InstructionObject(instruction, subjectName, objectName, value));				
				}
				else
					if(instruction.toLowerCase().equals("read"))
						read(new InstructionObject(instruction, subjectName, objectName, 0));
					else
						throw new Exception();
			}
			catch(Exception e){
				refMon.printBad(badInst);
			}
					
	
		}
	}
	
	public void read(InstructionObject insObj){
		refMon.executeRead(insObj.getSname(), insObj.getOname());
	}
	
	public void write(InstructionObject insObj){
		refMon.executeWrite(insObj.getSname(), insObj.getOname(), insObj.getVal());
	}
	
	public void createSubject(String subjName, String level){
		refMon.createSubject(subjName, level);
	}

	public ReferenceMonitor getRefMon(){
		return refMon;
	}
}