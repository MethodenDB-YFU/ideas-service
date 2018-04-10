package de.yfu.intranet.ideas.service;

import de.yfu.intranet.ideas.data.domain.Comment;
import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.data.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final IdeaService ideaService;

    @Autowired
    public CommentService(
            final CommentRepository commentRepository,
            final IdeaService ideaService) {
        this.commentRepository = commentRepository;
        this.ideaService = ideaService;
    }

    public List<Comment> comment(UUID ideaId, Comment comment) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }

        idea.addComment(comment);
        return ideaService.updateIdea(idea).getComments();
    }

    public Comment editComment(UUID ideaId, UUID commentId, Comment comment) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }
        Comment existing = commentRepository.findOneByIdeaAndId(idea, commentId);

        if (existing == null) {
            throw new Exception("No comment found for the given idea");
        }

        comment.setId(commentId);
        comment.setIdea(idea);
        return commentRepository.save(comment);
    }

    public void deleteComment(UUID ideaId, UUID commentId, Comment comment) throws Exception {
        Idea idea = ideaService.getIdea(ideaId);
        if (idea == null) {
            throw new Exception("No idea was found for the given ID");
        }

        Comment existing = commentRepository.findOneByIdeaAndId(idea, commentId);
        if(existing == null) {
            throw new Exception("No such comment found for this idea");
        }

        if (existing.getCommentBy().equals(comment.getCommentBy())) {
            commentRepository.delete(commentId);
        }
        else {
            throw new Exception("Comment wasn't made by the current user");
        }
    }
}
