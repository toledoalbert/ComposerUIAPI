package org.tekkotsu.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.tekkotsu.api.Graphical;
import org.tekkotsu.commands.DeleteCommand;

public class AppDeletePolicy extends ComponentEditPolicy {

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		
		DeleteCommand command = new DeleteCommand();
		command.setModel(getHost().getModel());
		//Graphical g = getHost().getParent().getModel();
		System.out.println(getHost().getParent().getModel().toString());
		command.setParentModel(getHost().getParent().getModel());
		return command;
		
	}
}
