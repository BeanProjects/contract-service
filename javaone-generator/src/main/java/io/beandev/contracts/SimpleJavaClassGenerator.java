package io.beandev.contracts;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;

public class SimpleJavaClassGenerator {
    public static void main(String[] args) {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackageDeclaration("io.beandev.contracts.example");

        ClassOrInterfaceDeclaration myClass = cu.addClass("MyClass");
        myClass.addField(int.class, "myField", Modifier.Keyword.PRIVATE);

        MethodDeclaration myMethod = myClass.addMethod("myMethod", Modifier.Keyword.PUBLIC);
        myMethod.setType(void.class);
        myMethod.getBody().ifPresent(body ->
                body.addStatement("System.out.println(\"Hello, world!\");"));

        System.out.println(cu);

        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(SimpleJavaClassGenerator.class).resolve("../generated/sources/"));
        sourceRoot.add("specs.main.java.io.beandev.contracts.example", "MyClass.java", cu);
        // This saves all the files we just read to an output directory.
        sourceRoot.saveAll();
    }
}