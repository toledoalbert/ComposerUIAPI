package org.tekkotsu.wizards;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.tekkotsu.api.DefaultClassReader;
import org.tekkotsu.api.MapBuilderWizard;
import org.tekkotsu.api.MapBuilderWriter;
import org.tekkotsu.api.MapRequestObject;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.Parameter;

public class NodeWizardPage extends WizardPage {

	//Data structures
	Map nodeMap = new HashMap<String, NodeClass>();
	
	//Components
	private Composite container;
	private Label lLabel, nodeClass, lParameter;
	private Text label, parameter ;
	private Combo nodeCombo;
	private DefaultClassReader reader;
	
	public NodeWizardPage() {
		super("Node Instance Wizard");
		setTitle("New Node Instance");
		setDescription("This wizard helps you create a new node instance for the setup machine of your behavior.");
	}

	@Override
	public void createControl(Composite parent) {
	
		//Composite for the wizardpage
	    container = new Composite(parent, SWT.NONE);
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 2;
	    layout.makeColumnsEqualWidth = true;	  
	  
	    //First row
	    lLabel = new Label(container, SWT.NONE);
	    lLabel.setText("Label");
	    lLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));

	    label = new Text(container, SWT.SINGLE | SWT.BORDER);
	    label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
	    
	    //Second Row
	    nodeClass = new Label(container, SWT.NONE);
	    nodeClass.setText("Node Class");
	    nodeClass.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
	    
	    nodeCombo = new Combo(container, SWT.READ_ONLY);
	    try {
			reader = new DefaultClassReader();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    for(int i = 0; i < reader.getNodes().size(); i++){
	    	nodeCombo.add(reader.getNodes().get(i).getName());
	    	nodeMap.put(reader.getNodes().get(i).getName(), reader.getNodes().get(i));
	    }
    
	    nodeCombo.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
	    
	    //Third Row
	    lParameter = new Label(container, SWT.NONE);
	    lParameter.setText("Parameter");
	    lParameter.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));

	    parameter = new Text(container, SWT.SINGLE | SWT.BORDER);
	    parameter.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
	    
	    lParameter.setVisible(false);
	    parameter.setVisible(false);
	    
	    //Depending on the selected node show parameter field or not.
	    nodeCombo.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent e) {
	            if (nodeCombo.getText().equals("SoundNode") | nodeCombo.getText().equals("SpeechNode")) {
	              lParameter.setVisible(true);
	              parameter.setVisible(true);
	            }else{
	            	lParameter.setVisible(false);
	            	parameter.setVisible(false);
	            }
	            
	            if (!label.getText().isEmpty() && ((parameter.getVisible() 
		        		  && !parameter.getText().isEmpty()) | (!parameter.getVisible()))){
		        	  setPageComplete(true);
		         }else{
		        	 setPageComplete(false);
		         }
	        }
	    });
	    
   
    // Required to avoid an error in the system
    setControl(container);
    setPageComplete(false);

	}
	
	//Methods to return results
	public NodeInstance getNode(){
		
		//Create instance
		NodeInstance instance = new NodeInstance((NodeClass)nodeMap.get(nodeCombo.getText()));
		instance.setLabel(label.getText());
		
		//If there is a parameter add it to the instance
		if(parameter.getVisible() && !parameter.getText().isEmpty())
			instance.getParameters().get(0).setValue(parameter.getText()); //TODO
		
		
		return instance;
		
	}

	
 
}

