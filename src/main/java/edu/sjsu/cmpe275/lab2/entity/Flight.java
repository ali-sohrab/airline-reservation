package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sohrab-Ali
 *
 */
@Entity
@Table(name = "FLIGHT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "flightNumber", scope = Flight.class)
@JsonInclude(Include.NON_EMPTY)
public class Flight implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FLIGHT_NUMBER", unique = true)
	private String flightNumber; // Each flight has a unique flight number.

	@NotNull
	@Column(name = "PRICE")
	private int price;

	@NotNull
	@Column(name = "SOURCE")
	private String from;

	@NotNull
	@Column(name = "DESTINATION")
	private String to;

	/*
	 * Date format: ​yy-mm-dd-hh,​ do not include minutes and seconds. **
	 * Example: 2017-03-22-19 The system only needs to supports PST.You can
	 * ignore other time zones.
	 */
	@NotNull
	@Column(name = "DEP_TIME")
	@JsonFormat(pattern = "yyyy-mm-dd-hh")
	private Date departureTime;

	@NotNull
	@Column(name = "ARR_TIME")
	@JsonFormat(pattern = "yyyy-mm-dd-hh")
	private Date arrivalTime;

	@NotNull
	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Column(name = "SEATS_LEFT")
	@JsonIgnore
	private int seatsLeft;

	@Embedded
	private Plane plane; // Embedded

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PASSENGER_FLIGHT", joinColumns = {
			@JoinColumn(name = "FLIGHT_NUMBER", referencedColumnName = "FLIGHT_NUMBER") }, inverseJoinColumns = {
					@JoinColumn(name = "PASSENGER_ID", referencedColumnName = "PASSENGER_ID") })
	private List<Passenger> passengerList;

	public Flight() {
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(int seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
}
