package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.entity.Reservation;

/**
 * @author Sohrab-Ali
 *
 */
public interface IReservationService {

	public Reservation makeReservation(long id, String flightList);

	public Reservation getReservation(long orderNumber);

	public Reservation updateReservation(long orderNumber, String flightsAdded, String flightsRemoved);

	public Reservation searchReservation(long id, String from, String to, String flightNumber);

	public boolean deleteReservation(long orderNumber);

}
