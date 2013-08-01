package org.tekkotsu.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DefaultClassReader {

	//Attributes
	ArrayList<NodeClass> nodes;
	ArrayList<TransitionClass> trans;
	FileInputStream nodesFile;
	FileInputStream transFile;
	
	
	//Constructor
	@SuppressWarnings("unchecked")
	public DefaultClassReader() throws FileNotFoundException{
		
		//XStream object
		XStream xstream = new XStream(new DomDriver());
		
		nodesFile = new FileInputStream("/home/roger/workspaceIndigo/TGEF/DefaultNodes.xml");//TODO
		transFile = new FileInputStream("/home/roger/workspaceIndigo/TGEF/DefaultTransitions.xml"); //TODO
		
		nodes = (ArrayList<NodeClass>) xstream.fromXML(nodesFile);
		trans = (ArrayList<TransitionClass>) xstream.fromXML(transFile);	
		
	}
	
	//Method to get transitions as a list
	public ArrayList<TransitionClass> getTransitions(){
		return trans;
	}
	
	//Method to get nodes as a list
	public ArrayList<NodeClass> getNodes(){
		return nodes;
	}
	

}
