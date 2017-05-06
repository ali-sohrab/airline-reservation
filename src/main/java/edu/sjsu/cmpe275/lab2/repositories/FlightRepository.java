package edu.sjsu.cmpe275.lab2.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.lab2.entity.Flight;

/**
 * @author Sohrab-Ali
 *
 */
public interface FlightRepository extends CrudRepository<Flight, String> {
}
