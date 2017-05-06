package edu.sjsu.cmpe275.lab2.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.repositories.FlightRepository;

/**
 * @author Sohrab-Ali
 *
 */
@Transactional
@Repository
public class FlightDAO implements IFlightDAO {

	@Autowired
	private FlightRepository flightRepository;

	private static Flight flight = null;

	@Override
	public Flight getFlight(String flightNumber) {
		flight = flightRepository.findOne(flightNumber);
		return flight;
	}

	@Override
	public void updateSeatsLeft(Flight fl) {
		fl.setSeatsLeft(fl.getSeatsLeft() - 1);
		flightRepository.save(fl);
	}

	@Override
	public Flight createFlight(Flight fl) {
		if (!flightRepository.exists(fl.getFlightNumber())) {
			flight = flightRepository.save(fl);
		}
		return flight;
	}

	@Override
	public Flight updateFlight(Flight fl) {
		if (flightRepository.exists(fl.getFlightNumber())) {
			flight = flightRepository.save(fl);
		}
		return flight;
	}

	@Override
	public boolean deleteFlight(String flightNumber) {
		boolean deleted = false;
		if (flightRepository.exists(flightNumber)) {
			flightRepository.delete(flightNumber);
			deleted = true;
		}
		return deleted;
	}

}
