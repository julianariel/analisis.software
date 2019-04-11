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

public class ListadoPacMed extends JDialog {

	private JTextField codMedico;
	private JTable table;

	public ListadoPacMed(Informes informes) {
		super(informes, true);
		setTitle("Listado de pacientes por médico");
		this.setBounds(380, 200, 480, 310);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel nombremed = new JLabel("");
		nombremed.setEnabled(false);
		nombremed.setBounds(168, 61, 242, 15);
		getContentPane().add(nombremed);

		codMedico = new JTextField();
		codMedico.setBounds(168, 31, 114, 19);
		getContentPane().add(codMedico);
		codMedico.setColumns(10);

		JLabel lblNewLabel = new JLabel("C\u00F3digo del M\u00E9dico:");
		lblNewLabel.setBounds(38, 29, 129, 15);
		getContentPane().add(lblNewLabel);

		Object[] columnNames = { "Codigo", "Nombre", "Apellido" };

		Object[][] data = new Object[100][3]; // matriz donde guardo datos de
												// paciente

		JTable table = new JTable(data, columnNames);

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(38, 91, 414, 105);
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

				Object[] columnNames = { "Codigo", "Nombre", "Apellido" };

				Object[][] data = new Object[100][3]; // matriz donde guardo
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
							pane.setBounds(38, 91, 414, 105);
							getContentPane().add(pane);

						}
						// hasta aca valide el formato del codigo, y si
						// pertenece a un medico
						else {

							// todo ok, buscar los pacientes atendido por el
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

									data[cont][0] = datospacmed[0];

									try {

										archdatospac = new File(arch.datospac());
										frdatospac = new FileReader(archdatospac);
										brdatospac = new BufferedReader(frdatospac);

										String lineadatospac = new String();
										String datospac[] = new String[3];

										while ((lineadatospac = brdatospac.readLine()) != null) {

											datospac = lineadatospac.split(" ");

											if (Integer.parseInt(datospac[0]) == Integer.parseInt(datospacmed[0])) {

												data[cont][1] = datospac[1];
												data[cont][2] = datospac[2];
												break;

											}

										}

										frdatospac.close();

									} catch (Exception e) {

										// catch del archivo datospaciente
										System.out.println(e.getMessage());

									}

									cont++;
								}

							} // aca termina la busqueda de pacientes del medico

							// aca imprime la tabla

							table2 = new JTable(data, columnNames);

							// table2.setBounds(323, 158, 1, 1);
							pane = new JScrollPane(table2);
							pane.setBounds(38, 91, 414, 105);
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
		btnConsultar.setBounds(307, 29, 117, 25);
		getContentPane().add(btnConsultar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(38, 55, 70, 15);
		getContentPane().add(lblNombre);

		JButton btnSalir = new JButton("Cancelar");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSalir.setBounds(174, 219, 117, 25);
		getContentPane().add(btnSalir);
	}
}
