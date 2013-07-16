package org.tekkotsu.api;

import java.util.ArrayList;

public class BehaviorWriter {
	
	//Attributes
	private NodeClass nodeClass;
	private String registerCall;
	private String include;
	private String comment;
	private SetupMachine setup;
	
	//Constructor
	public BehaviorWriter(NodeClass nodeClass){
		this.nodeClass = nodeClass;
		
		this.registerCall = "\n\t}\n}\n\nREGISTER_BEHAVIOR(" + this.nodeClass.getName() + ");";
		this.include = "#include \"Behaviors/StateMachine.h\" \n\n";
		this.comment = "//" + nodeClass.getName() + "Behavior\n";
		this.setup = nodeClass.getSetupMachine();
	}
	
	//Method to get the fsm content as string.
	public String getFSM(){
		
		//String for class header.
		String classHeader = "$nodeclass " + nodeClass.getName() + " : " + "StateNode {\n";
		String setupHeader = "\t$setupmachine {\n\n";
		
		String meat = getMeat();
		
		String fsm = include + comment + classHeader + setupHeader + meat + registerCall;
		
		System.out.println(fsm);
		
		return fsm;
	}
	
	//Method to set optional comment.
	public void setComment(String comment){
		this.comment = comment;
	}
	
	//Method to get node declarations.
	public String getNodes(){
		
		//Create string to return
		String nodes = "";
		
		//Create list of nodeinstance objects from setup object.
		ArrayList<NodeInstance> nodeList = this.setup.getNodes();
		
		//For every node in the setupmachine
		for(int i = 0; i < nodeList.size(); i++){
			
			//Get the current node instance object.
			NodeInstance current = nodeList.get(i);
			
			//Print its label: constructorname
			nodes += "\t\t" + current.getLabel() + ": " + current.getType().getConstructor().getName();
			
			//if any parameters
			if(current.getNumOfParameters() > 0){
				
				//open parantheses
				nodes += "(";
				
				//for every parameter in the constructor
				for(int j = 0; j < current.getNumOfParameters(); j++){
					
					//get the value of parameter and add to the string
					nodes +=  current.getParameters().get(j).getValue();
					
					if(j != current.getNumOfParameters()-1){
						nodes += ", ";
					}
					
				}
				
				//close paranteses of the constructor
				nodes += ")\n";
				
			}
		}
		
		return nodes;
	}
	
	
	//Method to get the transitions
	public String getTransitions(){
		
		//Create string to return
		String transitions = "";
		
		//Create transition instance objects from the setup object.
		ArrayList<TransitionInstance> transList = this.setup.getTransitions();
		
		//for every transition initialized
		for(int i = 0; i < transList.size(); i++){
			
			transitions += "\t\t";
			
			//Get current transition instance
			TransitionInstance current = transList.get(i);
			
			//Get sources and targets
			ArrayList<NodeInstance> sources = current.getSources();
			ArrayList<NodeInstance> targets = current.getTargets();
			
			//if there are multiple sources
			if(current.getNumOfSources() > 1){
				
				//open curly braces to put  labels of nodes in.
				transitions += "{";
				
				//for every source
				for(int j = 0; j < current.getNumOfSources(); j++){
					
					transitions += sources.get(j).getLabel() + ", ";
					
				}
				
				//close curly braces
				transitions += "}";
				
			}else{
				
				//print label of the source
				transitions += current.getSources().get(0).getLabel() + " ";
				
			}
			
			//get transition constructor.
			ConstructorCall currentTransitionConstructor = current.getType().getConstructor();
			
			//Get current transition constructor name
			transitions += "=" + currentTransitionConstructor.getName();
			
			//if current transition constructor has any parameters
			if(currentTransitionConstructor.getParameters().size() > 0){
				transitions += "(";
				
				//for every parameter of transition.
				for(int h = 0; h < currentTransitionConstructor.getParameters().size(); h++){
					transitions +=  currentTransitionConstructor.getParameters().get(h).getValue();
					if(h != currentTransitionConstructor.getParameters().size()-1){
						transitions += ", ";
					}
				}
				transitions += ")";
			}
			
			transitions += "=> ";
			
			//if there are multiple targets
			if(current.getNumOfTargets() > 1){
				
				//open curly braces to put  labels of nodes in.
				transitions += "{";
				
				//for every source
				for(int j = 0; j < current.getNumOfTargets(); j++){
					
					//print the label
					transitions += targets.get(j).getLabel();
					
					if(j != current.getNumOfTargets()-1){
						transitions += ", ";
					}
					
				}
				
				//close curly braces
				transitions += "}";
				
			}else{
				
				//print label of the target
				transitions += current.getTargets().get(0).getLabel() + " ";
				
			}
			
			//go to the next line
			transitions += "\n";
			
			}
		
		//return the transitions
		return transitions;
		
	}
	
	
	//Method to get meat.
	public String getMeat(){
		
		//Add string to create the meat.
		String meat = getNodes() + "\n\n" + getTransitions();
	
		
		return meat;
		
	}
	
}
