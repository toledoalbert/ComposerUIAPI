package org.tekkotsu.gef;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class NodeInstanceFigure extends Figure{

	//Attributes
	private Label label = new Label();
	private Label type = new Label();
	private XYLayout layout;
	
	//Constructor
	public NodeInstanceFigure(){
		
		layout = new XYLayout();
		setLayoutManager(layout);
		
		label.setForegroundColor(ColorConstants.blue);
		add(label, ToolbarLayout.ALIGN_CENTER);
		setConstraint(label, new Rectangle(5, 5, -1, -1));
		
		type.setForegroundColor(ColorConstants.blue);
		add(type, ToolbarLayout.ALIGN_CENTER);
		setConstraint(type, new Rectangle(5, 17, -1, -1));

		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.orange);
		
		setBorder(new LineBorder(5));
		setOpaque(true);
		
	}
	
	//Mutator methods
	public void setLayout(Rectangle rect){
		getParent().setConstraint(this, rect);
	}
	
	public void setLabelText(String labelText){
		label.setText(labelText);
	}
	
	public void setTypeText(String typeText){
		type.setText(typeText);
	}
	
}
