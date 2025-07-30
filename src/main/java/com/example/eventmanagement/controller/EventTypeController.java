package com.example.eventmanagement.controller;

import com.example.eventmanagement.entity.EventType;
import com.example.eventmanagement.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @GetMapping
    public List<EventType> getAllEventTypes() {
        return eventTypeService.getAllEventTypes();
    }

    @PostMapping
    public EventType createEventType(@RequestBody EventType eventType) {
        return eventTypeService.createEventType(eventType);
    }
}
