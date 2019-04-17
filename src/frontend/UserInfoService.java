package frontend;

public interface UserInfoService {
	
	/**find user by account**/
	public User findUser(String account);
	
	/**update user**/
	public User updateUser(User user);
}