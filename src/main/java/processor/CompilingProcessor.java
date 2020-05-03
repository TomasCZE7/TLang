package processor;

import core.ApplicationMain;
import core.variable.Variable;
import processor.Processor;
import processor.VariableProcessor;
import util.FileParser;
import window.ConsoleWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompilingProcessor extends Processor {

    private String[] lines;
    private VariableProcessor variableProcessor;
    private FileParser compileFileParser;

    public CompilingProcessor() {
        compileFileParser = new FileParser("src/main/resources/src.compile.tl");
    }

    @Override
    public void process(String source) {
        ApplicationMain.tLang.getConsoleTextArea().setText("");
        System.out.println("Starting compilation process...");
        this.variableProcessor = new VariableProcessor();
        variableProcessor.clear();
        this.lines = source.split("\n");
        for(int i = 0; i < lines.length; i++){
            String line = lines[i];
            line = line.split("//")[0];
            line = line.trim();
            if(!line.isEmpty() && line.endsWith(";"))
                line = line.substring(0, line.length()-1);
            if(line.contains("=")) {
                variableProcessor.process(line);
            }
            lines[i] = line;
        }
        compileFileParser.write(lines);
        System.out.println("Compilation ended successfully.");
    }

    @Override
    public void clear() {

    }
}
