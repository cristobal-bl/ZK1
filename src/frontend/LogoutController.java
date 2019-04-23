package frontend;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class LogoutController extends SelectorComposer<Component> {

    //services
    AuthenticationService authService = new AuthenticationServiceImpl();

    @Listen("onClick=#logout")
    public void doLogout(){
        authService.logout();
        Executions.sendRedirect("/");
    }
}