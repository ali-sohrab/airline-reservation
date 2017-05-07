package edu.sjsu.cmpe275.lab2.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.entity.Reservation;
import edu.sjsu.cmpe275.lab2.error.TransactionException;
import edu.sjsu.cmpe275.lab2.repositories.FlightRepository;
import edu.sjsu.cmpe275.lab2.repositories.PassengerRepository;

/**
 * @author Sohrab-Ali
 *
 */
@Transactional
@Repository
public class PassengerDAO implements IPassengerDAO {

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private FlightRepository flightRepository;

	private static Passenger pass = null;

	@Override
	public Passenger addPassenger(Passenger passenger) throws TransactionException {
		try {
			pass = passengerRepository.save(passenger);
		} catch (Exception e) {
			throw new TransactionException("Passenger with same phone number already exist");
		}
		return pass;
	}

	@Override
	public Passenger getPassenger(long id) {
		pass = passengerRepository.findOne(id);
		return pass;
	}

	@Override
	public Passenger updatePassenger(long id, Passenger passenger) throws TransactionException {
		try {
			passengerRepository.save(passenger);
		} catch (Exception e) {
			throw new TransactionException("DB constraint occured, update was not successful");
		}
		return pass;
	}

	@Override
	public boolean deletePassenger(long id) {
		boolean deleted = false;
		if (passengerRepository.exists(id)) {
			pass = passengerRepository.findOne(id);
			List<Reservation> reservations = pass.getReservations();
			if (reservations != null) {
				for (Reservation reservation : reservations) {
					List<Flight> flights = reservation.getFlights();
					if (flights != null) {
						for (Flight flight : flights) {
							flight.setSeatsLeft(flight.getSeatsLeft() - 1);
							flightRepository.save(flight);
						}
					}
				}
			}
			passengerRepository.delete(id);
			deleted = true;
		}
		return deleted;
	}
}
