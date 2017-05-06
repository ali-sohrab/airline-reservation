package edu.sjsu.cmpe275.lab2.entity;

import java.io.Serializable;

/**
 * @author Sohrab-PC
 *
 */
public class Reservation_Flight_Id implements Serializable {

	private static final long serialVersionUID = 1L;

	private long orderNumber;
	private String flightNumber;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + (int) (orderNumber ^ (orderNumber >>> 32));
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
		Reservation_Flight_Id other = (Reservation_Flight_Id) obj;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (orderNumber != other.orderNumber)
			return false;
		return true;
	}

}
