import java.util.*;
import java.io.*;
import java.nio.charset.*;
import static java.lang.System.*;

public class PasswordCrack{
	
	static ArrayList<String> dictionary = new ArrayList<String>();
	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<String> cracked = new ArrayList<String>();
	static ArrayList<String> notcracked = new ArrayList<String>();

	//constructor
	public PasswordCrack(){
		//initialize variables


	}	


	public static void main(String args[]) throws IOException{


		PasswordCrack pw = new PasswordCrack();

		pw.readDictionary(args[0]);
		pw.readPassword(args[1]);

		for(int i = 0; i < users.size(); i++){
			User temp = users.get(i);
			int sizeofcrack = cracked.size();

			//System.out.println("For user: " + temp.name);

			String thispw = temp.password;

			HashMap<String, String> listofdictionaryencryption = encrypt(dictionary, temp);
			
			for(Map.Entry<String, String> entry : listofdictionaryencryption.entrySet()){

				String encryptedmapkey = entry.getKey();
				String usersencryptedpw = temp.password;
				String originalpassword = entry.getValue();
				if(encryptedmapkey.equals(usersencryptedpw)){
					cracked.add(originalpassword);
				}
			}

			if(sizeofcrack == cracked.size()){
				notcracked.add(thispw);
			}
		}

		System.out.println("I can crack " + cracked.size() + " cases.");
		System.out.println("List of cracked:");
		for(int i = 0; i < cracked.size(); i++){
			System.out.println(cracked.get(i));
		}

		System.out.println();
		System.out.println("I can not crack " + notcracked.size() + " cases.");
		System.out.println("List of uncracked:");
		for(int i = 0; i < notcracked.size(); i++){
			System.out.println(notcracked.get(i));
		}


	}

	public void readDictionary(String filename) throws IOException{
		File input = new File(filename + ".txt");

		Scanner fileScanner = new Scanner(input);

		dictionary = new ArrayList<String>(391);

		while(fileScanner.hasNext()){

			dictionary.add(fileScanner.next());
		}
	}

	public void readPassword(String filename) throws IOException{
		File input = new File(filename + ".txt");

		Scanner fileScanner = new Scanner(input);

		users = new ArrayList<User>(50);

		while(fileScanner.hasNext()){
			String temp = fileScanner.nextLine();
			String[] info = temp.split(":");

			String username = info[0];
			String password = info[1];
			String salt = password.substring(0,2);

			//System.out.println("username : " + username + " password: " + password + " salt: " + salt);

			/*
			for(int i = 0; i < info.length; i++){
				System.out.println(info[i]);
			}
	
			michael
			atbWfKL4etk4U
			500
			500
			Michael Ferris
			/home/michael
			/bin/bash

			*/

			User thisuser = new User(username, password, salt);

			users.add(thisuser);
		}	
	}

	public static HashMap<String, String> encrypt(ArrayList<String> dictionary, User u){
		HashMap<String, String> returnmap = new HashMap<String,String>();

		jcrypt jc = new jcrypt();

		String salt = u.salt;

		for(int i = 0; i < dictionary.size(); i++){
			//System.out.println(dictionary.get(i));

			String dicword = dictionary.get(i);
			//System.out.println("For this iteration of the loop, the salt is: " + salt + " and the dictionary word is: " + dicword);

			String epass = jc.crypt(salt, dicword);
			//System.out.println("The epass is: " + epass);
			returnmap.put(epass, dicword);
		}

		/*
		for(Map.Entry<String, String> entry : returnmap.entrySet()){
			System.out.println("The original password: " + entry.getValue() + " and the encrypted is: " + entry.getKey());
		}
		*/
		return returnmap;
	}




}