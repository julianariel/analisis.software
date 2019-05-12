package grupo1.gestiontesting.test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import grupo1.gestiontesting.Parser;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollBar;

public class MainFrame {

	/**
	 * Components
	 */
	//Frame
	JFrame frame = new JFrame("Herramienta de Testing");
	//FileChooser
	JFileChooser fileChooser = new JFileChooser();
	//Menu
	JMenuBar menubar = new JMenuBar();
	JMenu analisis = new JMenu("Análisis");
	JMenuItem elegirCarpeta = new JMenuItem("Elegir Carpeta...");
	//Lists
	JList listArchivosJava = new JList();
	DefaultListModel listModelArchivos = new DefaultListModel();
	JList listClases = new JList();
	JList listMetodos = new JList();
	DefaultListModel listModelMetodos = new DefaultListModel();
	//ScrollPane
	JScrollPane scrollPaneArchivosJava = new JScrollPane();
	JScrollPane scrollPaneMetodos = new JScrollPane();
	JScrollPane scrollPaneCodigo = new JScrollPane();
	//TextArea
	JTextArea textAreaCodigo = new JTextArea();
	
	
	//Other
	private String filePath;
	private File[] fileArray;
	private File[] metodos;
	private final Parser parser = new Parser();
	private List<MethodDeclaration> methods;
	
	public MainFrame() {
		// make the frame half the height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int height = screenSize.height;
//		int width = screenSize.width;
		frame.setSize(855, 690);
		
		// here's the part where i center the jframe on screen
		frame.setLocationRelativeTo(null);
		
		ImageIcon img = new ImageIcon("D:\\Proyectos\\analisis.software\\TP\\GestionTesting\\target\\images\\herramienta-icon-5.jpg");
		frame.setIconImage(img.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menubar);
		menubar.add(analisis);
		analisis.add(elegirCarpeta);
		frame.getContentPane().setLayout(null);
		
		textAreaCodigo.setFont(new Font("Mongolian Baiti", Font.BOLD, 13));
		textAreaCodigo.setBounds(10, 423, 819, 196);
		textAreaCodigo.setEditable(false);
		frame.getContentPane().add(textAreaCodigo);
		
		JPanel panel = new JPanel();
		panel.setBounds(634, 11, 195, 401);
		frame.getContentPane().add(panel);
		
		listArchivosJava.setBounds(10, 34, 609, 147);
		listArchivosJava.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listJavaFileValueChanged(e);
			}
		});
		frame.getContentPane().add(listArchivosJava);
		
		
		listClases.setBounds(10, 231, 299, 147);
		frame.getContentPane().add(listClases);
		
		JLabel labelSeleccioneArchivo = new JLabel("Seleccione un archivo de la lista:");
		labelSeleccioneArchivo.setFont(new Font("Calibri", Font.BOLD, 12));
		labelSeleccioneArchivo.setForeground(Color.BLUE);
		labelSeleccioneArchivo.setBounds(10, 11, 396, 24);
		frame.getContentPane().add(labelSeleccioneArchivo);
		
		
		listMetodos.setBounds(319, 231, 299, 147);
		listMetodos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listMethodValueChanged(e);
			}
		});
		frame.getContentPane().add(listMetodos);
		
		JLabel labelSeleccioneClase = new JLabel("Seleccione una clase de la lista:");
		labelSeleccioneClase.setFont(new Font("Calibri", Font.BOLD, 12));
		labelSeleccioneClase.setForeground(Color.BLUE);
		labelSeleccioneClase.setBounds(10, 209, 299, 24);
		frame.getContentPane().add(labelSeleccioneClase);
		
		JLabel label = new JLabel("Seleccione un método de la lista:");
		label.setFont(new Font("Calibri", Font.BOLD, 12));
		label.setForeground(Color.BLUE);
		label.setBounds(319, 209, 299, 24);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Código del método seleccionado:");
		label_1.setFont(new Font("Calibri", Font.BOLD, 12));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(10, 399, 299, 24);
		frame.getContentPane().add(label_1);
		
		
		scrollPaneArchivosJava.setBounds(598, 34, 19, 147);
//		scrollPaneArchivosJava.setViewportView(listArchivosJava);
		frame.getContentPane().add(scrollPaneArchivosJava);
		
		scrollPaneMetodos.setBounds(598, 231, 19, 147);
//		scrollPaneMetodos.setViewportView(listMetodos);
		frame.getContentPane().add(scrollPaneMetodos);
		
		scrollPaneCodigo.setBounds(810, 423, 19, 196);
//		scrollPaneCodigo.setViewportView(textAreaCodigo);
		frame.getContentPane().add(scrollPaneCodigo);
		elegirCarpeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elegirCarpeta(e);
			}
		});
		
		frame.setVisible(true);
	}
	
	protected void listMethodValueChanged(ListSelectionEvent e) {
        MethodDeclaration method;
        method = methods.stream().filter(p -> p.getNameAsString().equals(listMetodos.getSelectedValue())).collect(Collectors.toList()).get(0);
        
        for(Statement statement : method.getBody().get().getStatements())
        {
        	textAreaCodigo.append(statement.toString());
        	textAreaCodigo.append("\n");
        }
	}

	protected void listJavaFileValueChanged(ListSelectionEvent e) {
		methods = parser.getMethods(filePath + "\\" + listArchivosJava.getSelectedValue());
		
		for(int i=0; i < methods.size(); i++) {
			listModelMetodos.add(i, methods.get(i).getNameAsString());
		} 
		listMetodos.setModel(listModelMetodos);
	}

	protected void elegirCarpeta(ActionEvent e) {
		fileChooser.setAccessory(elegirCarpeta);
		fileChooser.setDialogTitle("Elegir carpeta");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(frame);
		
		if(result == JFileChooser.CANCEL_OPTION) {
			filePath = "";
		} else if(result == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getAbsolutePath();
			
			File directory = new File(filePath);
	        fileArray = directory.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".java");
				}
			});
	        
	        for(int i=0; i < fileArray.length; i++) {
	        	String nombreArchivoJava = fileArray[i].getName();
	        	listModelArchivos.add(i, nombreArchivoJava);
	        }
	        
	        listArchivosJava.setModel(listModelArchivos);
	        
		}
	}

	public static void main(String args[]) {
        MainFrame mf = new MainFrame();
    }
}
