package Learning;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TicTacToe");

        FieldDrawManager fieldManager=new FieldDrawManager();

        frame.setResizable(false);
        frame.add(fieldManager);
        frame.pack();
        frame.setVisible(true);
    }
}