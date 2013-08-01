/*
 * Created on Oct 24, 2004
 */
package commands;

import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.*;
import tgef.LayoutData;

/**
 * @author asangpet
 *
 */
public class CreateMultiTransitionCommand extends Command {
	private LayoutData            contentsModel;
	private MultiTransitionModel  model;
	
	@Override
	public void execute() {		
		contentsModel.addChild(model);
		model.doPostAdd();		
	}
	
	public void setContentsModel(Object model) {
		contentsModel = (LayoutData) model;
	}
	
	public void setMultiTransitionModel(Object model) {
		this.model = (MultiTransitionModel)model;		
	}
		
	@Override
	public void undo() {
		model.doPreDelete(contentsModel);
		contentsModel.removeChild(model);
	}
}
