package org.tekkotsu.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

//This is the class that will be extended in every layout changing command
public abstract class AbstractLayoutCommand extends Command {

	//Set the constraint
	public abstract void setConstraint(Rectangle rect);

	//Set the model which is the real object
	public abstract void setModel(Object model);
}
