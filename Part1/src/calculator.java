//package calculator;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.util.ArrayList;

public class calculator {
	public static void main(String[] args) {
		new CalculatorView();
	}
}

class CalculatorController {

	public CalculatorController() {
		
	}

	public String calculate(Double number1, Double number2, String operator) {
		try{
			if (operator.equals("+")) {
				return  String.valueOf(number1+number2);
			}
			else if (operator.equals("*")) {
				return  String.valueOf(number1*number2);
			}
			else if (operator.equals("/")) {
				return  String.valueOf(number1/number2);
			}
			else if (operator.equals("-")) {
				return String.valueOf(number1-number2);
			}
			else {
				return "NaN";
			}
		} catch (Exception e) {
			return "NaN";
		}
		
	}
}

class CalculatorView extends JFrame {

	private JPanel numberRow = new JPanel();
	private JPanel operationRow = new JPanel();
	
	private JTextField operationNumber1 = new JTextField();
	private JLabel operationSigh = new JLabel("+");
	private JTextField operationNumber2 = new JTextField();
	private JLabel equalSigh = new JLabel("=");
	private JTextField resultField = new JTextField("");
	
	private JButton addButton = new JButton("+");
	private JButton decButton = new JButton("-");
	private JButton mulButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton okButton = new JButton("OK");
	private CalculatorController calculator = new CalculatorController();

	//@SuppressWarnings({ "rawtypes", "unchecked" })
	public CalculatorView() {
		setTitle("计算器");
		setSize(400, 400);
		
		int a = 1;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//居中显示
		setLocationRelativeTo(null);
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				operationSigh.setText("+");
				if (checkValid()) {
					Double number1 = Double.parseDouble(operationNumber1.getText());
					Double number2 = Double.parseDouble(operationNumber2.getText());
					resultField.setText(calculator.calculate(number1, number2, "+"));
				}
			
			}
		});
		
		
		decButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				operationSigh.setText("-");
				if (checkValid()) {
					Double number1 = Double.parseDouble(operationNumber1.getText());
					Double number2 = Double.parseDouble(operationNumber2.getText());
					resultField.setText(calculator.calculate(number1, number2, "-"));
				}
			}
		});

		mulButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				operationSigh.setText("*");
				if (checkValid()) {
					Double number1 = Double.parseDouble(operationNumber1.getText());
					Double number2 = Double.parseDouble(operationNumber2.getText());
					resultField.setText(calculator.calculate(number1, number2, "*"));
				}
			}
		});

		divButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				operationSigh.setText("/");
				if (checkValid()) {
					Double number1 = Double.parseDouble(operationNumber1.getText());
					Double number2 = Double.parseDouble(operationNumber2.getText());
					resultField.setText(calculator.calculate(number1, number2, "/"));
				}
			}
		});

		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkValid()) {
					Double number1 = Double.parseDouble(operationNumber1.getText());
					Double number2 = Double.parseDouble(operationNumber2.getText());
					String operator = operationSigh.getText();
					resultField.setText(calculator.calculate(number1, number2, operator));
				}
				else {
					JOptionPane.showMessageDialog(null, "Please check your input:)", "Sorry", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		GridLayout gridLayout = new GridLayout(2, 1, 1, 1);
		setLayout(gridLayout);
		
		//设置布局 一行五个
		GridLayout fiveColsGridLayout = new GridLayout(1, 5, 1, 1);
		numberRow.setLayout(fiveColsGridLayout);
		operationRow.setLayout(fiveColsGridLayout);
		
		resultField.setEditable(false);

		//设置边界
		Border border = new javax.swing.border.CompoundBorder(null, new LineBorder(Color.GRAY));
		operationNumber1.setBorder(border);
		operationSigh.setBorder(border);
		operationNumber2.setBorder(border);
		equalSigh.setBorder(border);
		resultField.setBorder(border);
		okButton.setBorder(new javax.swing.border.CompoundBorder(null, new LineBorder(Color.BLUE)));
		
		//设置文本居中
		operationNumber1.setHorizontalAlignment(JTextField.CENTER);
		operationSigh.setHorizontalAlignment(JTextField.CENTER);
		operationNumber2.setHorizontalAlignment(JTextField.CENTER);
		equalSigh.setHorizontalAlignment(JTextField.CENTER);
		resultField.setHorizontalAlignment(JTextField.CENTER);

		numberRow.add(operationNumber1);
		numberRow.add(operationSigh);
		numberRow.add(operationNumber2);
		numberRow.add(equalSigh);
		numberRow.add(resultField);
		
		//添加操作符按钮
		operationRow.add(addButton);
		operationRow.add(decButton);
		operationRow.add(mulButton);
		operationRow.add(divButton);
		operationRow.add(okButton);
		
		add(numberRow);
		add(operationRow);
		this.pack();
		setVisible(true);
	}

	public void recoverBorder() {
		Border border = new javax.swing.border.CompoundBorder(null, new LineBorder(Color.GRAY));
		operationNumber1.setBorder(border);
		operationNumber2.setBorder(border);
	}


	public boolean checkValid() {
		String number1 = operationNumber1.getText();
		String number2 = operationNumber2.getText();
		String format = "(-)?\\d+(\\.\\d+)?";
		if (!number1.matches(format)) {
			operationNumber1.setBorder(new javax.swing.border.CompoundBorder(null, new LineBorder(Color.RED)));
			java.util.Timer timer = new java.util.Timer(); 
			timer.schedule(new TimerTask() {
				public void run() {recoverBorder();}
				}, 600);
		}
		if (!number2.matches(format)) {
			operationNumber2.setBorder(new javax.swing.border.CompoundBorder(null, new LineBorder(Color.RED)));
			java.util.Timer timer = new java.util.Timer(); 
			timer.schedule(new TimerTask() {
				public void run() {recoverBorder();}
				}, 600);
		}
		return number1.matches(format) && number2.matches(format);
	}

}
