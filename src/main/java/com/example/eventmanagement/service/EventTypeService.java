package com.example.eventmanagement.service;

import com.example.eventmanagement.entity.EventType;
import com.example.eventmanagement.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    public List<EventType> getAllEventTypes() {
        return eventTypeRepository.findAll();
    }

    public EventType createEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }
}
