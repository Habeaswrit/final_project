package com.example.eventmanagement.repository;

import com.example.eventmanagement.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
