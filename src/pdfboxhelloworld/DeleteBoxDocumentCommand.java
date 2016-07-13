/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxhelloworld;

import static java.lang.Float.parseFloat;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erik
 */
public class DeleteBoxDocumentCommand extends DocumentCommand {

    /**
     *
     * @return A new Command instance that implements a reciprocal operation to
     * whatever operation(s) the receiver performs when executed.
     */
    @Override
    public Command makeReciprocalCommand() {
        AddBoxDocumentCommand result = new AddBoxDocumentCommand();
        assert null != result;

        result.setArgs(arguments);
        result.setOwner(this.getOwner());

        return result;
    }

        public boolean execute(Stack<Command> undoStack) {
        boolean result = super.execute(undoStack);
        assert null != arguments && arguments.length == 5;
        try {
            Float pageNumber = parseFloat(arguments[0]);
            Float lowerLeftX = parseFloat(arguments[1]);
            Float lowerLeftY = parseFloat(arguments[2]);
            Float width = parseFloat(arguments[3]);
            Float height = parseFloat(arguments[4]);

            // ToDo: Implement adding box to document
            result = true;
        } catch (NumberFormatException | NullPointerException ex) {
            Logger.getLogger(CommandManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
