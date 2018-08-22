package uk.dioxic.ccpt.model;


import uk.dioxic.ccpt.annotations.CardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @CardNumber
    private Integer number;

    @NotEmpty
    private String name;

    @NotNull
    private BigDecimal balance;

    @NotNull
    @Column(name = "cardLimit")
    private BigDecimal limit;

    public Card(long id, @NotNull Integer number, @NotEmpty String name, @NotNull BigDecimal balance, @NotNull BigDecimal limit) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.balance = balance;
        this.limit = limit;
    }

    public Card() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", limit=" + limit +
                '}';
    }
}
