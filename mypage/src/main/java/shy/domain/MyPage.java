package shy.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import shy.MypageApplication;
import shy.domain.OrderListCreated;
import shy.domain.PayListCreated;

@Entity
@Table(name = "MyPage_table")
@Data
//<<< DDD / Aggregate Root
public class MyPage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String histId;

    private String contId;

    private String workType;

    private String description;

    @PostPersist
    public void onPostPersist() {
        PayListCreated payListCreated = new PayListCreated(this);
        payListCreated.publishAfterCommit();

        OrderListCreated orderListCreated = new OrderListCreated(this);
        orderListCreated.publishAfterCommit();
    }

    public static MyPageRepository repository() {
        MyPageRepository myPageRepository = MypageApplication.applicationContext.getBean(
            MyPageRepository.class
        );
        return myPageRepository;
    }

    //<<< Clean Arch / Port Method
    public static void createPaylist(BillCanceled billCanceled) {
        //implement business logic here:

        /** Example 1:  new item 
        MyPage myPage = new MyPage();
        repository().save(myPage);

        PayListCreated payListCreated = new PayListCreated(myPage);
        payListCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(billCanceled.get???()).ifPresent(myPage->{
            
            myPage // do something
            repository().save(myPage);

            PayListCreated payListCreated = new PayListCreated(myPage);
            payListCreated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void createPaylist(BillCompleted billCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        MyPage myPage = new MyPage();
        repository().save(myPage);

        PayListCreated payListCreated = new PayListCreated(myPage);
        payListCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(billCompleted.get???()).ifPresent(myPage->{
            
            myPage // do something
            repository().save(myPage);

            PayListCreated payListCreated = new PayListCreated(myPage);
            payListCreated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void createOrderList(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        MyPage myPage = new MyPage();
        repository().save(myPage);

        OrderListCreated orderListCreated = new OrderListCreated(myPage);
        orderListCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(myPage->{
            
            myPage // do something
            repository().save(myPage);

            OrderListCreated orderListCreated = new OrderListCreated(myPage);
            orderListCreated.publishAfterCommit();

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

    public String getHistId() {
        return this.histId;
    }

    public void setHistId(String histId) {
        this.histId = histId;
    }

    public String getContId() {
        return this.contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getWorkType() {
        return this.workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
//>>> DDD / Aggregate Root
