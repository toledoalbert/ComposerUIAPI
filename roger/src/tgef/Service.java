package tgef;

import java.beans.PropertyChangeSupport;

import org.eclipse.swt.graphics.Color;



public class Service extends Node {
	private int etage;
	
	//color property
	private Color color;
	
	
	
	//static property fields
	public static final String PROPERTY_COLOR = "ServiceColor";
	public static final String PROPERTY_FLOOR = "ServiceFloor";

	
	//color randomization
	private Color createRandomColor() {
		return new Color(null,
		(new Double(Math.random() * 128)).intValue() + 128 ,
		(new Double(Math.random() * 128)).intValue() + 128 ,
		(new Double(Math.random() * 128)).intValue() + 128 );
		}
	
	
	public Service(){
		this.color= createRandomColor();
	}
	
	public  Color getColor(){
		return color;
	}
	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		
		System.out.println("Set color here");
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, color);
		
	}
	

	
	public void setEtage(int etage) {
	this.etage = etage;
	}
	
	public int getEtage() {
	return this.etage;
	}

}
