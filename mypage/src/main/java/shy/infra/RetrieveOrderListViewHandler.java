package shy.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shy.config.kafka.KafkaProcessor;
import shy.domain.*;

@Service
public class RetrieveOrderListViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private RetrieveOrderListRepository retrieveOrderListRepository;
    //>>> DDD / CQRS
}
