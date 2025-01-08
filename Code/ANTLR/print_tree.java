import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class print_tree {

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
            printTree(tree, parser, 0);
        }
    }

    private static void printTree(ParseTree tree, Parser parser, int indent) {
        String indentString = new String(new char[indent]).replace("\0", "    "); // Create an indentation string
        if (tree instanceof TerminalNode) {
            System.out.println(indentString + "'" + tree.getText() + "'");
        } else if (tree instanceof ParserRuleContext) {
            System.out.println(indentString + parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()]);
            for (int i = 0; i < tree.getChildCount(); i++) {
                printTree(tree.getChild(i), parser, indent + 1);
            }
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
