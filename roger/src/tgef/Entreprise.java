package tgef;

public class Entreprise extends Node {
	
		private String address;
		private int capital;
		
		//static property sheet field
		public static final String PROPERTY_CAPITAL = "EntrepriseCapital";

		
		
		public void setAddress(String address) {
		this.address = address;
		}
		
		public void setCapital(int capital) { 
		this.capital = capital; 
		} 
		public String getAddress() {
		return this.address; 
		} 
		public int getCapital() {
		return this.capital;
		}


}
