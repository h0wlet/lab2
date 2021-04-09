
import command.Sort;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    static Logger logger = Logger.getLogger(Sort.class.getName());

    private final Map<String, Vector<String>> _commands;
    private final Vector<String> _sequence;

    public Parser() {
        _commands = new HashMap<>();
        _sequence = new Vector<>();
    }

    public void parserWorks(String fileName) throws Exception {
        logger.info("Parser started work!");
        boolean checkCSED = true;

        File file = new File(fileName);
        Scanner in = new Scanner(file);

        String line = in.nextLine();
        if (!line.equals("desc")){
            //throw new IOException("Wrong workflow format!");
            logger.log(Level.WARNING, "Wrong workflow format!");
        }

        while (in.hasNextLine()) {
            Vector<String> args = new Vector<>();

            line = in.nextLine();
            String[] arr = line.split(" ");

            Collections.addAll(args, arr);

            if ((args.get(0).equals("csed")) && (args.size() == 1)) {
                checkCSED = false;
                break;
            }

            String key = args.get(0);
            args.remove(0);
            args.remove(0);
            _commands.put(key, args);
        }

        if (checkCSED) {
            //throw new Exception("Csed was not found!");
            logger.log(Level.WARNING, "Csed was not found!");
        }

        line = in.nextLine();
        if (line.equals("")) {
            //throw new Exception("Сommand sequence was not found!");
            logger.log(Level.WARNING, "Сommand sequence was not found!");
        }
        String[] arr = line.split(" ");

        int count = 0;
        for (var i : arr) {
            if (count % 2 != 0 && !i.equals("->")) {
                //throw new Exception("Wrong sequence format!");
                logger.log(Level.WARNING, "Wrong sequence format!");
            }
            if (count % 2 == 0 && !i.equals("->")) _sequence.add(i);
            count++;
        }
        in.close();
        logger.info("Parser finished work!");
    }

    public Map<String, Vector<String>> getCommands() {
        return _commands;
    }

    public Vector<String> getSequence() {
        return _sequence;
    }
}
