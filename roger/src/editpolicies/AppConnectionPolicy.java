package editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import tgef.Node;
import tgef.Connection;
import commands.ConnectionCreateCommand;
import commands.ConnectionReconnectCommand;
import commands.CreateConnectionCommand;
import commands.ReconnectConnectionCommand;



public class AppConnectionPolicy extends GraphicalNodeEditPolicy {

    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        //ConnectionCreateCommand cmd = (ConnectionCreateCommand)request.getStartCommand();
        CreateConnectionCommand cmd= (CreateConnectionCommand)request.getStartCommand();
    	Node targetNode = (Node)getHost().getModel();
        cmd.setTarget(targetNode);
        return cmd;
    }

    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        //ConnectionCreateCommand cmd = new ConnectionCreateCommand();
        CreateConnectionCommand cmd = new CreateConnectionCommand();
        Node sourceNode = (Node)getHost().getModel();
        //cmd.setConnectionType(Integer.parseInt(request.getNewObjectType().toString()));
        cmd.setSource(sourceNode);
        request.setStartCommand(cmd);
        return cmd;
    }

    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        //Connection conn = (Connection)request.getConnectionEditPart().getModel();
        
    	Node sourceNode = (Node)getHost().getModel();
        //ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(conn);
        ReconnectConnectionCommand cmd = new ReconnectConnectionCommand();
    	cmd.setNewSource(sourceNode);        
        return cmd;
    }

    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        //Connection conn = (Connection)request.getConnectionEditPart().getModel();
        Node targetNode = (Node)getHost().getModel();
        //ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(conn);
        ReconnectConnectionCommand cmd = new ReconnectConnectionCommand();
        cmd.setNewTarget(targetNode);        
        return cmd;
        
    }

	}
