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
        value = "/pays/{id}/pay",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Pay pay(
        @PathVariable(value = "id") Long id,
        @RequestBody PayCommand payCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pay/pay  called #####");
        Optional<Pay> optionalPay = payRepository.findById(id);

        optionalPay.orElseThrow(() -> new Exception("No Entity Found"));
        Pay pay = optionalPay.get();
        pay.pay(payCommand);

        payRepository.save(pay);
        return pay;
    }

    @RequestMapping(
        value = "/pays/{id}/cancel",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Pay cancel(
        @PathVariable(value = "id") Long id,
        @RequestBody CancelCommand cancelCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pay/cancel  called #####");
        Optional<Pay> optionalPay = payRepository.findById(id);

        optionalPay.orElseThrow(() -> new Exception("No Entity Found"));
        Pay pay = optionalPay.get();
        pay.cancel(cancelCommand);

        payRepository.save(pay);
        return pay;
    }
}
//>>> Clean Arch / Inbound Adaptor
