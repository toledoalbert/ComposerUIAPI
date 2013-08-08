package composer;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		/*String editorArea = layout.getEditorArea();
		
		IFolderLayout tabs = layout.createFolder("ID_TABS_FOLDER", IPageLayout.RIGHT, 0.3f, editorArea);
		
		//tabs.addView(IPageLayout.ID_OUTLINE);
		tabs.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		layout.setEditorAreaVisible(false);
		*/
	}
}
