package moneycalculator.swing;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import moneycalculator.model.CurrencySet;
import moneycalculator.ui.ExchangeDialog;
import moneycalculator.ui.MoneyDisplay;

public class ApplicationFrame extends JFrame {
    
    private final CurrencySet currencySet;
    private final Map<String,ActionListener> listeners;
    private ExchangeDialog exchangeDialog;
    private MoneyDisplay moneyDisplay;
    
    public ApplicationFrame(CurrencySet currencySet) {
        super();
        this.setResizable(false);
        this.currencySet = currencySet;
        this.listeners = new HashMap<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,120);
        this.setTitle("Money Calculator");
        this.setLocationRelativeTo(null);
        this.createWidgets();
        this.setVisible(true);
        ApplicationFrame.this.add(createMoneyDisplay(),NORMAL);
        
    }

    public void register(String command, ActionListener actionListener) {
        this.listeners.put(command, actionListener);
    }

    public ExchangeDialog getDialog() {
        return exchangeDialog;
    }
    
    public MoneyDisplay getMoneyLabel() {
        return moneyDisplay;
    }

    private void createWidgets() {
        this.add(createToolbarPanel(),BorderLayout.CENTER);
        this.add(createDialog(),BorderLayout.NORTH);
    }
    private JPanel createToolbarPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createCalculateButton());
        panel.add(createExitButton());
        return panel;
    }

    private JButton createCalculateButton() {
        JButton button = new JButton("Calculate");
        
        button.addActionListener(createListener("Calculate"));
        return button;
    }
    private JButton createExitButton(){
        JButton button = new JButton("Exit");
        button.addActionListener(createListener("Exit"));
        return button;
    }

    private ActionListener createListener(final String text) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                listeners.get(text).actionPerformed(event);
                ApplicationFrame.this.exchangeDialog.reset();
            }
        };
    }

    private JPanel createDialog() {
        ExchangeDialogPanel panel = new ExchangeDialogPanel(currencySet);
        this.exchangeDialog = panel;
        return panel;
    }

    private JLabel createMoneyDisplay() {
        MoneyDisplayLabel moneyLabel = new MoneyDisplayLabel();
        this.moneyDisplay = moneyLabel;
        return moneyLabel;
    }

}