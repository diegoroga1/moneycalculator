package moneycalculator.model;

public class Exchange {
    
    public final Money money;
    public final Currency currency;

    public Exchange(Money money, Currency currency) {
        this.money = money;
        this.currency = currency;
    }

    public Money getMoney() {
        return money;
    }

    public Currency getCurrency() {
        return currency;
    }
    
}