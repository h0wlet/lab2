
import command.Command;
import command.CommandType;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.*;

public class Workflow {
    static Logger logger = Logger.getLogger(CommandFactory.class.getName());

    public void workflowWorks(String fileName) throws Exception {
        ArrayList<String> text = new ArrayList<>();
        Parser parser = new Parser();

        try {
            parser.parserWorks(fileName);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Problem with parser!", ex);
            throw new Exception("Problem with parser!", ex);
        }

        Map<String, Vector<String>> commands = parser.getCommands();
        Vector<String> sequence = parser.getSequence();

        for (int i = 0; i < sequence.size(); i++) {

            String commandName = commands.get(sequence.get(i)).get(0);
            Vector<String> arr = new Vector<>();

            for (int j = 1; j < commands.get(sequence.get(i)).size(); j++){
                arr.add(commands.get(sequence.get(i)).get(j));
            }

            Command command = CommandFactory.getInstance().getCommand(commandName);

            if (i == 0 && command.getType() != CommandType.IN) {
                logger.log(Level.WARNING, "Wrong sequence format!");
                throw new WrongSequenceFormat("Wrong sequence format!");
            }
            if (i == commands.size() - 1 && command.getType() != CommandType.OUT) {
                logger.log(Level.WARNING, "Wrong sequence format!");
                throw new WrongSequenceFormat("Wrong sequence format!");
            }

            try {
                command.execute(text, arr);
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Problem with command execute!", ex);
                throw new Exception("Problem with command execute!", ex);
            }
        }
    }

}
