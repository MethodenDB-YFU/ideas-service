package de.yfu.intranet.ideas.api;

import de.yfu.intranet.ideas.api.resources.IdeaResource;
import de.yfu.intranet.ideas.api.resources.mapper.IdeaMapper;
import de.yfu.intranet.ideas.data.domain.Idea;
import de.yfu.intranet.ideas.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class IdeaController {

    static final String IDEA_PATH = "/ideas";
    static final String CONTENT_TYPE_IDEA = "application/json";

    private final IdeaMapper ideaMapper;
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(
            final IdeaMapper ideaMapper,
            final IdeaService ideaService) {
        this.ideaMapper = ideaMapper;
        this.ideaService = ideaService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = IDEA_PATH,
            produces = CONTENT_TYPE_IDEA
    )
    public Set<IdeaResource> getAllIdeas() {
        return ideaMapper.mapToResource(ideaService.getAll());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = IDEA_PATH + "/{ideaId}",
            produces = CONTENT_TYPE_IDEA
    )
    public IdeaResource getIdea(@PathVariable UUID ideaId) {
        return ideaMapper.mapToResource(ideaService.getIdea(ideaId));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = IDEA_PATH,
            consumes = CONTENT_TYPE_IDEA,
            produces = CONTENT_TYPE_IDEA
    )
    public ResponseEntity<IdeaResource> postIdea(
            @RequestBody final IdeaResource ideaResource) {
        Idea idea = ideaMapper.mapToIdea(ideaResource);
        Idea createdIdea = ideaService.createIdea(idea);
        return new ResponseEntity<>(ideaMapper.mapToResource(createdIdea), HttpStatus.CREATED);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            value = IDEA_PATH + "/{ideaId}",
            produces = CONTENT_TYPE_IDEA,
            consumes = CONTENT_TYPE_IDEA
    )
    public ResponseEntity<IdeaResource> updateIdea(
            @PathVariable UUID ideaId,
            @RequestBody IdeaResource ideaResource) {
        ideaResource.setId(ideaId);
        final Idea idea = ideaMapper.mapToIdea(ideaResource);
        final Idea updatedIdea = ideaService.updateIdea(idea);
        return new ResponseEntity<IdeaResource>(ideaMapper.mapToResource(updatedIdea), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = IDEA_PATH + "/{ideaId}"
    )
    public ResponseEntity<Void> deleteIdea(@PathVariable UUID ideaId) throws Exception {
        ideaService.deleteIdea(ideaId);
        return ResponseEntity.noContent().build();
    }
}
