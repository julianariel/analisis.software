package pacientes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registro extends JDialog {

	private JPanel contentPanel;
	private JTextField usuario;
	private JPasswordField pass;
	private JPasswordField repPass;

	public Registro(Inicio inicio) {
		super(inicio, true);
		contentPanel = new JPanel();
		setTitle("Registro de Usuario");

		setBounds(100, 100, 332, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(34, 29, 135, 14);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblNewLabel = new JLabel("Contrase\u00F1a:");
			lblNewLabel.setBounds(34, 60, 135, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblRepitaContrasea = new JLabel("Repita Contrase\u00F1a:");
			lblRepitaContrasea.setBounds(34, 91, 135, 14);
			contentPanel.add(lblRepitaContrasea);
		}
		{
			usuario = new JTextField();
			usuario.setBounds(179, 26, 86, 20);
			contentPanel.add(usuario);
			usuario.setColumns(10);
		}
		{
			pass = new JPasswordField();
			pass.setBounds(179, 57, 86, 20);
			contentPanel.add(pass);
		}
		{
			repPass = new JPasswordField();
			repPass.setBounds(179, 88, 86, 20);
			contentPanel.add(repPass);
		}
		{
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					registro();
				}
			});
			btnRegistrar.setBounds(104, 132, 89, 23);
			contentPanel.add(btnRegistrar);
		}
	}
	
	private void registro() {
		
		String usr = usuario.getText();
		String psw = String.valueOf(pass.getPassword());
		String repPsw = String.valueOf(repPass.getPassword());
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		BufferedWriter fichero = null;
		PrintWriter pw = null;
		
		Archivos arch = new Archivos();
		boolean encontro = false;
		
		if (usr.length() == 0) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un usuario");
		} else {
			
		
			// Comprobamos si existe el Código de Médico
			try {
				archivo = new File(arch.login());
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
	
				String linea;
				String Datos[] = new String[2];
	
				while ((linea = br.readLine()) != null) {
					Datos = linea.split(" ");
	
					if (Datos[0].compareTo(usr) == 0) {
						encontro = true;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != fr) {
						fr.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if (encontro) {
				JOptionPane.showMessageDialog(null, "El usuario ingresado ya existe");
				usuario.setText("");
				pass.setText("");
				repPass.setText("");
			} else {
				if (psw.length() == 0 || repPsw.length() == 0) {
					JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña");
				} else {
					if (psw.compareTo(repPsw) != 0) {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
						pass.setText("");
						repPass.setText("");
					} else {
						try {
	
							fichero = new BufferedWriter(new FileWriter(arch.login(), true));
							pw = new PrintWriter(fichero);
							
							pw.println(usr + " " + psw);
							
							JOptionPane.showMessageDialog(null, "Usuario registrado!");
							dispose();
							
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
	
								if (null != fichero)
									fichero.close();
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

}
