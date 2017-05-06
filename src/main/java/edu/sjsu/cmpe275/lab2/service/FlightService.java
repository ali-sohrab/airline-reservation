package edu.sjsu.cmpe275.lab2.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.lab2.dao.IFlightDAO;
import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.entity.Plane;

/**
 * @author Sohrab-Ali
 *
 */
@Service
public class FlightService implements IFlightService {

	@Autowired
	private IFlightDAO iFlightDAO;

	private static Flight flight = null;

	@Override
	public Flight createFlight(String flightNumber, int price, String from, String to, String departureTime,
			String arrivalTime, String description, int capacity, String model, String manufacturer,
			int yearOfManufacture) throws ParseException {
		Flight fl = new Flight();
		fl = setContent(fl, flightNumber, price, from, to, departureTime, arrivalTime, description, capacity, model,
				manufacturer, yearOfManufacture);
		flight = iFlightDAO.createFlight(fl);
		return flight;
	}

	@Override
	public Flight getFlight(String flightNumber) {
		flight = iFlightDAO.getFlight(flightNumber);
		System.out.println(flight.getArrivalTime().toString());
		System.out.println(flight.getDepartureTime().toString());
		return flight;
	}

	@Override
	public Flight updateFlight(String flightNumber, int price, String from, String to, String departureTime,
			String arrivalTime, String description, int capacity, String model, String manufacturer,
			int yearOfManufacture) throws ParseException {
		Flight fl = new Flight();
		fl = setContent(fl, flightNumber, price, from, to, departureTime, arrivalTime, description, capacity, model,
				manufacturer, yearOfManufacture);
		flight = iFlightDAO.updateFlight(fl);
		return flight;
	}

	@Override
	public boolean deleteFlight(String flightNumber) {
		boolean deleted = false;
		deleted = iFlightDAO.deleteFlight(flightNumber);
		return deleted;
	}

	private Flight setContent(Flight fl, String flightNumber, int price, String from, String to, String departureTime,
			String arrivalTime, String description, int capacity, String model, String manufacturer,
			int yearOfManufacture) throws ParseException {
		Plane pl = new Plane(capacity, model, manufacturer, yearOfManufacture);
		fl.setFlightNumber(flightNumber);
		fl.setPrice(price);
		fl.setFrom(from);
		fl.setTo(to);
		fl.setSeatsLeft(capacity);
		fl.setDepartureTime(convertStringToDate(departureTime));
		fl.setArrivalTime(convertStringToDate(arrivalTime));
		fl.setDescription(description);
		fl.setPlane(pl);
		return fl;
	}

	private Date convertStringToDate(String time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd-hh");
		Date myDate = format.parse(time);
		System.out.println(myDate.toString());
		return myDate;
	}

}