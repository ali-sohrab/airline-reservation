package edu.sjsu.cmpe275.lab2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.entity.Reservation;
import edu.sjsu.cmpe275.lab2.repositories.ReservationRepository;

/**
 * @author Sohrab-Ali
 *
 */
@Transactional
@Repository
public class ReservationDAO implements IReservationDAO {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private IPassengerDAO iPassengerDAO;

	@Autowired
	private IFlightDAO iFlightDAO;

	private static Reservation res = null;

	@Override
	public Reservation makeReservation(long id, String flightList) {
		Reservation reservation = new Reservation();
		Flight flight = null;
		List<Flight> list = null;
		int price = 0;
		Passenger passenger = iPassengerDAO.getPassenger(id);
		if (passenger != null) {
			reservation.setPassenger(passenger);
			String[] str = flightList.split(",");
			list = new ArrayList<>();
			for (int i = 0; i < str.length; i++) {
				flight = iFlightDAO.getFlight(str[i]);
				if (flight != null && flight.getSeatsLeft() > 0) {
					price += flight.getPrice();
					list.add(flight);
					iFlightDAO.updateSeatsLeft(flight);
				}
			}
			reservation.setFlights(list);
			reservation.setPrice(price);
			res = reservationRepository.save(reservation);
		}
		return res;
	}

	@Override
	public Reservation getReservation(long orderNumber) {
		res = reservationRepository.findOne(orderNumber);
		return res;
	}

	@Override
	public Reservation updateReservation(long orderNumber, String flightsAdded, String flightsRemoved) {
		res = getReservation(orderNumber);
		Flight flight = null;
		List<Flight> list = null;
		if (res != null) {
			String[] flightsAddedArr = flightsAdded.split(",");
			if (flightsAddedArr != null) {

			}
			list = new ArrayList<>();
		}

		return null;
	}

	@Override
	public Reservation searchReservation(long id, String from, String to, String flightNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReservation(long orderNumber) {
		boolean deleted = false;
		if (reservationRepository.exists(orderNumber)) {
			reservationRepository.delete(orderNumber); // available seats must update
			deleted = true;
		}
		return deleted;
	}
}
