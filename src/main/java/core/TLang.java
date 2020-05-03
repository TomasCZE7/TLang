package core;

import math.LangMath;
import math.Vector2;
import math.Vector4;
import processor.CompilingProcessor;
import util.FileParser;
import window.ConsoleWindow;
import window.Window;
import window.component.ButtonComponent;
import window.component.ScrollPanelComponent;
import window.component.TableComponent;
import window.WindowType;
import window.component.TextComponent;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;

public class TLang {

    private Window mainWindow;
    private Window variablesWindow;
    private CompilingProcessor compilingProcessor;
    private TableComponent variableTable;
    private FileParser sourceFileParser = new FileParser("src/main/resources/src.tl");
    private JTextArea consoleTextArea;

    public TLang(){
        compilingProcessor = new CompilingProcessor();
        mainWindow = new Window("Editor", WindowType.EDITOR)
                .setSize(new Vector2<>(1300, 680));
        JTextArea codeArea = new JTextArea();
        codeArea.setText(sourceFileParser.readIntoString());
        ScrollPanelComponent codeScrollPanel = (ScrollPanelComponent) new ScrollPanelComponent(codeArea)
                        .setPositionAndSize(
                            new Vector4<>(75, 20, 930, 400)
                        );
        codeScrollPanel.show();
        mainWindow.getFrame().getContentPane().add(codeScrollPanel.getScrollPane());
        ButtonComponent runButton = (ButtonComponent) new ButtonComponent("RUN")
                .setPosition(new Vector2<>(10, 20));
        runButton.addActionListener(e -> {
                compilingProcessor.process(codeArea.getText());
        });

        mainWindow.addComponent(runButton);

        codeArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_F5){
                    compilingProcessor.process(codeArea.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setupVariableTable();
        setupConsole();
        setupLabels();

        mainWindow.open();
    }
    private void setupLabels(){
        TextComponent codeLabel = new TextComponent("CODE", new Vector2<>(75, 5));
        mainWindow.addComponent(codeLabel);

        TextComponent consoleLabel = new TextComponent("CONSOLE", new Vector2<>(75, 422));
        mainWindow.addComponent(consoleLabel);

        TextComponent variableTableLabel = new TextComponent("VARIABLE TABLE", new Vector2<>(1010, 5));
        mainWindow.addComponent(variableTableLabel);
    }

    private void setupVariableTable(){
        variableTable = new TableComponent();
        ScrollPanelComponent panelComponent = new ScrollPanelComponent(variableTable.getComponent());
        panelComponent.setPositionAndSize(new Vector4<>(1010, 20, 250, 400));
        mainWindow.getFrame().getContentPane().add(panelComponent.getScrollPane());
    }

    private void setupConsole(){
        consoleTextArea = new JTextArea();
        ScrollPanelComponent scrollPanelComponent = new ScrollPanelComponent(consoleTextArea);
        scrollPanelComponent.setPositionAndSize(new Vector4<>(75, 440, 930, 190));
        mainWindow.getFrame().getContentPane().add(scrollPanelComponent.getScrollPane());
        PrintStream con = new PrintStream(new ConsoleWindow(consoleTextArea));
        System.setOut(con);
        System.setErr(con);
    }

    public JTextArea getConsoleTextArea() {
        return consoleTextArea;
    }

    public Window getVariablesWindow() {
        return variablesWindow;
    }

    public TableComponent getVariableTable() {
        return variableTable;
    }

    public CompilingProcessor getCompilingProcessor() {
        return compilingProcessor;
    }
}
