package shy.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import shy.domain.*;

@Component
public class PayHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Pay>> {

    @Override
    public EntityModel<Pay> process(EntityModel<Pay> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/pay")
                .withRel("pay")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/cancel")
                .withRel("cancel")
        );

        return model;
    }
}
