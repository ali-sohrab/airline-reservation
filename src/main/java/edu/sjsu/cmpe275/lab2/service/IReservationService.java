package edu.sjsu.cmpe275.lab2.service;

import java.util.Set;

import edu.sjsu.cmpe275.lab2.entity.Reservation;
import edu.sjsu.cmpe275.lab2.error.TransactionException;

/**
 * @author Sohrab-Ali
 *
 */
public interface IReservationService {

	public Reservation makeReservation(long id, String flightList) throws TransactionException;

	public Reservation getReservation(long orderNumber);

	public Reservation updateReservation(long orderNumber, String flightsAdded, String flightsRemoved);

	public Set<Reservation> searchReservation(long id, String from, String to, String flightNumber);

	public boolean deleteReservation(long orderNumber);

}
