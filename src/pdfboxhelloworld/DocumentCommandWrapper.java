/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxhelloworld;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author erik
 */
public class DocumentCommandWrapper {

    /**
     * The document that will be modified by Commands.
     */
    private final PDDocument wrappedDocument;

    /**
     * The object that maintains a mapping from String names to Commands and
     * provided Undo and Redo support.
     */
    private final CommandManager commandManager;

    /**
     *
     * @param aPath A valid file system path to a valid PDF document.
     * @return An instance of DocumentCommandWrapper configured to apply
     * Commands to the document at aPath.
     * @throws IOException
     */
    private static DocumentCommandWrapper loadDosumentAtPath(String aPath) throws IOException {
        DocumentCommandWrapper result = null;
        PDDocument document = PDDocument.load(new File(aPath));

        if (null != document) {
            result = new DocumentCommandWrapper(document);
        }

        return result;
    }

    /**
     *
     * @param aDocument The document to which Commands will be applied.
     */
    public DocumentCommandWrapper(PDDocument aDocument) {
        assert null != aDocument;
        wrappedDocument = aDocument;
        commandManager = new CommandManager();
    }

    /**
     *
     * @param aString The name of a command that has been previously registered.
     * @param args An array of Strings encoding arguments to be used by the
     * command when the command is executed. For example: A hypothetical
     * AddBoxCommand might use arguments encoding a page number, lower left
     * corner X coordinate in Postscript Points (1/72 in) lower left corner Y
     * coordinate in Postscript Points (1/72 in) width in Postscript Points
     * (1/72 in) height in Postscript Points (1/72 in) i.e. ["3", "72.0",
     * "144.0", "36.5", "80.0"] is interpreted to mean Add Box to Page 3. The
     * box has lower left corner 1 inch (72 points) to the left of the page edge
     * and two 2 inches (144.0 points) down from the top of the page. The box
     * has a width of 36.5 points and a height of 80.0 points.
     * @return The Command previously registered with the name aString or null
     * if no command has been registered with the name, aString.
     */
    public DocumentCommand makeCommandWithName(String aString, String[] args) {
        DocumentCommand result = (DocumentCommand) commandManager.makeCommandWithName(aString);
        assert null != result;

        result.setOwner(this);
        result.setArgs(args);

        return result;
    }

}
