package moneycalculator.persistence;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public interface ExchangeRateLoader {
    public ExchangeRate load(Currency from, Currency to);
    public double calculateRate(Currency from, Currency to);
}
