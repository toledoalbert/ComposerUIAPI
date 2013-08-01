/*
 * Created on Sep 12, 2004
 */
package tekkotsu.edit.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.PlatformUI;
//import org.tekkotsu.ui.editor.StateMachineEditor;
import org.tekkotsu.ui.util.*;

/**
 * EditPart component extension with properties listener.
 * @author asangpet
 */
abstract public class MyAbstractGraphicalEditPart extends AbstractGraphicalEditPart 
	implements PropertyChangeListener {
	
	@Override
	public void activate() {
		super.activate();
		((AbstractModel)getModel()).props.addPropertyChangeListener(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		((AbstractModel)getModel()).props.removePropertyChangeListener(this);		
	}
	
	/*protected void setDirty() {
		StateMachineEditor editor = (StateMachineEditor)PlatformUI.getWorkbench()
		  .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		editor.setDirty();
	}*/
}
