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
@Table(name = "RESERVATION_FLIGHT")
@IdClass(Reservation_Flight_Id.class)
public class Reservation_Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORDER_NUMBER")
	private long orderNumber;

	@Id
	@Column(name = "FLIGHT_NUMBER")
	private String flightNumber;

	public Reservation_Flight() {
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

}
