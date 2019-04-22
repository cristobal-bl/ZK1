package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SidebarPageConfigImpl implements SidebarPageConfig{

	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigImpl() {
		pageMap.put("fn1",new SidebarPage("zk", "ZK", "/FEFiles/img/image.png", "http://zkoss.org"));
		pageMap.put("fn2",new SidebarPage("demo", "ZK Demo", "/FEFiles/img/image.png", "http://zkoss.org"));
		pageMap.put("fn3",new SidebarPage("devref", "ZK Developer Reference", "/FEFiles/img/image.png", "http://zkoss.org"));
		pageMap.put("fn4",new SidebarPage("fn1","Profile (MVC)","/FEFiles/img/image.png","/FEFiles/ZUL/index-profile-mvc.zul"));
		pageMap.put("fn5",new SidebarPage("fn3","Todo List (MVC)","/FEFiles/img/image.png","/FEFiles/ZUL/index-todolist-mvc.zul"));
	}
	
	//Below: Mandatory code because of interface implementation
	@Override
	public List<SidebarPage> getPages() {
		// TODO Auto-generated method stub
		return new ArrayList<SidebarPage>(pageMap.values());
	}

	@Override
	public SidebarPage getPage(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
