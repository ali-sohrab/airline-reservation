package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Sohrab-Ali
 *
 */
@Entity
@Table(name = "PASSENGER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Passenger.class)
@JsonInclude(Include.NON_EMPTY)
public class Passenger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASSENGER_ID")
	private long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstname;

	@NotNull
	@Column(name = "LAST_NAME")
	private String lastname;

	@NotNull
	@Column(name = "AGE")
	private int age;

	@NotNull
	@Column(name = "GENDER")
	private String gender;

	@NotNull
	@Column(name = "PHONE", unique = true)
	private long phone; // Phone numbers must be unique

	@OneToMany(mappedBy = "passenger")
	private List<Reservation> reservations;

	public Passenger() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
