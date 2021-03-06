package org.tekkotsu.wizards;

import java.io.FileNotFoundException;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Label;
import org.tekkotsu.api.BehaviorWriter;
import org.tekkotsu.api.DefaultClassReader;
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
	  
    //Add the generated code to code view
	composer.ClassView.getCodeText().setText(page.getWriter().getCode());
	
	//Create sub nodeclass
	NodeClass map = page.getWriter().getNodeClass();
	
	//Add nodeclass to the nodeclass object of behavior as subclass
	composer.ClassView.getNodeClass().addSubClass(map);
	
	//Add name to the sub classes list in the ui
	composer.ClassView.getSubs().add(map.getName());
	
	/*//Write the new subclass to the xml file so in the node wizard it can show.
	DefaultClassReader reader = null;
	try {
		reader = new DefaultClassReader();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	reader.getNodes().add(map);*/

    return true;
  }
}
 
