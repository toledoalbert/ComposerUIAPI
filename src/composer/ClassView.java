package composer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.ConstructorCall;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.gef.StateMachineEditorInput;
import org.tekkotsu.wizards.MapWizard;
import org.tekkotsu.wizards.NodeWizard;
import org.tekkotsu.wizards.TransWizard;

public class ClassView extends ViewPart {

	//Data structures to store the fsm representations
	static NodeClass behavior = new NodeClass("MyBehavior", new ConstructorCall("const"));
	
	//Components
	static Label lName, lSetup, lNodes, lTrans, lSubs, lMethods, lVars, lFSM;
	static Text name, code, fileName;
	static ToolBar bar;
	static ToolItem mapWizard, newSubclass, newMethod, newNode, newTrans, newVar;
	static List vars, nodes, trans, methods, subs;
	static Button generate, save, editSetup;

	@Override
	public void createPartControl(final Composite parent) {
		
		//Set layout for the composite
		GridLayout gridParent = new GridLayout();
		gridParent.horizontalSpacing = 10;
		//gridParent.makeColumnsEqualWidth = true;
		gridParent.numColumns = 5;
		parent.setLayout(gridParent);
		
		//Create the toolbar
		bar = new ToolBar(parent, SWT.BORDER);
		bar.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 3,1));
		
		lFSM = new Label(parent, SWT.NONE);
		lFSM.setText("Generated FSM Code");
		lFSM.setLayoutData(new GridData(SWT.BEGINNING, SWT.BOTTOM, false, false, 2,1)); 
		
		
		//ToolItems ROW
		newNode = new ToolItem(bar, SWT.PUSH);
		newNode.setText("Node");
		
		newTrans = new ToolItem(bar, SWT.PUSH);
		newTrans.setText("Transition");
		
		newVar = new ToolItem(bar, SWT.PUSH);
		newVar.setText("Variable");
		
		newMethod = new ToolItem(bar, SWT.PUSH);
		newMethod.setText("Method");
		
		newSubclass = new ToolItem(bar, SWT.PUSH);
		newSubclass.setText("Subclass");
		
		mapWizard = new ToolItem(bar, SWT.PUSH);
		mapWizard.setText("MapBuilder");
		
		//First ROW
		lName = new Label(parent, SWT.NONE);
		lName.setText("Behavior Name:");
		lName.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1)); //TODO
		
		name = new Text(parent, SWT.BORDER);
		name.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2,1));
		
		code = new Text(parent, SWT.BORDER);
		GridData codeGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 2,10);
		codeGrid.widthHint = 500;
		code.setLayoutData(codeGrid);
		
		
		//Second ROW
		lSetup = new Label(parent, SWT.NONE);
		lSetup.setText("Setup Machine");
		lSetup.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
		
		editSetup = new Button(parent, SWT.PUSH);
		editSetup.setText("Edit");
		editSetup.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 2,1));
		
		//Third ROW
		lNodes = new Label(parent, SWT.NONE);
		lNodes.setText("Nodes");
		lNodes.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
		
		lTrans = new Label(parent, SWT.NONE);
		lTrans.setText("Transitions");
		lTrans.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 2,1));
		
		//Fourth ROW
		nodes = new List(parent, SWT.BORDER);
		GridData nodesGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 1,3);
		nodesGrid.heightHint = 200;
		nodes.setLayoutData(nodesGrid);
		
		trans = new List(parent, SWT.BORDER);
		GridData transGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 2,3);
		transGrid.heightHint = 200;
		trans.setLayoutData(transGrid);
		
		//Fifth ROW
		lSubs = new Label(parent, SWT.NONE);
		lSubs.setText("Sub Classes");
		lSubs.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
		
		lMethods = new Label(parent, SWT.NONE);
		lMethods.setText("Methods");
		lMethods.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
		
		lVars = new Label(parent, SWT.NONE);
		lVars.setText("Variables");
		lVars.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
		
		//Sixth ROW
		subs = new List(parent, SWT.BORDER);
		GridData subsGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 1,3);
		subsGrid.heightHint = 200;
		subs.setLayoutData(subsGrid);
		
		methods = new List(parent, SWT.BORDER);
		GridData methodsGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 1,3);
		methodsGrid.heightHint = 200;
		methods.setLayoutData(methodsGrid);
		
		vars = new List(parent, SWT.BORDER);
		GridData varsGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 1,3);
		varsGrid.heightHint = 200;
		vars.setLayoutData(varsGrid);
		
		//Seventh ROW
		generate = new Button(parent, SWT.PUSH);
		generate.setText("Show FSM Code!");
		generate.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, false, 3,1));
		
		fileName = new Text(parent, SWT.BORDER);
		fileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,1));
		fileName.setText("MyCode");
		
		save = new Button(parent, SWT.NONE);
		save.setText("Save Code");
		save.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
		String path;		
		
		
		//Operation for save code button
		editSetup.addSelectionListener(new SelectionListener() {
			 
			public void widgetDefaultSelected(SelectionEvent e) {
			}
 
			public void widgetSelected(SelectionEvent e) {
				
				//Get the page object
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
				//Try opening the editor with the editor input.
				try {
					page.openEditor(new StateMachineEditorInput("StateMachine"), "editor.statemachineeditor", false);
					page.setEditorAreaVisible(true);
				} catch (PartInitException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				
			}
		});		
		
		
		//Operation for save code button
		save.addSelectionListener(new SelectionListener() {
			 
			public void widgetDefaultSelected(SelectionEvent e) {
			}
 
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(save.getShell(),  SWT.OPEN  );
				dlg.setText("Save");
				String path = dlg.open();
				System.out.println(path);
				if (path == null) return;
				
				File file = new File(path+"/"+fileName.getText()+".cc.fsm");
				try {
					FileWriter write = new FileWriter(file);
					write.write(code.getText());
					write.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		

		//Operation for the mapbuilder wizard.
		mapWizard.addSelectionListener(new SelectionAdapter(){
					
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
		
		
		//Operation for the new node instance wizard. from the toolbar.
		newNode.addSelectionListener(new SelectionAdapter(){
					
			@Override
			public void widgetSelected(SelectionEvent e){
						
				WizardDialog wizDial = new WizardDialog(parent.getShell(),new NodeWizard());
				if(wizDial.open()==Window.OK){
					System.out.println("Ok pressed");
				}else{
					System.out.println("Cancel pressed");
				}
						
			}
					
		});
		
		
		//Operation for the new transition instance wizard. from the toolbar.
		newTrans.addSelectionListener(new SelectionAdapter(){
					
			@Override
			public void widgetSelected(SelectionEvent e){
						
				WizardDialog wizDial = new WizardDialog(parent.getShell(),new TransWizard());
				if(wizDial.open()==Window.OK){
					System.out.println("Ok pressed");
				}else{
					System.out.println("Cancel pressed");
				}
						
			}
					
		});
		
		
		//Operation for the fsm code generator button
		generate.addSelectionListener(new SelectionAdapter(){
			
			@Override
			public void widgetSelected(SelectionEvent e){
					
				//If name field is filled
				if(!name.getText().isEmpty()){
					behavior.setName(name.getText());
					fileName.setText(name.getText());
				}
				
				//Set the generated code views text to the recently generated code.
				code.setText(new BehaviorWriter(behavior).writeBehavior());
			}
					
		});
		

	}
	
	//Static Methods to provide access to the components
	public static Text getCodeText(){
		return code;
	}
	
	public static List getSubs(){
		return subs;
	}
	
	public static NodeClass getNodeClass(){
		return behavior;
	}
	
	public static List getNodesList(){
		return nodes;
	}
	
	public static List getTransList(){
		return trans;
	}
	
	//Method to return common gridData objects.
	public GridData spanOne(int span){
		GridData grid = new GridData();
		grid.horizontalSpan = span;
		return grid;
	}
	
	


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	
}
