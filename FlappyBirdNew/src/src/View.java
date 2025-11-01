import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel{
    int width = 360;
    int height = 640;

    private Logic logic;
    private Image backgroundImage;
    private JButton restartButton;
    private JLabel gameOverLabel;
    private JLabel finalScoreLabel;
    private JLabel highScoreLabel;

    private int highScore = 0;

    public View(Logic logic){
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        //Tambahkan fokus dan Key Listener untuk menerima input keyboard
        setFocusable(true);
        addKeyListener(logic);

        // Gunakan layout null agar bisa menempatkan elemen secara bebas
        setLayout(null);

        //Label "Game Over"
        gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setBounds(30, height / 2 - 120, 300, 50);
        gameOverLabel.setVisible(false);
        add(gameOverLabel);

        //Label skor akhir
        finalScoreLabel = new JLabel("", SwingConstants.CENTER);
        finalScoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        finalScoreLabel.setForeground(Color.WHITE);
        finalScoreLabel.setBounds(30, height / 2 - 60, 300, 40);
        finalScoreLabel.setVisible(false);
        add(finalScoreLabel);

        //Label high score
        highScoreLabel = new JLabel("", SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        highScoreLabel.setForeground(new Color(255, 220, 150));
        highScoreLabel.setBounds(30, height / 2 - 20, 300, 40);
        highScoreLabel.setVisible(false);
        add(highScoreLabel);

        //Tombol restart
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 22));
        restartButton.setFocusPainted(false);
        restartButton.setBackground(new Color(50, 180, 90));
        restartButton.setForeground(Color.WHITE);
        restartButton.setBounds(100, height / 2 + 40, 160, 50);
        restartButton.setVisible(false);
        restartButton.setBorder(BorderFactory.createEmptyBorder());
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        restartButton.addActionListener(e -> {
            logic.restartGame();
            hideGameOverUI();
            requestFocusInWindow(); // biar fokus ke keyboard lagi
        });
        add(restartButton);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0, width, height, null);

        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null){
            for(int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        //Overlay gelap saat game over
        if (logic.gameOver) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0, 0, 0, 150)); //Semi transparan
            g2.fillRect(0, 0, width, height);
        }
    }

    public void showGameOverUI(int score) {
        // Update skor tertinggi
        if (score > highScore) highScore = score;

        gameOverLabel.setVisible(true);
        finalScoreLabel.setText("Your Score: " + score);
        finalScoreLabel.setVisible(true);
        highScoreLabel.setText("High Score: " + highScore);
        highScoreLabel.setVisible(true);
        restartButton.setVisible(true);

        repaint();
    }

    public void hideGameOverUI() {
        gameOverLabel.setVisible(false);
        finalScoreLabel.setVisible(false);
        highScoreLabel.setVisible(false);
        restartButton.setVisible(false);
    }
}
