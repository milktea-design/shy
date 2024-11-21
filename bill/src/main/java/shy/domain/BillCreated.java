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
    private String billId;
    private String useYm;
    private String userName;
    private String phoneNumber;
    private String billAmt;
    private String billState;

    public BillCreated(Bill aggregate) {
        super(aggregate);
    }

    public BillCreated() {
        super();
    }
}
//>>> DDD / Domain Event
