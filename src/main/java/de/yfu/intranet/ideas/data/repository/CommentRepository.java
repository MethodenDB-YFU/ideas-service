package de.yfu.intranet.ideas.data.repository;

import de.yfu.intranet.ideas.data.domain.Comment;
import de.yfu.intranet.ideas.data.domain.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {

    Comment findOneByIdeaAndId(Idea idea, UUID commentId);
}
