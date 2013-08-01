package tgef;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;


public class MyEditorInput implements IEditorInput {
public String name = null;
private File file = null;


public MyEditorInput(String name) {
    this.name = name;
    file = new File(ResourcesPlugin.getWorkspace().getRoot().getRawLocation() + "/" + name + ".session.saved");
}

public File getFile() {
    return file;
}
@Override
public boolean exists() {
     return (this.name != null);
}
public boolean equals(Object o) {
if (!(o instanceof MyEditorInput))
       return false;
return ((MyEditorInput)o).getName().equals(getName());
}
@Override
public ImageDescriptor getImageDescriptor() {
    return ImageDescriptor.getMissingImageDescriptor();
}
@Override
public String getName() {
    return this.name;
}
@Override
public IPersistableElement getPersistable() {
// TODO Auto-generated method stub
return null;
}
@Override
public String getToolTipText() {
    return this.name;
}
@Override
public Object getAdapter(Class adapter) {
	// TODO Auto-generated method stub
	return null;
}


}
