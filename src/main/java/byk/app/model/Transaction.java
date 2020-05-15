package byk.app.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Size(max=250)
    @NonNull
    private String descr;

    @Size(max=250)
    private String rawDescr;

    private Float amount;

    @NonNull
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIdentityReference(alwaysAsId = true)
    private Account origin;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIdentityReference(alwaysAsId = true)
    private Account target;

    public Transaction() {

    }

    public Transaction(String rawDesc, float amount) {
        this.rawDescr = rawDesc;
        this.descr = rawDescr;
        this.amount = amount;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getRawDescr() {
        return rawDescr;
    }

    public void setRawDescr(String rawDescr) {
        this.rawDescr = rawDescr;
    }

    public Account getOrigin() {
        return origin;
    }

    public Transaction setOrigin(Account origin) {
        this.origin = origin;
        return this;
    }

    public Account getTarget() {
        return target;
    }

    public Transaction setTarget(Account target) {
        this.target = target;
        return this;
    }

}
