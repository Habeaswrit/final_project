package com.example.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendeeId;

    private String name;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
