package byk.app.model;

import byk.app.model.exception.TooEarlyException;
import byk.app.resolver.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "origin") //    fetch = FetchType.LAZY
    @JsonIgnore
    private Set<Transaction> originTransactions  = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "target")
    @JsonIgnore
    private Set<Transaction> targetTransactions  = new HashSet<>();

    private LocalDateTime created;

    @JsonIgnore
    private LocalDateTime refreshed;

    public Account() {
        this.total = 0f;
        this.refreshed = LocalDateTime.now();
        this.created = LocalDateTime.now();
    }

    public Account(String name, String descr) {
        this();
        this.name = name;
        this.descr = descr;
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

    public Set<Transaction> getOriginTransactions() {
        return originTransactions;
    }

    public void setOriginTransactions(Set<Transaction> originTransactions) {
        this.originTransactions = originTransactions;
    }

    public Set<Transaction> getTargetTransactions() {
        return targetTransactions;
    }

    public void setTargetTransactions(Set<Transaction> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }

    public LocalDateTime getRefreshed() {
        return refreshed;
    }

    public void setRefreshed(LocalDateTime refreshed) {
        this.refreshed = refreshed;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void removeOriginTransaction(Transaction trans) {
        trans.setOrigin(null);
        this.total += trans.getAmount();
        this.originTransactions.remove(trans);
    }

    public void removeTargetTransaction(Transaction trans) {
        trans.setTarget(null);
        this.total -= trans.getAmount();
        this.targetTransactions.remove(trans);
    }

    public void addTotal(float add) {
        this.total += add;
    }

    public void addOriginTransaction(Transaction trans) {
        this.originTransactions.add(trans);
    }

    public void addTargetTransaction(Transaction trans) {
        this.targetTransactions.add(trans);
    }

    public void refreshTotal() {
        if (this.refreshed.plusMinutes(10).isAfter(LocalDateTime.now())) {
            throw new TooEarlyException("Refreshing too fast");
        }
        float newTotal = 0f;
        for(Transaction trans: this.originTransactions) {
            newTotal -= trans.getAmount();
        }
        for(Transaction trans: this.targetTransactions) {
            newTotal += trans.getAmount();
        }
        setTotal(newTotal);
        this.refreshed = LocalDateTime.now();
    }
}
