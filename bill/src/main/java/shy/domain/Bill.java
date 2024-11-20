package shy.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import shy.BillApplication;
import shy.domain.BillCanceled;
import shy.domain.BillCompleted;
import shy.domain.BillCreated;

@Entity
@Table(name = "Bill_table")
@Data
//<<< DDD / Aggregate Root
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String billId;

    private String useYm;

    private String userName;

    private String phoneNumber;

    private String billAmt;

    private String billState;

    @PostPersist
    public void onPostPersist() {
        BillCreated billCreated = new BillCreated(this);
        billCreated.publishAfterCommit();

        BillCompleted billCompleted = new BillCompleted(this);
        billCompleted.publishAfterCommit();

        BillCanceled billCanceled = new BillCanceled(this);
        billCanceled.publishAfterCommit();
    }

    public static BillRepository repository() {
        BillRepository billRepository = BillApplication.applicationContext.getBean(
            BillRepository.class
        );
        return billRepository;
    }

    //<<< Clean Arch / Port Method
    public static void createBill(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item */
        Bill bill = new Bill();
        repository().save(bill);

        BillCreated billCreated = new BillCreated(bill);
        billCreated.publishAfterCommit();
        

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(bill->{
            
            bill // do something
            repository().save(bill);

            BillCreated billCreated = new BillCreated(bill);
            billCreated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void completeBill(BillPayed billPayed) {
        //implement business logic here:

        /** Example 1:  new item */
        Bill bill = new Bill();
        repository().save(bill);

        BillCompleted billCompleted = new BillCompleted(bill);
        billCompleted.publishAfterCommit();
        

        /** Example 2:  finding and process
        
        repository().findById(billPayed.get???()).ifPresent(bill->{
            
            bill // do something
            repository().save(bill);

            BillCompleted billCompleted = new BillCompleted(bill);
            billCompleted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancelBill(BillCanceled billCanceled) {
        //implement business logic here:

        /** Example 1:  new item */
        Bill bill = new Bill();
        repository().save(bill);

        BillCanceled billCanceled = new BillCanceled(bill);
        billCanceled.publishAfterCommit();
        

        /** Example 2:  finding and process
        
        repository().findById(billCanceled.get???()).ifPresent(bill->{
            
            bill // do something
            repository().save(bill);

            BillCanceled billCanceled = new BillCanceled(bill);
            billCanceled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillId() {
        return this.billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getUseYm() {
        return this.useYm;
    }

    public void setUseYm(String useYm) {
        this.useYm = useYm;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillAmt() {
        return this.billAmt;
    }

    public void setBillAmt(String billAmt) {
        this.billAmt = billAmt;
    }

    public String getBillState() {
        return this.billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

}
//>>> DDD / Aggregate Root
