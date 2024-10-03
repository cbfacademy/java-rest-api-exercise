import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ious")
public class IOU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    

    private UUID id;
    private String borrower;
    private String lender;
    private BigDecimal amount;
    private Instant dateTime;

    public IOU(String lender, String borrower, BigDecimal amount, Instant dateTime) {
        this.lender = lender;
        this.borrower =borrower;
        this.amount =amount;
        this.dateTime = dateTime;
    }


    public UUID getId(){
        return id;
    } 

    public String getBorrower(){
        return borrower;
    } 

    public void setBorrower(String borrower){
        this.borrower = borrower;
    } 

    public String getLender(){
        return lender;
    } 

    public void setLender(String lender){
        this.lender = lender;
    } 

    public BigDecimal getAmount(){
        return amount;
    } 

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    } 

    public Instant getDateTime(){
        return dateTime;
    }

    public void setDateTime(Instant dateTime){
        this.dateTime = dateTime;
    } 
}
