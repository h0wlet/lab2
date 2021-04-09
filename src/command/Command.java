package command;

import java.util.*;

public interface Command {
    void execute(ArrayList<String> text, Vector<String> arguments) throws Exception;
    CommandType getType();
}
