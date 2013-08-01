package tests;

import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.ConstructorCall;
import org.tekkotsu.api.Method;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;
import org.tekkotsu.api.Variable;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		NodeClass node = new NodeClass("Node", new ConstructorCall("Const"));
		
		NodeClass node2 = new NodeClass("Node2", new ConstructorCall("Const"));
		
		NodeClass node3 = new NodeClass("Node3", new ConstructorCall("Const"));
		
		node2.addVariable(new Variable("int", "num"));
		
		Method met = new Method("getSum", "void");
		
		met.setBody("method body");
		
		node2.addMethod(met);
		node2.addMethod(met);
		
		node2.addParent(new ConstructorCall("Const"));
		
		node2.addSubClass(node3);
		
		node.addSubClass(node2);

		SetupMachine setup = new SetupMachine();
		
		NodeInstance inst1 = new NodeInstance(node2);
		setup.addNode(inst1);
		node.setSetupMachine(setup);
		
		
		new BehaviorWriter(node).writeBehavior();
		
		
		
	}

}
