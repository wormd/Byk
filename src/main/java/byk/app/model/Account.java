package byk.app.model;

import byk.app.resolver.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope=Account.class, resolver = EntityIdResolver.class)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String descr;

    private Float total = 0f;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true) //    fetch = FetchType.LAZY
    @JsonIgnore
    private Set<Transaction> transactions  = new HashSet<>();

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

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void removeTransUpdateTotal(Transaction trans) {
        Account target = trans.getTarget();
        target.removeTargetTrans(trans);
        this.total += trans.getAmount();
        this.transactions.remove(trans);
    }

    public void removeTargetTrans(Transaction trans) {
        trans.setTarget(null);
        trans.setOrigin(null);
        this.total -= trans.getAmount();
        this.transactions.remove(trans);
    }

    public void addTotal(float add) {
        this.total += add;
    }

    public void addTransaction(Transaction trans) {
        this.transactions.add(trans);
    }
}
