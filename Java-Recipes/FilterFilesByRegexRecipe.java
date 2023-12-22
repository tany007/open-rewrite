import org.openrewrite.*;
import java.nio.file.Path;
import java.util.List;

public class FilterFilesByRegexRecipe extends Recipe {
    private final String regexPattern;

    public FilterFilesByRegexRecipe(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    @Override
    protected List<SourceFile> visit(List<SourceFile> sourceFiles, ExecutionContext context) {
        return refactor(sourceFiles, file -> {
            Path filePath = file.getSourcePath();
            String fileName = filePath.getFileName().toString();

            // Filter files based on the regex pattern
            if (fileName.matches(regexPattern)) {
                // Include the file if it matches the pattern
                return file;
            } else {
                // Exclude the file if it doesn't match the pattern
                return null;
            }
        });
    }
}
