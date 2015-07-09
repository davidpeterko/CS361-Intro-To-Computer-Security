


public class User{
	
	String name;
	String salt;
	String password;

	public User(String username, String pw, String s){

		name = username;
		salt = s;
		password = pw;
	}
	
}