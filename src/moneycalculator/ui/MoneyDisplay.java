package moneycalculator.ui;

import moneycalculator.model.Money;

public interface MoneyDisplay {
    public void reset();
    public void showMoney(Money money);
}
