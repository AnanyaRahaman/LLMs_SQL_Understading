import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class RunPostgreSQLQueryFinal {

    public static void main(String[] args) {
        String sqlQuery = "select * from users"; // Your SQL query

        // Convert your input string to an ANTLR input stream.
        CharStream input = CharStreams.fromString(sqlQuery);

        // Create a lexer that feeds off of input CharStream.
        PostgreSQLLexer lexer = new PostgreSQLLexer(input);

        // Create a buffer of tokens pulled from the lexer.
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer.
        PostgreSQLParser parser = new PostgreSQLParser(tokens);

        // Add a custom error listener to report syntax errors.
        parser.removeErrorListeners(); // Remove default console error listener.
        SyntaxErrorListener errorListener = new SyntaxErrorListener();
        parser.addErrorListener(errorListener);

        // Begin parsing at root rule.
        ParseTree tree = parser.root(); // Assuming 'root' is the starting rule.

        // Check if any syntax errors were encountered.
        if (errorListener.hasErrors()) {
            System.out.println("The SQL query contains syntax errors:");
            System.out.println(errorListener.getSyntaxErrors());
        } else {
            System.out.println("The SQL query is correct.");
            // Print the syntax tree in LISP-style notation.
            System.out.println("Syntax Tree: " + tree.toStringTree(parser));
        }
    }

    // Custom error listener class to capture syntax errors.
    private static class SyntaxErrorListener extends BaseErrorListener {
        private final StringBuilder syntaxErrors = new StringBuilder();
        private boolean hasErrors = false;

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            hasErrors = true;
            syntaxErrors.append("line ").append(line).append(":").append(charPositionInLine).append(" ").append(msg).append("\n");
        }

        public boolean hasErrors() {
            return hasErrors;
        }

        public String getSyntaxErrors() {
            return syntaxErrors.toString();
        }
    }
}
