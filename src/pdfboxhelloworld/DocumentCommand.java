/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxhelloworld;

/**
 *
 * @author erik
 */
public abstract class DocumentCommand extends Command {

    /**
     *
     */
    private DocumentCommandWrapper owner;

    /**
     *
     * @param anOwner
     * @param args
     */
    DocumentCommand() {
        super();
    }

    /**
     *
     * @return A Command instance that implements a reciprocal operation to
     * whatever operation(s) the receiver performs when executed.
     */
    public abstract Command makeReciprocalCommand();

    /**
     *
     * @return The DocumentCommandWrapper that owns the Command. The
     * DocumentCommandWrapper may be used in the implementation of commands. For
     * example, a Command may call methods of the owner's document.
     */
    public DocumentCommandWrapper getOwner() {
        return owner;
    }

    /**
     *
     * @param anOwner
     */
    public void setOwner(DocumentCommandWrapper anOwner) {
        owner = anOwner;
    }
}
