import java.util.*;
import java.io.*;
import java.nio.charset.*;
import static java.lang.System.*;

public class Encoder{

	TreeMap<String, String> firstMap;
	TreeMap<String, String> secondMap;
	String rand,fileInput, encodedFirst, encodedSecond, decoded;
	Scanner scan;
	int loc;
	

	public static void main(String args[])throws FileNotFoundException, IOException{
		Encoder enc = new Encoder(args);	
	}

	public Encoder(String args[])throws FileNotFoundException, IOException{
		 firstMap = new TreeMap<String, String>();
		 secondMap = new TreeMap<String, String>();
		 loc = 0;

		 rand = "";
		 fileInput = "";
		 encodedFirst = "";
		 encodedSecond = "";

		 int totalfreq;
		 ArrayList<Integer> firstFreq = readInput(args[0]);
		 totalfreq = firstFreq.remove(firstFreq.size() - 1);
		 ArrayList<String> alphabet = makeFirstAlpha(firstFreq.size());
		 HuffHelp huffTree = new HuffHelp(firstFreq, alphabet);
		 
		 TreeNode firstRoot = huffTree.getTreeRoot();
		 createEncoding(firstMap, firstRoot, "");
		 out.println("\nThe first-order encoding is the following:");
		 printEncoding(firstMap);

		 ArrayList<Integer> secondFreq = secondOrder(firstFreq);
		 ArrayList<String> secondAlpha = makeSecondAlpha(firstFreq.size(), alphabet);
		 huffTree = new HuffHelp(secondFreq, secondAlpha);

		 TreeNode secondRoot = huffTree.getTreeRoot();
		 createEncoding(secondMap, secondRoot, "");
		 out.println("\nThe second-order encoding is the following:");
		 printEncoding(secondMap);

		 makeFile(Integer.parseInt(args[1]), firstFreq, totalfreq);

		 firstFileEnc();
		 fileDec(encodedFirst, firstRoot, 1);

		 secondFileEnc();
		 fileDec(encodedSecond, secondRoot, 2);
	}
	
	public void fileDec(String input, TreeNode root, int whichDec)throws FileNotFoundException, IOException{
		PrintWriter pout = new PrintWriter("testText.dec"+whichDec, "UTF-8");
		decoded = "";
		scan = new Scanner(input);
		loc = 0;
		while(loc < input.length()){
			loc = 0;
			int lastVisited = decoder(root, input, loc);
			input = input.substring(lastVisited);

		}
		pout.println(decoded);
		pout.close();
	}

	
	public int  decoder(TreeNode node, String input, int y){
		if(node.getLeft() == null && node.getRight() == null){
			decoded += node.getValue();
			return y;
		}
		if(input.charAt(y) == '0')
			return decoder(node.getLeft(), input, ++y);
		return decoder(node.getRight(), input, ++y);
	}

	public void firstFileEnc()throws FileNotFoundException, IOException{
		PrintWriter pout = new PrintWriter("testText.enc1", "UTF-8");
		for(int x = 0; x < fileInput.length(); x++)
			encodedFirst += firstMap.get(fileInput.charAt(x)+"");
		pout.println(encodedFirst);
		pout.close();

	}

	public void secondFileEnc()throws FileNotFoundException, IOException{
		PrintWriter pout = new PrintWriter("testText.enc2", "UTF-8");
		for(int x = 0; x < fileInput.length(); x+=2)
			encodedSecond += secondMap.get(fileInput.charAt(x)+""+fileInput.charAt(x+1));
		pout.println(encodedSecond);
		pout.close();

	}

	public void makeFile(int charCount, ArrayList<Integer> firstFreq, int totalfreq)throws FileNotFoundException, IOException{
		PrintWriter pout = new PrintWriter("testText", "UTF-8");
		for(int y = 0; y < firstFreq.size(); y++)
			for(int k = 0; k < firstFreq.get(y); k++)
				rand += (char)(y + 65) + "";
		for(int x = 0; x < charCount; x++)
			fileInput += randomizer(totalfreq);
		pout.println(fileInput);
		pout.close();
	}

	public String randomizer(int tot){
		Random randNum = new Random();
		return ""+ rand.charAt(randNum.nextInt(tot));
	}

	public ArrayList<String> makeFirstAlpha(int size){
		ArrayList<String> alpha = new ArrayList<String>();
		for(int x = 0; x < size; x++)
			alpha.add((char)(x + 65)+"");
		return alpha;
	}

	public ArrayList<String> makeSecondAlpha(int size, ArrayList<String> first){
		ArrayList<String> alpha = new ArrayList<String>();
		for(int x = 0; x < size; x++)
				for(int y = 0; y < size; y++)
					alpha.add(first.get(x)+first.get(y));
		return alpha;
	}

	public ArrayList<Integer> secondOrder(ArrayList<Integer> firstFreq){
			ArrayList<Integer> listOfFreq = new  ArrayList<Integer>();
			for(int x = 0; x < firstFreq.size(); x++)
				for(int y = 0; y < firstFreq.size(); y++)
					listOfFreq.add(firstFreq.get(x) * firstFreq.get(y));
			return listOfFreq;
	}


	public static ArrayList<Integer> readInput(String filename) throws FileNotFoundException, IOException{
		File f = new File(filename);
		Scanner fileScanner = new Scanner("");

		 ArrayList<Integer> listOfFreq = new  ArrayList<Integer>();


		try{
			fileScanner = new Scanner(f);
		}
		catch(Exception e){
			System.out.println("Input file not found");
		}

		int totalfreq = 0;

		//this grabs the next frequency value
		while(fileScanner.hasNextInt()){
			int frequency = fileScanner.nextInt();

			listOfFreq.add(frequency);
			totalfreq += frequency;
		}

		listOfFreq.add(totalfreq);

		return listOfFreq;
	}

	public static void createEncoding(TreeMap<String, String> inputMap, TreeNode node, String code){
		if(node.getLeft() != null)
			createEncoding(inputMap, node.getLeft(), code + "0");
		if(node.getLeft() == null && node.getRight() == null){
			inputMap.put(node.getValue(), code);
			return;
		}
		createEncoding(inputMap, node.getRight(), code + "1");
	}

	public static void printEncoding(TreeMap<String, String> inputMap){
		for(Map.Entry<String, String> entry: inputMap.entrySet())
			out.println(entry.getKey() + " " + entry.getValue());
		out.println();
	}




}