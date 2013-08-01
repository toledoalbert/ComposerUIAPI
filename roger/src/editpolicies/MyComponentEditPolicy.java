/*
 * Created on Sep 12, 2004
 */
package editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import commands.*;

/**
 * @author asangpet
 */

public class MyComponentEditPolicy extends ComponentEditPolicy {
	@Override
	protected Command createDeleteCommand(GroupRequest deleteReqeust) {
		DeleteCommand command = new DeleteCommand();
		command.setContentsModel(getHost().getParent().getModel());
		command.setStateNodeModel(getHost().getModel());
		return command;
	}
}
