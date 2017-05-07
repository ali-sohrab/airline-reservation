package edu.sjsu.cmpe275.lab2.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sjsu.cmpe275.lab2.entity.Reservation_Flight;

public interface ReservationFlightRepository extends JpaRepository<Reservation_Flight, String> {

	public Set<Reservation_Flight> findByFlightNumber(String flightNumber);

	@Query("SELECT rf.orderNumber FROM Reservation_Flight rf, Flight f WHERE rf.flightNumber = f.flightNumber AND f.from = :from")
	public Set<Long> findByFrom(@Param("from") String from);

	@Query("SELECT rf.orderNumber FROM Reservation_Flight rf, Flight f WHERE rf.flightNumber = f.flightNumber AND f.to = :to")
	public Set<Long> findByTo(@Param("to") String to);

}
