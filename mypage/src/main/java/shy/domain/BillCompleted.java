package shy.domain;

import java.util.*;
import lombok.*;
import shy.domain.*;
import shy.infra.AbstractEvent;

@Data
@ToString
public class BillCompleted extends AbstractEvent {

    private Long id;
}
