import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class syntax_tree{

    public static void main(String[] args) {
        String filePath = "C:\\Users\\anany\\Downloads\\user_study.txt";     
        try {
            // Read the content of the file
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            // Assume each query is separated by a semicolon
            String[] queries = content.split(";");

            int queryNumber = 1; // Initialize a counter for numbering the queries

            for (String sqlQuery : queries) {
                if (sqlQuery.trim().isEmpty()) {
                    continue; // Skip empty queries
                }
                // Process each query, passing the current query number
                processQuery(sqlQuery, queryNumber);
                queryNumber++; // Increment the counter for the next query
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processQuery(String sqlQuery, int queryNumber) {
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

        // Begin parsing at root rule, replace 'root' with the actual root rule of your grammar.
        ParseTree tree = parser.root();

        // Print the syntax tree in LISP-style notation regardless of errors.
        System.out.println(queryNumber + ". Syntax Tree: " + tree.toStringTree(parser));

        // Check if any syntax errors were encountered and report them.
        if (errorListener.hasErrors()) {
            System.out.println("SQL query contains syntax errors:");
            System.out.println(errorListener.getSyntaxErrors());
        } else {
            System.out.println("SQL query is correct.");
        }
    }

    // Custom error listener class
    public static class SyntaxErrorListener extends BaseErrorListener {
        private final StringBuilder syntaxErrors = new StringBuilder();

        public boolean hasErrors() {
            return syntaxErrors.length() > 0;
        }

        public String getSyntaxErrors() {
            return syntaxErrors.toString();
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            syntaxErrors.append("line ")
                        .append(line)
                        .append(":")
                        .append(charPositionInLine)
                        .append(" ")
                        .append(msg)
                        .append("\n");
        }
    }
}
