import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gacha_select {

    private static JButton selectedButton = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ガチャ");
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // メインパネルを作成してフレームに追加
        JPanel mainPanel = new JPanel(new GridLayout(1, 3)); // 1行に3つの画像を配置
        frame.add(mainPanel, BorderLayout.CENTER);

        // 画像を表示するボタンを作成
        for (int i = 1; i <= 5; i++) {
            ImageIcon imageIcon = new ImageIcon("images/image" + i + ".png"); // 画像ファイルのパスを指定
            JButton button = new JButton(imageIcon);
            button.setPreferredSize(new Dimension(200, 200)); // ボタンのサイズを設定

            // ボタンのデフォルトアクションを無効にする
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setBorderPainted(false);

            // ボタンのクリックイベントのリスナーを追加
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedButton != null) {
                        selectedButton.setBorder(null); // 前に選択されたボタンの境界線をリセット
                    }
                    selectedButton = button; // 選択されたボタンを記録
                    selectedButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3)); // 赤い境界線に変更
                }
            });

            mainPanel.add(button);
        }

        // 決定ボタンを作成
        JButton decideButton = new JButton("決定");
        decideButton.setPreferredSize(new Dimension(100, 50)); // ボタンのサイズを設定
        decideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 決定ボタンがクリックされたときの処理を記述
                if (selectedButton != null) {
                    JOptionPane.showMessageDialog(frame, "選択された画像: " + selectedButton.getIcon().toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "画像を選択してください。");
                }
            }
        });

        // 決定ボタンをフレームの一番下に配置
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(decideButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
