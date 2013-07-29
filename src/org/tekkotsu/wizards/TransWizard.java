package org.tekkotsu.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Label;
import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.SetupMachine;


public class TransWizard extends Wizard {

  protected TransWizardPage page;

  public TransWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  @Override
  public void addPages() {
    page = new TransWizardPage();
    addPage(page);
  }

  @Override
  public boolean performFinish() {
	  
	//If the behavior doesnt have a setup machine create one
	if(composer.ClassView.getNodeClass().getSetupMachine() == null)
		composer.ClassView.getNodeClass().setSetupMachine(new SetupMachine());
	
	//add the nodeinstance to the setupmachine
	composer.ClassView.getNodeClass().getSetupMachine().addNode(page.getNode());
	
	//Add node to the ui list
	composer.ClassView.getNodesList().add(page.getNode().getLabel());

    return true;
  }
}
 
