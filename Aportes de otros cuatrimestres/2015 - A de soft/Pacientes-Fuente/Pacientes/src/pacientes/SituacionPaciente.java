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

public class SituacionPaciente extends JDialog {
	private JTextField codPaciente;
	private JTextField codDoctor;
	private JTextField diagnostico;

	public SituacionPaciente(IngresoDatos ingreso) {
		super(ingreso, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Situación del Paciente");
		getContentPane().setLayout(null);
		this.setBounds(380, 200, 341, 239);
		setLocationRelativeTo(null);

		codPaciente = new JTextField();
		codPaciente.setBounds(159, 44, 137, 19);
		getContentPane().add(codPaciente);
		codPaciente.setColumns(10);

		codDoctor = new JTextField();
		codDoctor.setBounds(159, 74, 137, 19);
		getContentPane().add(codDoctor);
		codDoctor.setColumns(10);

		JButton btnSituacionPaciente = new JButton("Registrar ");
		btnSituacionPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codPac = new String();
				String codMed = new String();
				String diag = new String();
				int error = 0;
				boolean encontro = false;

				File archivo = null;
				FileReader fr = null;
				BufferedReader br = null;

				BufferedWriter fichero = null;
				PrintWriter pw = null;

				String regex = "^[a-zA-Z]+$";
				Archivos arch = new Archivos();

				try {

					fichero = new BufferedWriter(new FileWriter(arch.situpac(), true));
					pw = new PrintWriter(fichero);

					codPac = codPaciente.getText();

					try {
						Integer.parseInt(codPac);
					} catch (Exception e) {
						error = 1;
					}

					if (error == 1 || Integer.parseInt(codPac) < 1 || Integer.parseInt(codPac) > 10000) {

						JOptionPane.showMessageDialog(null, "El código de paciente ingresado no es válido");

						codPaciente.setText("");
						codDoctor.setText("");
						diagnostico.setText("");
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

						if (!encontro) {
							JOptionPane.showMessageDialog(null, "El código de paciente ingresado no existe");

							codPaciente.setText("");
							codDoctor.setText("");
							diagnostico.setText("");
							error = 0;
						} else {

							codMed = codDoctor.getText();

							try {
								Integer.parseInt(codMed);
							} catch (Exception e) {
								error = 1;
							}
							
							if (error == 1 || Integer.parseInt(codMed) < 1 || Integer.parseInt(codMed) > 10000) {

								JOptionPane.showMessageDialog(null, "El código de Médico ingresado no es válido");

								codPaciente.setText("");
								codDoctor.setText("");
								diagnostico.setText("");
								error = 0;
							} else {
								
								// Comprobamos si existe el Código de Médico
								try {
									archivo = null;
									fr = null;
									br = null;
									archivo = new File(arch.datosmed());
									fr = new FileReader(archivo);
									br = new BufferedReader(fr);

									String linea;
									String Datos[] = new String[4];
									encontro = false;
									
									while ((linea = br.readLine()) != null) {
										Datos = linea.split(" ");

										if (Datos[0].compareTo(codMed) == 0) {
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
								
								if (!encontro) {
									JOptionPane.showMessageDialog(null, "El código de médico ingresado no existe");

									codDoctor.setText("");
									diagnostico.setText("");
									error = 0;
								} else {

									diag = diagnostico.getText();
									if (diag.length() == 0) {
										JOptionPane.showMessageDialog(null, "Debe ingresar un diagnóstico");
										diagnostico.setText("");
										error = 0;
									} else {									
										pw.println(codPac + " " + codMed + " " + diag);
										
										JOptionPane.showMessageDialog(null, "Se han registrado los datos correctamente");
										dispose();
									}
								}
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
		btnSituacionPaciente.setBounds(162, 149, 127, 25);
		getContentPane().add(btnSituacionPaciente);

		JLabel lblIngrese = new JLabel("C\u00F3digo de paciente:");
		lblIngrese.setBounds(22, 44, 148, 15);
		getContentPane().add(lblIngrese);

		JLabel lblIngreseNombreDe = new JLabel("C\u00F3digo del m\u00E9dico:");
		lblIngreseNombreDe.setBounds(22, 75, 127, 15);
		getContentPane().add(lblIngreseNombreDe);

		diagnostico = new JTextField();
		diagnostico.setBounds(159, 104, 137, 19);
		getContentPane().add(diagnostico);
		diagnostico.setColumns(10);

		JLabel lblApellidoDePaciente = new JLabel("Diagn\u00F3stico:");
		lblApellidoDePaciente.setBounds(22, 107, 103, 15);
		getContentPane().add(lblApellidoDePaciente);

		JButton btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSalir.setBounds(33, 149, 117, 25);
		getContentPane().add(btnSalir);
	}

}
