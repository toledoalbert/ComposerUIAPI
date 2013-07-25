package composer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.tekkotsu.api.ConstructorCall;
import org.tekkotsu.api.NodeClass;

public class ClassView extends ViewPart {

	public ClassView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
		//Components
		Text behaviorName;
		List varList, methodList, subList;
		Button setupMachine;
		
		
		//Set layout for the composite
		GridLayout gridParent = new GridLayout();
		gridParent.horizontalSpacing = 10;
		gridParent.makeColumnsEqualWidth = true;
		gridParent.numColumns = 3;
		parent.setLayout(gridParent);
		

		Label nameLabel = new Label(parent, SWT.NONE);
		nameLabel.setText("Behavior Name");
		nameLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false,4,1));
		
		
		
		behaviorName = new Text(parent, SWT.SINGLE | SWT.BORDER);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		gridData.horizontalSpan = 2;
		behaviorName.setLayoutData(gridData);
		
		Label varsLabel = new Label(parent, SWT.NONE);
		varsLabel.setText("Variables");
		varsLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false,4,1));
		
		varList = new List(parent, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		varList.setItems(new String[]{"var1", "var2", "var3", ".."});
		gridData = new GridData(GridData.FILL, SWT.BEGINNING, true, true);
		gridData.verticalSpan = 4;
		gridData.horizontalSpan = 1;
		int listHeight = varList.getItemHeight() * 5;
		Rectangle trim = varList.computeTrim(0, 0, 0, listHeight);
		gridData.heightHint = trim.height;
		System.out.println(trim.height);
		varList.setLayoutData(gridData);
		
		
		

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	
}
