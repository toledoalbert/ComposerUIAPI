package tgef;

import java.beans.PropertyChangeEvent;
import java.util.List; 

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure; 
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.draw2d.geometry.Rectangle;
import org.tekkotsu.ui.util.AbstractModel;

import editpolicies.AppDeletePolicy;
import editpolicies.AppEditLayoutPolicy;
import editpolicies.AppRenamePolicy;


                                                                                     
public class ServicePart extends AppAbstractEditPart {

@Override
protected IFigure createFigure() {
IFigure figure = new ServiceFigure();
return figure;
}

@Override 
//Applies edit policies which call written commands
protected void createEditPolicies() { 
	
	installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
	//Applies delete Policy
	installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());
//Applies rename policy
	installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());


} 

protected void refreshVisuals(){ 
ServiceFigure figure = (ServiceFigure)getFigure();
Service model = (Service)getModel(); 

figure.setName(model.getName());
figure.setEtage(model.getEtage());
figure.setLayout(model.getLayout());
figure.setBackgroundColor(model.getColor());

}


public List<AbstractModel> getModelChildren() {
return ((Service)getModel()).getChildrenArray();
}


@Override
public void propertyChange(PropertyChangeEvent evt) {
	//refreshes view when size is increased or moved
	if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
//Refreshes view if children are added or removed
if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();

//refreshes visuals for name change
if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();
//refreshes visuals for color change
if (evt.getPropertyName().equals(Service.PROPERTY_COLOR)) 
{
	System.out.println("Color property changed");
	refreshVisuals();
	System.out.println(evt);
	
}
if (evt.getPropertyName().equals(Service.PROPERTY_FLOOR)) refreshVisuals();

}


}

