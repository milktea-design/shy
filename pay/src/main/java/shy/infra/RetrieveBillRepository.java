package shy.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shy.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "retrieveBills",
    path = "retrieveBills"
)
public interface RetrieveBillRepository
    extends PagingAndSortingRepository<RetrieveBill, Long> {}
