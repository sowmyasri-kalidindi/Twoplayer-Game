import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class TwoPlayerGameSwing implements ActionListener {

    JFrame frame = new JFrame();
    JPanel t_panel = new JPanel();
    JPanel bt_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] bton = new JButton[9];

    int chance_flag = 0;
    Random random = new Random();
    boolean pl1_chance;

    TwoPlayerGameSwing() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(250, 184, 97));
        frame.setTitle("Tic Tac Toe Game");
        frame.setLayout(new BorderLayout());

        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.RED);
        textfield.setFont(new Font("Serif", Font.BOLD, 50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic Tac Toe");
        textfield.setOpaque(true);

        t_panel.setLayout(new BorderLayout());
        t_panel.add(textfield);

        bt_panel.setLayout(new GridLayout(3, 3));
        bt_panel.setBackground(Color.BLACK);

        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton("");
            bton[i].setFont(new Font("Serif", Font.BOLD, 100));
            bton[i].setFocusable(false);
            bton[i].addActionListener(this);
            bton[i].setBackground(Color.CYAN);
            bt_panel.add(bton[i]);
        }

        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);
        frame.setVisible(true);

        startGame();
    }

    public void startGame() {
        int chance = random.nextInt(100);
        pl1_chance = chance % 2 == 0;
        textfield.setText(pl1_chance ? "Player X Turn" : "Player O Turn");
    }

    public void gameOver(String msg) {
        Object[] options = {"Restart", "Exit"};
        int n = JOptionPane.showOptionDialog(
                frame,
                msg,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0) {
            frame.dispose();
            new TwoPlayerGameSwing();
        } else {
            frame.dispose();
        }
    }

    public void matchCheck() {

        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] w : wins) {
            if (bton[w[0]].getText().equals("X") &&
                bton[w[1]].getText().equals("X") &&
                bton[w[2]].getText().equals("X")) {
                xWins(w[0], w[1], w[2]);
                return;
            }

            if (bton[w[0]].getText().equals("O") &&
                bton[w[1]].getText().equals("O") &&
                bton[w[2]].getText().equals("O")) {
                oWins(w[0], w[1], w[2]);
                return;
            }
        }

        if (chance_flag == 9) {
            gameOver("Game Draw!");
        }
    }

    public void xWins(int a, int b, int c) {
        highlight(a, b, c);
        gameOver("Player X Wins!");
    }

    public void oWins(int a, int b, int c) {
        highlight(a, b, c);
        gameOver("Player O Wins!");
    }

    void highlight(int a, int b, int c) {
        bton[a].setBackground(Color.YELLOW);
        bton[b].setBackground(Color.YELLOW);
        bton[c].setBackground(Color.YELLOW);
        for (JButton btn : bton) btn.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == bton[i] && bton[i].getText().equals("")) {

                if (pl1_chance) {
                    bton[i].setForeground(Color.BLUE);
                    bton[i].setText("X");
                    textfield.setText("Player O Turn");
                } else {
                    bton[i].setForeground(Color.GREEN);
                    bton[i].setText("O");
                    textfield.setText("Player X Turn");
                }

                pl1_chance = !pl1_chance;
                chance_flag++;
                matchCheck();
            }
        }
    }

    public static void main(String[] args) {
        new TwoPlayerGameSwing();
    }
}
