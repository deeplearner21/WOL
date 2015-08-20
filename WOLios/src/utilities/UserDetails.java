package utilities;

public class UserDetails{
	
	private String userName;
	private String password;
	private String roleName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", password=" + password
				+ ", roleName=" + roleName + "]";
	}
	
	
	
}
