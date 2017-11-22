package edu.mum.cs544.wind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs544.wind.service.AppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/persons/{personId}/sessions/{sessionId}/appointments")
	private String addAppointment(@PathVariable Long personId, @PathVariable Long sessionId) {
		return appointmentService.addAppointment(personId, sessionId);
	}
}
