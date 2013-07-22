package org.tekkotsu.api;

public class MapBuilderWriter {

	//Attributes
	private MapBuilderWizard wizard;
	private Method doStart;
	private NodeClass nClass;
	
	//Constructor
	public MapBuilderWriter(MapBuilderWizard wizard){
		this.wizard = wizard;
		this.doStart = new Method("doStart", "void");
		this.nClass = new NodeClass("LookForObjects", new ConstructorCall("const"));
	}
	
	//
	public String getCode(){
		
		//Header string
		String code = "";
		
		//Return the code string.
		return code;
		
	}
	
	public Method getDoStart(){
		
		//add the code body
		doStart.setBody(getCode());
		
		//Return the method dostart
		return doStart;
	}
}
