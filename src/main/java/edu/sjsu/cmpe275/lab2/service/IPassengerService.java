package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.error.TransactionException;

/**
 * @author Sohrab-Ali
 *
 */
public interface IPassengerService {

	public Passenger getPassenger(long id);

	public boolean deletePassenger(long id);

	public Passenger addPassenger(String firstname, String lastname, int age, String gender, String phone)
			throws TransactionException;

	public Passenger updatePassenger(long id, String firstname, String lastname, int age, String gender, String phone)
			throws TransactionException;

}
