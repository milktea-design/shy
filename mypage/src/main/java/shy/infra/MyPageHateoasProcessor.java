package shy.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import shy.domain.*;

@Component
public class MyPageHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<MyPage>> {

    @Override
    public EntityModel<MyPage> process(EntityModel<MyPage> model) {
        return model;
    }
}
