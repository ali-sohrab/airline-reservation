package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.entity.Flight;

/**
 * @author Sohrab-Ali
 *
 */
public interface IFlightDAO {

	public Flight getFlight(String flightNumber);

	public void updateSeatsLeft(Flight fl);

	public Flight createFlight(Flight fl);

	public Flight updateFlight(Flight fl);

	public boolean deleteFlight(String flightNumber);

}
