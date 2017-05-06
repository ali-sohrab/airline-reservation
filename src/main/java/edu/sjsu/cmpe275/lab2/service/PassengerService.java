package edu.sjsu.cmpe275.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.dao.IPassengerDAO;
import edu.sjsu.cmpe275.lab2.entity.Passenger;

/**
 * @author Sohrab-Ali
 *
 */
@Service
public class PassengerService implements IPassengerService {

	@Autowired
	private IPassengerDAO iPassengerDAO;

	private static Passenger pass = null;

	@Override
	public Passenger getPassenger(long id) {
		pass = iPassengerDAO.getPassenger(id);
		return pass;
	}

	@Override
	public boolean deletePassenger(long id) {
		boolean deleted = false;
		deleted = iPassengerDAO.deletePassenger(id);
		return deleted;
	}

	@Override
	public Passenger addPassenger(String firstname, String lastname, int age, String gender, long phone) {
		Passenger passenger = new Passenger();
		passenger = setContent(passenger, firstname, lastname, age, gender, phone);
		pass = iPassengerDAO.addPassenger(passenger);
		return pass;
	}

	@Override
	public Passenger updatePassenger(long id, String firstname, String lastname, int age, String gender, long phone) {
		Passenger passenger = new Passenger();
		passenger.setId(id);
		passenger = setContent(passenger, firstname, lastname, age, gender, phone);
		pass = iPassengerDAO.updatePassenger(id, passenger);
		return pass;
	}

	private Passenger setContent(Passenger passenger, String firstname, String lastname, int age, String gender,
			long phone) {
		passenger.setAge(age);
		passenger.setFirstname(firstname);
		passenger.setGender(gender);
		passenger.setLastname(lastname);
		passenger.setPhone(phone);
		return passenger;
	}
}
