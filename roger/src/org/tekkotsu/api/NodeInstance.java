package org.tekkotsu.api;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import java.util.ArrayList;



import tgef.Node;

public class NodeInstance extends Node{
	
	//Attributes
	private NodeClass type;
	private String label;
	private ArrayList<Parameter> parameters;
	private ArrayList<TransitionInstance> outTrans;		//Outgoing transitions
	private ArrayList<TransitionInstance> inTrans;		//Incoming transitions
	private String color;
	
	private Color col;
	
    
		
		
	//static property fields
	public static final String PROPERTY_COL = "NodeInstanceColor";
	public static final String PROPERTY_TYPE = "Node_InstanceType";
	public static final String PROPERTY_PARAMETER="Node_InstanceParameter";
	public static final String PROPERTY_LABEL="Node_Instance_Label";
	
	//Full argument Constructor
	public NodeInstance(NodeClass type, String label, ArrayList<Parameter> parameters, ArrayList<TransitionInstance> outTrans, ArrayList<TransitionInstance> inTrans){

		this.type = type;
		this.label = label;
		this.parameters = parameters;
		this.outTrans = outTrans;
		this.inTrans = inTrans;
		this.color = type.getColor();
		this.col=createRandomColor();
	}
	
	//Instance from type (nodeclass) constructor
	public NodeInstance(NodeClass type){
		
		this.type = type;
		this.label = type.getName().toLowerCase();
		this.parameters = type.getParameters();
		this.inTrans = new ArrayList<TransitionInstance>();
		this.outTrans = new ArrayList<TransitionInstance>();
		this.color = type.getColor();		//TODO Value can be manipulated for slightly lighter or darker.
		this.col=createRandomColor();
		 /*red = (new Double(Math.random() * 128)).intValue() + 128;
	        green = (new Double(Math.random() * 128)).intValue() + 128;
	        blue = (new Double(Math.random() * 128)).intValue() + 128;*/
	}

	//Sets Color
	public void setColor(Color col) {
		 /*this.red = red;
	        this.green = green;
	        this.blue = blue;*/
		
		Color oldColor = this.col;
		this.col = col;

		
		
		getListeners().firePropertyChange(PROPERTY_COL, oldColor, col);
		
	}

	//color randomization
		private Color createRandomColor() {
			return new Color(null,
			(new Double(Math.random() * 128)).intValue() + 128 ,
			(new Double(Math.random() * 128)).intValue() + 128 ,
			(new Double(Math.random() * 128)).intValue() + 128 );
			}
	
		//Clone routine
	  
		

	//Accessor methods
	
	public  Color getCol(){
		
		return col;
	}
	
	
	public NodeClass getType(){
		return type;
	}

	public String getLabel(){
		return label;
	}

	public ArrayList<Parameter> getParameters(){
		return parameters;
	}

	public ArrayList<TransitionInstance> getOutTrans(){
		return outTrans;
	}

	public ArrayList<TransitionInstance> getInTrans(){
		return inTrans;
	}
	
	public String getColor(){
		return color;
	}

	//Returns number of parameters.
	public int getNumOfParameters(){
		return parameters.size();
	}
	
	//Returns number of outgoing transitions.
	public int getNumOfOutTrans(){
		return outTrans.size();
	}
	
	//Returns number of incoming transitions.
	public int getNumOfInTrans(){
		return inTrans.size();
	}



	//Mutator methods
	public void setType(NodeClass type){
		this.type = type;
	}
	
	public void setLabel(String label){
		this.label = label;
		getListeners().firePropertyChange(PROPERTY_LABEL, null, label);
	}
	
	public void setParameters(ArrayList<Parameter> parameters){
		this.parameters = parameters;
	}
	
	public void setOutTrans(ArrayList<TransitionInstance> outTrans){
		this.outTrans = outTrans;
	}

	public void setInTrans(ArrayList<TransitionInstance> inTrans){
		this.inTrans = inTrans;
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
	
	//Add/Remove transitions
	public void addInTrans(TransitionInstance inTrans){
		this.inTrans.add(inTrans);
	}
	
	public void addOutTrans(TransitionInstance outTrans){
		this.outTrans.add(outTrans);
	}
	
	public void removeInTrans(TransitionInstance inTrans){
		this.inTrans.remove(inTrans);
	}
	
	public void removeOutTrans(TransitionInstance outTrans){
		this.outTrans.remove(outTrans);
	}
	

}
