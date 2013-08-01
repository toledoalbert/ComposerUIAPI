package tgef;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart; 
import org.eclipse.swt.SWT;
import org.tekkotsu.ui.util.AbstractModel;

import editpolicies.AppEditLayoutPolicy;
                                                           
public class EntreprisePart extends AppAbstractEditPart{
@Override
protected IFigure createFigure() {
IFigure figure = (IFigure) new EntrepriseFigure();

ConnectionLayer connLayer = (ConnectionLayer)getLayer(LayerConstants.CONNECTION_LAYER);
connLayer.setAntialias(SWT.ON);
connLayer.setConnectionRouter(new ShortestPathConnectionRouter(figure));
return figure;
}
@Override
protected void createEditPolicies() {
	installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());

}

protected void refreshVisuals(){
EntrepriseFigure figure = (EntrepriseFigure)getFigure();
Entreprise model = (Entreprise)getModel();

figure.setName(model.getName());
figure.setAddress(model.getAddress());
figure.setCapital(model.getCapital());
}
//Changed
public List<AbstractModel> getModelChildren() {
return ((Entreprise)getModel()).getChildrenArray();
}
//refreshes view if children are added or removed or changed
@Override
public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();
		if (evt.getPropertyName().equals(Entreprise.PROPERTY_CAPITAL)) refreshVisuals();
		}



}








