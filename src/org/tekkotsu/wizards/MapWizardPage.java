package org.tekkotsu.wizards;

import java.util.ArrayList;

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
import org.tekkotsu.api.MapRequestObject;

public class MapWizardPage extends WizardPage {
  private Text name, map;
  private Button april, pursueShapes;
  private Label lName, lMap, lApril, lObjects, lRed, lGreen, lBlue, lLine, lCylinder, lBlob, lPursueShapes;
  private Button lineR, lineG, lineB, cylR, cylG, cylB, blobR, blobB, blobG;
  private Composite container;

  public MapWizardPage() {
    super("MapBuilder Requests Wizard");
    setTitle("MapBuilder Requests");
    setDescription("This wizard helps you generate the right code for MapBuilder Requests");
  }

  @Override
  public void createControl(Composite parent) {
	
	//ArrayList to store the toggle buttons of objects
	ArrayList<Button> objects = new ArrayList<Button>();
	  
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
    lineR = new Button(container, SWT.TOGGLE); objects.add(lineR);
    lineG = new Button(container, SWT.TOGGLE); objects.add(lineG);
    lineB = new Button(container, SWT.TOGGLE); objects.add(lineB);
    
    //Row six for cylinder object
    lCylinder = new Label(container, SWT.NONE); 
    lCylinder.setText("Cylinder");
    cylR = new Button(container, SWT.TOGGLE); objects.add(cylR);
    cylG = new Button(container, SWT.TOGGLE); objects.add(cylG);
    cylB = new Button(container, SWT.TOGGLE); objects.add(cylB);
    
    //Row seven for blob object
    lBlob = new Label(container, SWT.NONE); 
    lBlob.setText("Blob");
    blobR = new Button(container, SWT.TOGGLE); objects.add(blobR);
    blobG = new Button(container, SWT.TOGGLE); objects.add(blobG);
    blobB = new Button(container, SWT.TOGGLE); objects.add(blobB);
    
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

  
  //Function to provide ArrayList of MapRequestObject to wizard
  public ArrayList<MapRequestObject> getObjects(){
  	
	//List to return
  	ArrayList<MapRequestObject> objs = new ArrayList<MapRequestObject>();
  	
  	if(lineR.getSelection()) objs.add(new MapRequestObject("lineDataType", "red"));
  	if(lineG.getSelection()) objs.add(new MapRequestObject("lineDataType", "green"));
  	if(lineB.getSelection()) objs.add(new MapRequestObject("lineDataType", "blue"));
  	
  	if(cylR.getSelection()) objs.add(new MapRequestObject("cylinderDataType", "red"));
  	if(cylG.getSelection()) objs.add(new MapRequestObject("cylinderDataType", "green"));
  	if(cylB.getSelection()) objs.add(new MapRequestObject("cylinderDataType", "blue"));
  	
  	if(blobR.getSelection()) objs.add(new MapRequestObject("blobDataType", "red"));
  	if(blobG.getSelection()) objs.add(new MapRequestObject("blobeDataType", "green"));
  	if(blobB.getSelection()) objs.add(new MapRequestObject("blobDataType", "blue"));
  	
  	//Return the list
  	return objs;
  	
  	
  }
  
  //Function to provide a mapwizard object.
  public MapBuilderWizard getMapBuilder(){
	  
	  //create the object to return
	  MapBuilderWizard mapW = new MapBuilderWizard();
	  
	  //set the name
	  mapW.setName(name.getText());
	  
	  //set the april boolean
	  mapW.setAprilTag(april.getSelection());
	  
	  //set the pursueShapes boolean
	  mapW.setPursueShapes(pursueShapes.getSelection());
	  
	  //set the type of the map
	  mapW.setMap(map.getText());
	  
	  //set the list of the request objects
	  mapW.setRequests(getObjects());
	  
	  //return the builder
	  return mapW;
  }
  
  
  
  public MapBuilderWriter getWriter() {
    return new MapBuilderWriter(getMapBuilder());
  }
}

