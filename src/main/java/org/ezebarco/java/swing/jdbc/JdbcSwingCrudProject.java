package org.ezebarco.java.swing.jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JdbcSwingCrudProject extends JFrame {

    private Container p;
    private JTextField nameField = new JTextField();
    private JTextField priceField = new JTextField();
    private JTextField quantityField = new JTextField();

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

        p.add(formPanel, BorderLayout.WEST);
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

            Object[] product = new Object[]{name, price, quantity};
        }
    }
}
