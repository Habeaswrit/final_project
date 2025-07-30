package com.example.eventmanagement.controller;

import com.example.eventmanagement.entity.Attendee;
import com.example.eventmanagement.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping("/event/{eventId}")
    public Attendee addAttendeeToEvent(@PathVariable Long eventId, @RequestBody Attendee attendee) {
        return attendeeService.addAttendeeToEvent(eventId, attendee);
    }

    @GetMapping("/{id}")
    public Attendee getAttendeeById(@PathVariable Long id) {
        return attendeeService.getAttendeeById(id)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));
    }
}
