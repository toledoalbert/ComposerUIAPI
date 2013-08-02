package org.tekkotsu.gef;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class SetupMachineFigure extends Figure{

	//Attributes
	private Label label = new Label();
	private XYLayout layout;
	
	//Constructor
	public SetupMachineFigure(){
		
		layout = new XYLayout();
		setLayoutManager(layout);
		
		label.setText("Setup Machine");
		label.setForegroundColor(ColorConstants.blue);
		add(label, ToolbarLayout.ALIGN_CENTER);
		setConstraint(label, new Rectangle(5, 5, -1, -1));

		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.green);
		
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
	
	
}
