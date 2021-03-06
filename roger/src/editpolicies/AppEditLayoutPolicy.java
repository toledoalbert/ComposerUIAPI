package editpolicies;

import tekkotsu.edit.parts.SubConnectionEditPart;
import tgef.EmployeFigure;
import tgef.EmployePart;
import tgef.EntreprisePart;
import tgef.NodeInstanceFigure;
import tgef.NodeInstancePart;
import tgef.ServiceFigure;
import tgef.ServicePart;
import org.eclipse.gef.EditPart;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import commands.AbstractLayoutCommand;
import commands.EmployeChangeLayoutCommand;
import commands.MoveBendpointCommand;
import commands.NodeInstanceChangeLayoutCommand;
import commands.NodeInstanceCreateCommand;
import commands.ServiceChangeLayoutCommand;
import commands.ServiceCreateCommand;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy{
	
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
	
		AbstractLayoutCommand command = null;
		
		if (child instanceof NodeClassPart) {
			
			command = new NodeClassChangeLayoutCommand();
			
		} else if (child instanceof SetupMachinePart) {
			
			command = new SetupMachineChangeLayoutCommand();
			
			/*else if (child instanceof SubConnectionEditPart){
		   command = new MoveBendpointCommand();*/
			
		} else if (child instanceof NodeInstancePart) {
			
			command = new NodeInstanceChangeLayoutCommand();
		}
	
		//Set the model to the model of the right editpart
		command.setModel(child.getModel());
	
		//Set the constraint of the model
		command.setConstraint((Rectangle)constraint);
		
		return command;
	
	}
	
	
	
	@Override
	protected Command getCreateCommand(CreateRequest request) {

		if (request.getType() == REQ_CREATE &&  getHost() instanceof SetupMachinePart) {
			
			//Create the nodeinstance creation command
			NodeInstanceCreateCommand cmd = new NodeInstanceCreateCommand();
			
			//
			cmd.setEntreprise(getHost().getModel());
			cmd.setNodeInstance(request.getNewObject());
			Rectangle constraint = (Rectangle)getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ?
			NodeInstanceFigure.NODEINSTANCE_FIGURE_DEFWIDTH : constraint.width;
			constraint.height = (constraint.height <= 0) ?
			NodeInstanceFigure.NODEINSTANCE_FIGURE_DEFHEIGHT : constraint.height;
			cmd.setLayout(constraint);
			return cmd;
				
		}

		return null;
	}
	


}
