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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.tekkotsu.api.DefaultClassReader;
import org.tekkotsu.api.MapBuilderWizard;
import org.tekkotsu.api.MapBuilderWriter;
import org.tekkotsu.api.MapRequestObject;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.Parameter;

public class TransWizardPage extends WizardPage {

	//Data structures
	Map nodeMap = new HashMap<String, NodeClass>();
	
	//Components
	private Composite container;
	private Label lTrans, lNodes, lParameter, lSources, lTargets;
	private Text parameter ;
	private Combo transCombo, nodesCombo;
	private Button addSource, addTrans;
	private List sources, transitions;
	private DefaultClassReader reader;
	
	public TransWizardPage() {
		super("Transition Instance Wizard");
		setTitle("New Transition Instance");
		setDescription("This wizard helps you create a new transition instance for the setup machine of your behavior.");
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
	    lTrans = new Label(container, SWT.NONE);
	    lTrans.setText("Transitions");
	    lTrans.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));

	    lNodes = new Label(container, SWT.NONE);
	    lNodes.setText("Nodes");
	    lNodes.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false, 1,1));
	    
	    //Second Row
	    transCombo = new Combo(container, SWT.READ_ONLY);
	    try {
			reader = new DefaultClassReader();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    for(int i = 0; i < reader.getNodes().size(); i++){
	    	transCombo.add(reader.getNodes().get(i).getName());
	    	nodeMap.put(reader.getNodes().get(i).getName(), reader.getNodes().get(i));
	    }
    
	    transCombo.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
	    
	    nodesCombo = new Combo(container, SWT.READ_ONLY);
	    try {
			reader = new DefaultClassReader();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    for(int i = 0; i < reader.getNodes().size(); i++){
	    	nodesCombo.add(reader.getNodes().get(i).getName());
	    	nodeMap.put(reader.getNodes().get(i).getName(), reader.getNodes().get(i));
	    }
    
	    nodesCombo.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false, 1, 1));
	    
	   /*
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
	    });*/
	    
   
    // Required to avoid an error in the system
    setControl(container);
    setPageComplete(false);

	}
	
	//Methods to return results
	public NodeInstance getNode(){
		
		//Create instance
		NodeInstance instance = new NodeInstance((NodeClass)nodeMap.get(nodesCombo.getText()));
		
		//If there is a parameter add it to the instance
		if(parameter.getVisible() && !parameter.getText().isEmpty())
			instance.getParameters().get(0).setValue(parameter.getText()); //TODO
		
		
		return instance;
		
	}

	
 
}

