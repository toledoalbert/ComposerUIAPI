package org.tekkotsu.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BehaviorWriterTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	
		//Get soundnode class from file
		XStream xstream = new XStream(new DomDriver());
		System.out.println("XStream object created.\n");
		
		FileInputStream inputfile = new FileInputStream("Nodes.xml");
		System.out.println("FileInputStream object created.\n");
		
		NodeClass soundNode = (NodeClass) xstream.fromXML(inputfile);
		System.out.println("SoundNode NodeClass object created from XML file.\n");

		inputfile.close();
		System.out.println("InputFile closed.\n");
		
		//Create a transitionclass by hand.
		TransitionClass button = new TransitionClass("Button", new ConstructorCall("B"));
		System.out.println("Button transition class created by hand.\n");
		
		//Node instances from soundnode class to be created
		ArrayList<NodeInstance> nodes = new ArrayList<NodeInstance>();
		System.out.println("ArrayList to store node instances is created.\n");
		
		for(int i = 0; i < 4; i++){
			nodes.add(new NodeInstance(soundNode));
			nodes.get(i).setLabel(nodes.get(i).getLabel() + Integer.toString(i) );
			System.out.println("NodeInstance with label \"" + nodes.get(i).getLabel() + "\" is added to the list.\n");	
		}
		System.out.println("Creation and addition of the node instances complete.\n");
		
		//TransitionInstance from button transition class to be created.
		ArrayList<TransitionInstance> trans = new ArrayList<TransitionInstance>();
		System.out.println("ArrayList to store transition instances created.\n");
		
		TransitionInstance buttonInstance = new TransitionInstance(button);
		trans.add(buttonInstance);
		System.out.println("Instance of button transition created and added to the list.\n");
		
		//Add button transition as incoming to first three nodes.
		for(int i = 0; i < 3; i++){
			nodes.get(i).addInTrans(buttonInstance);
			trans.get(0).addTarget(nodes.get(i));
			System.out.println("Relation between button and " + nodes.get(i).getLabel() + " is built.\n");
		}
		
		//Add button to the last node instance as outgoing
		nodes.get(3).addOutTrans(buttonInstance);
		trans.get(0).addSource(nodes.get(3));
		System.out.println("Relation between button and " + nodes.get(3).getLabel() + "is built.\n");
		
		
		//Create setup machine
		SetupMachine setup = new SetupMachine();
		System.out.println("Setup machine object created.\n");
		
		setup.setNodes(nodes);
		System.out.println("Nodes added to the setupmachine.\n");
		
		setup.setTransitions(trans);
		System.out.println("Transitions added to the setupmachine.\n");
		
		NodeClass myBehavior = new NodeClass("MyBehavior", setup);
		System.out.println("NodeClass object for my behavior created.\n");
		
		BehaviorWriter writeBehavior = new BehaviorWriter(myBehavior);
		System.out.println("BehaviorWriter object created.\n");
		
		System.out.println("FSM CODE: \n");
		
		writeBehavior.getFSM();
		
	}

}
