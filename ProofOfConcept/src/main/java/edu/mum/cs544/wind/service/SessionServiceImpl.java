package edu.mum.cs544.wind.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.repository.SessionRepository;

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
    public Session updateSession(Session session) throws Exception {
    	Session sessionUpdated = null;
    	
    	if (session.getDate().isBefore(LocalDate.now())) {
    		sessionUpdated = sessionRepository.save(session);
    	} else {
    		throw new Exception("It is not allowed to change a session that already happened.");
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
