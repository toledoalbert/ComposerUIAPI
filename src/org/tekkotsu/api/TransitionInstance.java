package org.tekkotsu.api;

import java.util.ArrayList;

public class TransitionInstance {
	
	//Attributes
	private TransitionClass type;
	private ArrayList<Parameter> parameters;
	private ArrayList<NodeInstance> sources;
	private ArrayList<NodeInstance> targets;
	private String color;
	

	//Full argument Constructor
	public TransitionInstance(TransitionClass type, ArrayList<Parameter> parameters, ArrayList<NodeInstance> sources, ArrayList<NodeInstance> targets){

		this.type = type;
		this.parameters = parameters;
		this.sources = sources;
		this.targets = targets;
		this.color = type.getColor();
	}
	
	//Instance from type (transitionclass) constructor
	public TransitionInstance(TransitionClass type){
		
		this.type = type;
		this.parameters = type.getParameters();
		this.sources = new ArrayList<NodeInstance>();
		this.targets = new ArrayList<NodeInstance>();
		this.color = type.getColor();	//TODO Value can be manipulated for slightly lighter or darker.
		
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

}
