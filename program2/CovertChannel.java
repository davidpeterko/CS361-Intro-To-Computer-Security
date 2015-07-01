import java.util.*;
import java.io.*;
import static java.lang.System.*;



public class CovertChannel{
	
	//main
	public static void main (String args[])throws IOException{
		SecureSystem sys = new SecureSystem();

		//create Lyle and Hal		
		sys.createSubject("Lyle", "low");
		sys.createSubject("Hal", "high");
		

		//start clock
		long starttime = currentTimeMillis();
		if(args[0].equals("v")){
			//do verbose option
			//you should log the instructions that you generate one per line to a file called "log"
			sys.readInput(args[1], true);
		}
		else{
			sys.readInput(args[0], false);
		}

		//end clock
		long endtime = currentTimeMillis();

		//size of file / clock time = bandwidth (in m/s)
		long totaltime = endtime - starttime;
		System.out.println("start time: " + starttime + " and the end time: " + endtime + " and the total time: " + totaltime);
	
		int temp = sys.sizeoffile * 8;
		float bandwidth = temp/(float)totaltime;
		bandwidth /= 1000;

		System.out.println("the size of file in byte: " + sys.sizeoffile);
		System.out.println("the size of file in bits: " + temp);
		System.out.println("Bandwidth: " + bandwidth + " bits/ms (bits per microsecond).");

	}



	//To send a 0: 
	//RUN HAL
	//CREATE HAL OBJ

	//To send a 1:
	//RUN HAL











}