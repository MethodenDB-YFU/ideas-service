package de.yfu.intranet.ideas.api;

import de.yfu.intranet.ideas.api.resources.CommitResource;
import de.yfu.intranet.ideas.data.domain.Commit;
import de.yfu.intranet.ideas.service.CommitService;
import de.yfu.intranet.ideas.api.resources.mapper.CommitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CommitController {

    static final String IDEA_COMMIT_PATH = "/ideas/{ideaId}/commit";
    static final String IDEA_UNCOMMIT_PATH = "/ideas/{ideaId}/commit/{commitId}";
    static final String CONTENT_TYPE_COMMIT = "application/json";

    private final CommitMapper commitMapper;
    private final CommitService commitService;

    @Autowired
    public CommitController(
            final CommitService commitService,
            final CommitMapper commitMapper) {
        this.commitService = commitService;
        this.commitMapper = commitMapper;
    }

    @RequestMapping(
            method = PUT,
            value = IDEA_COMMIT_PATH,
            consumes = CONTENT_TYPE_COMMIT
    )
    public ResponseEntity<Void> commit(
            @PathVariable UUID ideaId,
            @RequestBody CommitResource commitResource) throws Exception {
        commitService.commit(ideaId, commitMapper.mapToCommit(commitResource));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            method = DELETE,
            value = IDEA_UNCOMMIT_PATH,
            consumes = CONTENT_TYPE_COMMIT
    )
    public ResponseEntity<Void> uncommit(
            @PathVariable final UUID ideaId,
            @PathVariable final UUID commitId,
            @RequestBody final CommitResource commitResource) throws Exception {
        final Commit commit = commitMapper.mapToCommit(commitResource);
        commitService.uncommit(ideaId, commitId, commit);
        return ResponseEntity.ok().build();
    }
}
