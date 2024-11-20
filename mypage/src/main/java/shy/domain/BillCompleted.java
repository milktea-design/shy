package shy.domain;

import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

@Data
@ToString
public class BillCompleted extends AbstractEvent {

    private Long id;
    private String billId;
    private String useYm;
    private String userName;
    private String phoneNumber;
    private String billAmt;
    private String billState;
}
