package org.tekkotsu.api;

import java.util.ArrayList;

public class MapBuilderWriter {

	//Attributes
	private MapBuilderWizard wizard;
	private Method doStart;
	private NodeClass nClass;
	
	//Constructor
	public MapBuilderWriter(MapBuilderWizard wizard){
		this.wizard = wizard;
		this.doStart = new Method("doStart", "void");
		this.nClass = new NodeClass(wizard.getName(), new ConstructorCall("const"));
	}
	
	//
	public String getCode(){
		
		//Header string
		String code = "//Wizard Generated Code starts here (MapBuilder requests)\n";
		
		//List of the objects from wizard
		ArrayList<MapRequestObject> objects = wizard.getObjects();
		
		//For every object make a new line and call the addObjectColor method
		for(int i = 0; i < objects.size(); i++){
			
			code += "\nmapreq.addObjectColor(" + objects.get(i).getShape() + ", " + objects.get(i).getColor() + ");";
			
		}
		
		//Add comment
		code += "\n\n//Wizard Generated Code ends here.";
		
		//Return the code string.
		return code;
		
	}
	
	public Method getDoStart(){
		
		//add the code body
		doStart.setBody(getCode());
		
		//Return the method dostart
		return doStart;
	}
	
	public NodeClass getNodeClass(){
		
		//add method to class
		nClass.addMethod(getDoStart());
		
		//return the class
		return nClass;
	}
}
