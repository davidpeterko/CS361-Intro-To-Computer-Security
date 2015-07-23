import java.util.*;
import java.io.*;
import static java.lang.System.*;


public class AES{
	
	int roundkey[][][] = new int[15][4][4];

	int sbox[][] = new int[16][16];

	int isbox[][] = new int[16][16];

	int rcon[] = new int[256];

	int inputmatrix[][] = new int[4][4];


	final static int[] LogTable = {
	0,   0,  25,   1,  50,   2,  26, 198,  75, 199,  27, 104,  51, 238, 223,   3, 
	100,   4, 224,  14,  52, 141, 129, 239,  76, 113,   8, 200, 248, 105,  28, 193, 
	125, 194,  29, 181, 249, 185,  39, 106,  77, 228, 166, 114, 154, 201,   9, 120, 
	101,  47, 138,   5,  33,  15, 225,  36,  18, 240, 130,  69,  53, 147, 218, 142, 
	150, 143, 219, 189,  54, 208, 206, 148,  19,  92, 210, 241,  64,  70, 131,  56, 
	102, 221, 253,  48, 191,   6, 139,  98, 179,  37, 226, 152,  34, 136, 145,  16, 
	126, 110,  72, 195, 163, 182,  30,  66,  58, 107,  40,  84, 250, 133,  61, 186, 
	43, 121,  10,  21, 155, 159,  94, 202,  78, 212, 172, 229, 243, 115, 167,  87, 
	175,  88, 168,  80, 244, 234, 214, 116,  79, 174, 233, 213, 231, 230, 173, 232, 
	44, 215, 117, 122, 235,  22,  11, 245,  89, 203,  95, 176, 156, 169,  81, 160, 
	127,  12, 246, 111,  23, 196,  73, 236, 216,  67,  31,  45, 164, 118, 123, 183, 
	204, 187,  62,  90, 251,  96, 177, 134,  59,  82, 161, 108, 170,  85,  41, 157, 
	151, 178, 135, 144,  97, 190, 220, 252, 188, 149, 207, 205,  55,  63,  91, 209, 
	83,  57, 132,  60,  65, 162, 109,  71,  20,  42, 158,  93,  86, 242, 211, 171, 
	68,  17, 146, 217,  35,  32,  46, 137, 180, 124, 184,  38, 119, 153, 227, 165, 
	103,  74, 237, 222, 197,  49, 254,  24,  13,  99, 140, 128, 192, 247, 112,   7};

    final static int[] AlogTable = {
	1,   3,   5,  15,  17,  51,  85, 255,  26,  46, 114, 150, 161, 248,  19,  53, 
	95, 225,  56,  72, 216, 115, 149, 164, 247,   2,   6,  10,  30,  34, 102, 170, 
	229,  52,  92, 228,  55,  89, 235,  38, 106, 190, 217, 112, 144, 171, 230,  49, 
	83, 245,   4,  12,  20,  60,  68, 204,  79, 209, 104, 184, 211, 110, 178, 205, 
	76, 212, 103, 169, 224,  59,  77, 215,  98, 166, 241,   8,  24,  40, 120, 136, 
	131, 158, 185, 208, 107, 189, 220, 127, 129, 152, 179, 206,  73, 219, 118, 154, 
	181, 196,  87, 249,  16,  48,  80, 240,  11,  29,  39, 105, 187, 214,  97, 163, 
	254,  25,  43, 125, 135, 146, 173, 236,  47, 113, 147, 174, 233,  32,  96, 160, 
	251,  22,  58,  78, 210, 109, 183, 194,  93, 231,  50,  86, 250,  21,  63,  65, 
	195,  94, 226,  61,  71, 201,  64, 192,  91, 237,  44, 116, 156, 191, 218, 117, 
	159, 186, 213, 100, 172, 239,  42, 126, 130, 157, 188, 223, 122, 142, 137, 128, 
	155, 182, 193,  88, 232,  35, 101, 175, 234,  37, 111, 177, 200,  67, 197,  84, 
	252,  31,  33,  99, 165, 244,   7,   9,  27,  45, 119, 153, 176, 203,  70, 202, 
	69, 207,  74, 222, 121, 139, 134, 145, 168, 227,  62,  66, 198,  81, 243,  14, 
	18,  54,  90, 238,  41, 123, 141, 140, 143, 138, 133, 148, 167, 242,  13,  23, 
	57,  75, 221, 124, 132, 151, 162, 253,  28,  36, 108, 180, 199,  82, 246,   1};




	public static void main(String[] args)throws FileNotFoundException, UnsupportedEncodingException, IOException {
		AES aes256 = new AES(args);

	}
	
	public AES(String[] args)throws FileNotFoundException, UnsupportedEncodingException, IOException {
		readInput(args[2]);
		makesboxs();
		makercon();
		readKeyFile(args[1]);

		aesHelp(args[2], args[0]);
	}

	public void aesHelp(String filename, String option)throws FileNotFoundException, UnsupportedEncodingException{
		if(option.equals("d")){
			PrintWriter pout = new PrintWriter(filename +".dec", "UTF-8");
			decrypt();
			String decoded = "";
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++){
					String hex = Integer.toHexString(inputmatrix[i][j]);
					hex = hex.toUpperCase();
					if(hex.length() == 1)
						hex = "0" + hex;
					decoded += hex + " ";
				}
				decoded += "\n";
			}
			pout.print(decoded);
			pout.close();
		}
		else{
			PrintWriter pout = new PrintWriter(filename +".enc", "UTF-8");
			encrypt();
			String encoded = "";
			for(int i = 0; i < 4; i++){
				for(int j = 0; j < 4; j++){
					String hex = Integer.toHexString(inputmatrix[i][j]);
					hex = hex.toUpperCase();
					if(hex.length() == 1)
						hex = "0" + hex;
					encoded += hex + " ";
				}
				encoded += "\n";
			}
			pout.print(encoded);
			pout.close();
		}
	}

	public void decrypt(){
		addroundkey(10);
		printInputMatrix();
		for(int r = 9; r > 0; r--){
			addroundkey(r);
			printInputMatrix();
			out.println("After iMixColumn:");
			for(int c = 0; c < 4; c++)
				invMixColumn2(c);
			ishiftRows();
			printInputMatrix();
			out.println("After isubBytes:");
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
					inputmatrix[i][j] = isubBytes(inputmatrix[i][j]);
			printInputMatrix();
		}
		addroundkey(0);
		printInputMatrix();

	}

	public void encrypt(){
		addroundkey(0);
		printInputMatrix();
		for(int r = 1; r < 10; r++){
			out.println("After subBytes:");
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
					inputmatrix[i][j] = subBytes(inputmatrix[i][j]);
			printInputMatrix();
			shiftRows();
			printInputMatrix();
			out.println("After mixColumn:");
			for(int c = 0; c < 4; c++)
				mixColumn2(c);
			printInputMatrix();
			addroundkey(r);
			printInputMatrix();
		}
		addroundkey(10);
		printInputMatrix();
	}





	public void readInput(String filename)throws FileNotFoundException, UnsupportedEncodingException, IOException{
		File f = new File(filename);

		Scanner fileScanner = new Scanner(new File (filename));

		int rowcounter = 0;
		while(fileScanner.hasNextLine()){

			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			int one, two, three, four, five, six, seven, eight;
			one = two = three = four = five = six = seven = eight =0;
			
			try{
				one = Integer.parseInt(lineScanner.next(), 16);

				two = Integer.parseInt(lineScanner.next(), 16);

				three = Integer.parseInt(lineScanner.next(), 16);

				four = Integer.parseInt(lineScanner.next(), 16);
			
				//five = Integer.parseInt(lineScanner.next(), 16);

				//six = Integer.parseInt(lineScanner.next(), 16);
				
				//seven = Integer.parseInt(lineScanner.next(), 16);
				
				//eight = Integer.parseInt(lineScanner.next(), 16);


			}
			catch(Exception e){
				out.println("Exception e");
			}

			inputmatrix[rowcounter][0] = one;
			inputmatrix[rowcounter][1] = two;
			inputmatrix[rowcounter][2] = three;
			inputmatrix[rowcounter][3] = four;

			//roundkey[1][rowcounter][0] = five;
			//roundkey[1][rowcounter][1] = six;
			//roundkey[1][rowcounter][2] = seven;
			//roundkey[1][rowcounter][3] = eight;


			rowcounter++;
		}

		out.println("The Plaintext is: ");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				String hex = Integer.toHexString(inputmatrix[i][j]);
				hex = hex.toUpperCase();
				if(hex.length() == 1)
					hex = "0" + hex;
				out.print(hex + " ");		
			}
			out.println("");
		}
	}



	public void readKeyFile(String filename)throws FileNotFoundException{

		Scanner fileScanner = new Scanner(new File (filename));

		int rowcounter = 0;
		while(fileScanner.hasNextLine()){

			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			int one, two, three, four, five, six, seven, eight;
			one = two = three = four = five = six = seven = eight =0;
			
			try{
				one = Integer.parseInt(lineScanner.next(), 16);

				two = Integer.parseInt(lineScanner.next(), 16);

				three = Integer.parseInt(lineScanner.next(), 16);

				four = Integer.parseInt(lineScanner.next(), 16);
			
				//five = Integer.parseInt(lineScanner.next(), 16);

				//six = Integer.parseInt(lineScanner.next(), 16);
				
				//seven = Integer.parseInt(lineScanner.next(), 16);
				
				//eight = Integer.parseInt(lineScanner.next(), 16);


			}
			catch(Exception e){
				out.println("Exception e");
			}

			roundkey[0][rowcounter][0] = one;
			roundkey[0][rowcounter][1] = two;
			roundkey[0][rowcounter][2] = three;
			roundkey[0][rowcounter][3] = four;

			//roundkey[1][rowcounter][0] = five;
			//roundkey[1][rowcounter][1] = six;
			//roundkey[1][rowcounter][2] = seven;
			//roundkey[1][rowcounter][3] = eight;


			rowcounter++;
		}

		out.println("\nThe CiperKey is: ");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
					String hex = Integer.toHexString(roundkey[0][i][j]);
					hex = hex.toUpperCase();
					if(hex.length() == 1)
						hex = "0" + hex;
					out.print(hex + " ");		
			}
			out.println();
		}



		expandroundkey();

	}

	// --------
	// makercon
	// --------
	public void makercon()throws FileNotFoundException{
		Scanner rconscanner = new Scanner(new File("rcon.txt"));


		for(int i = 0; i < 256; i++){
			int temp = Integer.parseInt(rconscanner.next().substring(2), 16);
			rcon[i] = temp;
		}
	}

	// --------
	// makesbox
	// --------
	public void makesboxs()throws FileNotFoundException{
		Scanner sboxscanner = new Scanner(new File("sbox.txt"));

		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				sbox[i][j] = Integer.parseInt(sboxscanner.next(), 16);
			}
		}

		sboxscanner = new Scanner(new File("isbox.txt"));

		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				isbox[i][j] = Integer.parseInt(sboxscanner.next(), 16);
			}
		}

	}

	//Nb = 
	//Nk = 8
	//Nr = 14


	// --------------
	// expandroundkey
	// --------------
	public void expandroundkey(){
		
		System.out.println("");
		System.out.println("The expandedd key is: ");

		for(int i = 0; i < 10; i++){
			int singlearray[] = new int[4];
				roundkey[i+1][0][0] = subBytes(roundkey[i][3][1]) ^ rcon[i+1] ^ roundkey[i][0][0];
				roundkey[i+1][0][1] = subBytes(roundkey[i][3][2]) ^ 0 ^ roundkey[i][0][1];
				roundkey[i+1][0][2] = subBytes(roundkey[i][3][3]) ^ 0 ^ roundkey[i][0][2];
				roundkey[i+1][0][3] = subBytes(roundkey[i][3][0]) ^ 0 ^ roundkey[i][0][3];


			for(int j = 1; j < 4; j++){
				roundkey[i+1][j][0] = roundkey[i+1][j-1][0] ^ roundkey[i][j][0];
				roundkey[i+1][j][1] = roundkey[i+1][j-1][1] ^ roundkey[i][j][1];
				roundkey[i+1][j][2] = roundkey[i+1][j-1][2] ^ roundkey[i][j][2];
				roundkey[i+1][j][3] = roundkey[i+1][j-1][3] ^ roundkey[i][j][3];
			}
		}
		
		for(int x = 0; x < 15; x++){
			for(int y = 0; y < 4; y++){
				for(int z = 0; z < 4; z++){
					String hex = Integer.toHexString(roundkey[x][z][y]);
					hex = hex.toUpperCase();
					if(hex.length() == 1)
						hex = "0" + hex;
					out.print(hex);
				}
				out.println();
			}
			out.println();
		}

	}

	
	// --------
	// subBytes
	// --------
	public int subBytes(int input){

		String hex = Integer.toHexString(input);
		if(hex.length() == 1)
			hex = "0" + hex;
		int one, two;

		one = Integer.parseInt(hex.charAt(0) + "", 16); 
		two = Integer.parseInt(hex.charAt(1)+ "", 16);

		return sbox[one][two];
	}

	// --------
	// isubBytes
	// --------
	public int isubBytes(int input){

		String hex = Integer.toHexString(input);
		if(hex.length() == 1)
			hex = "0" + hex;
		int one, two;

		one = Integer.parseInt(hex.charAt(0) + "", 16); 
		two = Integer.parseInt(hex.charAt(1)+ "", 16);

		return isbox[one][two];
	}



	// ---------
	// shiftRows
	// ---------
	public void ishiftRows(){
		out.println("After ishiftRows:");
		int temparray[] = new int[4];

		temparray[0] = inputmatrix[1][3];
		temparray[1] = inputmatrix[1][0];
		temparray[2] = inputmatrix[1][1];
		temparray[3] = inputmatrix[1][2];
		inputmatrix[1] = Arrays.copyOf(temparray, 4);

		temparray[0] = inputmatrix[2][2];
		temparray[1] = inputmatrix[2][3];
		temparray[2] = inputmatrix[2][0];
		temparray[3] = inputmatrix[2][1];
		inputmatrix[2] = Arrays.copyOf(temparray, 4);

		temparray[0] = inputmatrix[3][1];
		temparray[1] = inputmatrix[3][2];
		temparray[2] = inputmatrix[3][3];
		temparray[3] = inputmatrix[3][0];
		inputmatrix[3] = Arrays.copyOf(temparray, 4);

		out.println(Arrays.toString(temparray));
	}

	// ---------
	// ishiftRows
	// ---------
	public void shiftRows(){
		out.println("After shiftRows:");
		int temparray[] = new int[4];

		temparray[0] = inputmatrix[1][1];
		temparray[1] = inputmatrix[1][2];
		temparray[2] = inputmatrix[1][3];
		temparray[3] = inputmatrix[1][0];
		inputmatrix[1] = Arrays.copyOf(temparray, 4);

		temparray[0] = inputmatrix[2][2];
		temparray[1] = inputmatrix[2][3];
		temparray[2] = inputmatrix[2][0];
		temparray[3] = inputmatrix[2][1];
		inputmatrix[2] = Arrays.copyOf(temparray, 4);

		temparray[0] = inputmatrix[3][3];
		temparray[1] = inputmatrix[3][0];
		temparray[2] = inputmatrix[3][1];
		temparray[3] = inputmatrix[3][2];
		inputmatrix[3] = Arrays.copyOf(temparray, 4);

		out.println(Arrays.toString(temparray));
	}


	// -----------
	// addroundkey
	// -----------

	public void addroundkey(int round){
		out.println("After addRoundKey(" + round + "):");

		for(int i = 0; i < 4; i++){

			for(int j = 0; j < 4; j++){
				inputmatrix[i][j] = inputmatrix[i][j] ^ roundkey[round][i][j];
			}

		}




	}

	public void printInputMatrix(){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				String hex = Integer.toHexString(inputmatrix[i][j]);
				hex = hex.toUpperCase();
				if(hex.length() == 1)
					hex = "0" + hex;
				out.print(hex + " ");
			}
			out.println();
		}
	}

	private int mul (int a, int b) {
	int inda = (a < 0) ? (a + 256) : a;
	int indb = (b < 0) ? (b + 256) : b;

	if ( (a != 0) && (b != 0) ) {
	    int index = (LogTable[inda] + LogTable[indb]);
	    int val = (int)(AlogTable[ index % 255 ] );
	    return val;
	}
	else 
	    return 0;
    } // mul

    public void mixColumn2 (int c) {
	// This is another alternate version of mixColumn, using the 
	// logtables to do the computation.
	
	int a[] = new int[4];
	
	// note that a is just a copy of st[.][c]
	for (int i = 0; i < 4; i++) 
	    a[i] = inputmatrix[i][c];
	
	// This is exactly the same as mixColumns1, if 
	// the mul columns somehow match the b columns there.
	inputmatrix[0][c] = (int)(mul(2,a[0]) ^ a[2] ^ a[3] ^ mul(3,a[1]));
	inputmatrix[1][c] = (int)(mul(2,a[1]) ^ a[3] ^ a[0] ^ mul(3,a[2]));
	inputmatrix[2][c] = (int)(mul(2,a[2]) ^ a[0] ^ a[1] ^ mul(3,a[3]));
	inputmatrix[3][c] = (int)(mul(2,a[3]) ^ a[1] ^ a[2] ^ mul(3,a[0]));
    } // mixColumn2

    public void invMixColumn2 (int c) {
	int a[] = new int[4];
	
	// note that a is just a copy of st[.][c]
	for (int i = 0; i < 4; i++) 
	    a[i] = inputmatrix[i][c];
	
	inputmatrix[0][c] = (int)(mul(0xE,a[0]) ^ mul(0xB,a[1]) ^ mul(0xD, a[2]) ^ mul(0x9,a[3]));
	inputmatrix[1][c] = (int)(mul(0xE,a[1]) ^ mul(0xB,a[2]) ^ mul(0xD, a[3]) ^ mul(0x9,a[0]));
	inputmatrix[2][c] = (int)(mul(0xE,a[2]) ^ mul(0xB,a[3]) ^ mul(0xD, a[0]) ^ mul(0x9,a[1]));
	inputmatrix[3][c] = (int)(mul(0xE,a[3]) ^ mul(0xB,a[0]) ^ mul(0xD, a[1]) ^ mul(0x9,a[2]));
     } // invMixColumn2


}