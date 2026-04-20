package org.ezebarco.java.swing.jdbc;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JdbcSwingCrudProject extends JFrame {

    private Container p;
    private JTextField nameField = new JTextField();
    private JTextField priceField = new JTextField();
    private JTextField quantityField = new JTextField();
    private ProductTableModel tableModel = new ProductTableModel();

    public JdbcSwingCrudProject() {
        super("Swing: GUI con Base de Datos MySQL!");

        //Obtenemos el Container principal de la ventana (espacio total disponible)
        p = getContentPane();
        //Divide el espacio del  en 5 zonas
        p.setLayout(new BorderLayout(20, 10));

        //Creo panel del formulario con gridLayout, para que tenga 4 filas y 2 columnas
        //20 y 10 son los espacios entre las grillas de la tabla
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 20, 10));
        //Crea un margen interno (padding) vacio entre todos los lados del panel
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton buttonSave = new JButton("Guardar");

        //Agrego al panel los componentes
        formPanel.add(new JLabel("Nombre :"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Precio: "));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Cantidad: "));
        formPanel.add(quantityField);

        formPanel.add(new JLabel(""));
        formPanel.add(buttonSave);
        buttonSave.addActionListener(new AddActionListener());

        //Creo un panel para la tabla
        JPanel tablePanel = new JPanel(new FlowLayout());

        //Creo la tabla y le pongo un modelo de tabla
        JTable jTable = new JTable();
        jTable.setModel(this.tableModel);

        //Creo un contenedor con barra de scroll y ponemos jTable adentro de este
        JScrollPane scroll = new JScrollPane(jTable);
        tablePanel.add(scroll);
        p.add(tablePanel,BorderLayout.EAST); //Panel de tabla a la derecha
        p.add(formPanel, BorderLayout.WEST); //Panel de form a la izquierda
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JdbcSwingCrudProject();
    }

    private class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Object[] product = new Object[]{null, name, price, quantity};
            //Agrego el producto a una fila de la tabla
            tableModel.getRows().add(product);
            //Se actualiza la grilla
            tableModel.fireTableDataChanged();

            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");
        }
    }

    private class ProductTableModel extends AbstractTableModel {

        private String[] columns = new String[]{"Id", "Nombre", "Precio", "Cantidad"};
        private List<Object[]> rows = new ArrayList<>();

        public List<Object[]> getRows() {
            return rows;
        }

        @Override
        public int getRowCount() {
            return rows.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rows.get(rowIndex)[columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
}
