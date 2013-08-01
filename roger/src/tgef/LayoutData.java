/*
 * Created on Sep 12, 2004
 */
package tgef;

//import static org.tekkotsu.ui.editor.resources.IDTag.*;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.tekkotsu.ui.util.AbstractModel;

import commands.DeleteCommand;

import tgef.ui.editor.model.AbstractConnectionModel;
//import org.jdom.Content;
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.JDOMException;
//import org.jdom.input.SAXBuilder;
//import org.jdom.output.Format;
//import org.jdom.output.XMLOutputter;
//import org.tekkotsu.ui.editor.model.commands.DeleteCommand;
//import org.tekkotsu.ui.util.Debugger;
//import org.tekkotsu.ui.util.FilesystemUtil;
//import org.tekkotsu.ui.util.Iteratorable;
//import org.tekkotsu.ui.util.RCPUtil;

/**
 * Parent model for all contents, representing the view
 * 
 * @author asangpet
 */

public class LayoutData extends AbstractModel {
	public static final String P_CHILDREN = "_children";

	public static final String P_EXECUTION_TRACE = "_trace";

	public static final String P_MODELSOURCE = "_source";

	public static final String P_SOURCE_EXIST = "_view_source_exist";

	// this list contains all edit part children
	// (state+transition+subconnection)
	private List<AbstractModel> editPartChildren = new ArrayList<AbstractModel>();

	private boolean   active      = true;

	//private IPath     xmlFilePath = null;
	//private ModelData modelData   = new ModelData();

	public static LayoutData newLayout()
	{
		LayoutData layoutData = new LayoutData();
		//layoutData.setPath(FilesystemUtil.createTempPath("tsl"));
		//.save();
		
		return layoutData;
	}
	
	/*public static LayoutData loadLayout(IPath path)
	{
		LayoutData layoutData = new LayoutData();
		layoutData.setPath(path);
		
		// read state information from file and construct the model
		try {
			SAXBuilder builder  = new SAXBuilder();
			Document   document = builder.build(path.toFile());
			Element    xml      = document.getRootElement();
			layoutData.parseXML(xml);
		} catch (FileNotFoundException e) {
			Debugger.printThrowable(e);
		} catch (JDOMException e) {
			Debugger.printThrowable(e);
		} catch (IOException e) {
			Debugger.printThrowable(e);
		}
		
		return layoutData;
	}
	*/
	private LayoutData() {
		//nothing
	}
	
	/*private LayoutData(IPath path) {		
		setPath(path);
	}*/

	/*public void verifySource() {
		List<AbstractModel> children = this.getPartChildren();
		Iterator<AbstractModel> iter = children.iterator();
		ModelData source = this.getModelData();
		while (iter.hasNext()) {
			Object next = iter.next();
			if (next instanceof Node) {
				((Node) next).verifySource(source);
			} 
		}
	}*/

	public void addChild(AbstractModel node) {
		node.setParent(this);
		editPartChildren.add(node);
		props.firePropertyChange(P_CHILDREN, null, null);
	}

	public void removeChild(Object child) {
		editPartChildren.remove(child);
		props.firePropertyChange(P_CHILDREN, null, null);
	}

	//	public LayoutData(IPath path) {
	//		super();
	//		this.setPath(path);
	//		this.save();
	//	}
		
		public List<AbstractModel> getPartChildren() {
		return editPartChildren;
	}

	public List<Node> getStateChildren() {
		List<Node>     nodes = new ArrayList<Node>();
		Iterator<AbstractModel> iter  = editPartChildren.iterator();
		
		while (iter.hasNext()) {
			AbstractModel next = iter.next();
			if (next instanceof Node) {
				nodes.add((Node)next);
			}
		}
		return nodes;
	}

	/**
	 * Search for a specific child model
	 * 
	 * @param id
	 *            String identification of the model
	 * @return Return a child model of this content
	 */
	public AbstractModel getPartChild(String id) {
		Iterator<AbstractModel> iter = getPartChildren().iterator();
		while (iter.hasNext()) {
			AbstractModel nextModel = iter.next();
			if (nextModel instanceof Node) {
				Node model = (Node) nextModel;
				if (id.equals(model.getName()))
					return model;
			}
		}
		return null;
	}

	/*public void parseXML(Element xml)
	{		
		assert(XML_layout_data.equals(xml.getName()));
		
		for( Object next : xml.getChildren() )
		{
			if( !(next instanceof Element) )
			{
				continue;
			}
			
			Element child = (Element) next;
			
			if (XML_state_tag.equals(child.getName()))
			{
				String id = child.getAttributeValue(XML_common_id);
				if (modelData.getNode(id) != null)
				{
					this.addChild(new StateNodeModel(child, this));
				}
				else
				{
					RCPUtil.openError(
						"Bad Input File",
						"id <<" + id + ">> not recognized"
					);
				}
			}
			else if (XML_transition_tag.equals(child.getName()))
			{
				MultiTransitionModel multiNode = new MultiTransitionModel(child, this);
				this.addChild(multiNode);
				multiNode.doPostAdd();
			}
			else if (XML_model_data.equals(child.getName()))
			{
				modelData.parseXML(child);
			}
			else {
				Debugger.printDebug("Unrecognized tag: " + child.getName());
			}
		}
	}
	
	@Override
	public Content getXML() {
		Element xml = new Element(XML_layout_data);
		xml.addContent(modelData.getXML());
		
		List<AbstractModel>     children    = getPartChildren();
		List<AbstractModel>     transitions = new ArrayList<AbstractModel>();
		Iterator<AbstractModel> iter        = children.iterator();
		
		for( AbstractModel childModel : new Iteratorable<AbstractModel>(iter)) {
			if (childModel instanceof MultiTransitionModel) {
				transitions.add(childModel);
			} else {
				Content child = childModel.getXML();
				if (child != null)
					xml.addContent(child);
			}
		}

		iter = transitions.iterator();
		for( AbstractModel childModel : new Iteratorable<AbstractModel>(iter)) {
			Content child = childModel.getXML();
			if (child != null)
				xml.addContent(child);
		}
		
		return xml;
	}

	/**
	 * @return Returns model source.
	 */
	/*public ModelData getModelData() {
		if (modelData == null) {
			modelData = new ModelData();
		}
		return modelData;
	}

	public void setModelSource(ModelData modelData) {
		this.modelData = modelData;
	}*/

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] { new PropertyDescriptor(
				P_MODELSOURCE, "click on\na node or\nlink in the\nlayout") };
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(P_MODELSOURCE)) {
			return "click on\na node or\nlink in the\nlayout";
		}
		return null;
	}

   /* public IPath getPath() {
        return xmlFilePath;
    }
    public void setPath(IPath path) {
        this.xmlFilePath = path;
    }*/
		
		/**
     * @return the y-offset of the bottom of the lowest child
     */
    public int getNeededHeight()
    {
    	int maxy = 0;
    	
    	for( Node child : getStateChildren() )
    	{
    		final Rectangle BOUND = child.getConstraint();
    		if( BOUND != null ) {
    			int y = BOUND.y + BOUND.height;
    			maxy = Math.max(y,maxy);
    		}
    	}
    	
    	return maxy;
    }
    
	@Override
	public void activate() {
		active = true;
		for( AbstractModel model : getPartChildren() )
		{
			model.activate();
		}
	}

	@Override
	public void deactivate() {
		active = false;
		for( AbstractModel model : getPartChildren() )
		{
			model.deactivate();
		}
	}

	public void select(String id) {
		AbstractModel am = getPartChild(id);
		if( am != null ) {
			am.activate();
		}
	}
	
	public void deselect(String id) {
		AbstractModel am = getPartChild(id);
		if( am != null ) {
			am.deactivate();
		}
	}

	@Override
	public boolean isPreview() {
		return !active;
	}
	

	
	private Map<String,Map<String,List<Point>>> subConnectionDescriptors;
	
	public List<Point> getConnectionDescriptor( String source, String dest ) {
		if( subConnectionDescriptors == null ) {
			return null;
		}
		Map<String,List<Point>> sourceMap = subConnectionDescriptors.get(source);
		if( sourceMap == null ) {
			return null;
		}
		return sourceMap.get(dest);
	}
	
	public void putConnectionDescriptor( String source, String dest, List<Point> points ) {
		if( subConnectionDescriptors == null ) {
			subConnectionDescriptors = new HashMap<String,Map<String,List<Point>>>();
		}
		Map<String,List<Point>> sourceMap = subConnectionDescriptors.get(source);
		if( sourceMap == null ) {
			sourceMap = new HashMap<String,List<Point>>();
			subConnectionDescriptors.put(source,sourceMap);
		}
		sourceMap.put(dest,points);
	}
	
	public void removeConnectionDescriptor( String source, String dest )
	{
		if( subConnectionDescriptors == null ) return;
		
		Map<String,List<Point>> sourceMap = subConnectionDescriptors.get(source);
		if( sourceMap == null ) return;
		
		sourceMap.remove(dest);
	}
		
	/*public boolean save() {
		return save(xmlFilePath);
	}

	private boolean save(IPath path) {
		Document doc = new Document();
		doc.addContent(this.getXML());

		//try to open the file for writing
		try {
			BufferedOutputStream ostream = new BufferedOutputStream(
					new FileOutputStream(path.toFile()));
			
			//try to write to the file
			try {
				XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
				outputter.output(doc, ostream);
				ostream.close();
				return true;
			} catch (IOException e) {
				MessageDialog.openError(
					null,
					"Could not write to file",
					e.toString()
				);
				Debugger.printThrowable(e);
			}
		} catch (FileNotFoundException e) {
			MessageDialog.openError(
				null,
				"Cannot open file",
				e.toString()
			);
			Debugger.printThrowable(e);
		}
		return false;
	}

	/**
	 * Prompts the user to save the layout data to a new file.
	 * @return true if the file was saved, false otherwise.
	 */
	/*public boolean saveAs( String guess ) {
		String[] filterExtensions = { "*.tsl" };
		FileDialog fileDialog = new FileDialog(RCPUtil.getShell(),SWT.SAVE);
		fileDialog.setText("Save As");
		fileDialog.setFileName(guess);
		fileDialog.setFilterExtensions(filterExtensions);
		
		FilesystemUtil.setFileDialogStartPath(fileDialog);
		String filename = fileDialog.open();
		FilesystemUtil.stickyPathSet(filename);
		
		if (filename!=null) {
			
			if( !filename.endsWith(".tsl") )
			{
				filename += ".tsl";
			}
			
			IPath path = new Path(filename); 
			if ( save(path) )
			{
				xmlFilePath = path; 
				return true;
			}
		}
		
		return false;
	}

	public String getEditorTabName() {
		return xmlFilePath.lastSegment();
	}*/

	/*public void clear() {
		List<AbstractModel> tempChildren = new ArrayList<AbstractModel>(editPartChildren);
		for(AbstractModel child : tempChildren)
		{
			DeleteCommand.delete(this, child);
		}
	}*/

	public void removeConnectionDescriptor(AbstractConnectionModel connection) {
		removeConnectionDescriptor(connection.getSource().getName(), connection.getTarget().getName());
		connection.clearBendpoints();
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
}