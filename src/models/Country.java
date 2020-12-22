package models;

public class Country {
	private String _countryCode;
	private String _name; 
	private String _continentName;
	
	public void setCountryCode(String countryCode) {
		_countryCode = countryCode;
	}
	
	public String getCountryCode() {
		return _countryCode;
	}
	
	/* ----------------------------------------------------------------------- */
	
	public void setName(String name) {
		_name = name;
	}
	
	public String getName() {
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
