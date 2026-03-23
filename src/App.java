
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App {

    public static void main(String[] args) {
        //### define Containers 
        JFrame win = new JFrame("Currency Calculator");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JPanel board = new JPanel();

        //### define  Layouts
        panel.setLayout(new BorderLayout());
        board.setLayout(new GridLayout(3,3));
        
        
        //### define Components
        JTextField inputValue = new JTextField(); // Input for converting currency
        JTextField newValue = new JTextField(); // Input for adding or changing currency
        JTextArea outputValue = new JTextArea(); // Displays the result
        JButton calc = new JButton("Calculate"); // triggers the calculation
        String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        JComboBox<String> ddInput = new JComboBox<String>(choices); //Dropdown for input currency
        JComboBox<String> ddTarget = new JComboBox<String>(choices); //Dropdown for output currency
        JComboBox<String> ddEdit = new JComboBox<String>(choices); //Dropdown for adding or editing currency
        
        // To add: Table for displaying an overview of the currencys -> loop through stoarge file, for each entry add new JTextArea
        

        //### add Components and define position
        panel.add(board,BorderLayout.CENTER);
        //panel.add(inputValue);
        //panel.add(calc);
        win.add(panel); // add panel to main Window
        win.pack(); // compute layout
        
        win.setVisible(true);
    }
} 
