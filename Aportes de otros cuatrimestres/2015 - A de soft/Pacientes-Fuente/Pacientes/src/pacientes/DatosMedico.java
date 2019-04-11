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

public class DatosMedico extends JDialog {
	private JTextField codMedico;
	private JTextField nombMedico;
	private JTextField apeMedico;
	private JTextField especialidad;

	public DatosMedico(IngresoDatos ingreso) {
		super(ingreso, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Datos M\u00E9dico");
		getContentPane().setLayout(null);
		this.setBounds(380, 200, 355, 258);
		setLocationRelativeTo(null);
		codMedico = new JTextField();
		codMedico.setBounds(176, 41, 114, 19);
		getContentPane().add(codMedico);
		codMedico.setColumns(10);

		nombMedico = new JTextField();
		nombMedico.setBounds(176, 71, 114, 19);
		getContentPane().add(nombMedico);
		nombMedico.setColumns(10);

		JButton btnRegistrarMedico = new JButton("Registrar ");
		btnRegistrarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codMed = new String();
				String nomMed = new String();
				String apeMed = new String();
				String espec = new String();
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

					fichero = new BufferedWriter(new FileWriter(arch.datosmed(), true));
					pw = new PrintWriter(fichero);

					codMed = codMedico.getText();

					try {
						Integer.parseInt(codMed);
					} catch (Exception e) {
						error = 1;
					}
					
					if (error == 1 || Integer.parseInt(codMed) < 1 || Integer.parseInt(codMed) > 10000) {

						JOptionPane.showMessageDialog(null, "El código ingresado no es válido");

						codMedico.setText("");
						nombMedico.setText("");
						apeMedico.setText("");
						especialidad.setText("");
						error = 0;
					} else {
						
						// Comprobamos si existe el Código de Médico
						try {
							archivo = new File(arch.datosmed());
							fr = new FileReader(archivo);
							br = new BufferedReader(fr);

							String linea;
							String Datos[] = new String[3];

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
						
						if (encontro) {
							JOptionPane.showMessageDialog(null, "El código de médico ingresado ya existe");

							codMedico.setText("");
							nombMedico.setText("");
							apeMedico.setText("");
							especialidad.setText("");
							error = 0;
						} else {

							nomMed = nombMedico.getText();
	
							apeMed = apeMedico.getText();
	
							if (nomMed.matches(regex) == false || apeMed.matches(regex) == false || nomMed.equals("")
									|| apeMed.equals("")) {
	
								JOptionPane.showMessageDialog(null, "Nombre y/o Apellido inválido");
	
								codMedico.setText("");
								nombMedico.setText("");
								apeMedico.setText("");
								especialidad.setText("");
							}
	
							else {
	
								espec = especialidad.getText();
	
								if (espec.matches(regex) == false || espec.equals("")) {
	
									JOptionPane.showMessageDialog(null, "Especialidad inválida");
	
									codMedico.setText("");
									nombMedico.setText("");
									apeMedico.setText("");
									especialidad.setText("");
								} else {
									pw.println(codMed + " " + nomMed + " " + apeMed + " " + espec);
	
									JOptionPane.showMessageDialog(null, "Se han registrado los datos correctamente");
									dispose();
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
		btnRegistrarMedico.setBounds(185, 175, 123, 25);
		getContentPane().add(btnRegistrarMedico);

		JLabel lblIngrese = new JLabel("Código de médico:");
		lblIngrese.setBounds(22, 44, 133, 15);
		getContentPane().add(lblIngrese);

		JLabel lblIngreseNombreDe = new JLabel("Nombre de médico:");
		lblIngreseNombreDe.setBounds(22, 74, 133, 15);
		getContentPane().add(lblIngreseNombreDe);

		apeMedico = new JTextField();
		apeMedico.setBounds(176, 101, 114, 19);
		getContentPane().add(apeMedico);
		apeMedico.setColumns(10);

		JLabel lblApellidoDePaciente = new JLabel("Apellido de médico:");
		lblApellidoDePaciente.setBounds(22, 104, 123, 15);
		getContentPane().add(lblApellidoDePaciente);

		JButton btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSalir.setBounds(38, 175, 117, 25);
		getContentPane().add(btnSalir);

		especialidad = new JTextField();
		especialidad.setBounds(176, 131, 114, 19);
		getContentPane().add(especialidad);
		especialidad.setColumns(10);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(22, 134, 113, 15);
		getContentPane().add(lblEspecialidad);
	}
}