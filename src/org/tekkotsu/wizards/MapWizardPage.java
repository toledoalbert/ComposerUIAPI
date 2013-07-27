package org.tekkotsu.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.tekkotsu.api.MapBuilderWizard;
import org.tekkotsu.api.MapBuilderWriter;

public class MapWizardPage extends WizardPage {
  private Text name;
  private Text map;
  private Button april;
  private Button pursueShapes;
  private String code;
  private MapBuilderWizard wiz;
  private Composite container;

  public MapWizardPage() {
    super("MapBuilder Requests Wizard");
    setTitle("MapBuilder Requests");
    setDescription("This wizard helps you generate the right code for MapBuilder Requests");
  }

  @Override
  public void createControl(Composite parent) {
	
	//Composite for the wizardpage
    container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    
    //Name
    Label nameLabel = new Label(container, SWT.NONE);
    nameLabel.setText("Name");
    name = new Text(container, SWT.BORDER | SWT.SINGLE);
    name.setText("");
    
    //Map
    Label mapLabel = new Label(container, SWT.NONE);
    mapLabel.setText("Map");
    map = new Text(container, SWT.BORDER | SWT.SINGLE);
    map.setText("");
    
    //AprilTag
    Label aprilLabel = new Label(container, SWT.NONE);
    aprilLabel.setText("AprilTag");
    april = new Button(container, SWT.RADIO);
    
    
    
    name.addKeyListener(new KeyListener() {

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (!name.getText().isEmpty()) {
          setPageComplete(true);

        }
      }

    });
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    name.setLayoutData(gd);
    // Required to avoid an error in the system
    setControl(container);
    setPageComplete(false);

  }

  public MapBuilderWriter getWriter() {
    return new MapBuilderWriter(wiz);
  }
}

