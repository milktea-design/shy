package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BillCreated extends AbstractEvent {

    private Long id;

    public BillCreated(Bill aggregate) {
        super(aggregate);
    }

    public BillCreated() {
        super();
    }
}
//>>> DDD / Domain Event
