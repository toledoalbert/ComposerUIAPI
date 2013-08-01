package commands;

import org.eclipse.gef.commands.Command;
import org.tekkotsu.api.TransitionInstance;



public class TransitionInstanceDeleteCommand extends Command {
    
    private TransitionInstance conn;
    
    public void setLink(Object model) {
    	if (model instanceof TransitionInstance) {
			this.conn = (TransitionInstance)model;
		}
    }
    
    @Override 
    public boolean canExecute() {
        if (conn == null) 
            return false; 
        return true;      
    }
    
    /*
    @Override 
    public void execute() {
        conn.disconnect();
    }*/
    
    @Override 
    public boolean canUndo() {
        if (conn == null) 
            return false;        
        return true; 
    }
    
    /*@Override 
    public void undo() {
        conn.connect();
    }*/
}
