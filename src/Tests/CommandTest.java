package Tests;

import command.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class CommandTest {
    @Test
    public void dump() throws Exception {
        Dump dumpTest = new Dump();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textAfterDump = new ArrayList<>();
        Vector<String> arguments = new Vector<>();

        for (int i = 0; i < 3; i++){
            text.add("Test" + i);
        }
        arguments.add("C:\\Users\\Лена\\lab2\\src\\Tests\\Dump_out.txt");
        dumpTest.execute(text, arguments);

        File file = new File(arguments.get(0));
        Scanner in = new Scanner(file);

        String line;
        while(in.hasNextLine()){
            line = in.nextLine();
            textAfterDump.add(line);
        }

        in.close();
        assertEquals(text, textAfterDump);
    }

    @Test
    public void grep() throws Exception {
        Grep grepTest = new Grep();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textAfterGrep = new ArrayList<>();
        Vector<String> arguments = new Vector<>();

        for (int i = 0; i < 3; i++){
            textAfterGrep.add("Test" + i);
        }
        arguments.add("Test0");
        text.addAll(textAfterGrep);
        text.remove(2);
        text.remove(1);

        grepTest.execute(textAfterGrep, arguments);

        assertEquals(text, textAfterGrep);
    }

    @Test
    public void read() throws Exception {
        ReadFile readTest = new ReadFile();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textAfterRead = new ArrayList<>();
        Vector<String> arguments = new Vector<>();

        for (int i = 0; i < 3; i++){
            text.add("Test" + i);
        }

        arguments.add("C:\\Users\\Лена\\lab2\\src\\Tests\\Read_in.txt");
        readTest.execute(textAfterRead, arguments);

        assertEquals(text, textAfterRead);
    }

    @Test
    public void replace() throws Exception {
        Replace replaceTest = new Replace();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textAfterReplace = new ArrayList<>();
        Vector<String> arguments = new Vector<>();

        for (int i = 0; i < 3; i++){
            text.add("Test" + i);
        }
        arguments.add("Test0");
        arguments.add("0test");

        textAfterReplace.addAll(text);

        replaceTest.execute(textAfterReplace, arguments);

        text.set(0, "0test");

        assertEquals(text, textAfterReplace);
    }

    @Test
    public void sort() throws Exception {
        Sort sortTest = new Sort();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textAfterSort = new ArrayList<>();
        Vector<String> arguments = new Vector<>();

        textAfterSort.add("c_Test");
        textAfterSort.add("a_Test");
        textAfterSort.add("b_Test");

        sortTest.execute(textAfterSort, arguments);

        text.add("a_Test");
        text.add("b_Test");
        text.add("c_Test");

        assertEquals(text, textAfterSort);
    }

    @Test
    public void write() throws Exception {
        WriteFile writeTest = new WriteFile();
        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> textAfterWrite = new ArrayList<>();
        Vector<String> arguments = new Vector<>();

        for (int i = 0; i < 3; i++){
            text.add("Test" + i);
        }

        arguments.add("C:\\Users\\Лена\\lab2\\src\\Tests\\Write_out.txt");
        writeTest.execute(text, arguments);

        File file = new File(arguments.get(0));
        Scanner in = new Scanner(file);

        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            textAfterWrite.add(line);
        }

        in.close();

        assertEquals(text, textAfterWrite);
    }
}