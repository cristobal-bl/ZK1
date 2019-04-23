package frontend;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class AuthenticationServiceImpl implements AuthenticationService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserInfoService userInfoService = new UserInfoServiceImpl();

    @Override
    public boolean login(String nm, String pd) {
        User user = userInfoService.findUser(nm);
        //a simple plan text password verification
        if(user==null || !user.getPassword().equals(pd)){
            return false;
        }

        Session sess = Sessions.getCurrent();
        UserCredential cre = new UserCredential(user.getAccount(),user.getFullName());

        sess.setAttribute("userCredential",cre);


        return true;
    }

    @Override
    public void logout() {
        Session sess = Sessions.getCurrent();
        sess.removeAttribute("userCredential");
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
