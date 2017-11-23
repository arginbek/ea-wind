package edu.mum.cs544.wind.controller;


import edu.mum.cs544.wind.service.AppointmentService;
import edu.mum.cs544.wind.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PersonService personService;

    @GetMapping("/persons/{personId}/sessions/{sessionId}/appointments")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public String addAppointment(@PathVariable Long personId, @PathVariable Long sessionId) {
        return appointmentService.addAppointment(personId, sessionId);
    }

    @GetMapping("/sessions/{sessionId}/appointments")
    @PreAuthorize(value = "hasAnyRole('ROLE_CUSTOMER')")
    public String addAppointment(@PathVariable Long sessionId, Principal principal) {
        String username = principal.getName();
        Long personId = personService.getPersonByUsername(username).getId();
        return appointmentService.addAppointment(personId, sessionId);
    }

    @DeleteMapping("/persons/{personId}/sessions/{sessionId}/appointments")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    public String removeAppointment(@PathVariable Long personId, @PathVariable Long sessionId) {
        return appointmentService.removeAppointment(personId, sessionId, true);
    }

    @DeleteMapping("/sessions/{sessionId}/appointments")
    @PreAuthorize(value = "hasAnyRole('ROLE_CUSTOMER')")
    public String removeAppointment(@PathVariable Long sessionId, Principal principal) {
        String username = principal.getName();
        Long personId = personService.getPersonByUsername(username).getId();
        return appointmentService.removeAppointment(personId, sessionId, false);
    }
}
