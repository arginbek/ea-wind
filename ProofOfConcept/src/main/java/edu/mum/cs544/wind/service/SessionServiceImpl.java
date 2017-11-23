package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.exception.SessionCreatePastException;
import edu.mum.cs544.wind.exception.SessionDeletePastException;
import edu.mum.cs544.wind.exception.SessionNotFoundException;
import edu.mum.cs544.wind.exception.SessionUpdatePastException;
import edu.mum.cs544.wind.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public Session addSession(Session session) {
        if (session.getDate().isEqual(LocalDate.now()) || session.getDate().isAfter(LocalDate.now())) {
            session = sessionRepository.save(session);
        } else {
            throw new SessionCreatePastException("It is not allowed to create a past session.");
        }

        return session;
    }

    @Override
    public Session updateSession(Long id, Session session) {
        Session sessionPersisted = sessionRepository.findOne(id);

        if (sessionPersisted != null) {
            if (sessionPersisted.getDate().isEqual(LocalDate.now()) || sessionPersisted.getDate().isAfter(LocalDate.now())) {
                sessionPersisted = sessionRepository.save(session);
            } else {
                throw new SessionUpdatePastException("It is not allowed to update a past session.");
            }
        } else {
            throw new SessionNotFoundException("Session not found.");
        }

        return sessionPersisted;
    }

    @Override
    public List<Session> getAllSession() {
        return (List<Session>) sessionRepository.findAll();
    }

    @Override
    public Session getSession(Long id) {
        return sessionRepository.findOne(id);
    }

    @Override
    public void removeSession(Long id) {
        Session sessionPersisted = sessionRepository.findOne(id);

        if (sessionPersisted != null) {
            if (sessionPersisted.getDate().isEqual(LocalDate.now()) || sessionPersisted.getDate().isAfter(LocalDate.now())) {
                sessionRepository.delete(id);
            } else {
                throw new SessionDeletePastException("It is not allowed to create a past session.");
            }
        } else {
            throw new SessionNotFoundException("Session not found.");
        }
    }
}
