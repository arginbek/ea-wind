package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Session;

import java.util.List;

public interface SessionService {
    Session addSession(Session session);

    Session updateSession(Session session) throws Exception;

    List<Session> getAllSession();

    Session getSession(Long sessionId);

    void removeSession(Long sessionId);
}
