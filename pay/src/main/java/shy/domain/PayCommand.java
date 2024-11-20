package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PayCommand {

    private Long payId;
    private Long billId;
    private String useYm;
    private String userName;
    private Money payAmt;
    private String workType;
}
