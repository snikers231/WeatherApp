package com.bostonkoseninsandwiches.weatherapp.Helpers;


import com.google.gson.annotations.SerializedName;


public class City{

	@SerializedName("country")
	private String country;

	@SerializedName("name")
	private String name;

	@SerializedName("lon")
	private double lon;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("type")
	private String type;

	@SerializedName("lat")
	private double lat;

	@SerializedName("geoname_id")
	private int geonameId;

	@SerializedName("population")
	private int population;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setIso2(String iso2){
		this.iso2 = iso2;
	}

	public String getIso2(){
		return iso2;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	public void setGeonameId(int geonameId){
		this.geonameId = geonameId;
	}

	public int getGeonameId(){
		return geonameId;
	}

	public void setPopulation(int population){
		this.population = population;
	}

	public int getPopulation(){
		return population;
	}

	@Override
 	public String toString(){
		return 
			"City{" + 
			"country = '" + country + '\'' + 
			",name = '" + name + '\'' + 
			",lon = '" + lon + '\'' + 
			",iso2 = '" + iso2 + '\'' + 
			",type = '" + type + '\'' + 
			",lat = '" + lat + '\'' + 
			",geoname_id = '" + geonameId + '\'' + 
			",population = '" + population + '\'' + 
			"}";
		}
}