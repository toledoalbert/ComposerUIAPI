package org.tekkotsu.api;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

import tgef.Node;
import tgef.ui.editor.model.AbstractConnectionModel;
import tgef.ui.editor.model.MultiTransitionModel;



public class TransitionInstance extends AbstractConnectionModel{
	//Connection attributes for transitions
	public static final String P_ARROWHEAD = "_arrowhead";
	public static final String P_LINEWIDTH = "_linewidth";
	public static final String ARROW_ARROWHEAD = "_arrow_head";
	public static final String ARROW_NOHEAD = "_arrow_nohead";	
	
	private String arrowType;
	private int lineWidth;
	private Color col;
	private MultiTransitionModel _parent;
	private Node sourceNode;
	private Node targetNode;
	
	
	
	
	//Attributes
	private TransitionClass type;
	private ArrayList<Parameter> parameters;
	private ArrayList<NodeInstance> sources;
	private ArrayList<NodeInstance> targets;
	private String color;
	

	//Full argument Constructor
	public TransitionInstance(TransitionClass type, ArrayList<Parameter> parameters, ArrayList<NodeInstance> sources, ArrayList<NodeInstance> targets){
        super(type);
		this.parameters = parameters;
		this.sources = sources;
		this.targets = targets;
		this.color = type.getColor();
	}
	
	//Instance from type (transitionclass) constructor with node sources and connection type
	public TransitionInstance(TransitionClass type){
		super(type);
		this.parameters = type.getParameters();
		this.sources = new ArrayList<NodeInstance>();
		this.targets = new ArrayList<NodeInstance>();
		this.color = type.getColor();	//TODO Value can be manipulated for slightly lighter or darker.
		
		this.sourceNode = sourceNode;
        this.targetNode = targetNode;
       
	}



	public TransitionInstance(Node sourceNode, Node targetNode) {
		super(sourceNode, targetNode);
      
	}

	//From Storyboard code-SubConnectionModel Constructors
	public TransitionInstance(MultiTransitionModel parent, Node source, Node dest, List<Point> bendpoints) {
		this(parent ,source, dest, bendpoints, ARROW_ARROWHEAD, 1);		
	}

	public TransitionInstance(MultiTransitionModel parent, Node source, Node dest, List<Point> bendpoints, int lineWidth) {
		this(parent ,source, dest, bendpoints, ARROW_ARROWHEAD, lineWidth);		
	}
	
	//Accessor methods
	public TransitionClass getType(){
		return type;
	}

	public ArrayList<Parameter> getParameters(){
		return parameters;
	}

	public ArrayList<NodeInstance> getSources(){
		return sources;
	}

	public ArrayList<NodeInstance> getTargets(){
		return targets;
	}
	
	public String getColor(){
		return color;
	}
	
	//Returns number of parameters.
	public int getNumOfParameters(){
		return parameters.size();
	}
	
	//Returns number of targets.
	public int getNumOfTargets(){
		return targets.size();
	}
	
	//Returns number of sources.
	public int getNumOfSources(){
		return sources.size();
	}	




	//Mutator methods
	public void setType(TransitionClass type){
		this.type = type;
	}
	
	public void setParameters(ArrayList<Parameter> parameters){
		this.parameters = parameters;
	}
	
	public void setSources(ArrayList<NodeInstance> sources){
		this.sources = sources;
	}

	public void setInTrans(ArrayList<NodeInstance> targets){
		this.targets = targets;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	

	
	//Add/Remove parameters
	public void addParameter(Parameter parameter){
		this.parameters.add(parameter);
	}
	
	public void removeParameter(Parameter parameter){
		this.parameters.remove(parameter);
	}
	
	//Add/Remove targets/sources
	public void addTarget(NodeInstance target){
		this.targets.add(target);
	}
	
	public void addSource(NodeInstance source){
		this.sources.add(source);
	}
	
	public void removeTarget(NodeInstance target){
		this.targets.remove(target);
	}
	
	public void removeSource(NodeInstance source){
		this.sources.remove(source);
	}
	
	//Connection Methods
	 public Node getSourceNode() {
	        return sourceNode;
	    }
	    
	    public Node getTargetNode() {
	        return targetNode;
	    }

	   /* public void connect() {
	        sourceNode.addConnections(this);
	        targetNode.addConnections(this);
	    }
	    
	    public void disconnect() {
	        sourceNode.removeConnection(this);
	        targetNode.removeConnection(this);
	    }
	    
	    public void reconnect(Node sourceNode, Node targetNode) {
	        if (sourceNode == null || targetNode == null || sourceNode == targetNode) {
	            throw new IllegalArgumentException();
	        }
	        disconnect();
	        this.sourceNode = sourceNode;
	        this.targetNode = targetNode;
	        connect();
	    }

	    public void setConnectionType(int connectionType) {
	        this.connectionType = connectionType;
	    }

	    public int getConnectionType() {
	        return connectionType;
	    }*/

	    
	    //StoryBoard Code
	    public TransitionInstance (MultiTransitionModel parent, Node source, Node dest, List<Point> bendpoints, String arrowType, int lineWidth) {
			super(bendpoints);
			setParent(parent);
			this._parent = parent;
			this.arrowType = arrowType;
			this.lineWidth = lineWidth;
			setSource(source);
			setTarget(dest);
			attachSource();
			attachTarget();
		}
		
		/* (non-Javadoc)
		 * @see org.tekkotsu.ui.editor.model.AbstractModel#getXML()
		 */
		/*@Override
		public Content getXML() {
			return null;
		}*/

		/**
		 * @return Returns the arrowType.
		 */
		public String getArrowType() {
			return arrowType;
		}
		/**
		 * @param arrowType The arrowType to set.
		 */
		public void setArrowType(String arrowType) {
			this.arrowType = arrowType;
			getListeners().firePropertyChange(P_ARROWHEAD, null, arrowType);
		}
		
		public void setLineWidth(int width) {
			lineWidth = width;
			getListeners().firePropertyChange(P_LINEWIDTH, null, Integer.valueOf(width));
		}
		
		public int getLineWidth() {
			return lineWidth;
		}
		
		@Override
		public String toString() {
			return _parent.getName();
		}
		
		@Override
		public String partialToString(Node snm) {	
			if( snm.equals( getSource() ) ) {
				//we are a single out trans
				return _parent.getName() + " : " + _parent.getDestsString();
			} else if ( snm.equals( getTarget() ) ) {
				//we are a single in trans
				return _parent.getSourcesString() + " : " + _parent.getName();
			} else {
				//failsafe
				return toString();
			}
		}

		@Override
		public boolean isPreview() {
			// TODO Auto-generated method stub
			return false;
		}

		
		public void setColor2(Color col) {
			this.col = col;
			getListeners().firePropertyChange(Node.P_COLOR,null,color);
		}

		public Color getColor2() {
			return col;
		}

		@Override
		public Object getAdapter(Class adapter) {
			// TODO Auto-generated method stub
			return null;
		}
	    
	    
}
