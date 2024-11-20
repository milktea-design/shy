package shy.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shy.config.kafka.KafkaProcessor;
import shy.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    BillRepository billRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_CreateBill(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener CreateBill : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        Bill.createBill(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BillPayed'"
    )
    public void wheneverBillPayed_CompleteBill(@Payload BillPayed billPayed) {
        BillPayed event = billPayed;
        System.out.println(
            "\n\n##### listener CompleteBill : " + billPayed + "\n\n"
        );

        // Sample Logic //
        Bill.completeBill(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BillCanceled'"
    )
    public void wheneverBillCanceled_CancelBill(
        @Payload BillCanceled billCanceled
    ) {
        BillCanceled event = billCanceled;
        System.out.println(
            "\n\n##### listener CancelBill : " + billCanceled + "\n\n"
        );

        // Sample Logic //
        Bill.cancelBill(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
