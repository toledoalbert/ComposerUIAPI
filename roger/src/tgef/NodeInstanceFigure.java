package tgef;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class NodeInstanceFigure extends Figure {
	
	public static final int NODEINSTANCE_FIGURE_DEFWIDTH = 90;
	public static final int NODEINSTANCE_FIGURE_DEFHEIGHT = 40;
	private Label labelAble = new Label();
	
	//Sets connection Anchor
	private ConnectionAnchor connectionAnchor;

	
	public NodeInstanceFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		labelAble.setForegroundColor(ColorConstants.darkGray);
		add(labelAble, ToolbarLayout.ALIGN_CENTER);
		 
		labelAble.setForegroundColor(ColorConstants.black);
		add(labelAble, ToolbarLayout.ALIGN_CENTER);
		
		setForegroundColor(ColorConstants.black);
		
		
		setBorder(new LineBorder(1));
		setOpaque(true);
		
		}
		
	
	public void setName(String text) {
		labelAble.setText(text);
		}
		
		
		public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect);
		}
		
		public ConnectionAnchor getConnectionAnchor() {
		    if (connectionAnchor == null) {
		      connectionAnchor = new ChopboxAnchor(this);
		    }
		    return connectionAnchor;
		  }


}
