package frontend;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AuthenticationInit implements Initiator {

    //services
    AuthenticationService authService = new AuthenticationServiceImpl();

    public void doInit(Page page, Map<String, Object> args) throws Exception {

        UserCredential cre = authService.getUserCredential();
        if(cre==null || cre.isAnonymous()){
            Executions.sendRedirect("/FEFiles/ZUL/login.zul");
            return;
        }
    }
}