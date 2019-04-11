package triangulo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField lado1;
	private JTextField lado2;
	private JTextField lado3;
	private JLabel tipo;

	// Main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor
	public Interface() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblLado = new JLabel("Lado 1:");
		lblLado.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLado.setBounds(121, 35, 46, 14);
		contentPane.add(lblLado);
		
		JLabel lblLado_1 = new JLabel("Lado 2:");
		lblLado_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLado_1.setBounds(121, 75, 46, 14);
		contentPane.add(lblLado_1);
		
		JLabel lblLado_2 = new JLabel("Lado 3:");
		lblLado_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLado_2.setBounds(121, 115, 46, 14);
		contentPane.add(lblLado_2);
		
		lado1 = new JTextField();
		lado1.setBounds(187, 32, 30, 20);
		contentPane.add(lado1);
		lado1.setColumns(10);
		
		lado2 = new JTextField();
		lado2.setBounds(187, 72, 30, 20);
		contentPane.add(lado2);
		lado2.setColumns(10);
		
		lado3 = new JTextField();
		lado3.setBounds(187, 112, 30, 20);
		contentPane.add(lado3);
		lado3.setColumns(10);
		
		JButton btnNewButton = new JButton("Obtener Tipo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obtenerTipo();
			}
		});
		btnNewButton.setBounds(114, 208, 121, 23);
		contentPane.add(btnNewButton);
		
		tipo = new JLabel("");
		tipo.setHorizontalAlignment(SwingConstants.CENTER);
		tipo.setForeground(Color.RED);
		tipo.setFont(new Font("Arial", Font.PLAIN, 12));
		tipo.setBounds(10, 154, 315, 43);
		contentPane.add(tipo);
	}
	
	private void obtenerTipo() {
		int a, b, c;
		
		if (isNumeric(this.lado1.getText()) && isNumeric(this.lado2.getText()) && isNumeric(this.lado3.getText())) {
			a = Integer.parseInt(this.lado1.getText());
			b = Integer.parseInt(this.lado2.getText());
			c = Integer.parseInt(this.lado3.getText());
			
			if (Math.abs(a - c) < b && b < (a + c)) {
				if (a ==  b) {
					if (b == c) {
						tipo.setText("Equilátero");
					} else {
						tipo.setText("Isósceles");
					}
				} else {
					if (b != c && c != a) {
						tipo.setText("Escaleno");
					} else {
						tipo.setText("Isósceles");
					}
				}
			} else {
				tipo.setText("La combinación ingresada no forma un triángulo");
			}
		} else {
			tipo.setText("Uno de los lados ingresados no es un valor entero");
		}
	}
	
	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}