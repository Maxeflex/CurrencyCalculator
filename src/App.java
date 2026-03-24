
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App {

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
        JTextField newValue = new JTextField(); // Input for adding or changing currency
        JTextField newCurrency = new JTextField();
        JTextArea outputValue = new JTextArea(); // Displays the result
        JButton calc = new JButton("Calculate"); // triggers the calculation
        JButton edit = new JButton("Edit"); // triggers the change currency
        JButton show = new JButton("SHOW");
        JButton addCurrency = new JButton("ADD");
        String[] choices = {"EURO", "USD", "CHOICE 3", "CHOICE 4", "CHOICE 5", "CHOICE 6"};
        JComboBox<String> ddInput = new JComboBox<String>(choices); //Dropdown for input currency
        JComboBox<String> ddTarget = new JComboBox<String>(choices); //Dropdown for output currency
        JComboBox<String> ddEditA = new JComboBox<String>(choices); //Dropdown for adding or editing currency
        JComboBox<String> ddEditB = new JComboBox<String>(choices); //Dropdown for adding or editing currency
        JComboBox<String> ddTableSelector = new JComboBox<String>(choices);
        //### add Components and define position
        // Build convertSection
        convertSection.setLayout(new GridLayout(3, 3));

        convertSection.add(new JLabel("INPUT"));
        convertSection.add(new JLabel()); // EMPTY
        convertSection.add(new JLabel("OUTPUT"));

        convertSection.add(inputValue);
        convertSection.add(calc);
        convertSection.add(outputValue);

        convertSection.add(ddInput);
        convertSection.add(new JLabel()); // EMPTY
        convertSection.add(ddTarget);

        // Build addSection
        editSection.setLayout(new GridLayout(5, 3));

        editSection.add(new JLabel()); // EMPTY
        editSection.add(new JLabel("Change Currency"));
        editSection.add(new JLabel()); // EMPTY

        editSection.add(ddEditA);
        editSection.add(newValue);
        editSection.add(ddEditB);

        editSection.add(new JLabel());
        editSection.add(edit);
        editSection.add(new JLabel());

        editSection.add(new JLabel("New Currency"));
        editSection.add(newCurrency);
        editSection.add(addCurrency);

        tableSection.add(new JLabel("Show Rates for:"));
        tableSection.add(ddTableSelector);
        tableSection.add(show);

        show.addActionListener(e -> {
            tableSection.removeAll();
            tableSection.setLayout(new GridLayout(3 + choices.length - 1, 3));
            tableSection.add(new JLabel("Show Rates for:"));
            tableSection.add(ddTableSelector);
            tableSection.add(show);
            String value = (String) ddTableSelector.getSelectedItem();
            tableSection.add(new JLabel("CURRENCY A"));
            tableSection.add(new JLabel("CURRENCY B"));
            tableSection.add(new JLabel("RATE"));
            for (int i = 0; i < choices.length; i++) {
                if (choices[i] != value) {
                    tableSection.add(new JLabel(value));
                    tableSection.add(new JLabel(choices[i]));
                    tableSection.add(new JLabel("Value"));
                }
            }
            win.pack();
        });

        win.add(panel); // add panel to main Window
        panel.add(new JLabel("CURRENCY CALCULATOR"),BorderLayout.NORTH);
        panel.add(convertSection,BorderLayout.CENTER);
        panel.add(addSection,BorderLayout.SOUTH);
        addSection.add(editSection, BorderLayout.NORTH);
        addSection.add(tableSection, BorderLayout.CENTER);
        win.pack(); // compute layout

        win.setVisible(
                true);
    }
}
