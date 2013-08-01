/*******************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Elias Volanakis - initial API and implementation
 *******************************************************************************/
package commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.tekkotsu.api.TransitionInstance;

import tgef.Connection;
import tgef.Node;





public class ConnectionCreateCommand extends Command {
	
	  private Node sourceNode, targetNode;
	    private Connection conn;
	    private int connectionType;
	    
	    public void setSourceNode(Node sourceNode) {
	        this.sourceNode = sourceNode;
	    }
	    
	    public void setTargetNode(Node targetNode) {
	        this.targetNode = targetNode;
	    }
	    
	    @Override 
	    public boolean canExecute() { 
	        if (sourceNode == null || targetNode == null) 
	            return false;
	        else if (sourceNode.equals(targetNode)) 
	            return false;
	        return true; 
	    }
	    
	    @Override 
	    public void execute() {
	        conn = new Connection(sourceNode, targetNode, connectionType);
	        conn.connect();
	    } 
	    
	    @Override 
	    public boolean canUndo() {
	        if (sourceNode == null || targetNode == null || conn == null) 
	            return false; 
	        return true;          
	    } 
	    
	    @Override 
	    public void undo() { 
	        conn.disconnect();
	    }

	    public void setConnectionType(int connectionType) {
	        this.connectionType = connectionType;
	    }

	    public int getConnectionType() {
	        return connectionType;
	    }
}
