package tgef;

import java.util.LinkedList;
import java.util.List;

import tgef.ui.editor.model.AbstractModel;
import tgef.ui.editor.model.MultiTransitionModel;



/**
 * @author asangpet
 *
 */
public class TransitionModel extends AbstractModel {
	List<String> sourceNodeList, destNodeList;
	int endCount = 0;
	
	public TransitionModel(String id, int start, MultiTransitionModel trans) {
		super(start,UNKNOWN_TIME,id,start,UNKNOWN_TIME, false);
		SourceTransitionModel strans = (SourceTransitionModel)trans.getSource();
		sourceNodeList = new LinkedList<String>(strans.getSourceNodes());
		destNodeList   = new LinkedList<String>(strans.getDestNodes());
	}

	public boolean isSourceNode(String id) {
		return sourceNodeList.contains(id);
	}
	
	public boolean isDestNode(String id) {
		return destNodeList.contains(id);
	}

	
	/*public int getTime() {
		return start;
	}*/
	
	//Visit later to add into tree
	/*@Override
	public RuntimeView.TreeObject getRuntimeViewData() {
		TreeParent par = new TreeParent(getID(),getTime(),getTime());
		par.addChild(new TreeObject("type: transition"));
		par.addChild(new TreeObject("fire at: "+timeToString(getRawStart())));
		par.addChild(new TreeObject("finish at: "+timeToString(getRawEnd())));
		return par;
	}*/

	
}
