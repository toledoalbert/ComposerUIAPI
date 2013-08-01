package tgef;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import editpolicies.AppDeletePolicy;
import editpolicies.AppEditLayoutPolicy;



public class EmployePart extends AppAbstractEditPart{
@Override
protected IFigure createFigure() {
IFigure figure = new EmployeFigure();
return figure;
}

//applies edit policies that call written commands
@Override
protected void createEditPolicies() {
	installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
	//installs new delete policy
	installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());


}
protected void refreshVisuals(){
EmployeFigure figure = (EmployeFigure)getFigure();
Employe model = (Employe)getModel();

figure.setName(model.getName());
figure.setFirstName(model.getPrenom());
figure.setLayout(model.getLayout());
}

public List<Node> getModelChildren() {
return new ArrayList<Node>();
}

@Override
public void propertyChange(PropertyChangeEvent evt) {
	if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
if(evt.getPropertyName().equals(Employe.PROPERTY_FIRSTNAME)) refreshVisuals();
	
}
}
