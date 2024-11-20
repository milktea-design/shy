package shy.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import shy.domain.*;

@Component
public class BillHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Bill>> {

    @Override
    public EntityModel<Bill> process(EntityModel<Bill> model) {
        return model;
    }
}
