/*
 * Created on Oct 11, 2004
 */
package tgef;

/**
 * @author asangpet
 */
public class SourceObjectModel {
	private String id;
	private String className;
	
	public SourceObjectModel() { /* nothing */ } 
	
	public SourceObjectModel(String id, String className) {
		setClassName(className);
		setId(id);
	}
	
	/**
	 * @return Returns the className.
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
}