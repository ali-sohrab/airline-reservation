package com.airline.reservation.server;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.sjsu.cmpe275.lab2.controller.PassengerController;
import edu.sjsu.cmpe275.lab2.entity.Passenger;
import edu.sjsu.cmpe275.lab2.service.PassengerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PassengerTest {

	MockMvc mockMvc;

	Passenger passenger;

	@Mock
	PassengerService passengerService;

	@InjectMocks
	PassengerController passengerController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
	}

	@Test
	public void createPassenger() throws Exception {

		/*Passenger passenger = new Passenger("XX", "YY", 11, "female", 456);
		when(passengerService.addPassenger("XX", "YY", 11, "female", 456)).thenReturn(passenger);

		mockMvc.perform(post("/passenger?firstname=XX&lastname=YY&age=11&gender=female&phone=456")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(0))).andExpect(jsonPath("$.firstname", is("XX")))
				.andExpect(jsonPath("$.lastname", is("YY"))).andExpect(jsonPath("$.age", is(11)))
				.andExpect(jsonPath("$.gender", is("female"))).andExpect(jsonPath("$.phone", is(456)));

		verify(passengerService, times(1)).addPassenger("XX", "YY", 11, "female", 456);
		verifyNoMoreInteractions(passengerService);*/
	}
}