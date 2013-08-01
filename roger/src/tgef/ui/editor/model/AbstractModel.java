package tgef.ui.editor.model;

import tgef.LayoutData;

/*
 import src.org.tekkotsu.ui.storyboard.model.AbstractModel;
 
import src.org.tekkotsu.ui.storyboard.model.Object;
import src.org.tekkotsu.ui.storyboard.model.Override;
import src.org.tekkotsu.ui.storyboard.model.PropertyChangeListener;
import src.org.tekkotsu.ui.storyboard.model.PropertyChangeSupport;
import src.org.tekkotsu.ui.storyboard.model.RuntimeView;
import src.org.tekkotsu.ui.storyboard.model.String;*/

public class AbstractModel{

//private PropertyChangeSupport props = new PropertyChangeSupport(this);

/** raw start time **/
protected int rawstart;
/** raw end time **/
protected int rawend;

/** processed start time **/
protected int start;
/** processed end time **/
protected int end;

/** if this node is closed **/
protected boolean finished = false;

/** the id of the node **/
protected String id;

protected int layoutIndex = 0;

private boolean selected = false;

public static final int UNKNOWN_TIME = -1;

public static final String ETID_ACTIVATE = "A";
public static final String ETID_DEACTIVATE = "D";
public static final String ETID_STATUS = "S";

public static final String P_SELECT = "_select";
public static final String P_DESELECT = "_deselect";

public AbstractModel(int rawstart, int rawend, String id, int start, int end, boolean finish) {
	this.id = id;
	this.rawstart = rawstart;
	this.rawend = rawend;
	this.start = start;
	this.end = end;
	this.finished = finish;
}

public String getID() {
	return id;
}

public int getRawStart() {
	return rawstart;
}

public int getRawEnd() {
	return rawend;
}

public int getStart() {
	return start;
}

public int getEnd() {
	return end;
}

public void setEnd(int rawend, int end) {
	if( end == start ) {
		++end;
	}
	this.rawend = rawend;
	this.end = end;
	this.finished = true;
}

public boolean isFinish() {
	return finished;
}

public boolean isSelected() {
	return selected;
}

@Override
public String toString() {
	return id + "::" + start + ":" + end;
}

@Override
public boolean equals(Object obj) {
	if( this == obj )
		return true;
	if (obj == null)
		return false;
	if (!(obj instanceof AbstractModel)) {
		return false;
	}

	AbstractModel comp = (AbstractModel) obj;
	return comp.getStart() == this.getStart()
			&& comp.getEnd() == this.getEnd()
			&& comp.getID().equals(this.getID());
}

@Override
public int hashCode() {
	return getStart() + (getEnd() ^ id.hashCode());
}

/*public void select() {
	Debugger.printStoryboardInfo("SELECT " + this);
	selected  = true;
	firePropertyChange(P_SELECT,null,this);
}

public void deselect() {
	selected = false;
	firePropertyChange(P_DESELECT,null,this);
}

/*public void addPropertyChangeListener(PropertyChangeListener listener) {
	props.addPropertyChangeListener(listener);
}

public void firePropertyChange(String propName, Object oldValue, Object newValue) {
	props.firePropertyChange(propName,oldValue,newValue);
}

public void removePropertyChangeListener(PropertyChangeListener listener) {
	props.removePropertyChangeListener(listener);
}

/*public RuntimeView.TreeObject getRuntimeViewData() {
	return new RuntimeView.TreeObject("Unknown Object");
}*/

public int getLayoutIndex() {
	return layoutIndex;
}

public void setLayoutIndex(int idx) {
	layoutIndex = idx;
}


}