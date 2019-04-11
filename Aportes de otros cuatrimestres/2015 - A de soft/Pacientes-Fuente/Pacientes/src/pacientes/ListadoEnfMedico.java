package pacientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ListadoEnfMedico extends JDialog {
	private JTextField codMedico;
	private JTable table;

	public ListadoEnfMedico(Informes informes) {
		super(informes, true);
		setTitle("Enfermedades que atiende médico");
		getContentPane().setLayout(null);
		this.setBounds(380, 200, 486, 361);
		setLocationRelativeTo(null);
		
		JLabel nombremed = new JLabel("");
		nombremed.setEnabled(false);
		nombremed.setBounds(183, 71, 205, 15);
		getContentPane().add(nombremed);

		codMedico = new JTextField();
		codMedico.setBounds(183, 41, 114, 19);
		getContentPane().add(codMedico);
		codMedico.setColumns(10);

		JLabel lblNewLabel = new JLabel("C\u00F3digo del M\u00E9dico:");
		lblNewLabel.setBounds(44, 44, 129, 15);
		getContentPane().add(lblNewLabel);

		Object[] columnNames = { "Enfermedad" };

		Object[][] data = new Object[100][1]; // matriz donde guardo datos de
												// paciente

		JTable table = new JTable(data, columnNames);

		// table2.setBounds(323, 158, 1, 1);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(44, 109, 392, 137);
		getContentPane().add(pane);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				File archivo = null;
				FileReader fr = null;
				BufferedReader br = null;
				File archsitupac = null;
				FileReader frsitupac = null;
				BufferedReader brsitupac = null;
				File archdatospac = null;
				FileReader frdatospac = null;
				BufferedReader brdatospac = null;
				int encontro = 0, error = 0;
				String codMed = new String();
				JTable table2;
				JScrollPane pane;
				Archivos arch = new Archivos();

				Object[] columnNames = { "Enfermedad" };

				Object[][] data = new Object[100][1]; // matriz donde guardo
														// datos de paciente

				codMed = codMedico.getText();

				try {
					Integer.parseInt(codMed);
				} catch (Exception e) {

					error = 1;
				}
				if (error == 1 || Integer.parseInt(codMed) < 1 || Integer.parseInt(codMed) > 10000) {

					JOptionPane.showMessageDialog(null, "El código ingresado no es válido");

					codMedico.setText("");
					error = 0;
				} else {

					try {

						archivo = new File(arch.datosmed());
						fr = new FileReader(archivo);
						br = new BufferedReader(fr);

						String linea;
						String datosmed[] = new String[4];

						while ((linea = br.readLine()) != null) {

							datosmed = linea.split(" ");

							if (Integer.parseInt(datosmed[0]) == Integer.parseInt(codMed)) {
								encontro = 1;
								break;

							}

						}

						if (encontro == 0) {
							JOptionPane.showMessageDialog(null, "El código ingresado no existe");

							codMedico.setText("");
							nombremed.setText("");
							nombremed.setEnabled(false);
							table2 = new JTable(data, columnNames);

							// table2.setBounds(323, 158, 1, 1);
							pane = new JScrollPane(table2);
							pane.setBounds(44, 109, 392, 137);
							getContentPane().add(pane);

						}
						// hasta aca valide el formato del codigo, y si
						// pertenece a un medico
						else {

							// todo ok, buscar las enfermedades atendidas por el
							// medico.

							nombremed.setText(datosmed[1] + " " + datosmed[2]);
							nombremed.enable(true);
							archsitupac = new File(arch.situpac());
							frsitupac = new FileReader(archsitupac);
							brsitupac = new BufferedReader(frsitupac);

							String lineasitupac = new String();
							String datospacmed[] = new String[3];
							int cont = 0;

							while ((lineasitupac = brsitupac.readLine()) != null) {

								datospacmed = lineasitupac.split(" ");

								if (Integer.parseInt(datospacmed[1]) == Integer.parseInt(datosmed[0])) {

									data[cont][0] = datospacmed[2]; // encontre
																	// el
																	// medico,
																	// guardo
																	// enfermedad

									cont++;
								}

							} // aca termina la busqueda de enfermedades

							// aca imprime la tabla

							table2 = new JTable(data, columnNames);

							// table2.setBounds(323, 158, 1, 1);
							pane = new JScrollPane(table2);
							pane.setBounds(44, 109, 392, 137);
							getContentPane().add(pane);

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

				}
			}
		});
		btnConsultar.setBounds(319, 39, 117, 25);
		getContentPane().add(btnConsultar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(92, 71, 70, 15);
		getContentPane().add(lblNombre);

		JButton btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSalir.setBounds(183, 270, 117, 25);
		getContentPane().add(btnSalir);

	}

}
