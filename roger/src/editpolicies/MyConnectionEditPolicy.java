/*
 * Created on Sep 19, 2004
 */
package editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import tgef.ui.editor.model.AbstractConnectionModel;
import commands.*;
/**
 * @author asangpet
 *
 */
public class MyConnectionEditPolicy extends ConnectionEditPolicy {
	
	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		command.setConnectionModel(getHost().getModel());			
		command.setContentsModel(((AbstractConnectionModel)(getHost().getModel())).getParent2());
		return command;
	}

}
