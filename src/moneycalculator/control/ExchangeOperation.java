package moneycalculator.control;

import java.io.IOException;
import moneycalculator.persistence.mock.MockExchangeRateLoader;
import moneycalculator.model.Currency;
import moneycalculator.model.Exchange;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.process.Exchanger;
import moneycalculator.ui.ExchangeDialog;
import moneycalculator.ui.MoneyDisplay;
import moneycalculator.persistence.web.CurrencyReaderExchangeRateLoader;

public class ExchangeOperation {
    
    private final ExchangeDialog dialog;
    private final MoneyDisplay money;

    public ExchangeOperation(ExchangeDialog dialog, MoneyDisplay money) {
        this.dialog = dialog;
        this.money = money;
    }
    
    public void execute() throws IOException {
        Exchange exchange = readExchange();
        ExchangeRate exchangeRate = readExchangeRate(exchange.getMoney().getCurrency(), exchange.getCurrency());
        Money money = exchangeMoney(exchange.getMoney(),exchangeRate);
        this.money.showMoney(money);
    }

    private Exchange readExchange() {
        return dialog.getExchange();
    }
    
     private ExchangeRate readExchangeRate(Currency from, Currency to) throws IOException {
        return new CurrencyReaderExchangeRateLoader().load(from,to);
    }
    
    private Money exchangeMoney(Money money, ExchangeRate exchangeRate) {
        return new Exchanger(money,exchangeRate).calculate();
    }

}
