package edu.sjsu.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Reservation;
import edu.sjsu.cmpe275.lab2.error.ErrorResponse;
import edu.sjsu.cmpe275.lab2.service.IReservationService;

/**
 * @author Sohrab-Ali
 *
 */
@RestController
public class ReservationController {

	@Autowired
	private IReservationService iReservationService;

	private static Reservation res = null;

	@PostMapping("/reservation")
	public ResponseEntity<Object> makeReservation(@RequestParam(name = "passengerId", required = true) long id,
			@RequestParam(name = "flightLists", required = true) String flightLists) {
		res = iReservationService.makeReservation(id, flightLists);

		if (res != null) {
			return ResponseEntity.ok(res);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("400", "Sorry, time overlap, total passengers/capacity");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/reservation/{number}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getReservation(@PathVariable(name = "number", required = true) long orderNumber) {

		res = iReservationService.getReservation(orderNumber);

		if (res != null) {
			return ResponseEntity.ok(res);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Reservation with number " + orderNumber + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/reservation", produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> searchReservation(@RequestParam(name = "passengerId", required = true) long id,
			@RequestParam(name = "from", required = true) String from,
			@RequestParam(name = "to", required = true) String to,
			@RequestParam(name = "flightNumber", required = true) String flightNumber) {

		res = iReservationService.searchReservation(id, from, to, flightNumber);

		if (res != null) {
			return ResponseEntity.ok(res);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404", "Sorry, time overlap, total passengers/capacity");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/reservation/{number}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateReservation(@PathVariable(name = "number", required = true) long orderNumber,
			@RequestParam(name = "flightsAdded", required = true) String flightsAdded,
			@RequestParam(name = "flightsRemoved", required = true) String flightsRemoved) {

		res = iReservationService.updateReservation(orderNumber, flightsAdded, flightsRemoved);

		if (res != null) {
			return ResponseEntity.ok(res);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404", "Sorry, time overlap, total passengers/capacity");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/reservation/{number}")
	public ResponseEntity<Object> deleteReservation(@PathVariable(name = "number", required = true) long orderNumber) {
		boolean deleted = false;
		deleted = iReservationService.deleteReservation(orderNumber);
		if (deleted == true) {
			return ResponseEntity.ok("Reservation with number " + orderNumber + " is deleted successfully");
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Reservation with number " + orderNumber + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}
}
