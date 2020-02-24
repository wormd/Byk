package byk.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "account_cashbook_join")
public class AccountCashbookJoin {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @ManyToOne
    @JoinColumn(name = "cashbookline_id")
    @JsonBackReference
    private Cashbookline cashbookline;

    private boolean creditor;

    public AccountCashbookJoin() {

    }

    public AccountCashbookJoin(boolean creditor) {
        this.creditor = creditor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cashbookline getCashbookline() {
        return cashbookline;
    }

    public void setCashbookline(Cashbookline cashbookline) {
        this.cashbookline = cashbookline;
    }

    public boolean isCreditor() {
        return creditor;
    }

    public void setCreditor(boolean creditor) {
        this.creditor = creditor;
    }
}
