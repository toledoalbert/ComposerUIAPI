package composer;

//Adds toolbar to the editor, placed in contributor class of the editor
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

public class StateMachineEditorActionBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
	
		//Handles undo action
		addRetargetAction(new UndoRetargetAction());
		
		//handles redo action
		addRetargetAction(new RedoRetargetAction());
	
		//handles delete action
		addRetargetAction(new DeleteRetargetAction());
	
		//handles zoom in and zoom out actions
		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());

	}
	
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		
		//Contributes undo and redo actions to toolbar
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		
		//contributes delete action to toolbar
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));

		//contributes zoom in and zoom out to tool bar
		toolBarManager.add(new Separator());
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
		toolBarManager.add(new ZoomComboContributionItem(getPage()));

		}


	
	@Override
	protected void declareGlobalActionKeys() {
	}

	public void contributeToMenu(IMenuManager menuManager) {
	
	}

}
