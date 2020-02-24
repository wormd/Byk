package byk.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    private Float total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Se fosse CashbookLine sarebbe cashbook_line
    @JsonManagedReference
    private Set<AccountCashbookJoin> accountCashbookJoins  = new HashSet<>();;

    public Account() {
        this.total = 0f;
    }

    public Account(String name, String address) {
        this.name = name;
        this.address = address;
        this.total = 0f;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String number) {
        this.address = number;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Set<AccountCashbookJoin> getAccountCashbookJoins() {
        return accountCashbookJoins;
    }

    public void setAccountCashbookJoins(Set<AccountCashbookJoin> accountCashBookJoins) {
        this.accountCashbookJoins = accountCashBookJoins;
//        for (AccountCashbookJoin tmp: accountCashBookJoins) {
//            tmp.setAccount(this);
//            this.accountCashbookJoins.add(tmp);
//        }
    }
}
