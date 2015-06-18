import java.util.*;
import java.io.*;
import java.nio.charset.*;
import static java.lang.System.*;

public class SecureSystem{
	
	int sizeoffile;

	ReferenceMonitor refMon;
	BadInstruction badInst;
	
	public static void main (String args[])throws IOException{
		SecureSystem sys = new SecureSystem();
		System.out.println("\nReading from file: " + args[0] + "\n");
				
		sys.createSubject("Lyle", "low");
		sys.createSubject("Hal", "high");

		sys.getRefMon().createObject("LObj", 0);
		sys.getRefMon().createObject("HObj", 1);
		
		sys.readInstructionFile(args[0]);
	}

	public SecureSystem(){
		badInst = new BadInstruction("BAD");
		refMon = new ReferenceMonitor();
		sizeoffile = 0;
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
						if(instruction.toLowerCase().equals("create"))
							create(new InstructionObject(instruction, subjectName, objectName, 0));
							else
								if(instruction.toLowerCase().equals("destroy"))
									destroy(new InstructionObject(instruction, subjectName, objectName, 0));
									else 
										if(instruction.toLowerCase().equals("run"))
											run(new InstructionObject(instruction, subjectName, objectName, 0));
											else
												throw new Exception();
			}
			catch(Exception e){
				refMon.printBad(badInst);
			}
					
	
		}
	}

	public void readInput(String filename, boolean verbose)throws FileNotFoundException, UnsupportedEncodingException, IOException{
		File f = new File(filename);
		PrintWriter logFile = new PrintWriter("log", "UTF-8");
		FileOutputStream outFile = new FileOutputStream(filename + ".out");


		sizeoffile = (int) f.length();
		Scanner fileScanner = new Scanner("");

		try{
			fileScanner = new Scanner(f);

		}
		catch(Exception e){
			System.out.println("found not found.");
		}
		//reading from input
		while(fileScanner.hasNextLine()){

			byte[] bytearray = fileScanner.nextLine().getBytes();

			for(int i = 0; i < bytearray.length; i++){
				
				String result = "";

				byte current_Byte = bytearray[i];
				result = String.format("%8s", Integer.toBinaryString(current_Byte & 0xFF)).replace(' ', '0');			

				for(int j = 0; j < result.length(); j++){
					if(result.charAt(j) == '0'){
						create(new InstructionObject("create", "HAL", "temporary", 0));
						if(verbose)
							logFile.println("CREATE HAL OBJ");

					} 

					create(new InstructionObject("create", "LYLE", "temporary", 0));
					write(new InstructionObject("write", "LYLE", "temporary", 1));
					read(new InstructionObject("read", "LYLE", "temporary", 0));
					destroy(new InstructionObject("destroy", "LYLE", "temporary", 0));

					String line = run(new InstructionObject("run", "LYLE", "temporary", 0));

					if(line.length() == 8){
						//byte bte[] = line.getBytes(Charset.forName("UTF-8"));
						//byte bte[] = line.getBytes();
						byte bte = (byte)Integer.parseInt(line, 2);
						outFile.write(bte);
					} 

					if(verbose){
						logFile.println("CREATE LYLE OBJ");
						logFile.println("WRITE LYLE OBJ 1");
						logFile.println("READ LYLE OBJ");
						logFile.println("DESTROY LYLE OBJ");
						logFile.println("RUN LYLE OBJ");
					}

				}

				//sizeoffile++;

				
			}
		}

		outFile.close();
		logFile.close();
	}

	
	
	public void createSubject(String subjName, String level){
		refMon.createSubject(subjName, level);
	}


	public void read(InstructionObject insObj){
		refMon.executeRead(insObj.getSname(), insObj.getOname());
	}
	
	public void write(InstructionObject insObj){
		refMon.executeWrite(insObj.getSname(), insObj.getOname(), insObj.getVal());
	}

	//project 2

	
	public void create(InstructionObject insObj){
		refMon.executeCreate(insObj.getSname(), insObj.getOname(), insObj.getVal());
	}

	public void destroy(InstructionObject insObj){
		refMon.executeDestroy(insObj.getSname(), insObj.getOname(), insObj.getVal());
	}

	public String run(InstructionObject insObj){
		return refMon.executeRun(insObj.getSname());
	}

	public ReferenceMonitor getRefMon(){
		return refMon;
	}




}