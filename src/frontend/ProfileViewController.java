package frontend;

import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

public class ProfileViewController extends SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//wire components
	@Wire
	Label account;
	@Wire
	Textbox fullName;
	@Wire
	Textbox email;
	@Wire
	Datebox birthday;
	@Wire
	Listbox country;
	@Wire
	Textbox bio;
	
	//services
	AuthenticationService authService = new AuthenticationServiceImpl();
	UserInfoService userInfoService = new UserInfoServiceImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		ListModelList<String> countryModel = new ListModelList<String>(CommonInfoService.getCountryList());
		country.setModel(countryModel);
		
		refreshProfileView();
	}
	
	private void refreshProfileView() {
		UserCredential userCredential = authService.getUserCredential();
		User user = userInfoService.findUser(userCredential.getAccount());
		if(user == null) {
			//TODO handle unauthenticated access
			return;
		}
		
		//apply bean value to UI elements
		account.setValue(user.getAccount());
		fullName.setValue(user.getFullName());
		email.setValue(user.getEmail());
		birthday.setValue(user.getBirthday());
		bio.setValue(user.getBio());
		
		((ListModelList)country.getModel()).addToSelection(user.getCountry());
	}
	
	@Listen("onClick = #saveProfile")
	public void doSaveProfile() {
		UserCredential userCredential = authService.getUserCredential();
		User user = userInfoService.findUser(userCredential.getAccount());
		if(user == null) {
			//TODO handle unauthenticated access
			return;
		}
		
		//apply component value to bean
		user.setFullName(fullName.getValue());
		user.setEmail(email.getValue());
		user.setBirthday(birthday.getValue());
		user.setBio(bio.getValue());
		
		Set<String> selection = ((ListModelList)country.getModel()).getSelection();
		if(!selection.isEmpty()) {
			user.setCountry(selection.iterator().next());
		}else {
			user.setCountry(null);
		}
		
		userInfoService.updateUser(user);
		
		Clients.showNotification("Your profile is updated");
	}
	
	@Listen("onClick = button[label='Reload']")
	public void doReloadProfile() {
		refreshProfileView();
	}
}
