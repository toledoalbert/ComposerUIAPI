package tgef;

import java.io.Serializable;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.ui.util.AbstractModel;

import tekkotsu.edit.parts.SubConnectionEditPart;
import tgef.ui.editor.model.SubConnectionModel;




public class NodePropertySource implements IPropertySource{
//Was node had to implement abstract model instead
	private AbstractModel node;
	
	public NodePropertySource(AbstractModel node) {
	this.node = node;
	}
	
	@Override
	public Object getEditableValue() {
		// returns property value can be returned as null
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		
		if (node instanceof Entreprise)
			properties.add(new PropertyDescriptor(Node.PROPERTY_RENAME, "Name"));
			
		
		if (node instanceof Service) {
		properties.add(new ColorPropertyDescriptor(Service.PROPERTY_COLOR, "Color"));
		properties.add(new TextPropertyDescriptor(Service.PROPERTY_FLOOR, "Etage"));
		}
		
		if(node instanceof NodeInstance){
			properties.add(new ColorPropertyDescriptor(NodeInstance.PROPERTY_COL, "Color"));
			properties.add(new PropertyDescriptor(NodeInstance.PROPERTY_TYPE, "Type"));
			properties.add(new TextPropertyDescriptor(NodeInstance.PROPERTY_LABEL, "Label"));
		}
		//Just added Todo
		if(node instanceof SubConnectionModel){
			properties.add(new ColorPropertyDescriptor(SubConnectionModel.PROPERTY_COLOR, "Color"));	
		}
		
		else if (node instanceof Employe) {
		properties.add(new PropertyDescriptor(Employe.PROPERTY_FIRSTNAME, "Prenom"));
		}
		
		
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	//retrieval of property value depending on identifier
	@Override
	public Object getPropertyValue(Object id) {
		//Was Node Todo
		if (id.equals(AbstractModel.PROPERTY_RENAME))
			return node.getName();
		if (id.equals(Service.PROPERTY_COLOR))
			return ((Service)node).getColor().getRGB();
		if(id.equals(NodeInstance.PROPERTY_COL))
			return ((NodeInstance)node).getCol().getRGB();
		if(id.equals(NodeInstance.PROPERTY_TYPE))
			return ((NodeInstance)node).getType().getName();
		if(id.equals(NodeInstance.PROPERTY_LABEL))
			return ((NodeInstance)node).getLabel();
		//Testing SubConnectionModel
		if(id.equals(SubConnectionModel.PROPERTY_COLOR))
			return((SubConnectionModel)node).getColor().getRGB();
		if (id.equals(Service.PROPERTY_FLOOR))
			return Integer.toString(((Service)node).getEtage());
		if (id.equals(Entreprise.PROPERTY_CAPITAL))
			return Integer.toString(((Entreprise)node).getCapital());
		if (id.equals(Employe.PROPERTY_FIRSTNAME))
			return (((Employe)node).getPrenom());
			
		return null;

	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}

	//Resets property value after user edits it
	@Override
	public void setPropertyValue(Object id, Object value) {
	if (id.equals(AbstractModel.PROPERTY_RENAME))
		node.setName((String)value);
	else if (id.equals(Service.PROPERTY_COLOR)) {
		Color newColor = new Color(null, (RGB)value);
		((Service)node).setColor(newColor);
		}
	else if(id.equals(NodeInstance.PROPERTY_COL)){
		Color newColor = new Color(null, (RGB)value);
		((NodeInstance)node).setColor(newColor);
	}
	else if(id.equals(NodeInstance.PROPERTY_LABEL)){
		((NodeInstance)node).setLabel((String)value);
	}
	//Testing SUbConnectionModel
	else if(id.equals(SubConnectionModel.PROPERTY_COLOR)){
		Color newColor = new Color(null, (RGB)value);
		((SubConnectionModel)node).setColor(newColor);
	}
	
	else if (id.equals(Service.PROPERTY_FLOOR)) {
		try {
		       Integer floor = Integer.parseInt((String)value);
		       ((Service)node).setEtage(floor);
		    }
		       catch (NumberFormatException e) { }
		}
	else if (id.equals(Entreprise.PROPERTY_CAPITAL)) {
		 try {
		       Integer capital = Integer.parseInt((String)value);
		       ((Entreprise)node).setCapital(capital);
		      }
		catch (NumberFormatException e) { }
		}

		
	}

}
