package part.tree;
import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.tekkotsu.ui.util.AbstractModel;

import editpolicies.AppDeletePolicy;

import tgef.Node;
import tgef.Employe;
public class EmployeTreeEditPart extends AppAbstractTreeEditPart{
	
	protected List<AbstractModel> getModelChildren() {
		return ((Employe)getModel()).getChildrenArray();
		}
		
		@Override
		protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());
		}
		
		public void refreshVisuals(){
		Employe model = (Employe)getModel();
		setWidgetText(model.getName()+" "+model.getPrenom());
		
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW));

		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
		if(evt.getPropertyName().equals(Employe.PROPERTY_FIRSTNAME)) refreshVisuals();
		}


}
