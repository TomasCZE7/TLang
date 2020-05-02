package core;

import math.Vector2;
import math.Vector4;
import util.FileParser;
import window.Window;
import window.component.ButtonComponent;
import window.component.TableComponent;
import window.component.TextAreaComponent;
import window.WindowType;

public class TLang {

    private Window mainWindow;
    private Window variablesWindow;
    private CompilingProcessor compilingProcessor;
    private TableComponent variableTable;
    private FileParser sourceFileParser = new FileParser("src/main/resources/src.tl");

    public TLang(){
        compilingProcessor = new CompilingProcessor();
        mainWindow = new Window("Editor", WindowType.EDITOR).
                setSize(new Vector2<>(1024, 680));
        TextAreaComponent textAreaComponent = (TextAreaComponent) new TextAreaComponent()
                .addText(sourceFileParser.readIntoString())
                .setPositionAndSize(new Vector4<>(75, 30, 930, 610));
        mainWindow.addComponent(textAreaComponent);
        ButtonComponent buttonComponent = (ButtonComponent) new ButtonComponent("RUN")
                .setPosition(new Vector2<>(10, 30));
        buttonComponent.addActionListener(e -> {
            try {
                compilingProcessor.process(textAreaComponent.getTextArea().getText());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        mainWindow.addComponent(buttonComponent);
        mainWindow.open();

        variablesWindow = new Window("Variable lookup", WindowType.DEBUG).setSize(400, 400);
        variableTable = (TableComponent) new TableComponent().setSize(new Vector2<>(250, 400));
        variablesWindow.addComponent(variableTable);
        variablesWindow.open();

    }

    public Window getVariablesWindow() {
        return variablesWindow;
    }

    public TableComponent getVariableTable() {
        return variableTable;
    }
}
