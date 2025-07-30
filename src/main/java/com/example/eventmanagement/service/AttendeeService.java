package com.example.eventmanagement.service;

import com.example.eventmanagement.entity.Attendee;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.repository.AttendeeRepository;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private EventRepository eventRepository;

    public Attendee addAttendeeToEvent(Long eventId, Attendee attendee) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            attendee.setEvent(eventOpt.get());
            return attendeeRepository.save(attendee);
        }
        throw new RuntimeException("Event not found with id: " + eventId);
    }

    public Optional<Attendee> getAttendeeById(Long id) {
        return attendeeRepository.findById(id);
    }
}
