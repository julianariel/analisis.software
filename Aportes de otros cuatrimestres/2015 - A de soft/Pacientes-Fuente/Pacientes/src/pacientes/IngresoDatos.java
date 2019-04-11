package pacientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class IngresoDatos extends JDialog  {
	public IngresoDatos(Inicio inicio) {
		super(inicio, true);
		setTitle("Ingreso Datos");
		getContentPane().setLayout(null);
		this.setBounds(380, 200, 330, 300);
		setLocationRelativeTo(null);
		
		JButton btnDatosPaciente = new JButton("Datos Paciente");
		btnDatosPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				abrirDatosPaciente();				
			}
		});
		btnDatosPaciente.setBounds(66, 52, 171, 25);
		getContentPane().add(btnDatosPaciente);
		
		JButton btnSituacinDelPaciente = new JButton("Situación del Paciente");
		btnSituacinDelPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirSitPac();
			}
		});
		btnSituacinDelPaciente.setBounds(66, 90, 171, 25);
		getContentPane().add(btnSituacinDelPaciente);
		
		JButton btnDatosDelMdico = new JButton("Datos del médico");
		btnDatosDelMdico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDatosMed();				
			}
		});
		btnDatosDelMdico.setBounds(66, 126, 171, 25);
		getContentPane().add(btnDatosDelMdico);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			  
				dispose();			
			}
		});
		btnNewButton.setBounds(66, 182, 171, 25);
		getContentPane().add(btnNewButton);
	}
	
	private void abrirDatosPaciente() {
		DatosPaciente datopac = new DatosPaciente(this);				
		datopac.setVisible(true);	
	}
	
	private void abrirSitPac() {
		SituacionPaciente situpac = new SituacionPaciente(this);			
		situpac.setVisible(true);
	}
	
	private void abrirDatosMed() {
		DatosMedico datmed = new DatosMedico(this);				
		datmed.setVisible(true);
	}
}
