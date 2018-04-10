package de.yfu.intranet.ideas.api;

import de.yfu.intranet.ideas.api.resources.CommentResource;
import de.yfu.intranet.ideas.api.resources.mapper.CommentMapper;
import de.yfu.intranet.ideas.data.domain.Comment;
import de.yfu.intranet.ideas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CommentController {

    static final String COMMENT_PATH = "/ideas/{ideaId}/comment";
    static final String CONTENT_TYPE_COMMENT = "application/json";

    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @Autowired
    public CommentController(
            final CommentMapper commentMapper,
            final CommentService commentService) {
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @RequestMapping(
            method = POST,
            value = COMMENT_PATH,
            consumes = CONTENT_TYPE_COMMENT,
            produces = CONTENT_TYPE_COMMENT
    )
    public ResponseEntity<List<CommentResource>> postComment(
            @PathVariable UUID ideaId,
            @RequestBody CommentResource commentResource) throws Exception {
        final Comment comment = commentMapper.mapToComment(commentResource);
        final List<Comment> createdComments = commentService.comment(ideaId, comment);
        return new ResponseEntity<>(commentMapper.mapToResource(createdComments), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = PUT,
            value = COMMENT_PATH + "/{commentId}",
            consumes = CONTENT_TYPE_COMMENT,
            produces = CONTENT_TYPE_COMMENT
    )
    public ResponseEntity<CommentResource> editComment(
            @PathVariable UUID ideaId,
            @PathVariable UUID commentId,
            @RequestBody CommentResource commentResource) throws Exception {
        final Comment comment = commentMapper.mapToComment(commentResource);
        final Comment updatedComment = commentService.editComment(ideaId, commentId, comment);
        return new ResponseEntity<>(commentMapper.mapToResource(updatedComment), HttpStatus.OK);
    }

    @RequestMapping(
            method = DELETE,
            value = COMMENT_PATH + "/{commentId}"
    )
    public ResponseEntity<Void> deleteComment(
            @PathVariable final UUID ideaId,
            @PathVariable final UUID commentId,
            @RequestBody final CommentResource commentResource) throws Exception {
        final Comment comment = commentMapper.mapToComment(commentResource);
        commentService.deleteComment(ideaId, commentId, comment);
        return ResponseEntity.noContent().build();
    }
}
