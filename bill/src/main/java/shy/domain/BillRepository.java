package shy.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shy.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "bills", path = "bills")
public interface BillRepository
    extends PagingAndSortingRepository<Bill, Long> {}
