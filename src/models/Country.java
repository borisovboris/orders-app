package models;

public class Country {
	private int _countryCode;
	private String _name; 
	private String _continentName;
	
	public void setCountryCode(int countryCode) {
		_countryCode = countryCode;
	}
	
	public int getCountryCode() {
		return _countryCode;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setCountryName(String name) {
		_name = name;
	}
	
	public String getCountryName() {
		return _name;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setContinentName(String continentName) {
		_continentName = continentName;
	}
	
	public String getContinentName() {
		return _continentName;
	}
}
