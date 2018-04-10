package de.yfu.intranet.ideas.data.repository;

import de.yfu.intranet.ideas.data.domain.Commit;
import de.yfu.intranet.ideas.data.domain.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommitRepository extends CrudRepository<Commit, UUID> {

    Commit findOneByIdeaAndCommitBy(Idea idea, UUID commitBy);
}
