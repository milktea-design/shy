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
    MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BillCanceled'"
    )
    public void wheneverBillCanceled_CreatePaylist(
        @Payload BillCanceled billCanceled
    ) {
        BillCanceled event = billCanceled;
        System.out.println(
            "\n\n##### listener CreatePaylist : " + billCanceled + "\n\n"
        );

        // Sample Logic //
        MyPage.createPaylist(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BillCompleted'"
    )
    public void wheneverBillCompleted_CreatePaylist(
        @Payload BillCompleted billCompleted
    ) {
        BillCompleted event = billCompleted;
        System.out.println(
            "\n\n##### listener CreatePaylist : " + billCompleted + "\n\n"
        );

        // Sample Logic //
        MyPage.createPaylist(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_CreateOrderList(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener CreateOrderList : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        MyPage.createOrderList(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
