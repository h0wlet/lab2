
import command.Command;
import command.CommandType;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Workflow {
    static Logger logger = Logger.getLogger(CommandFactory.class.getName());

    public void workflowWorks(String fileName) throws Exception {
        ArrayList<String> text = new ArrayList<>();
        Parser parser = new Parser();

        try {
            parser.parserWorks(fileName);
        } catch (Exception ex) {
            //throw new Exception("Parser!");
            logger.log(Level.WARNING, "Problem with parser!", ex);
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
                //throw new Exception("Read!");
                logger.log(Level.WARNING, "Wrong sequence format!");
            }
            if (i == commands.size() - 1 && command.getType() != CommandType.OUT) {
                //throw new Exception("Write!");
                logger.log(Level.WARNING, "Wrong sequence format!");
            }

            try {
                command.execute(text, arr);
            } catch (Exception ex) {
                //throw new Exception("Problem with command creation!");
                logger.log(Level.WARNING, "Create command!", ex);
            }
        }
    }

}
