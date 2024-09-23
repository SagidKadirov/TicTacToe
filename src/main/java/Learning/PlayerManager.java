package Learning;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager extends JFrame {
    private final List<Player> players = new ArrayList<>();
    private boolean gameModeSelected = false;
    private int flag = 1;
    private final boolean[]modes=new boolean[]{false,false,false};


    public PlayerManager() {
        this.setSize(200, 200);
        this.setLocation(400, 350);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Game modes");
        this.setFocusable(true);
        this.setLayout(new GridLayout(1, 1));
        JPanel choicePanel = new JPanel();
        this.add(choicePanel);
        choicePanel.setDoubleBuffered(true);
        choicePanel.setBorder(BorderFactory.createTitledBorder("Select the mode"));
        JButton humanVsAI = new JButton("Human Vs AI");
        choicePanel.add(humanVsAI);
        JButton humanVsHuman = new JButton("Human Vs Human");
        choicePanel.add(humanVsHuman);
        JButton AIvsAI = new JButton("AI vs AI");
        choicePanel.add(AIvsAI);
        humanVsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerManager.super.setVisible(false);
                creatingHuman(1);
            }
        });
        humanVsHuman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerManager.super.setVisible(false);
                creatingHuman(2);
            }
        });
        AIvsAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerManager.super.setVisible(false);
                players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(null)));
                players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(players.getFirst())));
                JOptionPane.showMessageDialog(null, "Game started!!!");
                gameModeSelected = true;
            }
        });
        this.setResizable(false);
        this.setVisible(false);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isGameModeSelected() {
        return gameModeSelected;
    }

    public void setGameModeSelected(boolean gameModeSelected) {
        this.gameModeSelected = gameModeSelected;
    }

    public void creatingHuman(int count) {
        JFrame humanSettingsFrame = new JFrame(count == 1 ? "Player Settings" : "Players settings");
        humanSettingsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        humanSettingsFrame.setLocation(400, 400);
        humanSettingsFrame.setSize(300, 190);
        //!CommonPanel
        JPanel commonPanel = new JPanel();
        humanSettingsFrame.add(commonPanel);
        commonPanel.setLayout(new GridLayout(3, 1));
        commonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        //!NamePanel
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1, 1));
        //!Symbols Panel
        JPanel symbolsPanel = new JPanel();
        symbolsPanel.setLayout(new GridLayout(1, 2));
        symbolsPanel.setBorder(new TitledBorder("Select your symbol"));
        //!Buttons panel
        JPanel buttonsPanel = new JPanel();
        //!textField
        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Enter your name"));
        //!RadioButtons
        JRadioButton symbolX = new JRadioButton("X  (first turn)");
        JRadioButton symbolO = new JRadioButton("O  (second turn)");
        ButtonGroup group = new ButtonGroup();
        group.add(symbolX);
        group.add(symbolO);
        //!Buttons
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        namePanel.add(nameField);
        symbolsPanel.add(symbolX);
        symbolsPanel.add(symbolO);
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(cancelButton);
        commonPanel.add(namePanel);
        commonPanel.add(symbolsPanel);
        commonPanel.add(buttonsPanel);
        humanSettingsFrame.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag == 1) {
                    if (!nameField.getText().isEmpty() && (symbolX.isSelected() || symbolO.isSelected())) {
                        players.add(new Player(true, nameField.getText(), symbolO.isSelected() ? 'O' : 'X'));
                        JOptionPane.showMessageDialog(humanSettingsFrame, "Player " + players.getFirst().getName() + " settings saved!");
                        flag = 2;
                    } else {
                        JOptionPane.showMessageDialog(humanSettingsFrame, "Oooops something wrong!\nWrite name and select symbol");
                    }
                }
                if (flag == 2 && count == 1) {
                    players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(players.getFirst())));
                    if (players.getLast().getSymbol() == 'X') {
                        GameInteract.setBotsTurn(true);
                    }
                    humanSettingsFrame.setVisible(false);
                    flag = 1;
                    JOptionPane.showMessageDialog(humanSettingsFrame, "Game started!!!");
                    gameModeSelected = true;
                    System.out.println(players.getFirst() + "\n" + players.getLast());
                }
                if (flag == 3 && count == 2) {
                    if (!nameField.getText().isEmpty()) {
                        players.add(new Player(true, nameField.getText(), symbolO.isSelected() ? 'O' : 'X'));
                        nameField.setText("");
                        symbolX.setEnabled(true);
                        symbolO.setEnabled(true);
                        symbolX.setSelected(false);
                        symbolO.setSelected(false);
                        humanSettingsFrame.setVisible(false);
                        flag = 1;
                        JOptionPane.showMessageDialog(humanSettingsFrame, "Player " + players.getLast().getName() + " settings saved!");
                        JOptionPane.showMessageDialog(humanSettingsFrame, "Game started!!!");
                        gameModeSelected = true;
                        System.out.println(players.getFirst() + "\n" + players.getLast());
                    } else {
                        nameField.setText("");
                        JOptionPane.showMessageDialog(humanSettingsFrame, "Write the second player name");
                    }
                }
                if (flag == 2 && count == 2) {
                    nameField.setText("");
                    symbolX.setSelected(players.getFirst().getSymbol() != 'X');
                    symbolO.setSelected(players.getFirst().getSymbol() != 'O');
                    symbolX.setEnabled(false);
                    symbolO.setEnabled(false);
                    JOptionPane.showMessageDialog(humanSettingsFrame, "Write the second player name");
                    flag = 3;
                }


            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                symbolX.setEnabled(true);
                symbolO.setEnabled(true);
                symbolX.setSelected(false);
                symbolO.setSelected(false);
                humanSettingsFrame.dispose();
                PlayerManager.super.setVisible(true);
                PlayerManager.super.setState(JFrame.NORMAL);
            }
        });

    }


}
