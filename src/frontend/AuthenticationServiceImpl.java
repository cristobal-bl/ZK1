package frontend;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class AuthenticationServiceImpl implements AuthenticationService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(String account, String password) {
		return false; //not implemented yet
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	@Override
	public UserCredential getUserCredential() {
		Session ses = Sessions.getCurrent();
		UserCredential cre = (UserCredential)ses.getAttribute("userCredential");
		if(cre==null) {
			cre = new UserCredential(); //new anonymous user and set to session
			ses.setAttribute("userCredential", cre);			
		}
		return cre;
	}

}
