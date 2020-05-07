package byk.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String descr;

    private Float total;

    @OneToMany(cascade = CascadeType.ALL) //    fetch = FetchType.LAZY
    @JsonManagedReference
    private Set<Transaction> transactions  = new HashSet<>();;

    public Account() {
        this.total = 0f;
    }

    public Account(String name, String descr) {
        this.name = name;
        this.descr = descr;
        this.total = 0f;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> _transactions) {
        this.transactions = _transactions;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
