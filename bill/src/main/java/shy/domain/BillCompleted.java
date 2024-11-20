package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BillCompleted extends AbstractEvent {

    private Long id;

    public BillCompleted(Bill aggregate) {
        super(aggregate);
    }

    public BillCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
