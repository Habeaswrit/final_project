package com.example.eventmanagement.service;

import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.entity.EventType;
import com.example.eventmanagement.entity.Venue;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.EventTypeRepository;
import com.example.eventmanagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Transactional
    public Event createEvent(Event event) {
        // Validate venue if provided
        if (event.getVenue() != null && event.getVenue().getVenueId() != null) {
            Venue venue = venueRepository.findById(event.getVenue().getVenueId())
                .orElseThrow(() -> new IllegalArgumentException("Venue not found with id: " + event.getVenue().getVenueId()));
            event.setVenue(venue);
        }
        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id)
            .map(event -> {
                event.setName(updatedEvent.getName());
                event.setDate(updatedEvent.getDate());
                event.setDescription(updatedEvent.getDescription());
                // Validate venue if provided
                if (updatedEvent.getVenue() != null && updatedEvent.getVenue().getVenueId() != null) {
                    Venue venue = venueRepository.findById(updatedEvent.getVenue().getVenueId())
                        .orElseThrow(() -> new IllegalArgumentException("Venue not found with id: " + updatedEvent.getVenue().getVenueId()));
                    event.setVenue(venue);
                } else {
                    event.setVenue(null); 
                }
                return eventRepository.save(event);
            })
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    @Transactional
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Transactional
    public Event addTypeToEvent(Long eventId, Long typeId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        EventType type = eventTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("EventType not found"));

        event.getEventTypes().add(type);
        return eventRepository.save(event);
    }
}