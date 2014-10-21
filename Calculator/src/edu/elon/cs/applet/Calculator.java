package edu.elon.cs.applet;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*<applet code =”Calculator” width=250 height=350>

 </applet>*/

public class Calculator extends JApplet implements ActionListener

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton keysArray[];

	JPanel p1, p, keyPad;

	JTextField ledField;

	double result;

	boolean first;

	boolean foundKey;

	static boolean clearText;

	int prevOperator;

	JLabel l1;

	public void init()

	{

		p = new JPanel();

		l1 = new JLabel("Calculator");

		add(l1);

		p1 = new JPanel();

		ledField = new JTextField("0", 20);

		p1.add(ledField);

		keyPad = new JPanel();

		keysArray = new JButton[17];

		result = 0.0;

		prevOperator = 0;

		first = true;

		clearText = true;

		for (int i = 0; i <= 9; i++)

			keysArray[i] = new JButton(String.valueOf(i));

		keysArray[10] = new JButton("/");

		keysArray[11] = new JButton("*");

		keysArray[12] = new JButton("-");

		keysArray[13] = new JButton("+");

		keysArray[14] = new JButton("=");

		keysArray[15] = new JButton(".");

		keysArray[16] = new JButton("CLR");

		keyPad.setLayout(new GridLayout(4, 4));

		for (int i = 0; i <= 16; i++)

		{

			keyPad.add(keysArray[i]);

			keysArray[i].addActionListener(this);

		}

		add(l1, BorderLayout.NORTH);

		add(ledField, BorderLayout.CENTER);

		add(keyPad, BorderLayout.SOUTH);

		add(keysArray[16], BorderLayout.EAST);

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)

	{

		foundKey = false;

		for (int i = 0; i < keysArray.length && !foundKey; i++)

			if (e.getSource() == keysArray[i])

			{

				foundKey = true;

				switch (i)

				{

				case 0:

				case 1:

				case 2:

				case 3:

				case 4:

				case 5:

				case 6:

				case 7:

				case 8:

				case 9:

				case 15:

					if (clearText)

					{

						ledField.setText("");

						clearText = false;

					}
					ledField.setText(ledField.getText()
							+ keysArray[i].getLabel());

					break;

				case 10:

				case 11:

				case 12:

				case 13:

				case 14:

					clearText = true;

					if (first)

					{

						if (ledField.getText().length() == 0)

							result = 0.0;

						else
							result = Double.valueOf(ledField.getText())
									.doubleValue();

						first = false;

						prevOperator = i;

					}

					else

					{

						switch (prevOperator)

						{

						case 10:

							result /= Double.valueOf(ledField.getText())
									.doubleValue();

							break;

						case 11:

							result *= Double.valueOf(ledField.getText())
									.doubleValue();

							break;

						case 12:

							result -= Double.valueOf(ledField.getText())
									.doubleValue();

							break;

						case 13:

							result += Double.valueOf(ledField.getText())
									.doubleValue();

							break;

						}

						ledField.setText(Double.toString(result));

						if (i == 14)

							first = true;

						else

							prevOperator = i;

					}

					break;

				case 16:

					clearText = true;

					first = true;

					ledField.setText("");

					result = 0.0;

					prevOperator = 0;

					break;

				}

			}

	}
}