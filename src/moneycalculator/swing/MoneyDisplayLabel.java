package moneycalculator.swing;

import java.awt.Color;
import javax.swing.JLabel;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDisplay;

class MoneyDisplayLabel extends JLabel implements MoneyDisplay {

    public MoneyDisplayLabel() {
        super();
        this.setBackground(Color.lightGray);

    }

    @Override
    public void reset() {
        super.setText("");
    }

    @Override
    public void showMoney(Money money) {
        if(writeMoney(money)!= null) {
            super.setText("Result: " + writeMoney(money));
        } else {
            super.setText("Please, write only numeric characters and an amount greater than zero...");
        }
    }
    
    private String writeMoney(Money money) {
       if ( money.getAmount() == 0.0) {
           return null;
       }
       String amount = String.valueOf(money.getAmount()) + "0";
       if(String.valueOf(money.getAmount()).contains(".") && money.getAmount() != 0.0) {
            System.out.println(amount.substring(0, String.valueOf(money.getAmount()).indexOf(".") + 3)
            + " " + money.getCurrency().getCode());
            return(amount.substring(0, String.valueOf(money.getAmount()).indexOf(".") + 3)
            + " " + money.getCurrency().getCode());
        } else {
            return (amount + "0 " + money.getCurrency().getCode());
        }
    }
    
}
