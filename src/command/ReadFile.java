package command;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile implements Command {
    static Logger logger = Logger.getLogger(Sort.class.getName());

    @Override
    public void execute(ArrayList<String> text, Vector<String> arguments) throws Exception {
        //readfile <filename>  – считывание текстового файла в память, целиком.
        logger.info("Execution began!");

        if (arguments.size() != 1) {
            //throw new Exception("Args for read != 1");
            logger.log(Level.WARNING, "Args for read != 1");
        }

        File file = new File(arguments.get(0));
        Scanner in = new Scanner(file);
        String line;

        while(in.hasNextLine()){
            line = in.nextLine();
            text.add(line);
        }
        in.close();
        logger.info("Execution completed successfully!");
    }

    @Override
    public CommandType getType() {
        return CommandType.IN;
    }

}
