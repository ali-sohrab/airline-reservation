package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.entity.Passenger;

/**
 * @author Sohrab-Ali
 *
 */
public interface IPassengerDAO {

	public Passenger addPassenger(Passenger passenger);

	public Passenger getPassenger(long id);

	public Passenger updatePassenger(long id, Passenger passenger);

	public boolean deletePassenger(long id);

}
