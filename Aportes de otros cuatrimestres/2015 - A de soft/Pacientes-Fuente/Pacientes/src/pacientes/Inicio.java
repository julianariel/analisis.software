package pacientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Inicio extends JFrame {

	public Inicio(String usuario) {
		setTitle("Centro Médico");

		this.setBounds(380, 300, 328, 325);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JButton btnIngDatos = new JButton("Ingreso de Datos");
		btnIngDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirIngreso();
			}
		});
		btnIngDatos.setBounds(90, 131, 142, 25);
		getContentPane().add(btnIngDatos);

		JButton btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirInformes();
			}
		});
		btnInformes.setBounds(90, 167, 142, 25);
		getContentPane().add(btnInformes);

		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(90, 237, 142, 25);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Inicio.class.getResource("/pacientes/logo.png")));
		lblNewLabel.setBounds(111, 11, 104, 109);
		getContentPane().add(lblNewLabel);
		
		if(usuario.compareTo("admin") == 0) {
			JButton registro = new JButton("Registrar Usuarios");
			registro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					registro();
				}
			});
			registro.setBounds(90, 203, 142, 23);
			getContentPane().add(registro);
		}		
	}
	
	private void abrirIngreso() {
		IngresoDatos ingdato = new IngresoDatos(this);
		ingdato.setVisible(true);
	}
	
	private void abrirInformes() {
		Informes informes = new Informes(this);
		informes.setVisible(true);
	}
	
	public void registro() {
		Registro registro = new Registro(this);
		registro.setVisible(true);
	}
}
