package org.tekkotsu.api;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GenerateDefaultTransitions {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//Create ArrayList for all default transitions.
		ArrayList<TransitionClass> defaultTransitions = new ArrayList<TransitionClass>();
		
		//Create components of TimeOut transition
		ConstructorCall TimeoutConstructor = new ConstructorCall("T");
		Parameter miliSeconds = new Parameter("int", "1000");
		TimeoutConstructor.addParameter(miliSeconds);
		System.out.println("Components of TimeOut created.");
		//Create Timeout
		TransitionClass timeout = new TransitionClass("TimeoutTrans", TimeoutConstructor);
		timeout.setColor("#0AAF53");
		timeout.setDefinition("Fires after n miliseconds.");
		System.out.println("TimeoutTrans object created.");
		defaultTransitions.add(timeout);	
		
		//Create components of Completion transition
		ConstructorCall CompletionConstructor = new ConstructorCall("C");
		System.out.println("Components of CompletionTrans created.");
		//Create Completion
		TransitionClass comp = new TransitionClass("CompletionTrans", CompletionConstructor);
		comp.setColor("#0AAF53");
		comp.setDefinition("Fires when the source node's action is complete.");
		System.out.println("CompletionTrans object created.");
		defaultTransitions.add(comp);	
		
		//Create components of NullTrans transition
		ConstructorCall NullConstructor = new ConstructorCall("N");
		System.out.println("Components of NullTrans created.");
		//Create Completion
		TransitionClass nullt = new TransitionClass("NullTrans", NullConstructor);
		nullt.setColor("#0AAF53");
		nullt.setDefinition("Fires immeadiately.");
		System.out.println("NullTrans object created.");
		defaultTransitions.add(nullt);	
		
		//Create components of Button transition
		ConstructorCall ButtonConstructor = new ConstructorCall("B");
		System.out.println("Components of ButtonTrans created.");
		//Create Completion
		TransitionClass button = new TransitionClass("ButtonTrans", ButtonConstructor);
		button.setColor("#0AAF53");
		button.setDefinition("Fires when button clicked.");
		System.out.println("ButtonTrans object created.");
		defaultTransitions.add(button);	
		
		//XSTREAM PART to write it to xml file called DefaultTransitions.xml
		XStream xstream = new XStream(new DomDriver());
		try {
			//Create file to write
			FileOutputStream file = new FileOutputStream("DefaultTransitions.xml");
			System.out.println("DefaultTransitions.xml created.");
			
			//Write xml of soundNode to file.
			xstream.toXML(defaultTransitions, file);
			System.out.println("defaultTrans written to DefaultTransitions.xml");
			
			
			//Close file
			file.close();
			System.out.println("DefaultTransitions.xml closed.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		


	}

}
