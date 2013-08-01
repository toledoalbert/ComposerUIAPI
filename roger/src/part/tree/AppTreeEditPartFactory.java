package part.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.tekkotsu.api.NodeInstance;

import tgef.Employe;
import tgef.Entreprise;
import tgef.Service;

//Factory handles each separate edit part

public class AppTreeEditPartFactory implements EditPartFactory{
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
	  EditPart part = null;
	if (model instanceof Entreprise)
	  part = new EntrepriseTreeEditPart();
	else if (model instanceof Service)
	  part = new ServiceTreeEditPart();
	else if (model instanceof Employe)
	  part = new EmployeTreeEditPart();
	else if (model instanceof NodeInstance)
	  part = new NodeInstanceTreeEditPart();
	
	if (part != null)
	  part.setModel(model);

	
	return part;
	}


}
