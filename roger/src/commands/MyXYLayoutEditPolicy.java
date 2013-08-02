package editpolicies;

/**
 * @author asangpet
 */

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import tgef.ui.editor.model.MultiTransitionModel;
import tgef.*;
import commands.*;
import org.tekkotsu.ui.util.Debugger;

public class MyXYLayoutEditPolicy extends XYLayoutEditPolicy{
	
	@Override
	public Command getCommand(Request request) {
		Debugger.printDebug(Debugger.DEBUG_ALL, "XYLayoutEditPolicy getCommand() : "+request.getType());
		return super.getCommand(request);
	}

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		Debugger.printDebug(Debugger.DEBUG_ALL, "XYLayoutEditPolicy createAddCommand() :: " + child + " :: " + constraint);
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		Debugger.printDebug(Debugger.DEBUG_ALL, "XYLayoutEditPolicy createChangeConstraintCommand() :: " + child + " :: " + constraint);
		NodeInstanceChangeLayoutCommand command = new NodeInstanceChangeLayoutCommand();
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		
		return command;
	}
	
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Debugger.printDebug(Debugger.DEBUG_ALL, "XYLayoutEditPolicy getCreateCommand() : "+request.getNewObjectType());
		if (request.getNewObjectType().equals(NodeInstancePart.class)) {
			CreateCommand command = new CreateCommand();
			Rectangle constraint = new Rectangle(0,0,50,20);
			try {
				constraint.setLocation(request.getLocation());
			} catch (Exception e) {
				Debugger.printThrowable(e);
			}
			Node model = (Node)request.getNewObject();
		
			// check if the node is already present
			if (((LayoutData)getHost().getModel()).getPartChild(model.getId()) != null) return null;			
			
			model.setConstraint(constraint);			
			command.setContentsModel(getHost().getModel());
			command.setStateNodeModel(model);
			return command;
		} else if (request.getNewObjectType().equals(MultiTransitionModel.class)) {			
			CreateMultiTransitionCommand command = new CreateMultiTransitionCommand();
			Rectangle constraint = new Rectangle(0,0,50,20);
			try {
				constraint.setLocation(request.getLocation());		
			} catch (Exception e) {
				Debugger.printThrowable(e);
			}
			MultiTransitionModel model = (MultiTransitionModel)request.getNewObject();
		
			// check if the transition node is already present
			if (((LayoutData)getHost().getModel()).getPartChild(model.getId()) != null) return null;			
			model.setConstraint(constraint);			
			command.setContentsModel(getHost().getModel());
			command.setMultiTransitionModel(model);			
			Debugger.printDebug(Debugger.DEBUG_ALL, "XYLayoutEditPolicy getCreateCommand() : CreateMultiTransitionCommand : "+command);
			return command;			
		} else return null;		
	}
	
	@Override
	protected Command getDeleteDependantCommand(Request request) {
		Debugger.printDebug(Debugger.DEBUG_ALL, "XYLayoutEditPolicy getDeleteDependantCommand() :: " + request.getType() );
		return null;
	}
}
