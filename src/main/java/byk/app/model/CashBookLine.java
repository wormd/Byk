package byk.app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="cashbook")
public class CashBookLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String desc;
    private String rawDesc;
    private long contoId;
    private Float in;
    private Float out;
    private Long referenceId;
    private LocalDate date;
    private LocalDate valuta;

    public CashBookLine() {

    }

    public CashBookLine(String rawDesc, long contoId, long referenceId, float in, float out) {
        this.rawDesc = rawDesc;
        this.contoId = contoId;
        this.referenceId = referenceId;
        this.in = in;
        this.out = out;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String setDesc) {
        this.desc = setDesc;
    }

    public String getRawDesc() {
        return this.rawDesc;
    }

    public void setRawDesc(String rawDesc) {
        this.rawDesc = rawDesc;
    }

    public long getContoId() {
        return contoId;
    }

    public void setContoId(long contoId) {
        this.contoId = contoId;
    }

    public float getIn() {
        return in;
    }

    public void setIn(float in) {
        this.in = in;
    }

    public float getOut() {
        return out;
    }

    public void setOut(float out) {
        this.out = out;
    }

    public long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(long referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getValuta() {
        return valuta;
    }

    public void setValuta(LocalDate valuta) {
        this.valuta = valuta;
    }
}
