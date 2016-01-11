package moneycalculator.persistence.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

public class CurrencyReaderExchangeRateLoader implements ExchangeRateLoader {
    HashMap<String,Double> rates;

    public CurrencyReaderExchangeRateLoader() throws UnsupportedEncodingException, IOException {
        rates = new HashMap<>();
        setRates();
    }
    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(from,to,calculateRate(from,to));
    }

    @Override
    public double calculateRate(Currency from, Currency to) {
         return (1/rates.get(from.getCode())) * rates.get(to.getCode());
    }

    private void setRates() throws MalformedURLException, UnsupportedEncodingException, IOException {
        URL url = new URL("http://api.fixer.io/latest");
        String rate = "";
        HashMap<String,Double> rates2 = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {rate = line;};
            rate = rate.substring(42);
            String aux = "";
            boolean firstTime = false;
            for (int i = 0; i < rate.length(); i++) {
                if(rate.charAt(i) == '"' && !firstTime) {
                    int index = rate.indexOf(',', i);
                    if (index >= 0) {
                        aux = rate.substring(i,rate.indexOf(',', i));
                        this.rates.put(aux.substring(1,4), Double.parseDouble(aux.substring(6)));
                        firstTime = true;
                    } else {
                        aux = rate.substring(i,rate.indexOf('}', i));
                        this.rates.put(aux.substring(1,4), Double.parseDouble(aux.substring(6)));
                        this.rates.put("EUR", 1.0);
                        break;
                    }
                } else {
                    if (rate.charAt(i) == '"') {
                        firstTime = false;
                    }
                }
            }
        }
    }
}
