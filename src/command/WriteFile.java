package command;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile implements Command {
    static Logger logger = Logger.getLogger(Sort.class.getName());

    @Override
    public void execute(ArrayList<String> text, Vector<String> arguments) throws Exception {
        //writefile <filename> – запись текста в файл.
        logger.info("Execution began!");

        if (arguments.size() != 1) {
            //throw new Exception("Args for write != 1");
            logger.log(Level.WARNING, "Args for write != 1");
        }

        FileWriter writer = null;
        PrintWriter printer = null;

        try {
            writer = new FileWriter(arguments.get(0));
            printer = new PrintWriter(writer);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "File problems!", ex);
        }

        for(var i : text){
            printer.println(i);
        }

        printer.close();
        logger.info("Execution completed successfully!");
    }

    @Override
    public CommandType getType() { return CommandType.OUT; }
}
