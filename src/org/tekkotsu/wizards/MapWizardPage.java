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
  private Text name, map;
  private Button april, pursueShapes;
  private Label lName, lMap, lApril, lObjects, lRed, lGreen, lBlue, lLine, lCylinder, lBlob, lPursueShapes;
  private Button lineR, lineG, lineB, cylR, cylG, cylB, blobR, blobB, blobG;
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
    layout.numColumns = 4;
    layout.makeColumnsEqualWidth = true;
    

    //Row one for name
    lName = new Label(container, SWT.NONE);
    lName.setText("Name");
    
    name = new Text(container, SWT.SINGLE | SWT.BORDER);
    name.setText("");
    GridData nameGrid = new GridData(); 
    nameGrid.horizontalSpan = 3;  			
    nameGrid.horizontalAlignment = SWT.FILL;	//TODO doesnt fucking work
    name.setLayoutData(nameGrid);
    
    //Row two for map
    lMap = new Label(container, SWT.NONE);
    lMap.setText("Map");
    
    map = new Text(container, SWT.SINGLE | SWT.BORDER);
    map.setText("");
    GridData mapGrid = new GridData(); 
    mapGrid.horizontalSpan = 3;
    mapGrid.horizontalAlignment = SWT.FILL;	//TODO doesnt fucking work
    map.setLayoutData(mapGrid);
    
    //Row three for april tag
    lApril = new Label(container, SWT.NONE);
    lApril.setText("April Tag");
    
    april = new Button(container, SWT.TOGGLE);
    GridData aprilGrid = new GridData(); 
    aprilGrid.horizontalSpan = 3;
    april.setLayoutData(aprilGrid);
    
    /*
    //Composite for the object selections
    Composite objectGroup = new Composite(container, SWT.NONE);
    GridLayout objLayout = new GridLayout();
    objLayout.numColumns = 4;
    objLayout.makeColumnsEqualWidth = true;
    objectGroup.setLayout(objLayout);
    */
    
    //Row four for Objects and color names
    lObjects = new Label(container, SWT.NONE); 
    lRed = new Label(container, SWT.NONE); 
    lGreen = new Label(container, SWT.NONE); 
    lBlue = new Label(container, SWT.NONE);
    
    lObjects.setText("Objects");
    lRed.setText("Red");
    lGreen.setText("Green");
    lBlue.setText("Blue");
    
    //Row five for line object
    lLine = new Label(container, SWT.NONE); 
    lLine.setText("Line");
    lineR = new Button(container, SWT.TOGGLE);
    lineG = new Button(container, SWT.TOGGLE);
    lineB = new Button(container, SWT.TOGGLE);
    
    //Row six for cylinder object
    lCylinder = new Label(container, SWT.NONE); 
    lCylinder.setText("Cylinder");
    cylR = new Button(container, SWT.TOGGLE);
    cylG = new Button(container, SWT.TOGGLE);
    cylB = new Button(container, SWT.TOGGLE);
    
    //Row seven for blob object
    lBlob = new Label(container, SWT.NONE); 
    lBlob.setText("Blob");
    blobR = new Button(container, SWT.TOGGLE);
    blobG = new Button(container, SWT.TOGGLE);
    blobB = new Button(container, SWT.TOGGLE);
    
    //Row eight for pursueShapes
    lPursueShapes = new Label(container, SWT.FILL);
    lPursueShapes.setText("PursueShapes");
    
    pursueShapes = new Button(container, SWT.TOGGLE);
    
    
    

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
    
    
    
    
    // Required to avoid an error in the system
    setControl(container);
    setPageComplete(false);

  }

  public MapBuilderWriter getWriter() {
    return new MapBuilderWriter(wiz);
  }
}

