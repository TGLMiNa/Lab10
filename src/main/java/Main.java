import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileContent = FileUtils.readFileToString(
                new File("src/main/java/Utils.java"), Charset.defaultCharset());
        parse(fileContent);
    }

    /**
     * use ASTParse to parse string
     * @str - file content
     */
    public static void parse(String str) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodDeclaration method) {
                System.out.println(method.getName().getIdentifier());
                return true;
            }
        });
    }
}
