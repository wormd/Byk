package byk.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime created;
    private String name;
    private String invoice_name;
    private String invoice_addr;

    public Client() {
        created = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

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

    public String getInvoice_name() {
        return invoice_name;
    }

    public void setInvoice_name(String invoice_name) {
        this.invoice_name = invoice_name;
    }

    public String getInvoice_addr() {
        return invoice_addr;
    }

    public void setInvoice_addr(String invoice_addr) {
        this.invoice_addr = invoice_addr;
    }
}
