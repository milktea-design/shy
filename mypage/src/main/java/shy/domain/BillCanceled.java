package shy.domain;

import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

@Data
@ToString
public class BillCanceled extends AbstractEvent {

    private Long id;
}
