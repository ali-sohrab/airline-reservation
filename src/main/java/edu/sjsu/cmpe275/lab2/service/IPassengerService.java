package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.entity.Passenger;

/**
 * @author Sohrab-Ali
 *
 */
public interface IPassengerService {

	public Passenger getPassenger(long id);

	public boolean deletePassenger(long id);

	public Passenger addPassenger(String firstname, String lastname, int age, String gender, long phone);

	public Passenger updatePassenger(long id, String firstname, String lastname, int age, String gender, long phone);

}
