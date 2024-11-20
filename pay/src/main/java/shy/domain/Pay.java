package shy.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import shy.PayApplication;

@Entity
@Table(name = "Pay_table")
@Data
//<<< DDD / Aggregate Root
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String payId;

    private String billId;

    private String useYm;

    private String userName;

    private String payAmt;

    private String workType;

    @PrePersist
    public void onPrePersist() {}

    public static PayRepository repository() {
        PayRepository payRepository = PayApplication.applicationContext.getBean(
            PayRepository.class
        );
        return payRepository;
    }

    //<<< Clean Arch / Port Method
    public void pay(PayCommand payCommand) {
        //implement business logic here:

        BillPayed billPayed = new BillPayed(this);
        billPayed.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cancel(CancelCommand cancelCommand) {
        //implement business logic here:

        BillCanceled billCanceled = new BillCanceled(this);
        billCanceled.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
