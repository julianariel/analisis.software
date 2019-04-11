package pacientes;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DatosPaciente extends JDialog {
	private JTextField codPaciente;
	private JTextField nombPaciente;
	private JTextField apePaciente;

	public DatosPaciente(IngresoDatos ingreso) {
		super(ingreso, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Registro de Paciente");
		getContentPane().setLayout(null);
		this.setBounds(380, 200, 369, 224);
		setLocationRelativeTo(null);

		codPaciente = new JTextField();
		codPaciente.setBounds(190, 27, 114, 19);
		getContentPane().add(codPaciente);
		codPaciente.setColumns(10);

		nombPaciente = new JTextField();
		nombPaciente.setBounds(190, 57, 114, 19);
		getContentPane().add(nombPaciente);
		nombPaciente.setColumns(10);

		JButton btnRegistrarPaciente = new JButton("Registrar");
		btnRegistrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codPac = new String();
				String nomPac = new String();
				String apePac = new String();
				int error = 0;

				File archivo = null;
				FileReader fr = null;
				BufferedReader br = null;
				boolean encontro = false;

				BufferedWriter fichero = null;
				PrintWriter pw = null;
				String regex = "^[a-zA-Z]+$";
				Archivos arch = new Archivos();

				try {

					fichero = new BufferedWriter(new FileWriter(arch.datospac(), true));
					pw = new PrintWriter(fichero);

					codPac = codPaciente.getText();

					try {
						Integer.parseInt(codPac);
					} catch (Exception e) {
						error = 1;
					}

					if (error == 1 || Integer.parseInt(codPac) < 1 || Integer.parseInt(codPac) > 10000) {

						JOptionPane.showMessageDialog(null, "El código ingresado no es válido");

						codPaciente.setText("");
						nombPaciente.setText("");
						apePaciente.setText("");
						error = 0;
					} else {

						// Comprobamos si existe el Código de Paciente
						try {
							archivo = new File(arch.datospac());
							fr = new FileReader(archivo);
							br = new BufferedReader(fr);

							String linea;
							String DatosPacientes[] = new String[3];

							while ((linea = br.readLine()) != null) {
								DatosPacientes = linea.split(" ");

								if (DatosPacientes[0].compareTo(codPac) == 0) {
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
							JOptionPane.showMessageDialog(null, "El código de paciente ingresado ya existe");

							codPaciente.setText("");
							nombPaciente.setText("");
							apePaciente.setText("");
							error = 0;
						} else {

							nomPac = nombPaciente.getText();
							apePac = apePaciente.getText();

							if (nomPac.matches(regex) == false || apePac.matches(regex) == false || nomPac.equals("")
									|| apePac.equals("")) {

								JOptionPane.showMessageDialog(null, "Nombre y/o Apellido inválido");

								codPaciente.setText("");
								nombPaciente.setText("");
								apePaciente.setText("");

							}

							else {

								pw.println(codPac + " " + nomPac + " " + apePac);

								JOptionPane.showMessageDialog(null, "Se han registrado los datos correctamente");
								dispose();
							}
						}
					}
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

		});
		btnRegistrarPaciente.setBounds(194, 132, 122, 25);
		getContentPane().add(btnRegistrarPaciente);

		JLabel lblIngrese = new JLabel("C\u00F3digo de paciente:");
		lblIngrese.setBounds(34, 30, 133, 15);
		getContentPane().add(lblIngrese);

		JLabel lblIngreseNombreDe = new JLabel("Nombre de paciente:");
		lblIngreseNombreDe.setBounds(34, 61, 133, 15);
		getContentPane().add(lblIngreseNombreDe);

		apePaciente = new JTextField();
		apePaciente.setBounds(190, 87, 114, 19);
		getContentPane().add(apePaciente);
		apePaciente.setColumns(10);

		JLabel lblApellidoDePaciente = new JLabel("Apellido de paciente:");
		lblApellidoDePaciente.setBounds(34, 91, 133, 15);
		getContentPane().add(lblApellidoDePaciente);

		JButton btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSalir.setBounds(50, 132, 117, 25);
		getContentPane().add(btnSalir);
	}
}
