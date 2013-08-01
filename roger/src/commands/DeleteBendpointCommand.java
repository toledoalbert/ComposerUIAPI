/*
 * Created on Dec 22, 2004
 */
package commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.AbstractConnectionModel;

/**
 * @author asangpet
 *
 */
public class DeleteBendpointCommand extends Command {
	  private AbstractConnectionModel connection;
	  private Point oldLocation; 
	  private int index;
	  
	  @Override
	public void execute() {
	    oldLocation = connection.getBendpoints().get(index);
	    connection.removeBendpoint(index);
	  }

	  public void setConnectionModel(Object model) {
	    connection = (AbstractConnectionModel) model;
	  }

	  public void setIndex(int i) {
	    index = i;
	  }

	  @Override
	public void undo() {
	    connection.addBendpoint(index, oldLocation);
	  }
}
