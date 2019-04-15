package frontend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static protected List<User> userList = new ArrayList<User>();
	static {
		userList.add(new User("anonymous","1234","Anonymous","anonymous@your.com"));
		userList.add(new User("admin","1234","Admin","admin@your.com"));
		userList.add(new User("zkoss","1234","ZKOSS","info@your.org"));
	}
	
	/** synchronized is just because of the static userList to prevent concurrent access **/
	@Override
	public synchronized User findUser(String account) {
		int s = userList.size();
		for(int i = 0; i < s; i++) {
			User u = userList.get(i);
			if(account.equals(u.getAccount())) {
				return User.clone(u);
			}
		}
		return null;
	}

	/** synchronized is just because of the static userList to prevent concurrent access **/
	@Override
	public synchronized User updateUser(User user) {
		int s = userList.size();
		for(int i = 0; i < s; i++) {
			User u = userList.get(i);
			if(user.getAccount().equals(u.getAccount())) {
				userList.set(i, u = User.clone(user));
				return u;
			}
		}
		throw new RuntimeException("user not found "+user.getAccount());
	}

}
