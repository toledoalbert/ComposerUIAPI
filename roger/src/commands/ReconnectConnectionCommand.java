/*
 * Created on Sep 21, 2004
 */
package commands;

import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.*;
import tgef.Node;

/**
 * @author asangpet
 */
public class ReconnectConnectionCommand extends Command {
	private AbstractConnectionModel connection;

	private Node newSource = null;

	private Node newTarget = null;

	private Node oldSource = null;

	private Node oldTarget = null;

	@Override
	public void execute() {
		if (newSource != null) {
			oldSource = connection.getSource();
			reconnectSource(newSource);
		}
		if (newTarget != null) {
			oldTarget = connection.getTarget();
			reconnectTarget(newTarget);
		}
	}

	private void reconnectSource(Node source) {
		connection.detachSource();
		connection.setSource(source);
		connection.attachSource();
	}

	private void reconnectTarget(Node target) {
		connection.detachTarget();
		connection.setTarget(target);
		connection.attachTarget();
	}

	public void setConnectionModel(Object model) {
		connection = (AbstractConnectionModel) model;
	}

	public void setNewSource(Object model) {
		newSource = (Node) model;
	}

	public void setNewTarget(Object model) {
		newTarget = (Node) model;
	}

	@Override
	public void undo() {
		if (oldSource != null)
			reconnectSource(oldSource);
		if (oldTarget != null)
			reconnectTarget(oldTarget);
		oldSource = null;
		oldTarget = null;
	}
}