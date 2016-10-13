package de.fsmpi.data;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Marcel Fraas on 30.08.16.
 */

@Entity
public class Accounting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Date timestamp;

    @Column
    @Lob
    private byte[] table;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getTable() {
        return this.table;
    }

    public void setTable(byte[] table) {
        this.table = table;
    }

    public Accounting(Date timestamp, byte[] table) {
        this.timestamp = timestamp;
        this.table = table;
    }

    public Accounting() {}      // why JPA

    @Override
    public String toString() {
        return "Accounting{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }
}
