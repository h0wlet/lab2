package command;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.*;

public class Grep implements Command {

    static Logger logger = Logger.getLogger(Sort.class.getName());

    @Override
    public void execute(ArrayList<String> text, Vector<String> arguments) throws Exception {
        //grep <word> – выбор из входного текста строк, разделенных символами переноса строки, содержащих заданное слово <word>.
        logger.info("Execution began!");
        if (arguments.size() != 1) {
            logger.log(Level.WARNING, "Args for grep != 1");
            throw new WrongNumberOfArguments("Args for grep != 1");
        }

        String word = arguments.get(0);
        for (int i = 0; i < text.size(); i++){
            if (text.get(i).lastIndexOf(word) == -1){
                text.remove(i);
                i--;
            }
        }
        logger.info("Execution completed successfully!");
    }

    @Override
    public CommandType getType() {
        return CommandType.IN_OUT;
    }
}
