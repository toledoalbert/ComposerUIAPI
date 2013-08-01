package part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.tekkotsu.api.*;
import org.tekkotsu.ui.util.AbstractModel;

import tgef.Employe;
import tgef.Node;
import editpolicies.AppDeletePolicy;
import editpolicies.AppRenamePolicy;

public class NodeInstanceTreeEditPart extends AppAbstractTreeEditPart {
	
	protected List<AbstractModel> getModelChildren() {
		return ((NodeInstance)getModel()).getChildrenArray();
		}
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();

		}
		
		@Override
		protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());
		
		//Rename Policy
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());

		}
		
		public void refreshVisuals(){
		NodeInstance model = (NodeInstance)getModel();
		setWidgetText(model.getName());
		
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW));

		}



}
