package de.yfu.intranet.ideas.api.resources.mapper;

import de.yfu.intranet.ideas.api.resources.CommentResource;
import de.yfu.intranet.ideas.data.domain.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> mapToComment(List<CommentResource> commentResources);
    List<CommentResource> mapToResource(List<Comment> comments);
    Comment mapToComment(CommentResource commentResource);
    CommentResource mapToResource(Comment comment);
}
