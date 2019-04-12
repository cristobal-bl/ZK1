package frontend;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;

public class SidebarController extends SelectorComposer<Component>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Wire Components
	@Wire
	private Grid sidebar;
	
	//Services
	private SidebarPageConfig pageConfig = new SidebarPageConfigImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//Initialize view after view construction
		Rows rows = sidebar.getRows();
		
		for(SidebarPage page : pageConfig.getPages()) {
			Row row = constructSidebarRow(page.getName(), page.getLabel(), page.getIconUri(), page.getUri());
			rows.appendChild(row);
		}
	}
	
	private Row constructSidebarRow(String name, String label, String imageSrc, final String locationUri) {
		
		//construct component and hierarchy
		Row row = new Row();
		Image image = new Image(imageSrc);
		Label lab = new Label(label);
		
		row.appendChild(image);
		row.appendChild(lab);
		
		//set style attribute
		row.setClass("sidebar-fn");
		
		//create and register event listener
		EventListener<Event> actionListener = new SerializableEventListener<Event>() {

			// Add serial version UID as per serializable interface best practices?
			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(Event arg0) throws Exception {
				//redirect current URL to new location
				Executions.getCurrent().sendRedirect(locationUri);
			}			
		};
		
		row.addEventListener(Events.ON_CLICK, actionListener);
		
		return row;
	}
}
