package shy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;
import shy.config.kafka.KafkaProcessor;
import shy.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CancelBillTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        CancelBillTest.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public BillRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        Bill entity = new Bill();

        entity.setId(1L);
        entity.setBillId("B123");
        entity.setUseYm("202101");
        entity.setUserName("John Doe");
        entity.setPhoneNumber("1234567890");
        entity.setBillAmt("100.00");
        entity.setBillState("Paid");

        repository.save(entity);

        //when:

        BillCanceled event = new BillCanceled();

        event.setPayId(1L);
        event.setBillId(1L);
        event.setUseYm("202101");
        event.setUserName("John Doe");
        event.setPayAmt(50L);
        event.setWorkType("Cancellation");

        BillApplication.applicationContext = applicationContext;

        ObjectMapper objectMapper = new ObjectMapper()
            .configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
            );
        try {
            String msg = objectMapper.writeValueAsString(event);

            processor
                .inboundTopic()
                .send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build()
                );

            //then:

            Message<String> received = (Message<String>) messageCollector
                .forChannel(processor.outboundTopic())
                .poll();

            assertNotNull("Resulted event must be published", received);

            BillCanceled outputEvent = objectMapper.readValue(
                (String) received.getPayload(),
                BillCanceled.class
            );

            LOGGER.info("Response received: {}", received.getPayload());

            assertEquals(String.valueOf(outputEvent.getId()), 1L);
            assertEquals(String.valueOf(outputEvent.getBillId()), "B123");
            assertEquals(String.valueOf(outputEvent.getUseYm()), "202101");
            assertEquals(String.valueOf(outputEvent.getUserName()), "John Doe");
            assertEquals(
                String.valueOf(outputEvent.getPhoneNumber()),
                "1234567890"
            );
            assertEquals(String.valueOf(outputEvent.getBillAmt()), "100.00");
            assertEquals(
                String.valueOf(outputEvent.getBillState()),
                "Cancelled"
            );
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            assertTrue(e.getMessage(), false);
        }
    }
}
