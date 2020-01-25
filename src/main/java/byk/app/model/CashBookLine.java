package byk.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CashBookLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String desc;
    private String rawDesc;
    private long contoId;
    private float in;
    private float out;
    private long referenceId;
    private Date date;
    private Date valuta;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getValuta() {
        return valuta;
    }

    public void setValuta(Date valuta) {
        this.valuta = valuta;
    }
}
