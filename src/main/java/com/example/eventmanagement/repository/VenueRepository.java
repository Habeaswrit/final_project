package com.example.eventmanagement.repository;

import com.example.eventmanagement.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
