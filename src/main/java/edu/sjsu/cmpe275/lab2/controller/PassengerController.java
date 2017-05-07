package edu.sjsu.cmpe275.lab2.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.error.ErrorResponse;
import edu.sjsu.cmpe275.lab2.error.TransactionException;
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

	private HashMap<String, Passenger> response;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/passenger")
	public ResponseEntity addPassenger(@RequestParam(name = "firstname", required = true) String firstname,
			@RequestParam(name = "lastname", required = true) String lastname,
			@RequestParam(name = "age", required = true) int age,
			@RequestParam(name = "gender", required = true) String gender,
			@RequestParam(name = "phone", required = true) String phone) {
		try {
			pass = iPassengerService.addPassenger(firstname, lastname, age, gender, phone);
			response = new HashMap<String, Passenger>();
			response.put("passenger", pass);
			return ResponseEntity.ok(response);
		} catch (TransactionException e) {
			Map<String, ErrorResponse> err = new HashMap<>();
			ErrorResponse errorResponse = new ErrorResponse("400", e.getMessage());
			err.put("BadRequest", errorResponse);
			return new ResponseEntity(err, HttpStatus.BAD_REQUEST);

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/passenger/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object getPassengerAsJson(@PathVariable(name = "id", required = true) long id) {
		pass = iPassengerService.getPassenger(id);
		if (pass != null) {
			response = new HashMap<String, Passenger>();
			response.put("passenger", pass);
			return ResponseEntity.ok(response);
		} else {
			Map<String, ErrorResponse> err = new HashMap<>();
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Sorry, the requested passenger with id " + id + " does not exist");
			err.put("BadRequest", errorResponse);
			return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/passenger/{id}", params = "xml=true", produces = { MediaType.APPLICATION_XML_VALUE })
	public Object getPassengerAsXml(@PathVariable(name = "id", required = true) long id) throws JSONException {
		pass = iPassengerService.getPassenger(id);
		if (pass != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String passenger = objectMapper.writeValueAsString(pass);
				JSONObject jsonObject = new JSONObject(passenger);
				return ResponseEntity.ok(XML.toString(jsonObject));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

		} else {
			Map<String, ErrorResponse> err = new HashMap<>();
			ErrorResponse errorResponse = new ErrorResponse("404",
					"Sorry, the requested passenger with id " + id + " does not exist");
			err.put("BadRequest", errorResponse);
			return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/passenger/{id}")
	public ResponseEntity updatePassenger(@PathVariable(name = "id", required = true) long id,
			@RequestParam(name = "firstname", required = true) String firstname,
			@RequestParam(name = "lastname", required = true) String lastname,
			@RequestParam(name = "age", required = true) int age,
			@RequestParam(name = "gender", required = true) String gender,
			@RequestParam(name = "phone", required = true) String phone) throws TransactionException {
		try {
			iPassengerService.updatePassenger(id, firstname, lastname, age, gender, phone);
			pass = iPassengerService.getPassenger(id);
			response = new HashMap<String, Passenger>();
			response.put("passenger", pass);
			return ResponseEntity.ok(response);
		} catch (TransactionException e) {
			Map<String, ErrorResponse> err = new HashMap<>();
			ErrorResponse errorResponse = new ErrorResponse("404", e.getMessage());
			err.put("BadRequest", errorResponse);
			return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/passenger/{id}")
	public ResponseEntity<Object> deletePassenger(@PathVariable(name = "id", required = true) long id) {
		boolean deleted = false;
		deleted = iPassengerService.deletePassenger(id);
		if (deleted == true) {
			return ResponseEntity.ok("Passenger with id " + id + " is deleted successfully");
		} else {
			Map<String, ErrorResponse> err = new HashMap<>();
			ErrorResponse errorResponse = new ErrorResponse("404", "Passenger with id " + id + " does not exist");
			err.put("BadRequest", errorResponse);
			return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
		}
	}
}
