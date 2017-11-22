package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.exception.UpdatePastSessionException;

import java.util.List;

public interface SessionService {
    Session addSession(Session session);

    Session updateSession(Session session) throws UpdatePastSessionException;

    List<Session> getAllSession();

    Session getSession(Long sessionId);

    void removeSession(Long sessionId);
}
