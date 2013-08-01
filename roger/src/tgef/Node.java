package tgef;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertySource;
import org.tekkotsu.ui.util.AbstractModel;



import tgef.ui.editor.model.AbstractConnectionModel;
import tgef.ui.editor.model.ModelData;
import tgef.ui.editor.model.MultiTransitionModel;


//implements IAdaptable
public class Node extends AbstractModel {
	
	//List from abstract connection model
	protected List<AbstractConnectionModel> sourceConnections = new ArrayList<AbstractConnectionModel>();

    protected List<AbstractConnectionModel> targetConnections = new ArrayList<AbstractConnectionModel>();

    //Shape related Constants Storyboard
    public static final String P_SHAPE_STYLE_ELLIPSE = "Ellipse";
    public static final String P_SHAPE_STYLE_RECTANGLE = "Rectangle";
    public static final String P_SHAPE_STYLE_ROUNDED_RECTANGLE = "Rounded Rectangle";
    public static final String P_SHAPE_STYLE_HEXAGON = "Hexagon";
    private int shapeIndex = 0;
    public static final String P_SHAPE = "_shape";
    private String shape = P_SHAPE_STYLE_RECTANGLE;
    private static final String[] shapeValues = { P_SHAPE_STYLE_RECTANGLE,
        P_SHAPE_STYLE_ELLIPSE, P_SHAPE_STYLE_ROUNDED_RECTANGLE,
        P_SHAPE_STYLE_HEXAGON };
	
	
	//List of connections
	  private List<Connection> sourceConnectionss;
	  private List<Connection> targetConnectionss;
	 
	 //Constants for Source and Target Connections
	 public static final String SOURCE_CONNECTION = "SourceConnectionAdded";
	 public static final String TARGET_CONNECTION = "TargetConnectionAdded";
	  
	 //Other source connection
	 public static final String P_SOURCE_CONNECTION = "_source_connection";

	    public static final String P_TARGET_CONNECTION = "_target_connection";
	    public static final String P_CONSTRAINT = "_constraint";
	
	//Constant for Label
	   public static final String P_LABEL = "_label";
	
	//Activate events when GEditor properties are changed
	private PropertyChangeSupport listeners;
	
	//Used for updating the layout when things are moved
	public static final String PROPERTY_LAYOUT = "NodeLayout";

//Parameters	
private String id = "State";
private String label = "State";
private String name;
private Rectangle layout;
//Abstract Model
private List<AbstractModel> children;
private Node parent;
private SourceObjectModel source;
protected int stateID; 

//Layout data parent
protected tgef.LayoutData parent2;

private Rectangle constraint;

//for property sheets
private IPropertySource propertySource = null;


//Used for adding and deleting nodes
public static final String PROPERTY_ADD = "NodeAddChild";
public static final String PROPERTY_REMOVE = "NodeRemoveChild";
//Taken froms Storyboard activate and deactivate
public static final String P_ACTIVATE = "_state_activate";
public static final String P_DEACTIVATE = "_state_deactivate";
//used for renaming
public static final String PROPERTY_RENAME = "NodeRename";

//used for color editing *test
//public static final String PROPERTY_COLOR = "NodeChangeColor";

public static final String P_COLOR = "_color";

public Node(){
super();

this.name = "Unknown";
this.layout = new Rectangle(10, 10, 100, 100);
this.children = new ArrayList<AbstractModel>();
this.parent = null;
this.listeners = new PropertyChangeSupport(this);
this.sourceConnections = new ArrayList();
this.targetConnections = new ArrayList();

}
//Property Change Listener
public void addPropertyChangeListener(PropertyChangeListener listener) {
listeners.addPropertyChangeListener(listener);
}

public PropertyChangeSupport getListeners() {
return listeners;
}
public void removePropertyChangeListener(PropertyChangeListener listener) {
listeners.removePropertyChangeListener(listener);
}

//New parent methods
public void setParent2(tgef.LayoutData layoutData)
{
	this.parent2 = layoutData;
}


//Do post add method---Implemented from Storyboard
public void doPostAdd()
{
    LayoutData parent = (LayoutData) getParent2();
    List<AbstractModel> children = new ArrayList<AbstractModel>();//(parent.getPartChildren())
    Iterator<AbstractModel> iter = children.iterator();
    while (iter.hasNext())
    {
        AbstractModel model = iter.next();
        if (model instanceof MultiTransitionModel)
        {
            ((MultiTransitionModel) model).doPostAdd();
        }
    }
}


//Constraint
public Rectangle getConstraint()
{
    return constraint;
}

public void setName(String name) {
	String oldName = this.name;
	this.name = name;
	getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);

}

public String getName() {
return this.name;
}

//Set shape method--not functional yet
public void setShape(String shape)
{
    // verify shape style
    if (!shape.equals(shapeValues[shapeIndex]))
    {
        for (int i = 0; i < shapeValues.length; i++)
            if (shapeValues[i].equals(shape))
            {
                shapeIndex = i;
                break;
            }
    }
    if (!shape.equals(shapeValues[shapeIndex])) return;

    this.shape = shape;
    props.firePropertyChange(P_SHAPE, null, shape);
}

public void setShapeIndex(int index)
{
    shapeIndex = index;
    setShape(shapeValues[index]);
}


public void setLayout(Rectangle newLayout) {
	Rectangle oldLayout = this.layout;
	this.layout = newLayout;
	getListeners().firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);

}

public Rectangle getLayout() {
return this.layout;
}

//Get shape
public String getShape()
{
    return shape;
}


//Add child method also handles addition of new child created by user
public boolean addChild(Node child) {
boolean b = this.children.add(child);
if (b) {
child.setParent(this);
getListeners().firePropertyChange(PROPERTY_ADD, null, child);
}
return b;
}

//Handles if child is deleted
public boolean removeChild(Node child) {
boolean b = this.children.remove(child);
if (b)
getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
return b;
}

public List<AbstractModel> getChildrenArray() {
return this.children;
}

public void setParent(Node parent) {
this.parent = parent;
}

public Node getParent() {
return this.parent;
}



//Contains method used for new object creation

public boolean contains(Node child) {
return children.contains(child);
}


/*@Override
public Object getAdapter(Class adapter) {
	if (adapter == IPropertySource.class) { 
		if (propertySource == null) 
		propertySource = new NodePropertySource(this); 
		return propertySource; 
		} 
		return null; 
		} */



//Storyboard add source connection code etc
public List<AbstractConnectionModel> getModelSourceConnections()
{
    return sourceConnections;
}

public List<AbstractConnectionModel> getModelTargetConnections()
{
    return targetConnections;
}

public void addSourceConnection(AbstractConnectionModel connx)
{
    sourceConnections.add(connx);
    getListeners().firePropertyChange(P_SOURCE_CONNECTION, null, null);
}

public void addTargetConnection(AbstractConnectionModel connx)
{
    targetConnections.add(connx);
    getListeners().firePropertyChange(P_TARGET_CONNECTION, null, null);
}

public void removeSourceConnection(Object connx)
{
    sourceConnections.remove(connx);
    getListeners().firePropertyChange(P_SOURCE_CONNECTION, null, null);
}

public void removeTargetConnection(Object connx)
{
    targetConnections.remove(connx);
   getListeners().firePropertyChange(P_TARGET_CONNECTION, null, null);
}

//Returns the source
public SourceObjectModel getSource()
{
    return source;
}

public void setConstraint(Rectangle rect)
{
    constraint = rect;
    getListeners().firePropertyChange(P_CONSTRAINT, null, constraint);
}


public boolean addConnections (Connection conn) {
    if (conn.getSourceNode() == this) { 
        if (!sourceConnectionss.contains(conn)) {
            if (sourceConnectionss.add(conn)) {
                getListeners().firePropertyChange(SOURCE_CONNECTION, null, conn);
                return true;    
            }
            return false;
        }
    }
    else if (conn.getTargetNode() == this) { 
        if (!targetConnectionss.contains(conn)) {
            if (targetConnectionss.add(conn)) {
                getListeners().firePropertyChange(TARGET_CONNECTION, null, conn);
                return true;
            }
            return false;
        }
    }
    return false;
}

public boolean removeConnection(Connection conn) {
    if (conn.getSourceNode() == this) { 
        if (sourceConnections.contains(conn)) {
            if (sourceConnections.remove(conn)) {
                getListeners().firePropertyChange(SOURCE_CONNECTION, null, conn);
                return true;
            }
            return false;
        }
    }
    else if (conn.getTargetNode() == this) {
        if (targetConnections.contains(conn)) {
            if (targetConnections.remove(conn)) {
                getListeners().firePropertyChange(TARGET_CONNECTION, null, conn);
                return true;
            }
            return false;
        }
    }    
    return false;
}


/*public List<Connection> getSourceConnectionsArray() {
    return this.sourceConnections;
}

public List<Connection> getTargetConnectionsArray() {
    return this.targetConnections;
}*/
/*@Override
public boolean isPreview() {
	// TODO Auto-generated method stub
	return false;
}*/
public void attachSource() {
	// TODO Auto-generated method stub
	
}
@Override
public boolean isPreview() {
	// TODO Auto-generated method stub
	return false;
}


//Set source used in MultiTransitionModel
public void setSource(SourceObjectModel source)
{
    this.source = source;
}

//Used in conjunction with sourceNodeModel to create a Node using the SourceNodeCreationFactory
public Node(SourceNodeModel source)
{
    this();
    setId(source.getId());
    setLabel(source.getId());
    setSource(source);

    ModelData parent = source.getParentModel();
    int SIZE = parent.getNodes().size();
    
    //float hue = .97f * (stateID % SIZE) / SIZE;
	//float brightness = 0.6f + 0.2f * (stateID % 2);
	//float saturation = 0.8f;
	
	//java.awt.Color color = java.awt.Color.getHSBColor(hue,saturation,brightness);
    
    //RGB rgb = new RGB(color.getRed(), color.getGreen(), color.getBlue());
    //colorRegistry.put("Color" + stateID, rgb);
    //setColor(colorRegistry.getRGB("Color" + stateID));
}

//Pertains Setter and Getter for ID
public void setId(int id)
{
	stateID = id;
    setId("state" + Integer.toString(id));
}

public void setId(String text)
{
    this.id = text;
}

public String getId()
{
    return id;
}

//Sets label
public void setLabel(String text)
{
    this.label = text;
    getListeners().firePropertyChange(P_LABEL, null, text);
}




}



