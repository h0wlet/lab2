package command;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sort implements Command {
    static Logger logger = Logger.getLogger(Sort.class.getName());

    @Override
    public void execute(ArrayList<String> text, Vector<String> arguments) throws Exception {
        //sort – лексикографическая сортировка входного набора строк.
        logger.info("Execution began!");
        if (arguments.size() != 0) {
            //throw new Exception("Args for sort != 0");
            logger.log(Level.WARNING, "Args for sort != 0");
        }

        text.sort(String::compareTo);
        logger.info("Execution completed successfully!");
    }

    @Override
    public CommandType getType() {
        return CommandType.IN_OUT;
    }
}