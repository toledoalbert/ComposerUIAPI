package org.tekkotsu.api;

import java.util.ArrayList;

public class TransWriter {

	//component
	private TransitionInstance trans;
	
	//constructor
	public TransWriter(TransitionInstance trans){
		this.trans = trans;
	}
	
	//write trans method
	public String writeTrans(){
		
		//String to return
		String transitions = "";
		
		//Get current transition instance
		TransitionInstance current = trans;
		
		//Get sources and targets
		ArrayList<NodeInstance> sources = current.getSources();
		ArrayList<NodeInstance> targets = current.getTargets();
		
		//if there are multiple sources
		if(current.getNumOfSources() > 1){
			
			//open curly braces to put  labels of nodes in.
			transitions += "{";
			
			//for every source
			for(int j = 0; j < current.getNumOfSources(); j++){
				
				transitions += sources.get(j).getLabel() + ", "; //TODO
				
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
		
		return transitions;
		
		}
	
		
	}


	

