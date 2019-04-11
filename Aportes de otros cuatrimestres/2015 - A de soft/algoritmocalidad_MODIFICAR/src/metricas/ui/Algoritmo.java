package metricas.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Algoritmo {
	private static JFrame frame;
	private static JLabel resultLabel = new JLabel();
	
	
	
    private static JPanel getPanel(Color c)
    {
        JPanel result = new JPanel();
        result.setBorder(BorderFactory.createLineBorder(c));
        result.setSize(550, 750);
        return result;
    }
    
    
	public static void main(String[] args) {
//		frame = new JFrame("Algoritmo de calidad");
//		frame.setSize(550, 750);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		popularFrame(frame.getContentPane());
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//		

			JPanel p1 = getPanel(Color.GRAY);
	        JPanel p2 = getPanel(Color.GRAY);

	        GridLayout gridLayout = new GridLayout(1,2);
	     
	        JPanel middlePanel = new JPanel(gridLayout);
	        
	        middlePanel.setSize(550, 750);
	        
	        popularFrame(p1);
	        middlePanel.add(p1);
	        
	        GridBagConstraints c = getInitialConstraint();
	        p2.setLayout(new GridBagLayout());
	        p2.add(resultLabel, c);
	        middlePanel.add(p2);

	        JFrame mainFrame = new JFrame("Algoritmo de calidad");
	        mainFrame.setSize(550, 750);
	        mainFrame.add(middlePanel, BorderLayout.CENTER);
	        mainFrame.pack();

	        mainFrame.setLocationRelativeTo(null);
	        mainFrame.setVisible(true);
		
		
	}

	public static void popularFrame(Container pane) {
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = getInitialConstraint();
	    
	    // Funcionalidad
	    pane.add(generateTitle("Funcionalidad"), c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Seguridad de acceso:", "Funcionalidad"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Inicio de sesion de usuarios:", "Funcionalidad"),c);
	    c.gridy++;
	    
	    // Eficiencia
	    pane.add(generateTitle("Eficiencia"), c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Utilizacion de recursos:", "Eficiencia"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Comportamiento frente al tiempo:", "Eficiencia"),c);
	    c.gridy++;
	    
	    // Fiabilidad
	    pane.add(generateTitle("Fiabilidad"), c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Tolerancia a fallos:", "Fiabilidad"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Capacidad de recuperacion de errores:", "Fiabilidad"),c);
	    c.gridy++;
	    
	    // Mantenibilidad
	    pane.add(generateTitle("Mantenibilidad"), c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Capacidad de codigo de ser analizado:", "Mantenibilidad"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Capacidad de codigo de ser cambiado:", "Mantenibilidad"),c);
	    c.gridy++;
	    
	    // Usabilidad
	    pane.add(generateTitle("Usabilidad"), c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Capacidad de ser entendido:", "Usabilidad"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Capacidad de ser operado:", "Usabilidad"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Capacidad de ser atractivo para el usuario:", "Usabilidad"),c);
	    c.gridy++;
	    
	    // Portabilidad
	    pane.add(generateTitle("Portabilidad"), c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Adaptabilidad:", "Portabilidad"),c);
	    c.gridy++;
	    pane.add(Utils.generatePanel("Instalabilidad:", "Portabilidad"),c);
	    c.gridy++;
	    
	    c.gridy++;
	    c.gridy++;
	    
	    
	    pane.add(generateCalculateButton("Calcular"),c);
	  }

	public static GridBagConstraints getInitialConstraint(){
		GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 9.0;
	    c.gridwidth = 0;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    return c;
	}
	
	public static JLabel generateTitle(String title){
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createLineBorder(Color.red));
		
		return label;
	}
	
	public static JButton generateCalculateButton(String title){
		JButton btnCalcular = new JButton(title);
		
		btnCalcular.addActionListener(new ActionListener() {
			
	         public void actionPerformed(ActionEvent e) {
	        	 Map resultados = new HashMap();
	        	 
	        	 Iterator entries = Utils.rbGroups.entrySet().iterator();
	        	 boolean esSatisfactorio = true;
	        	 
	        	 while (entries.hasNext()) {
	        	   Entry thisEntry = (Entry) entries.next();
	        	 
	        	   Object funcionCaracteristica = thisEntry.getKey();
	        	   
	        	   String[] parts = funcionCaracteristica.toString().split("-");
	        	   String funcion = parts[0]; 
	        	   String caracteristica = parts[1];
	        	   String valorSeleccionado = thisEntry.getValue().toString();

	        	   System.out.println("Funcion: " + funcion + " Caracteristica:"+caracteristica+" Value: " + valorSeleccionado);
	        	   
	        	   if(!resultados.containsKey(funcion)){
	        		   System.out.println("nuevo:"+funcion+" pondero:"+Utils.obtenerPonderacion(valorSeleccionado));	
	        		   resultados.put(funcion, (Utils.obtenerPonderacion(valorSeleccionado)));
	        	   }
	        	   else{ 
	        		   System.out.println("agrego:"+funcion+" pondero:"+( Integer.parseInt(resultados.get(funcion).toString()) + Utils.obtenerPonderacion(valorSeleccionado) ));	        		   
	        		   resultados.put(funcion, ( Integer.parseInt(resultados.get(funcion).toString()) + Utils.obtenerPonderacion(valorSeleccionado) ) );
	        	   }
	        	   
	        	   if(valorSeleccionado.equals("Malo")) esSatisfactorio = false;
	        	   
	        	 }
	        	 
	        	 
	        	 Iterator iResults = resultados.entrySet().iterator();
	        	 
	        	 String htmlOut = "<html>";
	        	 while (iResults.hasNext()) {
	        		 Entry thisEntry = (Entry) iResults.next();
	        		 
	        		 float result = 0;
	        		 if(thisEntry.getKey().toString().equals("Usabilidad")){
	        			 result = (Integer.parseInt(thisEntry.getValue().toString()) / 6f);
	        		 }
	        		 else{
	        			 result = (Integer.parseInt(thisEntry.getValue().toString()) / 4f);
	        		 }
	        		 
	        		 
	        		 System.out.println("parse: "+(Integer.parseInt(thisEntry.getValue().toString())+" result:" + result));
	        		 
	        		 String evaluacionSalida = "";
	        		 String descripcionCriterio = "";
	        		 if(thisEntry.getKey().equals("Funcionalidad")){
	        			descripcionCriterio = "Capacidad del producto software para asegurar la integridad de los datos y la confidencialidad de estos.";
	        			
	        			if(result <= 0.25f) evaluacionSalida = "No cumple con encriptacion de datos y no tiene inicio de sesion de usuarios";
	        			if(result > 0.25f && result < 0.75f) evaluacionSalida = "No cumple con encriptacion de datos o no tiene inicio de sesion de usuarios";
	        			if(result >= 0.75f) evaluacionSalida = "Cumple con encriptacion de datos y tiene inicio de sesion de usuarios";
	        			
	        			evaluacionSalida+= "<br>";
	        		 }
	        		 
	        		 if(thisEntry.getKey().equals("Eficiencia")){
	        			descripcionCriterio = "Se evaluará la eficiencia del producto software de acuerdo al porcentaje de uso de procesador que realice.";
	        			
	        			if(result <= 0.25f) evaluacionSalida = "41% o  mas de uso del procesador.";
	        			if(result > 0.25f && result < 0.75f) evaluacionSalida = "11% a 40% de uso del procesador.";
	        			if(result >= 0.75f) evaluacionSalida = "10% o menos de uso del procesador.";
	        			evaluacionSalida+= "<br>";
	        		 }
	        			 
        			 if(thisEntry.getKey().equals("Fiabilidad")){
        				descripcionCriterio = "Es la capacidad del producto software de mantener la integridad de los datos cuando se producen fallas del sistema.";
        				 
 	        			if(result <= 0.25f) evaluacionSalida = "El sistema no reanuda las actividades si se produce una falla critica y no vuelve al estado en que estaba";
 	        			if(result > 0.25f && result < 0.75f) evaluacionSalida = "El sistema no reanuda las actividades si se produce una falla critica o no vuelve al estado en que estaba";
 	        			if(result >= 0.75f) evaluacionSalida = "El sistema reanuda las actividades si se produce una falla critica y vuelve al estado en que estaba";
 	        			evaluacionSalida+= "<br>";
        			 }
	        				 
    				 if(thisEntry.getKey().equals("Mantenibilidad")){
    					descripcionCriterio = "Para evaluar la capacidad que tiene el código para ser analizado se tiene en cuenta el porcentaje de comentarios que posee el código por cada método y en general.";
    					 
  	        			if(result <= 0.25f) evaluacionSalida = "14% o menos del código comentado.";
  	        			if(result > 0.25f && result < 0.75f) evaluacionSalida = "Entre 15 y 29% del código comentado.";
  	        			if(result >= 0.75f) evaluacionSalida = "30% o más del código comentado";
  	        			evaluacionSalida+= "<br>";
    				 }
    				 
    				 if(thisEntry.getKey().equals("Usabilidad")){
    					 descripcionCriterio = "Capacidad que posee el software, para ayudar a los usuarios ante una determinada situación donde se necesite asistencia.";

    					 if(result <= 0.25f) evaluacionSalida = "No cumple con ayuda contextual sobre menús y botones de acción ni Manual de usuario incorporado";
	   	        		 if(result > 0.25f && result < 0.75f) evaluacionSalida = "Cumple con ayuda contextual sobre menús y botones de acción o Manual de usuario incorporado";
	   	        		 if(result >= 0.75f) evaluacionSalida = "No cumple con ayuda contextual sobre menús y botones de acción y Manual de usuario incorporado";
    					 
    					 evaluacionSalida+= "<br>";
    				 }
    				 
    				 if(thisEntry.getKey().equals("Portabilidad")){
    					 descripcionCriterio = "Es la capacidad del producto software de adaptarse a diferentes sistemas operativos sin cambiar su estructura interna.";

    					 if(result <= 0.25f) evaluacionSalida = "Compatible con 1 sistema operativo.";
	   	        		 if(result > 0.25f && result < 0.75f) evaluacionSalida = "Compatible con 2 sistemas operativos.";
	   	        		 if(result >= 0.75f) evaluacionSalida = "Compatible con 3 o mas sistemas operativos.";
    					 
    					 evaluacionSalida+= "<br>";
    				 }
    				 
    				 htmlOut = htmlOut + "<br>"+thisEntry.getKey()+"<br>"+descripcionCriterio+"<br><p style='color:blue'>Puntaje:"+ Utils.getPorcentaje(result) + " > "+ evaluacionSalida + "</p>";    				 
    				 
	        		 
	        		 
	        	 }
	        	 
	        		 htmlOut = htmlOut +"<p style='color:red'>"+ ((esSatisfactorio)?"<br>Es SATISFACTORIO":"<br>Es NO SATISFACTORIO") +"</p>";
	        	 
	        	 resultLabel.setText(htmlOut + "</html>");
	        	 
	         }
	         
	      });
		
		return btnCalcular;
	}
}
