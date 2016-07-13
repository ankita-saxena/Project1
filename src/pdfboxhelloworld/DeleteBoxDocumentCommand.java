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
public class DeleteBoxDocumentCommand  extends DocumentCommand {
    /**
     *
     * @return A Command instance that implements a reciprocal operation to
     * whatever operation(s) the receiver performs when executed.
     */
    @Override
    public Command getUndoCommand() {
        AddBoxDocumentCommand result = new AddBoxDocumentCommand();
        assert null != result;
        
        result.setArgs(arguments);
        result.setOwner(this.getOwner());
        
        return result;
    }
    
}
