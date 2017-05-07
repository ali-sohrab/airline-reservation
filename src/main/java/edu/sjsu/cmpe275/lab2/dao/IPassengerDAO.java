package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.error.TransactionException;

/**
 * @author Sohrab-Ali
 *
 */
public interface IPassengerDAO {

	public Passenger addPassenger(Passenger passenger) throws TransactionException;

	public Passenger getPassenger(long id);

	public Passenger updatePassenger(long id, Passenger passenger) throws TransactionException;

	public boolean deletePassenger(long id);

}
