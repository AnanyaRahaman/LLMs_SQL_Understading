import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class more_info {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\anany\\Downloads\\syn.txt";     
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] queries = content.split(";");

            int queryNumber = 1;
            for (String sqlQuery : queries) {
                if (sqlQuery.trim().isEmpty()) {
                    continue;
                }
                processQuery(sqlQuery, queryNumber);
                queryNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processQuery(String sqlQuery, int queryNumber) {
        CharStream input = CharStreams.fromString(sqlQuery);
        PostgreSQLLexer lexer = new PostgreSQLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PostgreSQLParser parser = new PostgreSQLParser(tokens);

        parser.removeErrorListeners();
        SyntaxErrorListener errorListener = new SyntaxErrorListener();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.root();

        System.out.println(queryNumber + ". Processing SQL query:");
        if (errorListener.hasErrors()) {
            System.out.println("SQL query contains syntax errors:");
            System.out.println(errorListener.getSyntaxErrors());
        } else {
            System.out.println("SQL query is correct. Syntax Tree:");
            System.out.println(tree.toStringTree(parser)); // Print the entire tree in one line
        }
    }

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
            String error = String.format("Line %d:%d - %s%n", line, charPositionInLine, msg);
            syntaxErrors.append(error);
            System.out.println("Error at line " + line + ", position " + charPositionInLine + ": " + msg);
            if (e != null) {
                System.out.println("Suggested correction: " + suggestCorrection(msg));
            }
        }

        private String suggestCorrection(String msg) {
            if (msg.contains("mismatched input")) {
                return "Check SQL syntax near the error; possibly incorrect token.";
            } else if (msg.contains("no viable alternative")) {
                return "Check SQL syntax; incomplete expression or clause.";
            }
            return "Review SQL syntax.";
        }
    }
}
