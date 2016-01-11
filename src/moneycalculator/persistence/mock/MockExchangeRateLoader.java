package moneycalculator.persistence.mock;

import java.util.ArrayList;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    ArrayList<String> currency;
    ArrayList<Double> rate;
    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        setExchangeRate();
        return new ExchangeRate(from,to,calculateRate(from,to));
    }

    private void setExchangeRate() {
        addCurrency();
        addRate();
    }

    private void addCurrency() {
        currency = new ArrayList<>();
        currency.add("ARS");
        currency.add("AUS");
        currency.add("BRL");
        currency.add("BZD");
        currency.add("CAD");
        currency.add("CHF");
        currency.add("CNY");
        currency.add("EUR");
        currency.add("GBP");
        currency.add("JPY");
        currency.add("USD");
        currency.add("ZAR");
    }

    private void addRate() {
        rate = new ArrayList<>();
        rate.add(10.6141189);
        rate.add(1.46328341);
        rate.add(3.20413287);
        rate.add(2.482);
        rate.add(1.42159181);
        rate.add(1.20168211);
        rate.add(7.5);
        rate.add(1.0);
        rate.add(0.79610815);
        rate.add(147.0);
        rate.add(1.244325);
        rate.add(13.79);
        
    }

    public double calculateRate(Currency from, Currency to) {
        return (1/rate.get(currency.indexOf(from.getCode()))) * rate.get(currency.indexOf(to.getCode()));
    }
    
}
