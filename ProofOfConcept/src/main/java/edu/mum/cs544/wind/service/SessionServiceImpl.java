package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.exception.UpdatePastSessionException;
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
    @Autowired
    PersonService personService;

    @Override
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session updateSession(Session session) throws UpdatePastSessionException {
        Session sessionUpdated = null;

        if (session.getDate().isBefore(LocalDate.now())) {
            sessionUpdated = sessionRepository.save(session);
        } else {
            throw new UpdatePastSessionException("It is not allowed to change a session that already happened.");
        }

        return sessionUpdated;
    }

    @Override
    public List<Session> getAllSession() {
        return (List<Session>) sessionRepository.findAll();
    }

    @Override
    public Session getSession(Long sessionId) {
        return sessionRepository.findOne(sessionId);
    }

    @Override
    public void removeSession(Long sessionId) {
        sessionRepository.delete(sessionId);
    }
}
