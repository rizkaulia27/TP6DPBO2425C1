import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(135, 206, 250)); // warna langit cerah

        //Judul Game
        JLabel titleLabel = new JLabel("Flappy Bird", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 36));
        titleLabel.setForeground(new Color(255, 255, 0)); // kuning cerah
        titleLabel.setBounds(30, 100, 300, 60);
        panel.add(titleLabel);

        //Tombol Play
        JButton playButton = new JButton("Play Game");
        playButton.setFont(new Font("Dialog", Font.BOLD, 24));
        playButton.setBackground(new Color(60, 179, 113));
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.setBounds(80, 250, 200, 60);
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutup menu dan buka game
                dispose();
                new App(); // Jalankan game
            }
        });
        panel.add(playButton);

        //Tombol Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Dialog", Font.BOLD, 24));
        exitButton.setBackground(new Color(220, 20, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(80, 340, 200, 60);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainMenu();
    }
}