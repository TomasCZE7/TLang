package window.component;

import core.ApplicationMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableComponent extends Component{

    DefaultTableModel defaultTableModel;

    public TableComponent() {
        super(null);
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Datatype");
        defaultTableModel.addColumn("Value");
        setComponent(new JTable(defaultTableModel));
    }

    public TableComponent addRow(Object... vars){
        DefaultTableModel model = (DefaultTableModel) ApplicationMain.tLang.getVariableTable().getTable().getModel();
        model.addRow(vars);
        return this;
    }

    public JTable getTable(){
        return (JTable) getComponent();
    }

    public void clear() {
        defaultTableModel.setRowCount(0);
    }

    public void updateRow(String name, Object value) {
        for(int i = 0; i < defaultTableModel.getRowCount(); i++){
            if(defaultTableModel.getValueAt(i, 0) == name){
                defaultTableModel.setValueAt(value, i, 2);
            }

        }
    }
}
