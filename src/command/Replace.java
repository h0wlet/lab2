package command;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.*;

public class Replace implements Command {
    static Logger logger = Logger.getLogger(Sort.class.getName());

    @Override
    public void execute(ArrayList<String> text, Vector<String> arguments) throws Exception {
        //replace <word1> <word2> – замена слова <word1> словом <word2> во входном тексте.
        logger.info("Execution began!");
        if (arguments.size() != 2) {
            logger.log(Level.WARNING, "Args for replace != 2");
            throw new WrongNumberOfArguments("Args for replace != 2");
        }
        String word1 = arguments.get(0);
        String word2 = arguments.get(1);
        for (int i = 0; i < text.size(); i++){
            String line = text.get(i);
            line = line.replaceAll(word1, word2);
            text.remove(i);
            text.add(i, line);
        }
        logger.info("Execution completed successfully!");
    }

    @Override
    public CommandType getType() {
        return CommandType.IN_OUT;
    }
}
