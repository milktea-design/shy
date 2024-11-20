package shy.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shy.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/pays")
@Transactional
public class PayController {

    @Autowired
    PayRepository payRepository;

    @RequestMapping(
        value = "/pays/pay",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Pay pay(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody PayCommand payCommand
    ) throws Exception {
        System.out.println("##### /pay/pay  called #####");
        Pay pay = new Pay();
        pay.pay(payCommand);
        payRepository.save(pay);
        return pay;
    }

    @RequestMapping(
        value = "/pays/cancel",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Pay cancel(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CancelCommand cancelCommand
    ) throws Exception {
        System.out.println("##### /pay/cancel  called #####");
        Pay pay = new Pay();
        pay.cancel(cancelCommand);
        payRepository.save(pay);
        return pay;
    }
}
//>>> Clean Arch / Inbound Adaptor
