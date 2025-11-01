import javax.swing.*;
import java.awt.*;

public class App{
    private JFrame frame;
    private Logic logika;
    private View tampilan;
    private JLabel scoreLabel;

    public App(){
        frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        logika = new Logic();
        tampilan = new View(logika);
        logika.setView(tampilan);

        //JLabel untuk skor
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(20, 10, 200, 40);

        //Set label ke logic agar bisa diupdate
        logika.setScoreLabel(scoreLabel);

        tampilan.setBounds(0, 0, 360, 640);
        frame.add(tampilan);
        frame.add(scoreLabel);

        frame.setVisible(true);
        tampilan.requestFocus();
    }

    //Kalau kamu jalankan App langsung tanpa MainMenu
    public static void main(String[] args) {
        new App();
    }
}