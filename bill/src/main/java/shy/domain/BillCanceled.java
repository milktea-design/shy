package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BillCanceled extends AbstractEvent {

    private Long id;

    public BillCanceled(Bill aggregate) {
        super(aggregate);
    }

    public BillCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
