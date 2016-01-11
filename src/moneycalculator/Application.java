package moneycalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.control.ExchangeOperation;
import moneycalculator.persistence.mock.MockCurrencySetLoader;
import moneycalculator.model.CurrencySet;
import moneycalculator.swing.ApplicationFrame;
import moneycalculator.persistence.web.WebCurrencySetLoader;

public class Application {

    public static void main(String[] args) {
        CurrencySet currencySet = new WebCurrencySetLoader("currencies.txt").load();
        final ApplicationFrame frame = new ApplicationFrame(currencySet);
        frame.register("Calculate", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    new ExchangeOperation(frame.getDialog(), frame.getMoneyLabel()).execute();
                } catch (IOException ex) {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frame.register("Exit", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
            
        });
    }
    
}