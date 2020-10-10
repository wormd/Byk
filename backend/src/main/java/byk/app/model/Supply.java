package byk.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Supply {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Float mCost;
    private Integer payEverys;
    private LocalDate payDate;
    @OneToMany
    private List<Payment> payments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getmCost() {
        return mCost;
    }

    public void setmCost(Float mCost) {
        this.mCost = mCost;
    }

    public Integer getPayEverys() {
        return payEverys;
    }

    public void setPayEverys(Integer payEverys) {
        this.payEverys = payEverys;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
