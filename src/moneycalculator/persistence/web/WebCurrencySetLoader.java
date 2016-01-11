package moneycalculator.persistence.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;
import moneycalculator.persistence.CurrencySetLoader;

public class WebCurrencySetLoader implements CurrencySetLoader {

    CurrencySet currencySet;
    String path;

    public WebCurrencySetLoader(String path) {
        currencySet = new CurrencySet();
        this.path = path;
    }

    @Override
    public CurrencySet load() {
        readCurrencies(path);
        return currencySet;
    }

    private void readCurrencies(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.length() == 1) {
                    continue;
                }
                this.currencySet.add(new Currency(line.substring(0, 3),line.substring(4, line.indexOf(',', 4)),line.substring(line.lastIndexOf(',') + 1)));
            }
        } catch (IOException ex) {
        }
    }

}
