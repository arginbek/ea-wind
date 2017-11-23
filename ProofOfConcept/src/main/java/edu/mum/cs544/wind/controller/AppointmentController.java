package edu.mum.cs544.wind.controller;


import edu.mum.cs544.wind.service.AppointmentService;
import edu.mum.cs544.wind.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PersonService personService;

    @PostMapping("/appointments/persons/{personId}/sessions/{sessionId}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public String addAppointment(@PathVariable Long personId, @PathVariable Long sessionId) {
        return appointmentService.addAppointment(personId, sessionId);
    }

    @PostMapping("/appointments/sessions/{sessionId}")
    @PreAuthorize(value = "hasAnyRole('ROLE_CUSTOMER')")
    public String addAppointment(@PathVariable Long sessionId, Principal principal) {
        String username = principal.getName();
        Long personId = personService.getPersonByUsername(username).getId();
        return appointmentService.addAppointment(personId, sessionId);
    }

    @DeleteMapping("/appointments/persons/{personId}/sessions/{sessionId}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public String removeAppointment(@PathVariable Long personId, @PathVariable Long sessionId) {
        return appointmentService.removeAppointment(personId, sessionId, true);
    }

    @DeleteMapping("/appointments/sessions/{sessionId}")
    @PreAuthorize(value = "hasAnyRole('ROLE_CUSTOMER')")
    public String removeAppointment(@PathVariable Long sessionId, Principal principal) {
        String username = principal.getName();
        Long personId = personService.getPersonByUsername(username).getId();
        return appointmentService.removeAppointment(personId, sessionId, false);
    }
}
