package org.tekkotsu.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Label;
import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.NodeClass;


public class MapWizard extends Wizard {

  protected MapWizardPage page;

  public MapWizard() {
    super();
    setNeedsProgressMonitor(true);
  }

  @Override
  public void addPages() {
    page = new MapWizardPage();
    addPage(page);
  }

  @Override
  public boolean performFinish() {
    // Print the result to the console
    System.out.println(page.getWriter().getCode());
	  
	  
	/*  
	NodeClass mapBuilder = page.getWriter().getNodeClass();
    
    System.out.println(new BehaviorWriter(mapBuilder).getClassDef(0));*/

    return true;
  }
}
 
