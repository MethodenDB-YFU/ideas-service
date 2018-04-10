package de.yfu.intranet.ideas.data.repository;

import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.data.domain.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepository extends CrudRepository<Like, UUID> {

    Like findOneByIdeaAndLikeBy(Idea idea, UUID likeBy);
}
