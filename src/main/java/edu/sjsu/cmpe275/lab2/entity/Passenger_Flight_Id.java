/**
 * 
 */
package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;

/**
 * @author Sohrab-PC
 *
 */
public class Passenger_Flight_Id implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String flightNumber;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger_Flight_Id other = (Passenger_Flight_Id) obj;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
