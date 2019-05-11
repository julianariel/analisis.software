/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo1.gestiontesting;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

/**
 *
 * @author julian
 */
class MethodNameCollector extends VoidVisitorAdapter<List<MethodDeclaration>> {

    @Override
    public void visit(MethodDeclaration md, List<MethodDeclaration> collector) {
        super.visit(md, collector);
        
        collector.add(md);
    }
 }
