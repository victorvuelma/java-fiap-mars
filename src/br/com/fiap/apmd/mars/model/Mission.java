package br.com.fiap.apmd.mars.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String drone;

	private Date earthDate;

	private String marsMonth;
	private int marsDays;

	private float longitude;

	private float maxTemp;
	private float minTemp;
	private float atmPressure;

	public long getId() {
		return id;
	}

	public String getDrone() {
		return drone;
	}

	public void setDrone(String drone) {
		this.drone = drone;
	}

	public Date getEarthDate() {
		return earthDate;
	}

	public void setEarthDate(Date date) {
		this.earthDate = date;
	}

	public String getMarsMonth() {
		return marsMonth;
	}

	public void setMarsMonth(String marsMonth) {
		this.marsMonth = marsMonth;
	}

	public int getMarsDays() {
		return marsDays;
	}

	public void setMarsDays(int marsDays) {
		this.marsDays = marsDays;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}

	public float getAtmPressure() {
		return atmPressure;
	}

	public void setAtmPressure(float atmPressure) {
		this.atmPressure = atmPressure;
	}

}
