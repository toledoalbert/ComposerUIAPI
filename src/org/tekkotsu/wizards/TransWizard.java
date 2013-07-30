package org.tekkotsu.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Label;
import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.SetupMachine;
import org.tekkotsu.api.TransWriter;


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
	  
	//add the transition instance to the setupmachine
	composer.ClassView.getNodeClass().getSetupMachine().addTransition(page.getTransition());
	
	//put the trans to the ui list TODO
	composer.ClassView.getTransList().add(page.getTransition().getType().getName() + (composer.ClassView.getTransList().getItems().length + 1));

    return true;
  }
}
 
