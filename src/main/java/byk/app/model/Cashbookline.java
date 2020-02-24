package byk.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cashbookline {

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<AccountCashbookJoin> accountCashbookJoins = new HashSet<>();

    public Cashbookline() {

    }

    public Cashbookline(String rawDesc, float amount) {
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

    public void setDate(LocalDate valuta) {
        this.date = valuta;
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

    public Set<AccountCashbookJoin> getAccountCashbookJoins() {
        return accountCashbookJoins;
    }

    public void setAccountCashbookJoins(Set<AccountCashbookJoin> accountCashbookJoins) {
//        for (AccountCashbookJoin tmp: accountCashbookJoins) {
//            tmp.setCashbookline(this);
//        }
        this.accountCashbookJoins = accountCashbookJoins;
    }
}
