package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.dao.IReservationDAO;
import edu.sjsu.cmpe275.lab2.entity.Reservation;

/**
 * @author Sohrab-Ali
 *
 */
@Service
public class ReservationService implements IReservationService {

	@Autowired
	private IReservationDAO iReservationDAO;

	private static Reservation res = null;

	@Override
	public Reservation makeReservation(long id, String flightList) {
		res = iReservationDAO.makeReservation(id, flightList);
		return res;
	}

	@Override
	public Reservation getReservation(long orderNumber) {
		res = iReservationDAO.getReservation(orderNumber);
		return res;
	}

	@Override
	public Reservation updateReservation(long orderNumber, String flightsAdded, String flightsRemoved) {
		res = iReservationDAO.updateReservation(orderNumber, flightsAdded, flightsRemoved);
		return res;
	}

	@Override
	public Reservation searchReservation(long id, String from, String to, String flightNumber) {
		res = iReservationDAO.searchReservation(id, from, to, flightNumber);
		return res;
	}

	@Override
	public boolean deleteReservation(long orderNumber) {
		boolean deleted = false;
		deleted = iReservationDAO.deleteReservation(orderNumber);
		return deleted;
	}

}
