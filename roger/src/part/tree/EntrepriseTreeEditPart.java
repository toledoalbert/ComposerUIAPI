package part.tree;


import java.beans.PropertyChangeEvent;
import java.util.List;

import org.tekkotsu.ui.util.AbstractModel;

import tgef.Node;
import tgef.Entreprise;

//Lists tree part edit for outline for Entreprise


public class EntrepriseTreeEditPart extends AppAbstractTreeEditPart {
	protected List<AbstractModel> getModelChildren() {
		return ((Entreprise)getModel()).getChildrenArray();
		}
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
		if(evt.getPropertyName().equals(Entreprise.PROPERTY_CAPITAL)) refreshVisuals();
		}


}
