package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.unpri.CharacterManager.model.CharacterManager;
import com.unpri.CharacterManager.model.Character;

public class MainWindow extends JFrame {
    private CharacterManager characterManager;
    private JPanel characterPanel;
    private JLabel avgLevelLabel;

    public MainWindow(CharacterManager manager) {
        this.characterManager = manager;

        // Pengaturan Dasar Jendela (JFrame)
        setTitle("Aplikasi Character Manager - TP8");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Judul di bagian atas
        JLabel titleLabel = new JLabel("Daftar Karakter Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Panel untuk menampung daftar karakter (menggunakan JScrollPane)
        characterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(characterPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panel bawah untuk statistik rata-rata level
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        avgLevelLabel = new JLabel();
        southPanel.add(avgLevelLabel);
        add(southPanel, BorderLayout.SOUTH);

        // Memuat data pertama kali saat jendela dibuka
        refreshData();
    }

    // Method untuk menampilkan seluruh data karakter secara dinamis dan lengkap
    public void refreshData() {
        characterPanel.removeAll(); // Bersihkan panel sebelum isi ulang

        List<Character> characters = characterManager.getAllCharacters();
        double totalLevel = 0;

        for (Character c : characters) {
            JPanel card = new JPanel(new FlowLayout(FlowLayout.LEFT));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            
            // Format teks untuk menampilkan semua atribut karakter dengan rapi
            String infoTeks = String.format(
                "<html><b>%s</b> | Tipe: %s | Level: %d | HP: %d | Atk: %d | Mana: %d</html>",
                c.getName(),
                c.getType(),
                c.getLevel(),
                c.getHitPoints(),
                c.getAttack(),
                c.getMana()
            );

            JLabel infoLabel = new JLabel(infoTeks);
            card.add(infoLabel);
            
            characterPanel.add(card);
            totalLevel += c.getLevel();
        }

        // Statistik Rata-rata Level
        double avgLevel = characters.isEmpty() ? 0 : totalLevel / characters.size();
        avgLevelLabel.setText("Rata-rata Level: " + String.format("%.2f", avgLevel));

        // Refresh tampilan UI agar komponen ter-update secara real-time
        characterPanel.revalidate();
        characterPanel.repaint();
    }
}