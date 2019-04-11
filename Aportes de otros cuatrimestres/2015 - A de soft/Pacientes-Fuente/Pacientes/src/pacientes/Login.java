package pacientes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField password;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("Bienvenido!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 331);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(32, 157, 79, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(32, 194, 100, 14);
		contentPane.add(lblContrasea);
		
		usuario = new JTextField();
		usuario.setBounds(168, 154, 86, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnIngresar.setBounds(105, 238, 89, 23);
		contentPane.add(btnIngresar);
		
		password = new JPasswordField();
		password.setBounds(168, 194, 86, 20);
		contentPane.add(password);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/pacientes/logo.png")));
		lblNewLabel.setBounds(94, 23, 100, 102);
		contentPane.add(lblNewLabel);
	}
	
	private void login() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Archivos arch = new Archivos();
		
		String usr = this.usuario.getText();
		String pass = String.valueOf(this.password.getPassword());
		
		boolean logueo = false;
		
		try {
			archivo = new File(arch.login());
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			String datos[] = new String[2];

			while ((linea = br.readLine()) != null) {
				datos = linea.split(" ");
				
				if(datos[0].compareTo(usr) == 0 && datos[1].compareTo(pass) == 0) {
					logueo = true;
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
		
		if (logueo) {
			Inicio ingredat = new Inicio(usr);
			ingredat.setVisible(true);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Usuario y/o contraseña inválidos");
			usuario.setText("");
			password.setText("");
		}
	}
}
