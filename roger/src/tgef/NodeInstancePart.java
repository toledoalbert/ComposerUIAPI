package tgef;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.TransitionInstance;

import tgef.ui.editor.model.AbstractConnectionModel;

import editpolicies.*;




import editpolicies.*;
public class NodeInstancePart extends AppAbstractEditPart implements NodeEditPart {

	
	//Refreshes visuals if properties are changed (Node is moved)
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//Refreshes view when moved
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
		
		//detects if something is added or removed
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
        //Refreshes if renamed
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();
		
		if (evt.getPropertyName().equals(NodeInstance.PROPERTY_LABEL)) refreshVisuals();
		//refreshes visuals for color change
		if (evt.getPropertyName().equals(NodeInstance.PROPERTY_COL)) refreshVisuals();
		
		
		//Refreshes visuals for node connections. Using old connections
		if (evt.getPropertyName().equals(Node.SOURCE_CONNECTION)) refreshSourceConnections();
        if (evt.getPropertyName().equals(Node.TARGET_CONNECTION)) refreshTargetConnections();

        //Other refresh for connections
        if (evt.getPropertyName().equals(Node.P_SOURCE_CONNECTION)) refreshSourceConnections();
        if (evt.getPropertyName().equals(Node.P_TARGET_CONNECTION)) refreshTargetConnections();
		
			
		
	}
//Creates Node instance Figure
	protected IFigure createFigure() {
		IFigure figure = new NodeInstanceFigure();
		return figure;
		}

	//Employs edit policies when node instance is moved or deleted
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		//installs new delete policy
		installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());
		
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
		
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new AppConnectionPolicy());
		
		
		
		
	}
	
	//public List<Connection> getModelNodeInstanceConnections() {
      //  return ((NodeInstance)getModel()).getSourceConnectionsArray();
	//}
	
	 //public List<Connection> getModelTargetConnections() {
	   //     return ((NodeInstance)getModel()).getTargetConnectionsArray();
	    //}
	 
	 
	
	public List<Node> getModelChildren() {
		return new ArrayList<Node>();
		}
	
	//Refreshes visuals of node instance
	protected void refreshVisuals(){
		NodeInstanceFigure figure = (NodeInstanceFigure)getFigure();
		NodeInstance model = (NodeInstance)getModel();

		figure.setName(model.getLabel());
		figure.setLayout(model.getLayout());
		figure.setBackgroundColor(model.getCol());
		}
	
	//Pertain to adding and removing connections
	
	/*@Override
    public ConnectionAnchor getSourceConnectionAnchor(
            ConnectionEditPart connection) {
       return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(
            ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }*/
	
	private ConnectionAnchor getAnchor() {
		Node model = (Node)getModel();
		if (model.getShape().equals(Node.P_SHAPE_STYLE_ELLIPSE)) {
			return new EllipseAnchor(getFigure());
		}
		
		return new ChopboxAnchor(getFigure());
	}
	
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getAnchor();
	}
	
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getAnchor();
	}
	
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getAnchor();
	}
	
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getAnchor();
	}
	
	@Override
	protected List<AbstractConnectionModel> getModelSourceConnections() {
		return ((Node)getModel()).getModelSourceConnections();
	}
	
	@Override
	protected List<AbstractConnectionModel> getModelTargetConnections() {
		return ((Node)getModel()).getModelTargetConnections();
	}
	


}
