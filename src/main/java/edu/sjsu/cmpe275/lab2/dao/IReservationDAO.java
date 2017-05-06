package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.entity.Reservation;

/**
 * @author Sohrab-Ali
 *
 */
public interface IReservationDAO {

	public Reservation makeReservation(long passengerId, String flightList);

	public Reservation getReservation(long orderNumber);

	public Reservation updateReservation(long orderNumber, String flightsAdded, String flightsRemoved);

	public Reservation searchReservation(long id, String from, String to, String flightNumber);

	public boolean deleteReservation(long orderNumber);

}
