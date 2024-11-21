package shy.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceOrderCommand {

    private Long id;
    private String phoneNumber;
    private String userName;
    private String orderId;
    private OrderStatus status;
}
