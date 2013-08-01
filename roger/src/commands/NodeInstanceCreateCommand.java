package commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.tekkotsu.api.NodeInstance;


import tgef.Entreprise;

public class NodeInstanceCreateCommand extends Command{
	
	private Entreprise en;
	private NodeInstance srv;
	
	public NodeInstanceCreateCommand() {
	super();
	en = null;
	srv = null;
	}
	
 
	public void setNodeInstance(Object s) {
	if (s instanceof NodeInstance)
	this.srv = (NodeInstance)s;
	}
	
	
	public void setEntreprise(Object e) { 
	if (e instanceof Entreprise) 
	this.en = (Entreprise)e; 
	} 
	
	public void setLayout(Rectangle r) { 
	if (srv == null)
	return;
	
	srv.setLayout(r);
	
	} 
	
	@Override
	public boolean canExecute() {
	if (srv == null || en == null)
	return false;
	return true;
	}
	@Override
	public void execute() {
	en.addChild(srv);
	}
	
	@Override
	public boolean canUndo() {
	if (en == null || srv == null)
	return false;
	
	return en.contains(srv);
	}
	
	
	@Override
	public void undo() {
	en.removeChild(srv);
	}



}
