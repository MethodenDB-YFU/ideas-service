package de.yfu.intranet.ideas.data.repository;

import de.yfu.intranet.ideas.data.domain.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, UUID> {

    Set<Idea> findAll();

}
