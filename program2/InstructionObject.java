public class InstructionObject{
	
	//Variables
	String op;
	String sname;
	String oname;
	int val = 0;

	//Constructor 
	//Passed to the ReferenceMonitor
	public InstructionObject(String operation, String subject_name, 
							String object_name, int value){

		//Assign attributes of InstructionObject
		op = operation;
		sname = subject_name;
		oname = object_name;
		val = value;
	}

	/*******************************************************/

	/* METHODS FOR ACCESSING INSTRUCTION OBJECT ATTRIBUTES (may not be needed) */

	public String getOp(){

		return this.op;
	}

	public String getSname(){

		return this.sname;
	}

	public String getOname(){

		return this.oname;
	}

	public int getVal(){

		return this.val;
	}

	/*******************************************************/

	/* BAD INSTRUCTION OBJECT */

	public Object checkBadInstruction(InstructionObject object){

		//BAD INSTRUCTION
		if(object.getOp().equals("BAD")){
			BadInstruction tempBad = new BadInstruction("BAD");

			//return a BadInstruction object if the InstructionObject operation is set to BAD
			return tempBad;
		}
		else{
			//instruction is not bad just return a null object and overlook
			//can we return null?
			//changed functrion signature to OBJECT instead of BadInstruction
			return null;
		}
	}

}