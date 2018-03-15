package view;

/**
 * Holds two words of a command
 *
 */

public class Command {

    private CommandWord commandWord;
    private String secondWord;


    public Command (CommandWord commandWord, String secondWord) {

        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     *
     * @return Returns the first word of the command
     */

    public CommandWord getCommandWord() {

        return commandWord;
    }

    /**
     *
     * @return Returns second word of the command
     */

    public String getSecondWord() {

        return secondWord;
    }

    /**
     * Checks if the first word in the <code>Command</code> is <code>UNKNOWN</code>
     *
     * @return <code>true</code> if the first word is <code>UNKNOWN</code> and <code>false</code> if it is not
     */

    public boolean isUnknown() {

        return (commandWord == commandWord.UNKNOWN);
    }

    /**
     * Checks if the <code>Command</code> has second word
     *
     * @return <code>true</code> is it has second word, <code>false</code> if it doesn't
     */

    public boolean hasSecondWord() {

        return (secondWord != null);
    }
}
