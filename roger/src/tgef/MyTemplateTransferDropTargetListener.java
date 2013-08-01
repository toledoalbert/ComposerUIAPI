package tgef;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import creationFactory.NodeCreationFactory;

public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener
{
public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
super(viewer);
}

protected CreationFactory getFactory(Object template, String t) {
return new NodeCreationFactory((Class<?>)template, t);
}
}
