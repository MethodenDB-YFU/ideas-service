package de.yfu.intranet.ideas.api.resources.mapper;

import de.yfu.intranet.ideas.api.resources.CommitResource;
import de.yfu.intranet.ideas.data.domain.Commit;
import org.mapstruct.Mapper;

@Mapper
public interface CommitMapper {

    Commit mapToCommit(CommitResource commitResource);
}
