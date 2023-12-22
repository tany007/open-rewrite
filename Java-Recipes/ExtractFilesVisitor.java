import org.openrewrite.*;
import org.openrewrite.internal.lang.Nullable;
import org.openrewrite.java.JavaParser;
import org.openrewrite.java.JavaVisitor;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JavaType;
import java.util.List;

public class ExtractFilesVisitor extends JavaVisitor<J> {
    private final String regexPattern;
    private final String extractionDirectory;

    public ExtractFilesVisitor(String regexPattern, String extractionDirectory) {
        this.regexPattern = regexPattern;
        this.extractionDirectory = extractionDirectory;
    }

    @Override
    public J visitCompilationUnit(J.CompilationUnit cu) {
        List<J.Import> matchingImports = cu.getImports().stream()
                .filter(imp -> imp.getPackageName().printTrimmed().matches(regexPattern))
                .collect(Collectors.toList());

        for (J.Import matchingImport : matchingImports) {
            // Extract the matching import to a file in the extraction directory
            // You can use any file handling mechanism here (e.g., Java's Files API) to write the import to a file
            String importContent = matchingImport.printTrimmed();
            // Write `importContent` to a file in `extractionDirectory`
        }

        return cu;
    }
}

public class ExtractFilesRecipe extends Recipe {
    private final String regexPattern;
    private final String extractionDirectory;

    public ExtractFilesRecipe(String regexPattern, String extractionDirectory) {
        this.regexPattern = regexPattern;
        this.extractionDirectory = extractionDirectory;
    }

    @Override
    protected JavaVisitor<ExecutionContext> getVisitor() {
        return new ExtractFilesVisitor(regexPattern, extractionDirectory);
    }
}
