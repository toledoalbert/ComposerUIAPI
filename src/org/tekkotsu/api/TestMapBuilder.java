package org.tekkotsu.api;

public class TestMapBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapRequestObject obj1 = new MapRequestObject("blobDataType", "red");
		
		MapBuilderWizard wiz = new MapBuilderWizard("LookForObjects", "localMap", true, true);
		
		wiz.addRequestObject(obj1);
		
		MapBuilderWriter write = new MapBuilderWriter(wiz);
		
	
		
		new BehaviorWriter(	write.getNodeClass()).writeBehavior();
		
		System.out.println(write.getCode());

	}

}
