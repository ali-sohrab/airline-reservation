package edu.sjsu.cmpe275.lab2.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.entity.Reservation;
import edu.sjsu.cmpe275.lab2.entity.Reservation_Flight;
import edu.sjsu.cmpe275.lab2.error.TransactionException;
import edu.sjsu.cmpe275.lab2.repositories.FlightRepository;
import edu.sjsu.cmpe275.lab2.repositories.ReservationFlightRepository;
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
	private FlightRepository flightRepository;

	@Autowired
	private ReservationFlightRepository reservationFlightRepository;

	@Autowired
	private IPassengerDAO iPassengerDAO;

	@Autowired
	private IFlightDAO iFlightDAO;

	private static Reservation res = null;

	@Override
	public Reservation makeReservation(long id, String flightList) throws TransactionException {
		Reservation reservation = new Reservation();
		Flight flight = null;
		List<Flight> list = null;
		int price = 0;
		try {
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
		} catch (Exception e) {
			throw new TransactionException("DB constraint occured, update was not successful");
		}
		return res;
	}

	@Override
	public Reservation getReservation(long orderNumber) {
		res = reservationRepository.findOne(orderNumber);
		return res;
	}

	@Override
	public Reservation updateReservation(long orderNumber, String flightsAdded, String flightsRemoved){
		res = getReservation(orderNumber);
		Flight flight = null;
		List<Flight> list = null;
		if (res != null) {
			String[] flightsAddedArr = flightsAdded.split(",");
			if (flightsAddedArr != null) {

			}
			list = new ArrayList<>();
		}

		return res;
	}

	@Override
	public Set<Reservation> searchReservation(long id, String from, String to, String flightNumber) {
		Set<Reservation> response = new HashSet<>();
		Set<Long> reservationIds = new HashSet<>();
		boolean flag = false;

		if (id > 0) {
			Set<Reservation> reservations = reservationRepository.findByPassengerId(id);
			Set<Long> passengerReservationIds = new HashSet<>();

			for (Reservation reservation : reservations) {
				passengerReservationIds.add(reservation.getOrderNumber());
			}
			if (passengerReservationIds.size() == 0) {
				return response;
			} else {
				flag = true;
				reservationIds.addAll(passengerReservationIds);
			}
		}

		if (flightNumber != null && !flightNumber.isEmpty()) {
			Set<Reservation_Flight> reservations = reservationFlightRepository.findByFlightNumber(flightNumber);
			Set<Long> flightReservationIds = new HashSet<>();

			for (Reservation_Flight reservationFlight : reservations) {
				flightReservationIds.add(reservationFlight.getOrderNumber());
			}
			if (flightReservationIds.size() == 0) {
				return response;
			} else {
				if (flag) {
					reservationIds.retainAll(flightReservationIds);
					if (reservationIds.size() == 0) {
						return response;
					}
				} else {
					flag = true;
					reservationIds.addAll(flightReservationIds);
				}
			}
		}

		if (from != null && !from.isEmpty()) {
			Set<Long> reservations = reservationFlightRepository.findByFrom(from);
			Set<Long> fromReservationIds = new HashSet<>();

			if (reservations != null && reservations.size() > 0) {
				fromReservationIds.addAll(reservations);
				if (flag) {
					reservationIds.retainAll(fromReservationIds);
					if (reservationIds.size() == 0) {
						return response;
					}
				} else {
					flag = true;
					reservationIds.addAll(fromReservationIds);
				}
			} else {
				return response;
			}
		}

		if (to != null && !to.isEmpty()) {
			Set<Long> reservations = reservationFlightRepository.findByTo(to);
			Set<Long> toReservationIds = new HashSet<>();

			if (reservations != null && reservations.size() > 0) {
				toReservationIds.addAll(reservations);
				if (flag) {
					reservationIds.retainAll(toReservationIds);
					if (reservationIds.size() == 0) {
						return response;
					}
				} else {
					flag = true;
					reservationIds.addAll(toReservationIds);
				}
			} else {
				return response;
			}
		}

		Iterable<Reservation> iterable = reservationRepository.findAll(reservationIds);
		for (Reservation r : iterable) {
			response.add(r);
		}
		return response;

	}

	@Override
	public boolean deleteReservation(long orderNumber) {
		boolean deleted = false;
		if (reservationRepository.exists(orderNumber)) {
			res = reservationRepository.findOne(orderNumber);
			List<Flight> flights = res.getFlights();
			if (flights != null) {
				for (Flight flight : flights) {
					flight.setSeatsLeft(flight.getSeatsLeft() - 1);
					flightRepository.save(flight);
				}
			}
			reservationRepository.delete(orderNumber);
			deleted = true;
		}
		return deleted;
	}
}
