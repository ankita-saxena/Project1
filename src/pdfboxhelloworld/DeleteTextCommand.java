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
public class DeleteTextCommand extends DocumentCommand {

    /**
     *
     * @return A new Command instance that implements a reciprocal operation to
     * whatever operation(s) the receiver performs when executed.
     */
    @Override
    public Command makeReciprocalCommand() {
        AddTextCommand result = new AddTextCommand();
        assert null != result;

        result.setArgs(arguments);
        result.setOwner(this.getOwner());

        return result;
    }

        public boolean execute(Stack<Command> undoStack) {
        boolean result = super.execute(undoStack);
        assert null != arguments && arguments.length == 5;
        try {
            Integer pageNumber = Integer.parseInt(arguments[0]);
            Integer posToInsert = Integer.parseInt(arguments[1]);
            String textToInsert = arguments[2];

            // ToDo: Implement adding box to document
            result = true;
        } catch (NumberFormatException | NullPointerException ex) {
            Logger.getLogger(CommandManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
