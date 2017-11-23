package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Session;

import java.util.List;

public interface SessionService {
    Session addSession(Session session);

    Session updateSession(Long id, Session session);

    List<Session> getAllSession();

    Session getSession(Long id);

    void removeSession(Long id);
}
