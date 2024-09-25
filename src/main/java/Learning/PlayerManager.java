package Learning;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerManager extends JFrame {
    private final List<Player> players = new ArrayList<>();
    private final Boolean[] mode = new Boolean[]{false, false, false};

    public Boolean[] getModes() {
        return mode;
    }

    public PlayerManager() {
        this.setSize(250, 250);
        this.setLocation(400, 350);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Game modes");
        this.setFocusable(true);
        this.setLayout(new GridLayout(1, 1));
        JPanel choicePanel = new JPanel();
        this.add(choicePanel);
        choicePanel.setDoubleBuffered(true);
        choicePanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20), BorderFactory.createTitledBorder("Select the mode")));
        choicePanel.setLayout(new GridLayout(4, 1));
        JButton humanVsAI = new JButton("Human Vs AI");
        choicePanel.add(humanVsAI);
        JButton humanVsHuman = new JButton("Human Vs Human");
        choicePanel.add(humanVsHuman);
        JButton AIvsAI = new JButton("AI vs AI");
        choicePanel.add(AIvsAI);
        JButton exit = new JButton("Exit");
        choicePanel.add(exit);
        humanVsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerManager.super.setVisible(false);
                mode[0] = true;
                mode[1] = false;
                mode[2] = false;
                modeSettings(mode);
            }
        });
        humanVsHuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerManager.super.setVisible(false);
                mode[0] = false;
                mode[1] = true;
                mode[2] = false;
                modeSettings(mode);
            }
        });
        AIvsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerManager.super.setVisible(false);
                mode[0] = false;
                mode[1] = false;
                mode[2] = true;
                modeSettings(mode);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setVisible(false);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void modeSettings(Boolean[] mode) {
        if (mode[0]) {
            JFrame humanSettings = new JFrame("Player Settings");
            humanSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            humanSettings.setLocation(400, 400);
            humanSettings.setSize(300, 190);
            humanSettings.setResizable(false);

            //!CommonPanel
            JPanel commonPanel = new JPanel();
            commonPanel.setLayout(new GridLayout(3, 1));
            commonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            humanSettings.add(commonPanel);
            //!NamePanel and textField
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new GridLayout(1, 1));
            JTextField nameField = new JTextField();
            nameField.setBorder(BorderFactory.createTitledBorder("Enter your name"));
            namePanel.add(nameField);
            //!Symbols Panel and RadioButtons
            JPanel symbolsPanel = new JPanel();
            symbolsPanel.setLayout(new GridLayout(1, 2));
            symbolsPanel.setBorder(new TitledBorder("Select your symbol"));
            JRadioButton symbolX = new JRadioButton("X  (first turn)");
            JRadioButton symbolO = new JRadioButton("O  (second turn)");
            ButtonGroup group = new ButtonGroup();
            group.add(symbolX);
            group.add(symbolO);
            symbolsPanel.add(symbolX);
            symbolsPanel.add(symbolO);
            //!Buttons panel and buttons
            JPanel buttonsPanel = new JPanel();
            JButton confirmButton = new JButton("Confirm");
            JButton cancelButton = new JButton("Cancel");
            buttonsPanel.add(confirmButton);
            buttonsPanel.add(cancelButton);

            commonPanel.add(namePanel);
            commonPanel.add(symbolsPanel);
            commonPanel.add(buttonsPanel);
            humanSettings.setVisible(true);
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!nameField.getText().isEmpty() && (symbolX.isSelected() || symbolO.isSelected())) {
                        players.add(new Player(true, nameField.getText(), symbolX.isSelected() ? 'X' : '0',false));
                        players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(players.getFirst()),false));
                        if (players.getFirst().getSymbol() == '0') {
                            Collections.swap(players, 0, 1);
                        }
                        JOptionPane.showMessageDialog(humanSettings, "Player " + players.getFirst().getName() + " settings saved!");
                        JOptionPane.showMessageDialog(null, "Game started!!!");
                        GameInteract.setEndGame(false);
                        humanSettings.dispose();
                    } else {
                        JOptionPane.showMessageDialog(humanSettings, "Oooops something wrong!\nWrite name and select symbol");
                    }
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.clear();
                    nameField.setText("");
                    symbolX.setEnabled(true);
                    symbolO.setEnabled(true);
                    symbolX.setSelected(false);
                    symbolO.setSelected(false);
                    humanSettings.dispose();
                    PlayerManager.super.setVisible(true);
                    PlayerManager.super.setState(JFrame.NORMAL);
                }
            });
        }
        if (mode[1]) {
            JFrame humanSettings = new JFrame("Players Settings");
            humanSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            humanSettings.setLocation(400, 400);
            humanSettings.setSize(300, 220);
            humanSettings.setResizable(false);

            //!CommonPanel
            JPanel commonPanel = new JPanel();
            commonPanel.setLayout(new GridLayout(2, 1));
            commonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            humanSettings.add(commonPanel);
            //!NamePanel and textField
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new GridLayout(2, 1));
            JTextField firstNameField = new JTextField();
            firstNameField.setBorder(BorderFactory.createTitledBorder("First player name (X-symbol)"));
            JTextField secondNameField = new JTextField();
            secondNameField.setBorder(BorderFactory.createTitledBorder("Second player name (0-symbol)"));
            namePanel.add(firstNameField);
            namePanel.add(secondNameField);

            //!Buttons panel and buttons
            JPanel buttonsPanel = new JPanel();
            JButton confirmButton = new JButton("Confirm");
            JButton cancelButton = new JButton("Cancel");
            buttonsPanel.add(confirmButton);
            buttonsPanel.add(cancelButton);

            commonPanel.add(namePanel);
            commonPanel.add(buttonsPanel);
            humanSettings.setVisible(true);
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!firstNameField.getText().isEmpty() && !secondNameField.getText().isEmpty()) {
                        players.add(new Player(true, firstNameField.getText(), 'X',false));
                        players.add(new Player(true, secondNameField.getText(), '0',false));
                        JOptionPane.showMessageDialog(humanSettings, "Player " + players.getFirst().getName() + " and " + players.getLast().getName() + " settings saved!");
                        JOptionPane.showMessageDialog(null, "Game started!!!");
                        humanSettings.dispose();
                        GameInteract.setEndGame(false);
                    } else {
                        JOptionPane.showMessageDialog(humanSettings, "Oooops something wrong!\nWrite players names correctly!");
                    }
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    players.clear();
                    firstNameField.setText("");
                    secondNameField.setText("");
                    humanSettings.dispose();
                    PlayerManager.super.setVisible(true);
                    PlayerManager.super.setState(JFrame.NORMAL);
                }
            });
        }
        if (mode[2]) {
            players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(null),false));
            players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(players.getFirst()),false));
            JOptionPane.showMessageDialog(null, "Game started!!!");
            GameInteract.setEndGame(false);
        }


    }


}
