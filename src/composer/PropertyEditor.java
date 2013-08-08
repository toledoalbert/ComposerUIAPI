package composer;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.tekkotsu.api.*;

public class PropertyEditor implements IPropertySource{

	//Was node had to implement abstract model instead
	private AbstractModel component;

	public PropertyEditor(AbstractModel component) {
		this.component = component;
	}

	@Override
	public Object getEditableValue() {
		// returns property value can be returned as null
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		//List of properties to be edited
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();

		if (component instanceof NodeClass){
			properties.add(new TextPropertyDescriptor(NodeClass.PROPERTY_NAME, "Name"));
			properties.add(new ColorPropertyDescriptor(NodeClass.PROPERTY_COL, "Color"));
		}
		else if(component instanceof NodeInstance){
			properties.add(new ColorPropertyDescriptor(NodeInstance.PROPERTY_COL, "Color"));
			properties.add(new PropertyDescriptor(NodeInstance.PROPERTY_TYPE, "Type"));
			properties.add(new TextPropertyDescriptor(NodeInstance.PROPERTY_LABEL, "Label"));
		}
		else if(component instanceof SetupMachine){
			properties.add(new ColorPropertyDescriptor(NodeClass.PROPERTY_COL, "Color"));
		}
		
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	//retrieval of property value depending on identifier
	@Override
	public Object getPropertyValue(Object id) {
		
		//Was Node Todo
		if(id.equals(Graphical.PROPERTY_COL))
			return ((Graphical)component).getColor().getRGB();
		
		else if(id.equals(NodeInstance.PROPERTY_TYPE))
			return ((NodeInstance)component).getType().getName();
		
		else if(id.equals(NodeInstance.PROPERTY_LABEL))
			return ((NodeInstance)component).getLabel();
		
		else if(id.equals(NodeClass.PROPERTY_NAME))
			return ((NodeClass)component).getName();
		
		/*//Testing SubConnectionModel
		if(id.equals(SubConnectionModel.PROPERTY_COLOR))
			return((SubConnectionModel)node).getColor().getRGB();
		
		if (id.equals(Service.PROPERTY_FLOOR))
			return Integer.toString(((Service)node).getEtage());
		
		if (id.equals(Entreprise.PROPERTY_CAPITAL))
			return Integer.toString(((Entreprise)node).getCapital());
		
		if (id.equals(Employe.PROPERTY_FIRSTNAME))
			return (((Employe)node).getPrenom());
		*/
		
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
		
		if (id.equals(Graphical.PROPERTY_COL)){
			
			Color newColor = new Color(null, (RGB)value);
			((Graphical)component).setColor(newColor);
		}
		else if(id.equals(NodeInstance.PROPERTY_LABEL)){
			
			((NodeInstance)component).setLabel((String) value);
		}
		else if(id.equals(NodeClass.PROPERTY_NAME)){
			((NodeClass)component).setName((String)value);
		}
		


	}


}
