package org.tekkotsu.api;
import java.util.ArrayList;

//Node class for the Tekkotsu State Machine Composer
//Author: Albert Berk Toledo
//Start date: 06/17/2013


//Class Header
public class SetupMachine {
	

	//Attributes
	private ArrayList<NodeInstance> Nodes;				//List of the nodes
	private ArrayList<TransitionInstance> Transitions;	//List of the transitions


	//Constructor
	public SetupMachine(ArrayList<NodeInstance> Nodes, ArrayList<TransitionInstance> Transitions){
		this.Nodes = Nodes;
		this.Transitions = Transitions;
	}
	
	//No argument constructor to initialize empty setup machine.
	public SetupMachine(){
		this.Nodes = new ArrayList<NodeInstance>();
		this.Transitions = new ArrayList<TransitionInstance>();
	}



	//Accessor methods
	public ArrayList<NodeInstance> getNodes(){
		return Nodes;
	}
	
	public ArrayList<TransitionInstance> getTransitions(){
		return Transitions;
	}
	



	//Mutator methods
	public void setNodes(ArrayList<NodeInstance> Nodes){
		this.Nodes = Nodes;
	}
	
	public void setTransitions(ArrayList<TransitionInstance> Transitions){
		this.Transitions = Transitions;
	}
	




	//Mutator methods to add single elements to the lists
	public void addNode(NodeInstance node){
		this.Nodes.add(node);
	}
	
	public void addTransition(TransitionInstance transition){
		this.Transitions.add(transition);
	}
	
	//Mutator methods to remove single elements from the lists.
	public void removeNode(NodeInstance node){
		this.Nodes.remove(node);
	}
	
	public void removeTransition(TransitionInstance transition){
		this.Nodes.remove(transition);
	}
	
}
