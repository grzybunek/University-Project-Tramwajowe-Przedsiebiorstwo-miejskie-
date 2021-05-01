package bdbt2;

import java.util.Properties;

public class Login {

	private String username;
	private String password;
	private String permissions;

	/* constructor with field */
	public Login() {
		super();
	}

	public Login(String username, String password, String permissions) {
		super();
		this.username = username;
		this.password = password;
		this.permissions = permissions;
	}

	/* Getters and setters */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	/* toString() method */
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", permissions=" + permissions + "]";
	}
	
	public void checkPermissions() {
    	Properties prop=LoginProperties.load();
    	String[] temp= {};
    	try {
        	temp=prop.get(username).toString().split(" ");
    	} catch(java.lang.NullPointerException e) {	
    		this.permissions="b";
    		return;
    	}
    	String password= temp[0];
    	String permissions= temp[1];
    	
    	if(password.equals(this.password)) {
    		this.permissions=permissions;
    		return;
    	}
	}
	public void saveToFile() {
		Properties prop = LoginProperties.load();
		LoginProperties.write(prop, username, password, permissions);
	}			
	
	

}
