/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxhelloworld;

import java.util.Stack;

/**
 * This class encapsulates a commands that may be executed following the Command
 * Design Pattern. Each Command is able to supply a reciprocal command to be
 * executed if/when the undo is required. Undo and redo stacks are managed by
 * this class to enable undo and redo of ant Command.
 *
 * @author erik
 */
public abstract class Command {

    protected String[] arguments;

    /**
     *
     * @return A Command instance that implements a reciprocal operation to
     * whatever operation(s) the receiver performs when executed.
     */
    public abstract Command makeReciprocalCommand();

    /**
     *
     * @return
     */
    public boolean execute(Stack<Command> undoStack) {
        assert null != undoStack;

        Command undoCommand = this.makeReciprocalCommand();
        assert null != undoCommand;
        undoStack.push(undoCommand);

        return false;
    }

    /**
     *
     * @param args
     * @return
     */
    public Command() {
        this.arguments = new String[]{};
    }

    /**
     *
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
     */
    public void setArgs(String[] args) {
        this.arguments = args;
    }
}
