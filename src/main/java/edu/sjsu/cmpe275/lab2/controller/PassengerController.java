package edu.sjsu.cmpe275.lab2.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.error.ErrorResponse;
import edu.sjsu.cmpe275.lab2.service.IPassengerService;

/**
 * @author Sohrab-Ali
 *
 */
@RestController
public class PassengerController {

	@Autowired
	private IPassengerService iPassengerService;

	private static Passenger pass = null;

	@PostMapping("/passenger")
	public ResponseEntity<Passenger> addPassenger(@RequestParam(name = "firstname", required = true) String firstname,
			@RequestParam(name = "lastname", required = true) String lastname,
			@RequestParam(name = "age", required = true) int age,
			@RequestParam(name = "gender", required = true) String gender,
			@RequestParam(name = "phone", required = true) long phone) {
		pass = iPassengerService.addPassenger(firstname, lastname, age, gender, phone);
		if (pass != null) {
			return ResponseEntity.ok(pass);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("400", "Another passenger with the same number already exists");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	/*@GetMapping(value = "/passenger/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Passenger> getPassengerAsJson(@PathVariable(name = "id", required = true) long id,
			@RequestParam(name = "json", required = false) String json, HttpServletResponse response) {
		pass = iPassengerService.getPassenger(id);
		System.out.println("-------------------JSON ------------------------");
		HttpHeaders responseHeaders;
		ResponseEntity<Passenger> entity = null;
		if (pass != null) {
			// return ResponseEntity.ok(pass);
			responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.APPLICATION_JSON);
			responseHeaders.add("Accept", "application/xml;charset=utf-8");
			response.setContentType("application/json");
			entity = new ResponseEntity<Passenger>(pass, responseHeaders, HttpStatus.OK);
			return entity;
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Sorry, the requested passenger with id " + id + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}*/

	@GetMapping(value = "/passenger/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getPassengerAsXml(@PathVariable(name = "id", required = true) long id,
			@RequestParam(name = "xml", required = false) String xml,
			@RequestParam(name = "json", required = false) String json) {
		pass = iPassengerService.getPassenger(id);
		if (pass != null) {
			return ResponseEntity.ok(pass);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Sorry, the requested passenger with id " + id + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/passenger/{id}")
	public ResponseEntity<Passenger> updatePassenger(@PathVariable(name = "id", required = true) long id,
			@RequestParam(name = "firstname", required = true) String firstname,
			@RequestParam(name = "lastname", required = true) String lastname,
			@RequestParam(name = "age", required = true) int age,
			@RequestParam(name = "gender", required = true) String gender,
			@RequestParam(name = "phone", required = true) long phone) {
		pass = iPassengerService.updatePassenger(id, firstname, lastname, age, gender, phone);
		if (pass != null) {
			return ResponseEntity.ok(pass);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404", "Sorry, the requested passenger is not updated");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/passenger/{id}")
	public ResponseEntity<Object> deletePassenger(@PathVariable(name = "id", required = true) long id) {
		boolean deleted = false;
		deleted = iPassengerService.deletePassenger(id);
		if (deleted == true) {
			return ResponseEntity.ok("Passenger with id " + id + " is deleted successfully");
		} else {
			ErrorResponse errorResponse = new ErrorResponse("404", "Passenger with id " + id + " does not exist");
			return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
		}
	}

}
