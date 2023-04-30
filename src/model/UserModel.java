package model;
public class UserModel {
    private String name;
    private String password;

    public UserModel(String session, String password) {
        this.name = session;
        this.password = password;
    }

    public String getNameAccount() {
        return name;
    }

    public void setNameAccount(String session) {
        this.name = session;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean	MatchAccount(String n , String p ) {
    	if(this.name.equals(n)&& this.password.equals(p)) {
    		return true;
    	}
    	return false;
    }
    
    
}
