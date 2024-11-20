package shy.domain;

import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

@Data
@ToString
public class BillPayed extends AbstractEvent {

    private Long payId;
    private Long billId;
    private String useYm;
    private String userName;
    private Object payAmt;
    private String workType;
}
