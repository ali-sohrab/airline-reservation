package edu.sjsu.cmpe275.lab2.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import edu.sjsu.cmpe275.lab2.entity.Reservation;

/**
 * @author Sohrab-Ali
 *
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public Set<Reservation> findByPassengerId(long id);
}