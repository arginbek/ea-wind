package edu.mum.cs544.wind.repository;

import edu.mum.cs544.wind.domain.Session;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SessionRepository extends CrudRepository<Session, Long> {
}
