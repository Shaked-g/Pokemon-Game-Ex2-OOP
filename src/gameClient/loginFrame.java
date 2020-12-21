package gameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class extends JFrame Class that creates the frame for the login screen .
 */
public class loginFrame extends JFrame {

    /**
     * Constructor that creates the login screen on the frame.
     */
    public loginFrame() {
        setTitle("Pokemon Project!");
        setSize(500, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        setLayout(new BorderLayout());

        JLabel user = new JLabel("User ID: ");
        JTextField userID = new JTextField(20);
        JPanel panelTop = new JPanel();
        panelTop.add(user);
        panelTop.add(userID);

        JLabel scenario = new JLabel("Level Number: ");
        JTextField scenarioField = new JTextField(14);

        JPanel panelMiddle = new JPanel();
        panelMiddle.add(scenario);
        panelMiddle.add(scenarioField);

        JButton start = new JButton("Start");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);

        start.addActionListener(l -> Ex2.loginID = Integer.parseInt(userID.getText()));
        start.addActionListener(l -> Ex2.scenarioLevel = Integer.parseInt(scenarioField.getText()));
        start.addActionListener(l -> Ex2.server.start());
        start.addActionListener(l -> setVisible(false));

        add(panelTop, BorderLayout.NORTH);
        add(panelMiddle, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }
}