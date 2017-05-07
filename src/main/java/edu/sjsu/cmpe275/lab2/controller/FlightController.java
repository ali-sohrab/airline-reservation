package edu.sjsu.cmpe275.lab2.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.error.ErrorResponse;
import edu.sjsu.cmpe275.lab2.service.IFlightService;

/**
 * @author Sohrab-Ali
 *
 */
@RestController
public class FlightController {
	@Autowired
	private IFlightService iFlightService;

	private static Flight flight = null;

	@PostMapping(value = "/flight/{flightNumber}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Flight> createFlight(
			@PathVariable(name = "flightNumber", required = true) String flightNumber,
			@RequestParam(name = "price", required = true) int price,
			@RequestParam(name = "from", required = true) String from,
			@RequestParam(name = "to", required = true) String to,
			@RequestParam(name = "departureTime", required = true) String departureTime,
			@RequestParam(name = "arrivalTime", required = true) String arrivalTime,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "capacity", required = true) int capacity,
			@RequestParam(name = "model", required = true) String model,
			@RequestParam(name = "manufacturer", required = true) String manufacturer,
			@RequestParam(name = "yearOfManufacture", required = true) int yearOfManufacture) throws ParseException {

		flight = iFlightService.createFlight(flightNumber, price, from, to, departureTime, arrivalTime, description,
				capacity, model, manufacturer, yearOfManufacture);

		if (flight != null) {
			return ResponseEntity.ok(flight);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404", "Sorry, time overlap, total passengers/capacity");
			return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/flight/{flightNumber}", params = "xml=true", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Flight> getFlightAsXml(
			@PathVariable(name = "flightNumber", required = true) String flightNumber) {
		flight = iFlightService.getFlight(flightNumber);
		if (flight != null) {
			return ResponseEntity.ok(flight);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Sorry, the requested flight with number " + flightNumber + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/flight/{flightNumber}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Flight> getFlightAsJson(
			@PathVariable(name = "flightNumber", required = true) String flightNumber) {
		flight = iFlightService.getFlight(flightNumber);
		if (flight != null) {
			return ResponseEntity.ok(flight);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Sorry, the requested flight with number " + flightNumber + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/flight/{flightNumber}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Flight> updateFlight(
			@PathVariable(name = "flightNumber", required = true) String flightNumber,
			@RequestParam(name = "price", required = true) int price,
			@RequestParam(name = "from", required = true) String from,
			@RequestParam(name = "to", required = true) String to,
			@RequestParam(name = "departureTime", required = true) String departureTime,
			@RequestParam(name = "arrivalTime", required = true) String arrivalTime,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "capacity", required = true) int capacity,
			@RequestParam(name = "model", required = true) String model,
			@RequestParam(name = "manufacturer", required = true) String manufacturer,
			@RequestParam(name = "yearOfManufacture", required = true) int yearOfManufacture) throws ParseException {

		flight = iFlightService.updateFlight(flightNumber, price, from, to, departureTime, arrivalTime, description,
				capacity, model, manufacturer, yearOfManufacture);

		if (flight != null) {
			return ResponseEntity.ok(flight);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404", "Sorry, time overlap, total passengers/capacity");
			return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/airline/{flightNumber}")
	public ResponseEntity<Object> deleteFlight(
			@PathVariable(name = "flightNumber", required = true) String flightNumber) {
		boolean deleted = false;
		deleted = iFlightService.deleteFlight(flightNumber);
		if (deleted == true) {
			return ResponseEntity.ok("Flight with number " + flightNumber + " is deleted successfully");
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Flight with number " + flightNumber + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
		}
	}
}
