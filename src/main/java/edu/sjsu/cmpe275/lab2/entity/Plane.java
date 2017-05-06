package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Sohrab-Ali
 *
 */
@Embeddable
public class Plane implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CAPACITY")
	private int capacity;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "YEAR_OF_MANUFACTURE")
	private int yearOfManufacture;

	public Plane() {
	}

	public Plane(int capacity, String model, String manufacturer, int yearOfManufacture2) {
		super();
		this.capacity = capacity;
		this.model = model;
		this.manufacturer = manufacturer;
		this.yearOfManufacture = yearOfManufacture2;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

}
