package tgef;

import java.io.Serializable;

public class Connection implements Serializable {
	
	public static final int CONNECTION_DESIGN = 1;
	public static final int CONNECTION_RESOURCES = 2;
	public static final int CONNECTION_WORKPACKAGES = 3;
	
	private int connectionType;
	
	protected Node sourceNode;
	protected Node targetNode;
	
	public Connection(Node sourceNode, Node targetNode, int connectionType) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.connectionType = connectionType;
	}

	public Node getSourceNode() {
		return sourceNode;
	}
	
	public Node getTargetNode() {
		return targetNode;
	}

	public void connect() {
		sourceNode.addConnections(this);
		targetNode.addConnections(this);
	}
	
	public void disconnect() {
		sourceNode.removeConnection(this);
		targetNode.removeConnection(this);
	}
	
	public void reconnect(Node sourceNode, Node targetNode) {
		if (sourceNode == null || targetNode == null || sourceNode == targetNode) {
			throw new IllegalArgumentException();
		}
		disconnect();
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		connect();
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}

	public int getConnectionType() {
		return connectionType;
	}

}

