package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Sohrab-Ali
 *
 */
@Entity
@Table(name = "RESERVATION")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderNumber", scope = Reservation.class)
@JsonInclude(Include.NON_EMPTY)
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_NUMBER")
	private long orderNumber;

	@NotNull
	@Column(name = "PRICE")
	private int price; // sum of each flight’s price.

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PASSENGER_ID")
	private Passenger passenger;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RESERVATION_FLIGHT", joinColumns = {
			@JoinColumn(name = "ORDER_NUMBER", referencedColumnName = "ORDER_NUMBER") }, inverseJoinColumns = {
					@JoinColumn(name = "FLIGHT_NUMBER", referencedColumnName = "FLIGHT_NUMBER") })
	private List<Flight> flights;

	public Reservation() {
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

}
