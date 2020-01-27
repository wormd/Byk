package byk.app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="cashbook")
public class CashBookLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String descr;
    private String rawDescr;
    private long contoId;
    private Float vIn;
    private Float vOut;
    private Long referenceId;
    private LocalDate date;
    private LocalDate valuta;

    public CashBookLine() {

    }

    public CashBookLine(String rawDesc, long contoId, long referenceId, float in, float out) {
        this.rawDescr = rawDesc;
        this.contoId = contoId;
        this.referenceId = referenceId;
        this.vIn = in;
        this.vOut = out;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return this.descr;
    }

    public void setDesc(String setDesc) {
        this.descr = setDesc;
    }

    public String getRawDesc() {
        return this.rawDescr;
    }

    public void setRawDesc(String rawDesc) {
        this.rawDescr = rawDesc;
    }

    public long getContoId() {
        return contoId;
    }

    public void setContoId(long contoId) {
        this.contoId = contoId;
    }

    public float getIn() {
        return vIn;
    }

    public void setIn(float in) {
        this.vIn = in;
    }

    public float getOut() {
        return vOut;
    }

    public void setOut(float out) {
        this.vOut = out;
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
