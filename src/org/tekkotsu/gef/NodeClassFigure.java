package org.tekkotsu.gef;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class NodeClassFigure extends Figure{

	//Attributes
	private Label name = new Label();
	private XYLayout layout;
	
	//Constructor
	public NodeClassFigure(){
		
		layout = new XYLayout();
		setLayoutManager(layout);
		
		name.setForegroundColor(ColorConstants.blue);
		add(name, ToolbarLayout.ALIGN_CENTER);
		setConstraint(name, new Rectangle(5, 5, -1, -1));

		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.yellow);
		
		setBorder(new LineBorder(5));
		setOpaque(true);
		
	}
	
	//Mutator methods
	public void setLayout(Rectangle rect){
		getParent().setConstraint(this, rect);
	}
	
	public void setNameText(String nameText){
		name.setText(nameText);
	}
		
}
