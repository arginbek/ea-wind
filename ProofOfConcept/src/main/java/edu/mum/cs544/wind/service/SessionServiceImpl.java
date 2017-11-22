package edu.mum.cs544.wind.service;

import edu.mum.cs544.wind.domain.Person;
import edu.mum.cs544.wind.domain.Session;
import edu.mum.cs544.wind.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        long id = session.getCounselor().getId();
        Person person = personService.getPerson(id);
        session.setCounselor(person);
        return sessionRepository.save(session);
    }

    @Override
    public Session updateSession(Session session) {
        /*
         * Validate date.
    	if (session.getDate() ) {}
    	*/
        return sessionRepository.save(session);
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
