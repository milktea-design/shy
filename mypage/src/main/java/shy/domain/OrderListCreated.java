package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderListCreated extends AbstractEvent {

    private Long id;

    public OrderListCreated(MyPage aggregate) {
        super(aggregate);
    }

    public OrderListCreated() {
        super();
    }
}
//>>> DDD / Domain Event
