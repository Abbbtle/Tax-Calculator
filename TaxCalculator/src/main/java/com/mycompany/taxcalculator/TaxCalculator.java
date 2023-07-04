/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taxcalculator;

/**
 *
 * @author Sinelizwi Ntaku
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class TaxCalculator extends JFrame {
    private JTextField incomeTextField;
    private JButton calculateButton;
    private JButton clearButton;
    private JLabel netPayLabel;
    private JTextField netPayTextField;
    private JLabel taxObligationLabel;
    private JTextField taxObligationTextField;

    public TaxCalculator() {
        setTitle("SARS Tax Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400)); 
        getContentPane().setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new GridBagLayout());

        JLabel incomeLabel = new JLabel("Annual Income");
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        incomeTextField = new JTextField(10);
        incomeTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        incomeTextField.setBackground(Color.WHITE);
        incomeTextField.setBorder(new LineBorder(Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        topPanel.add(incomeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        topPanel.add(incomeTextField, gbc);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 18));
        calculateButton.setBackground(Color.GREEN);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        centerPanel.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        centerPanel.add(clearButton, gbc);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.WHITE);
        southPanel.setLayout(new GridBagLayout());

        taxObligationLabel = new JLabel("Tax Obligation ");
        taxObligationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        taxObligationTextField = new JTextField(10);
        taxObligationTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        taxObligationTextField.setEditable(false);
        taxObligationTextField.setBackground(Color.WHITE);
        taxObligationTextField.setBorder(new LineBorder(Color.BLACK));

        netPayLabel = new JLabel("Net Pay ");
        netPayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        netPayTextField = new JTextField(10);
        netPayTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        netPayTextField.setEditable(false);
        netPayTextField.setBackground(Color.WHITE);
        netPayTextField.setBorder(new LineBorder(Color.BLACK));

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        southPanel.add(taxObligationLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        southPanel.add(taxObligationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        southPanel.add(netPayLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        southPanel.add(netPayTextField, gbc);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        incomeTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                incomeTextField.setBorder(new LineBorder(Color.GREEN));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                incomeTextField.setBorder(new LineBorder(Color.BLACK));
            }
        });
        
        taxObligationTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                taxObligationTextField.setBorder(new LineBorder(Color.GREEN));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taxObligationTextField.setBorder(new LineBorder(Color.BLACK));
            }
        });

        netPayTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                netPayTextField.setBorder(new LineBorder(Color.GREEN));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                netPayTextField.setBorder(new LineBorder(Color.BLACK));
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    
    private void calculateTax() {
    try {
        double income = Double.parseDouble(incomeTextField.getText());

        if (income == 0) {
            throw new ArithmeticException("Division by zero");
        }

        double tax = calculateTaxObligation(income);
        double netPay = income - tax;

        taxObligationTextField.setText(String.format("R%.2f", tax));
        netPayTextField.setText(String.format("R%.2f", netPay));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter valid input", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (ArithmeticException e) {
        if (e.getMessage().equals("/0")) {
            JOptionPane.showMessageDialog(this, "Division by zero", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    private double calculateTaxObligation(double income) {
        double tax = 0.0;

        if (income >= 1 && income <= 237100) {
            tax = income * 0.18;
        } else if (income >= 237101 && income <= 370500) {
            tax = 42678 + income * 0.26;
        } else if (income >= 370501 && income <= 512800) {
            tax = 77362 + income * 0.31;
        } else if (income >= 512801 && income <= 673000) {
            tax = 121475 + income * 0.36;
        } else if (income >= 673001 && income <= 857900) {
            tax = 179147 + (income - 673000) * 0.39;
        } else if (income >= 857901 && income <= 1817000) {
            tax = 251258 + income * 0.41;
        } else if (income >= 1817001){
            tax = 644489 + income * 0.45;
        }

        else {
            System.out.println("You have entered a wrong number");
        }

        return tax;
    }

    private void clearFields() {
        incomeTextField.setText("");
        taxObligationTextField.setText("");
        netPayTextField.setText("");
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaxCalculator().setVisible(true);
            }
        });
    }
}