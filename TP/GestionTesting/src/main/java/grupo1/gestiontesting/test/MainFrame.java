package grupo1.gestiontesting.test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame {

	//Components
	JFrame frame = new JFrame("Herramienta de Testing");
	JFileChooser fileChooser = new JFileChooser();
	JMenuBar menubar = new JMenuBar();
	JMenu analisis = new JMenu("Análisis");
	JMenuItem elegirCarpeta = new JMenuItem("Elegir Carpeta...");
	
	//Other
	private String filePath;
	
	public MainFrame() {
		// make the frame half the height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(855, 690);
		
		// here's the part where i center the jframe on screen
		frame.setLocationRelativeTo(null);
		
		ImageIcon img = new ImageIcon("D:\\Proyectos\\analisis.software\\TP\\GestionTesting\\target\\images\\herramienta-icon-5.jpg");
		frame.setIconImage(img.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setJMenuBar(menubar);
		menubar.add(analisis);
		analisis.add(elegirCarpeta);
		elegirCarpeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elegirCarpeta(e);
			}
		});
		
		frame.setVisible(true);
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
	        File[] fileArray = directory.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".java");
				}
			});
	        
	        List<File> fileList = Arrays.asList(fileArray);
	        
	        fileList.stream().forEach(fl -> System.out.println(fl.getName()));
		}
		
	}

	public static void main(String args[]) {
        MainFrame mf = new MainFrame();
    }
	
}
