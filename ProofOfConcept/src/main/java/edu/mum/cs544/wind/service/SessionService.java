package edu.mum.cs544.wind.service;

import java.util.List;

import edu.mum.cs544.wind.domain.Session;

public interface SessionService {
    Session addSession(Session session);
	Session updateSession(Session session);
	List<Session> getAllSession();
	Session getSession(Long sessionId);
	void removeSession(Long sessionId);
}