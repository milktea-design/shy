package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PayListCreated extends AbstractEvent {

    private Long id;

    public PayListCreated(MyPage aggregate) {
        super(aggregate);
    }

    public PayListCreated() {
        super();
    }
}
//>>> DDD / Domain Event
