/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo1.gestiontesting;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
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
    public void Proccess(){
        try {
            File fitxer = new File("D:\\UNLaM\\Analisis de Software\\analisis.software\\TP\\Projecte\\src\\botiga\\main.java");
            CompilationUnit compilationUnit = StaticJavaParser.parse(fitxer);
            
            List<String> methodNames = new ArrayList<>();
            VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
            methodNameCollector.visit(compilationUnit, methodNames);
            methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
