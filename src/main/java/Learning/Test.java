package Learning;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("TicTacToe");
                frame.setLocation(300,300);

                FieldDrawManager fieldManager = new FieldDrawManager();

                frame.setResizable(false);
                frame.add(fieldManager);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}