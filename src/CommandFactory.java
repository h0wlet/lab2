import command.Command;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

public class CommandFactory {
    static Logger logger = Logger.getLogger(CommandFactory.class.getName());

    private Properties config = new Properties();

    private CommandFactory() throws IOException {
        var configStream = CommandFactory.class.getResourceAsStream("CommandFactory.config");
        if (configStream == null){
            //throw new IOException("Unable to read config!");
            logger.log(Level.WARNING, "Unable to read config!");
        }
        config.load(configStream);
    }

    private static volatile CommandFactory instance;

    public static CommandFactory getInstance() throws IOException{
        if (instance == null){
            synchronized (CommandFactory.class) {
                if (instance == null){
                    instance = new CommandFactory();
                }
            }
        }
        return instance;
    }

    public Command getCommand(String commandName) throws Exception{
        if (!config.containsKey(commandName)){
            //throw new Exception("Command not found!");
            logger.log(Level.WARNING, "Command not found!");

        }

        Command command = null;
        try {
            var commandClass = Class.forName(config.getProperty(commandName));
            var commandObject = commandClass.getDeclaredConstructor().newInstance();
            command = (Command) commandObject;
        } catch (Exception ex){
            logger.log(Level.WARNING, "Unable to create command!", ex);
            //throw new Exception("Unable to create command!", ex);
        }
        return command;
    }
}