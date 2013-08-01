/*
 * Created on Dec 22, 2004
 */
package commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.*;

/**
 * @author asangpet
 *
 */
public class MoveBendpointCommand extends Command {
	  private AbstractConnectionModel connection;
	  private Point oldLocation, newLocation;
	  private int index;

	  @Override
	  public void execute() {
	    oldLocation = connection.getBendpoints().get(index);
	    connection.replaceBendpoint(index, newLocation);
	  }

	  public void setConnectionModel(Object model) {
	    connection = (AbstractConnectionModel) model;
	  }

	  public void setIndex(int i) {
	    index = i;
	  }

	  public void setNewLocation(Point point) {
	    newLocation = point;
	  }

	  @Override
	  public void undo() {
	    connection.replaceBendpoint(index, oldLocation);
	  }
}
