package com.airline.reservation.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.springframework.http.MediaType;

import edu.sjsu.cmpe275.lab2.entity.Flight;
import edu.sjsu.cmpe275.lab2.service.FlightService;

@RunWith(SpringRunner.class)
@RestClientTest(FlightService.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@SpringBootTest
public class FlightTests {

	@Autowired
	private FlightService flightService;

	@Autowired
	private MockRestServiceServer server;

	@Test
	public void createFlight() throws Exception {
		
		/*this.server.expect(requestTo("/flight/{flightNumber}")).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		Flight flight = this.flightService.createFlight(flightNumber, price, from, to, departureTime, arrivalTime, description, capacity, model, manufacturer, yearOfManufacture));
		assertThat(flight).isEqualTo();*/
	}

}