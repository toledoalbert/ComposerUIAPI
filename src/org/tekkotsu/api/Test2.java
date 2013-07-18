package org.tekkotsu.api;

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
		
		node2.addSubClass(node3);
		
		node.addSubClass(node2);

		
		new BehaviorWriter(node).writeBehavior();
		
		
		
	}

}
