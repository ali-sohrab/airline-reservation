package edu.sjsu.cmpe275.lab2.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.lab2.entity.Passenger;
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

	private static Passenger pass = null;

	@Override
	public Passenger addPassenger(Passenger passenger) {
		pass = passengerRepository.save(passenger);
		return pass;
	}

	@Override
	public Passenger getPassenger(long id) {
		pass = passengerRepository.findOne(id);
		return pass;
	}

	@Override
	public Passenger updatePassenger(long id, Passenger passenger) {
		if (passengerRepository.exists(id)) {
			pass = passengerRepository.save(passenger);
		}
		return pass;
	}

	@Override
	public boolean deletePassenger(long id) {
		boolean deleted = false;
		if (passengerRepository.exists(id)) {
			passengerRepository.delete(id);
			deleted = true;
		}
		return deleted;
	}
}
