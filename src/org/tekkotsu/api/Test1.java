package org.tekkotsu.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		//Get lists of default classes
		ArrayList<NodeClass> nodes = new DefaultClassReader().getNodes();
		ArrayList<TransitionClass> transitions = new DefaultClassReader().getTransitions();
		
		//Print node classes
		System.out.println("Node Classes available:\n");
		
		for(int i = 0; i < nodes.size(); i++){
			System.out.println(nodes.get(i).getName() + " (" + nodes.get(i).getDefinition() + ")");
		}
		
		//Print transition classes
		System.out.println("\nTransition Classes available:\n");
		
		for(int i = 0; i < transitions.size(); i++){
			System.out.println(transitions.get(i).getName() + " (" + transitions.get(i).definition() + ")");
		}
		
		//Create scanner
		Scanner keyboard = new Scanner(System.in);
		
		//First prompt
		System.out.println("\n\nPlease follow the directions.");
		
		//Prompt for behavior name
		System.out.println("\nHow do you want to call your behavior? (Start with capital and one word.)");
		String behaviorName = keyboard.nextLine();
		
		//Create setup machine
		SetupMachine setup = new SetupMachine();
		
		//String to store answer
		String answer = "";
		
		do{
			
			//Prompt for node instances.
			System.out.println("\nDo you want to add node instances? Please type the class name from the list above. If not just press enter.");
			answer = keyboard.nextLine();
			
			if(!answer.equals("")){
				
				NodeClass type = nodes.get(0);
				
				for(int i = 0; i < nodes.size(); i++ ){
					if(answer.equals(nodes.get(i).getName())) type = nodes.get(i);
				}
				
				System.out.println("\n\tHow do you want to call this instance? (lowercase please)");
				answer = keyboard.nextLine();
				
				NodeInstance node = new NodeInstance(type);
				node.setLabel(answer);
				
				setup.addNode(new NodeInstance(type));
				
			}
			
		}while(!answer.equals(""));
		
		do{

		//Prompt for transition instances.
		System.out.println("\nDo you want to add transition instances? Please type the class name from the list above. If not just press enter.");
		answer = keyboard.nextLine();
		
		if(!answer.equals("")){
			
			TransitionClass type = transitions.get(0);
			
			for(int i = 0; i < transitions.size(); i++ ){
				if(answer.equals(transitions.get(i).getName())) type = transitions.get(i);
			}
			
			TransitionInstance currentTrans = new TransitionInstance(type);
			
			do{
				
				System.out.println("\n\tAny source for this transtion? (Insert label: default label is nodeclass name in lowercase.)");
				answer = keyboard.nextLine();
				
				if(!answer.equals("")){
					
					NodeInstance source = setup.getNodes().get(0);
					
					for(int i = 0; i < setup.getNodes().size(); i++ ){
						if(answer.equals(setup.getNodes().get(i).getLabel())) source = setup.getNodes().get(i);
					}
					
					currentTrans.addSource(source);	
					
				}
				
			}while(!answer.equals(""));
			
			
			
			do{
				
				System.out.println("\n\tAny targets for this transtion? (Insert label: default label is nodeclass name in lowercase.)");
				answer = keyboard.nextLine();
				
				if(!answer.equals("")){
					
					NodeInstance target = setup.getNodes().get(0);
					
					for(int i = 0; i < setup.getNodes().size(); i++ ){
						if(answer.equals(setup.getNodes().get(i).getLabel())) target = setup.getNodes().get(i);
					}
					
					currentTrans.addTarget(target);	
					
				}
				
			}while(!answer.equals(""));
			
			
			
			setup.addTransition(currentTrans);
			
		}
		
	}while(!answer.equals(""));
		

		//Create nodeclass that will serve as statemachine
		NodeClass stateMachine = new NodeClass(behaviorName, setup);
		
		BehaviorWriter behavior = new BehaviorWriter(stateMachine);
		
		System.out.println("FSM Code:\n\n");
		behavior.getFSM();
		
	}

}
