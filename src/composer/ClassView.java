package composer;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.tekkotsu.wizards.MapWizard;

public class ClassView extends ViewPart {

	public ClassView() {
		// TODO Auto-generated constructor stub
	}

	static Text code;
	
	public static Text getCodeText(){
		return code;
	}
	
	
	@Override
	public void createPartControl(final Composite parent) {
		// TODO Auto-generated method stub
		/*
		//Components
		Text behaviorName;
		List varList, methodList, subList;
		Button setupMachine;
		*/
		
		//Set layout for the composite
		GridLayout gridParent = new GridLayout();
		gridParent.horizontalSpacing = 10;
		gridParent.makeColumnsEqualWidth = true;
		gridParent.numColumns = 1;
		parent.setLayout(gridParent);
		
		//Button to launch wizard and its operations
				Button wizard = new Button(parent, SWT.BORDER);
				wizard.setText("Open MapBuilder Wizard");
				wizard.addSelectionListener(new SelectionAdapter(){
					
					@Override
					public void widgetSelected(SelectionEvent e){
						
						WizardDialog wizDial = new WizardDialog(parent.getShell(),new MapWizard());
						if(wizDial.open()==Window.OK){
							System.out.println("Ok pressed");
						}else{
							System.out.println("Cancel pressed");
						}
						
					}
					
				});
		
		code = new Text(parent, SWT.MULTI);
		code.setText("");
		
		GridData codeGrid = new GridData(); codeGrid.verticalSpan = 1; codeGrid.horizontalSpan = 1;
		codeGrid.grabExcessHorizontalSpace = true; codeGrid.grabExcessVerticalSpace = true;
		codeGrid.horizontalAlignment = SWT.FILL; codeGrid.verticalAlignment = SWT.FILL;
		code.setLayoutData(codeGrid);
		
		

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	
}
