package org.tekkotsu.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Label;


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

    return true;
  }
}
 
