package editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import commands.TransitionInstanceDeleteCommand;



public class AppTransitionInstanceDeleteEditPolicy extends ConnectionEditPolicy {

    @Override
    protected Command getDeleteCommand(GroupRequest arg0) {
        TransitionInstanceDeleteCommand command = new TransitionInstanceDeleteCommand();
        command.setLink(getHost().getModel());
        return command;
    }
}
