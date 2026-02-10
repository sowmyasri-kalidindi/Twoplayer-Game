import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class TwoPlayerGame implements ActionListener {

    JFrame frame = new JFrame();
    JLabel status = new JLabel();
    JPanel board = new JPanel();
    JButton[] buttons = new JButton[9];

    boolean playerX;
    int moves = 0;

    public TwoPlayerGame() {
        frame.setTitle("Tic Tac Toe");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        status.setFont(new Font("Arial", Font.BOLD, 24));
        status.setHorizontalAlignment(JLabel.CENTER);
        frame.add(status, BorderLayout.NORTH);

        board.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            board.add(buttons[i]);
        }

        frame.add(board);
        startGame();
        frame.setVisible(true);
    }

    void startGame() {
        Random r = new Random();
        playerX = r.nextBoolean();
        status.setText(playerX ? "Player X Turn" : "Player O Turn");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) return;

        btn.setText(playerX ? "X" : "O");
        moves++;
        checkWinner();

        playerX = !playerX;
        status.setText(playerX ? "Player X Turn" : "Player O Turn");
    }

    void checkWinner() {
        int[][] win = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] w : win) {
            if (!buttons[w[0]].getText().equals("") &&
                buttons[w[0]].getText().equals(buttons[w[1]].getText()) &&
                buttons[w[1]].getText().equals(buttons[w[2]].getText())) {

                status.setText("Player " + buttons[w[0]].getText() + " Wins!");
                disableButtons();
                return;
            }
        }

        if (moves == 9) {
            status.setText("Game Draw!");
        }
    }

    void disableButtons() {
        for (JButton b : buttons) {
            b.setEnabled(false);
        }
    }
}
