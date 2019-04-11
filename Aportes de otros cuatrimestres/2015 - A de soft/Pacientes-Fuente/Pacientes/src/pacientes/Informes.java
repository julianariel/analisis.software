package pacientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class Informes extends JDialog {

	public Informes(Inicio inicio) {
		super(inicio, true);
		setTitle("Informes");
		getContentPane().setLayout(null);
		this.setBounds(380, 200, 400, 215);
		setLocationRelativeTo(null);

		JButton btnPacMed = new JButton(" Pacientes por m\u00E9dico");
		btnPacMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirListadoPacMed();
			}
		});
		btnPacMed.setBounds(60, 34, 265, 25);
		getContentPane().add(btnPacMed);

		JButton btnEnfMedico = new JButton("Enfermedades atendidas por m\u00E9dico");
		btnEnfMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirListadoEnfMed();
			}
		});
		
		btnEnfMedico.setBounds(60, 70, 265, 25);
		getContentPane().add(btnEnfMedico);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(129, 115, 142, 25);
		getContentPane().add(btnNewButton);
	}
	
	private void abrirListadoPacMed() {
		ListadoPacMed pacmed = new ListadoPacMed(this);
		pacmed.setVisible(true);
	}
	
	private void abrirListadoEnfMed() {
		ListadoEnfMedico enfmed = new ListadoEnfMedico(this);
		enfmed.setVisible(true);
	}
}
