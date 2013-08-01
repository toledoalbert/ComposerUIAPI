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






	public class ConnectionReconnectCommand extends Command {
	    
	    private Connection conn;
	    private Node oldSourceNode;
	    private Node oldTargetNode;
	    private Node newSourceNode;
	    private Node newTargetNode;
	    
	    public ConnectionReconnectCommand(Connection conn) {
	        if (conn == null) {
	            throw new IllegalArgumentException();
	        }
	        this.conn = conn;
	        this.oldSourceNode = conn.getSourceNode();
	        this.oldTargetNode = conn.getTargetNode();
	    }
	    
	    public boolean canExecute() {
	        if (newSourceNode != null) {
	            return checkSourceReconnection();
	        } else if (newTargetNode != null) {
	            return checkTargetReconnection();
	        }
	        return false;
	    }
	    
	    private boolean checkSourceReconnection() {
	        if (newSourceNode == null) 
	            return false;
	        else if (newSourceNode.equals(oldTargetNode)) 
	            return false;
	        else if (!newSourceNode.getClass().equals(oldTargetNode.getClass()))
	            return false;
	        return true; 
	    }
	    
	    private boolean checkTargetReconnection() {
	        if (newTargetNode == null) 
	            return false;
	        else if (oldSourceNode.equals(newTargetNode)) 
	            return false;
	        else if (!oldSourceNode.getClass().equals(newTargetNode.getClass()))
	            return false;
	        return true; 
	    }
	    
	    public void setNewSourceNode(Node sourceNode) {
	        if (sourceNode == null) {
	            throw new IllegalArgumentException();
	        }
	        this.newSourceNode = sourceNode;
	        this.newTargetNode = null;
	    }
	    
	    public void setNewTargetNode(Node targetNode) {
	        if (targetNode == null) {
	            throw new IllegalArgumentException();
	        }
	        this.newSourceNode = null;
	        this.newTargetNode = targetNode;
	    }
	    
	    public void execute() {
	        if (newSourceNode != null) {
	            conn.reconnect(newSourceNode, oldTargetNode);
	        } else if (newTargetNode != null) {
	            conn.reconnect(oldSourceNode, newTargetNode);
	        } else {
	            throw new IllegalStateException("Should not happen");
	        }
	    }
	    
	    public void undo() {
	        conn.reconnect(oldSourceNode,oldTargetNode);
	    }
	}


