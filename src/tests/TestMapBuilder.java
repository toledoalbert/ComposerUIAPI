package tests;

import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.MapBuilderWizard;
import org.tekkotsu.api.MapBuilderWriter;
import org.tekkotsu.api.MapRequestObject;

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
