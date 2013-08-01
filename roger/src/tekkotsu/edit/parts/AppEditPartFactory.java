package tekkotsu.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.TransitionInstance;


import tgef.*;
import tgef.ui.editor.model.*;




public class AppEditPartFactory implements EditPartFactory {
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
	EditPart part = null;
	
 if (model instanceof Entreprise) { 
	 part = new EntreprisePart();
	 
 } else if (model instanceof Service){ 
	 part = new ServicePart();
	 
 } else if (model instanceof NodeInstance){
	 part = new NodeInstancePart();
	 
 } else if (model instanceof TransitionInstance){ 
     part = new TransitionInstancePart();
    
 } else if (model instanceof LayoutData){
	part = new ViewEditPart();
	
 }else if (model instanceof MultiTransitionModel){
		part = new MultiTransitionEditPart();
 
 }/*else if (model instanceof SubConnectionModel){
		part = new SubConnectionEditPart();}
 */
    part.setModel(model);
	return part;
}


}
