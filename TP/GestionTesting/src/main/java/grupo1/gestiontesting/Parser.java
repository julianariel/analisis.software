/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo1.gestiontesting;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian
 */
public class Parser {
	
    public List<MethodDeclaration> getMethods(String filePath){
        
    	List<MethodDeclaration> methodNames = new ArrayList<>();

        try {
//            File fitxer = new File("D:\\UNLaM\\Analisis de Software\\analisis.software\\TP\\Projecte\\src\\botiga\\main.java");
//            File fitxer = new File("D:\\UNLaM\\Analisis de Software\\analisis.software\\Triangulo\\src\\Triangulos\\Triangulo.java");
//            File fitxer = new File("D:\\Proyectos\\analisis.software\\Triangulo\\src\\Triangulos\\Triangulo.java");
            File fitxer = new File(filePath);
            CompilationUnit compilationUnit = StaticJavaParser.parse(fitxer);
       
            VoidVisitor<List<MethodDeclaration>> methodNameCollector = new MethodNameCollector();
            methodNameCollector.visit(compilationUnit, methodNames);
            compilationUnit.getComments().stream().forEach(fl -> System.out.println(fl.toString()));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return methodNames;            
    }

}
