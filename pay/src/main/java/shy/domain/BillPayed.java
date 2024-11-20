package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BillPayed extends AbstractEvent {

    private Long payId;
    private Long billId;
    private String useYm;
    private String userName;
    private Money payAmt;
    private String workType;

    public BillPayed(Pay aggregate) {
        super(aggregate);
    }

    public BillPayed() {
        super();
    }
}
//>>> DDD / Domain Event
