package metricas.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Utils {
	public static Map rbGroups = new HashMap();

	
	public static JPanel generatePanel(String caracteristica, String funcion){
		JPanel panelCaracteristicas = new JPanel();
		
		JLabel statusLabel = new JLabel(caracteristica); 
	    panelCaracteristicas.add(statusLabel);
		
	    valoresEncuesta(panelCaracteristicas,caracteristica, funcion);
	    panelCaracteristicas.setVisible(true); 
		return panelCaracteristicas;
	}
	
	private static void valoresEncuesta(JPanel panel, String caracteristica, String funcion) {
		rbGroups.put(funcion+"-"+caracteristica.substring(0, caracteristica.length()-1), "Regular");
		
		JRadioButton rbMalo = generarRB("Malo", KeyEvent.VK_M, caracteristica, funcion);
		JRadioButton rbRegular = generarRB("Regular", KeyEvent.VK_R, caracteristica, funcion, true);
		JRadioButton rbBueno = generarRB("Bueno", KeyEvent.VK_B, caracteristica, funcion);
			
	    ButtonGroup group = new ButtonGroup();
	    group.add(rbMalo);
	    group.add(rbRegular);
	    group.add(rbBueno);
	    
	    panel.add(rbMalo);
	    panel.add(rbRegular);
	    panel.add(rbBueno);      
	}
	
	
	private static JRadioButton generarRB(String RBName, int mnemonic, String panelName, String funcion ){
		return generarRB(RBName, mnemonic, panelName, funcion, false);
	}
	
	private static JRadioButton generarRB(String RBName, int mnemonic, String caracteristica, String funcion, boolean selected){
	  final JRadioButton rButton = new JRadioButton(RBName, selected);
		
	  rButton.setMnemonic(mnemonic);
	  rButton.setName(funcion+"-"+caracteristica+RBName);
	  
	  
	  rButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            JRadioButton btn = (JRadioButton) e.getSource();
	            System.out.println("Selected Button = " + btn.getName());
	            
	            String[] parts = btn.getName().split(":");
	            String p1 = parts[0]; 
	            String evaluacion = parts[1]; 
	            
	            String[] funcionCaracteristica = p1.split("-");
	            String funcion = funcionCaracteristica[0]; 
	            String caracteristica = funcionCaracteristica[1]; 
	            System.out.println("PRE:"+rbGroups.entrySet().size()+ " LOLA:"+funcion+"-"+caracteristica);
	            rbGroups.put(funcion+"-"+caracteristica, evaluacion);
	            System.out.println("POST:"+rbGroups.entrySet().size());
	        }
	    });
	  
	  
      return rButton;
	}
	
	public static int obtenerPonderacion(String valor){
		if (valor.equals("Malo"))
			return 0;

		if (valor.equals("Regular"))
			return 1;
		
		if (valor.equals("Bueno"))
			return 2;
		
		return 0;
	}
	
	public static String getPorcentaje(float result){
		if(result==1.0f)
			return "100%";
		
		return String.valueOf(result*100).substring(0, 2) + "%";
	}
}
