/**
 * 
 */
package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Sohrab-PC
 *
 */
@Entity
@Table(name = "PASSENGER_FLIGHT")
@IdClass(Passenger_Flight_Id.class)
public class Passenger_Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PASSENGER_ID")
	private long id;

	@Id
	@Column(name = "FLIGHT_NUMBER")
	private String flightNumber;

	public Passenger_Flight() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

}
