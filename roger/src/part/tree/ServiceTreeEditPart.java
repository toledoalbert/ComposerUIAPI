package part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;
import tgef.Node;
import tgef.Service;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.tekkotsu.ui.util.AbstractModel;

import editpolicies.AppDeletePolicy;
import editpolicies.AppRenamePolicy;

public class ServiceTreeEditPart extends AppAbstractTreeEditPart {
	
	protected List<AbstractModel> getModelChildren() {
		return ((Service)getModel()).getChildrenArray();
		}
		@Override
		protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());
		
		//specifies which parts can be renamed
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());

		}
		public void refreshVisuals(){
		Service model = (Service)getModel();
		setWidgetText(model.getName());
		
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
		}
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			//refresh visuals if something is added or removed
			if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
			if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
		//refreshes visuals if renamed
			if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();
if (evt.getPropertyName().equals(Service.PROPERTY_COLOR)) refreshVisuals();
if (evt.getPropertyName().equals(Service.PROPERTY_FLOOR)) refreshVisuals();
		
		}
		
		


}
