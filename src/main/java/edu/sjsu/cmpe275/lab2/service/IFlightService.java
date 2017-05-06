package edu.sjsu.cmpe275.lab2.service;

import java.text.ParseException;

import edu.sjsu.cmpe275.lab2.entity.Flight;

/**
 * @author Sohrab-Ali
 *
 */
public interface IFlightService {

	public Flight createFlight(String flightNumber, int price, String from, String to, String departureTime,
			String arrivalTime, String description, int capacity, String model, String manufacturer,
			int yearOfManufacture) throws ParseException;

	public Flight getFlight(String flightNumber);

	public Flight updateFlight(String flightNumber, int price, String from, String to, String departureTime,
			String arrivalTime, String description, int capacity, String model, String manufacturer,
			int yearOfManufacture) throws ParseException;

	public boolean deleteFlight(String flightNumber);

}
