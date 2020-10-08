package byk.app.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;
    private String descr;
    private LocalDateTime created;
    private LocalDateTime dueBy;
    private LocalDateTime doneDate;
    private Boolean cycle;
    private Boolean done;
    private long cycletime;

    @OneToOne
    private Payment payment;

    public Task() {
        this.setCreated(LocalDateTime.now());
        this.cycle = false;
        this.done = false;
    }

    public Task(Task copyFrom) {
        this();
        this.descr = copyFrom.getDescr();
        // should the created date be from parent reminder or not?
        this.created = copyFrom.getCreated();
        this.cycle = copyFrom.getCycle();
        this.cycletime = copyFrom.getCycletime();
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public LocalDateTime getDueBy() {
        return dueBy;
    }

    public void setDueBy(LocalDateTime dueBy) {
        this.dueBy = dueBy;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Boolean getCycle() {
        return cycle;
    }

    public void setCycle(Boolean cycle) {
        this.cycle = cycle;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void done() {
        this.setDone(true);
        this.setDoneDate(LocalDateTime.now());
    }

    public void undone() {
        this.setDone(false);
        this.setDoneDate(null);
    }

    public long getCycletime() {
        return cycletime;
    }

    public void setCycletime(long cycletime) {
        this.cycletime = cycletime;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }
}
