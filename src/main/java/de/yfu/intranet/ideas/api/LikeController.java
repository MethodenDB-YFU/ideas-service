package de.yfu.intranet.ideas.api;

import de.yfu.intranet.ideas.api.resources.LikeResource;
import de.yfu.intranet.ideas.data.domain.Like;
import de.yfu.intranet.ideas.service.LikeService;
import de.yfu.intranet.ideas.api.resources.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class LikeController {

    static final String IDEA_LIKE_PATH = "/ideas/{ideaId}/like";
    static final String IDEA_UNLIKE_PATH = "/ideas/{ideaId}/like/{likeId}";
    static final String CONTENT_TYPE_LIKE = "application/json";

    private final LikeMapper likeMapper;
    private final LikeService likeService;

    @Autowired
    public LikeController (
            final LikeService likeService,
            final LikeMapper likeMapper) {
        this.likeService = likeService;
        this.likeMapper = likeMapper;
    }

    @RequestMapping(
            method = PUT,
            value = IDEA_LIKE_PATH,
            consumes = CONTENT_TYPE_LIKE
    )
    public ResponseEntity<Void> like(
            @PathVariable UUID ideaId,
            @RequestBody LikeResource likeResource) throws Exception {
        likeService.like(ideaId, likeMapper.mapToLike(likeResource));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            method = DELETE,
            value = IDEA_UNLIKE_PATH,
            consumes = CONTENT_TYPE_LIKE
    )
    public ResponseEntity<Void> unlike(
            @PathVariable final UUID ideaId,
            @PathVariable final UUID likeId,
            @RequestBody final LikeResource likeResource) throws Exception {
        final Like like = likeMapper.mapToLike(likeResource);
        likeService.unlike(ideaId, likeId, like);
        return ResponseEntity.ok().build();
    }
}
