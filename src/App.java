
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App {

    static CurrencyService service = new CurrencyService();

    @SafeVarargs
    public static void refreshDropdowns(JComboBox<String>... dropdowns) {
        for (JComboBox<String> dd : dropdowns) {
            dd.removeAllItems(); // leeren

            for (String currency : service.getCurrencies()) {
                dd.addItem(currency);
            }
        }
    }

    public static void main(String[] args) {
        //### define Containers 
        JFrame win = new JFrame("Currency Calculator");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //win.setSize(400, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel convertSection = new JPanel();
        JPanel addSection = new JPanel();
        JPanel tableSection = new JPanel();
        JPanel editSection = new JPanel();
        tableSection.setLayout(new GridLayout(1, 3));
        addSection.setLayout(new BorderLayout());

        //### define Components
        JTextField inputValue = new JTextField(); // Input for converting currency
        JTextField newCurrencyValueInput = new JTextField(); // Input for 
        JTextField updateCurrencyValueInput = new JTextField();
        JTextField newCurrencyNameInput = new JTextField();
        JTextArea outputValue = new JTextArea(); // Displays the result
        JButton updateCurrency = new JButton("UPDATE"); // triggers the change currency
        JButton show = new JButton("SHOW");
        JButton addCurrency = new JButton("ADD");
        JComboBox<String> ddInput = new JComboBox<>(); //Dropdown for input currency
        JComboBox<String> ddTarget = new JComboBox<>(); //Dropdown for output currency
        JComboBox<String> ddEdit = new JComboBox<>(); //Dropdown for adding or editing currency
        JComboBox<String> ddTableSelector = new JComboBox<>();

        refreshDropdowns(ddInput, ddTarget, ddEdit, ddTableSelector);
        //### add Components and define position
        // Build convertSection
        convertSection.setLayout(new GridLayout(3, 3));

        convertSection.add(new JLabel("INPUT"));
        convertSection.add(new JLabel()); // EMPTY
        convertSection.add(new JLabel("OUTPUT"));

        convertSection.add(inputValue);
        convertSection.add(new JLabel());
        convertSection.add(outputValue);

        convertSection.add(ddInput);
        convertSection.add(new JLabel()); // EMPTY
        convertSection.add(ddTarget);

        // Build addSection
        editSection.setLayout(new GridLayout(7, 3));

        editSection.add(new JLabel()); // EMPTY
        editSection.add(new JLabel("Change Currency"));
        editSection.add(new JLabel()); // EMPTY

        editSection.add(new JLabel("Choose"));
        editSection.add(new JLabel("New Rate"));
        editSection.add(new JLabel());

        editSection.add(ddEdit);
        editSection.add(updateCurrencyValueInput);
        editSection.add(updateCurrency);

        editSection.add(new JLabel()); // EMPTY
        editSection.add(new JLabel("New Currency"));
        editSection.add(new JLabel()); // EMPTY

        editSection.add(new JLabel("Name"));
        editSection.add(new JLabel("Rate"));
        editSection.add(new JLabel());

        editSection.add(newCurrencyNameInput);
        editSection.add(newCurrencyValueInput);
        editSection.add(addCurrency);

        tableSection.add(new JLabel("Show Rates form EUR to ..."));
        tableSection.add(ddTableSelector);
        tableSection.add(show);

        // Baue ich gleich um.
        show.addActionListener(e -> {
            tableSection.removeAll();
            tableSection.setLayout(new GridLayout(3, 3));
            tableSection.add(new JLabel("Show Rates form EUR to ..."));
            tableSection.add(ddTableSelector);
            tableSection.add(show);

            tableSection.add(new JLabel("CURRENCY:"));
            tableSection.add(new JLabel("RATE:"));
            tableSection.add(new JLabel());
            String target = (String) ddTableSelector.getSelectedItem();
            tableSection.add(new JLabel(target));
            tableSection.add(new JLabel(String.valueOf(service.getRates(target))));
            tableSection.add(new JLabel());

            win.pack();
        });

        inputValue.addActionListener(e -> {
            try {
                String from = (String) ddInput.getSelectedItem();
                String to = (String) ddTarget.getSelectedItem();
                double amount = Double.parseDouble(inputValue.getText());
                outputValue.setText(String.valueOf(service.convert(amount, from, to)));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateCurrency.addActionListener(e -> {
            try {
                String from = (String) ddEdit.getSelectedItem();
                double amount = Double.parseDouble(updateCurrencyValueInput.getText());
                service.updateCurrency(from, amount);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        addCurrency.addActionListener(e -> {
            try {
                String from = newCurrencyNameInput.getText();
                double amount = Double.parseDouble(newCurrencyValueInput.getText());
                service.addCurrency(from, amount);
                refreshDropdowns(ddInput, ddTarget, ddEdit, ddTableSelector);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        win.add(panel); // add panel to main Window
        panel.add(new JLabel("CURRENCY CALCULATOR"), BorderLayout.NORTH);
        panel.add(convertSection, BorderLayout.CENTER);
        panel.add(addSection, BorderLayout.SOUTH);
        addSection.add(editSection, BorderLayout.NORTH);
        addSection.add(tableSection, BorderLayout.CENTER);
        win.pack(); // compute layout

        win.setVisible(
                true);
    }
}
