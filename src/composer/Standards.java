package composer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.ui.part.ViewPart;
import org.tekkotsu.api.*;

public class Standards extends ViewPart{
	
	@Override
	public void createPartControl(Composite comp){
		
		DefaultClassReader reader = null;
		
		try {
			reader = new DefaultClassReader();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get nodes and store in arraylist
		ArrayList<NodeClass> nodes = reader.getNodes();
		
		//Get transitions and store in lisst
		ArrayList<TransitionClass> trans = reader.getTransitions();
		
		
		//Create expandbar
		ExpandBar bar = new ExpandBar(comp, SWT.V_SCROLL);
		
		// First item nodes
		Composite composite = new Composite (bar, SWT.NONE);
		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		//Make expandbar for standard nodes.
		for(int i = 0; i< nodes.size(); i++){
			
			Button button = new Button (composite, SWT.PUSH);
			button.setText(nodes.get(i).getName());

		}
		
		ExpandItem expandNodes = new ExpandItem (bar, SWT.NONE, 0);
		expandNodes.setText("Standard Node Classes");
		expandNodes.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		expandNodes.setControl(composite);
		
		
		// First item nodes
		Composite composite2 = new Composite (bar, SWT.NONE);
		GridLayout layout2 = new GridLayout ();
		layout2.marginLeft = layout2.marginTop = layout2.marginRight = layout2.marginBottom = 10;
		layout2.verticalSpacing = 10;
		composite2.setLayout(layout);
		
		//Make expandbar for standard transitions.
		for(int i = 0; i< trans.size(); i++){
			
			Button button = new Button (composite2, SWT.PUSH);
			button.setText(trans.get(i).getName());

		}
		
		ExpandItem expandTrans = new ExpandItem (bar, SWT.NONE, 0);
		expandTrans.setText("Standard Transition Classes");
		expandTrans.setHeight(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		expandTrans.setControl(composite2);

		
	}
	
	@Override
	public void setFocus(){
		
	}

}
