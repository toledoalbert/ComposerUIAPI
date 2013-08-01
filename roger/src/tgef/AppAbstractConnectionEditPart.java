package tgef;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

public abstract class AppAbstractConnectionEditPart extends AbstractConnectionEditPart  {
    @Override
    protected IFigure createFigure() {
        // TODO Auto-generated method stub
        return super.createFigure();
    }
    
    public void activate() {
        super.activate(); 
    }
    
    public void deactivate() {
        super.deactivate();
    }

    @Override
    protected void createEditPolicies() {}
}

