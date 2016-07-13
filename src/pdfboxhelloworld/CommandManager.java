/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxhelloworld;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erik
 */
public class CommandManager {

    /**
     * A dictionary mapping command names to Command instances.
     */
    private final Hashtable<String, Constructor<?>> commandDictionary;
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandManager() {
        this.commandDictionary = new Hashtable<String, Constructor<?>>();
        this.undoStack = new Stack<Command>();
        this.redoStack = new Stack<Command>();
    }

    /**
     *
     * @param aString The name of a command that has been previously registered.
     * @return The Command previously registered with the name aString or null
     * if no command has been registered with the name, aString.
     */
    public Command makeCommandWithName(String aString) {
        Command result = null;

        if (commandDictionary.containsKey(aString)) {
            Constructor<?> ctor = commandDictionary.get(aString);
            try {
                result = (Command)ctor.newInstance(new Object[]{});
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(CommandManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    /**
     * Associate aCommand with aString name so that future calls to
     * getCommandForString() will return aCommand.
     *
     * @param theClass The Class to register. It must have a constructor that 
     * takes no arguments.
     * @param aString The name the class should be associated with
     */
    public void registerCommandClassNameWithName(Class<?> theClass, String aString) {

        try {
            Constructor<?> ctor = theClass.getConstructor(new Class<?>[]{});
            
            commandDictionary.put(aString, ctor);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(CommandManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method pops the top most Command in the Undo stack and executes the
     * Command. As a side effect, the reciprocal of the popped command is pushed
     * onto the Redo stack.
     *
     * @return The value returned from executing the Command at the top of the
     * Undo stack.
     */
    public boolean undo() {
        boolean result = false;

        if (!undoStack.empty()) {
            Command undoCommand = undoStack.pop();
            assert null != undoCommand;

            result = undoCommand.execute(this.redoStack);
        }

        return result;
    }

    /**
     * This method pops the top most Command in the Redo stack and executes the
     * Command. As a side effect, the reciprocal of the popped command is pushed
     * onto the Undo stack.
     *
     * @return The value returned from executing the Command at the top of the
     * Redo stack.
     */
    public boolean redo() {
        boolean result = false;

        if (!redoStack.empty()) {
            Command redoCommand = redoStack.pop();
            assert null != redoCommand;

            result = redoCommand.execute(undoStack);
        }

        return result;
    }

}
