import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class black {

    private JFrame frame; // ゲームウィンドウのフレーム
    private JPanel gamePanel; // ゲームプレイ画面のパネル
    private JButton hitButton; // プレイヤーがカードをヒットするためのボタン
    private JButton standButton; // プレイヤーがスタンドするためのボタン
    private JLabel playerScoreLabel; // プレイヤーのスコア表示用ラベル
    private JLabel dealerScoreLabel; // ディーラーのスコア表示用ラベル
    private Map<String, Image> cardImages; // カード画像を格納するMap
    private int[] playerCards; // プレイヤーの手札
    private int[] dealerCards; // ディーラーの手札
    private int playerCardCount; // プレイヤーの手札枚数
    private int dealerCardCount; // ディーラーの手札枚数
    private int playerScore; // プレイヤーのスコア
    private int dealerScore; // ディーラーのスコア

    public static void main(String[] args) {
        // ゲームを起動
        SwingUtilities.invokeLater(() -> {
            black game = new black();
            game.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Blackjack Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ゲーム画面のパネルを設定
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCards(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(800, 600));

        // スコア表示用ラベルを初期化
        playerScoreLabel = new JLabel("プレイヤーのスコア: 0");
        dealerScoreLabel = new JLabel("ディーラーのスコア: 0");

        // ボタンやスコア表示用ラベルを設定
        hitButton = new JButton("ヒット");
        standButton = new JButton("スタンド");

        // ボタンのクリックイベントを設定
        setupButton(hitButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPlayerCard();
            }
        });
        setupButton(standButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealerTurn();
                determineWinner();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);

        // フレームにコンポーネントを配置
        frame.add(playerScoreLabel, BorderLayout.NORTH);
        frame.add(dealerScoreLabel, BorderLayout.SOUTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        // ゲームの初期化
        initializeGame();
    }

    private void drawCards(Graphics g) {

        g.drawString("プレイヤー スコア: " + playerScore, 50, 480);
        int x = 50;
        int y = 300;

        // プレイヤーの手札を描画
        for (int i = 0; i < playerCardCount; i++) {
            int cardIndex = playerCards[i]; // カードの値（1から13）
            String cardName = getCardName(cardIndex);
            Image cardImage = cardImages.get(cardName);
            g.drawImage(cardImage, x, y, 100, 150, null); // カードのサイズを調整
            x += 110; // カード間の間隔
        }

        g.drawString("ディーラー スコア: " + dealerScore, 50, 30);
        x = 50;
        y = 50;

        // ディーラーの手札を描画
        for (int i = 0; i < dealerCardCount; i++) {
            int cardIndex = dealerCards[i];
            String cardName = getCardName(cardIndex);
            Image cardImage = cardImages.get(cardName);
            g.drawImage(cardImage, x, y, 100, 150, null); // カードのサイズを調整
            x += 110; // カード間の間隔
        }
    }

    private void initializeGame() {
        cardImages = new HashMap<>(); // カード画像を格納するMapを初期化

        String[] cardNames = {
            "spade_Q.png", "BackSide.png", "club_2.png", "club_3.png", "club_4.png",
            "club_5.png", "club_6.png", "club_7.png", "club_8.png", "club_9.png",
            "club_10.png", "club_A.png", "club_J.png", "club_K.png", "club_Q.png",
            "diamond_2.png", "diamond_3.png", "diamond_4.png", "diamond_5.png", "diamond_6.png",
            "diamond_7.png", "diamond_8.png", "diamond_9.png", "diamond_10.png", "diamond_A.png",
            "diamond_J.png", "diamond_K.png", "diamond_Q.png", "heart_2.png", "heart_3.png",
            "heart_4.png", "heart_5.png", "heart_6.png", "heart_7.png", "heart_8.png",
            "heart_9.png", "heart_10.png", "heart_A.png", "heart_J.png", "heart_K.png",
            "heart_Q.png", "JOKER.png", "sapde_J.png", "spade_2.png", "spade_3.png",
            "spade_4.png", "spade_5.png", "spade_6.png", "spade_7.png", "spade_8.png",
            "spade_9.png", "spade_10.png", "spade_A.png", "spade_K.png"
        };

        for (String cardName : cardNames) {
            String imagePath = "images/" + cardName; // 画像ファイルのパス
            Image cardImage = new ImageIcon(imagePath).getImage();
            cardImages.put(cardName, cardImage);
        }

        playerCards = new int[30]; // プレイヤーの手札を保持する配列
        dealerCards = new int[30]; // ディーラーの手札を保持する配列
        playerCardCount = 0; // プレイヤーの手札枚数
        dealerCardCount = 0; // ディーラーの手札枚数
        playerScore = 0; // プレイヤーのスコア
        dealerScore = 0; // ディーラーのスコア

        // 最初のカードを配る
        drawPlayerCard();
        drawPlayerCard();
        drawDealerCard();
        drawDealerCard();
    }

    private String getCardName(int cardIndex) {
        String cardName = "";

        // カードの値に応じてカード名（ファイル名）を生成
        if (cardIndex >= 2 && cardIndex <= 10) {
            cardName = "club_" + cardIndex + ".png";
        } else if (cardIndex == 1) {
            cardName = "club_A.png"; // エースの場合
        } else if (cardIndex == 11) {
            cardName = "club_J.png"; // ジャックの場合
        } else if (cardIndex == 12) {
            cardName = "club_Q.png"; // クイーンの場合
        } else if (cardIndex == 13) {
            cardName = "club_K.png"; // キングの場合
        } else if (cardIndex >= 14 && cardIndex <= 22) {
            // ダイヤの場合
            int diamondValue = cardIndex - 13;
            cardName = "diamond_" + diamondValue + ".png";
        } else if (cardIndex >= 23 && cardIndex <= 33) {
            // ハートの場合
            int heartValue = cardIndex - 22;
            cardName = "heart_" + heartValue + ".png";
        } else if (cardIndex >= 34 && cardIndex <= 44) {
            // スペードの場合
            int spadeValue = cardIndex - 33;
            cardName = "spade_" + spadeValue + ".png";
        }

        return cardName;
    }

    private void drawPlayerCard() {
        int card = getRandomCard(); // ランダムなカードを取得
        playerCards[playerCardCount++] = card; // プレイヤーの手札に追加
        playerScore += card; // スコアを更新
        playerScoreLabel.setText("プレイヤーのスコア: " + playerScore); // スコア表示を更新
        gamePanel.repaint(); // ゲーム画面を再描画

        if (playerScore >= 21) {
            dealerTurn(); // プレイヤーのスコアが21以上ならディーラーのターンに移行
            determineWinner(); // 勝者を判定
        }
    }

    private void drawDealerCard() {
        int card = getRandomCard(); // ランダムなカードを取得
        dealerCards[dealerCardCount++] = card; // ディーラーの手札に追加
        dealerScore += card; // スコアを更新
        dealerScoreLabel.setText("ディーラーのスコア: " + dealerScore); // スコア表示を更新
        gamePanel.repaint(); // ゲーム画面を再描画
    }

    private int getRandomCard() {
        Random random = new Random();
        return random.nextInt(13) + 1; // 1から13までのランダムなカードを返す
    }

    private void dealerTurn() {
        while (dealerScore < 17) {
            drawDealerCard(); // ディーラーはスコアが17以上になるまでカードを引く
        }

        setGameOver(true); // ゲームオーバーフラグを立てる
        determineWinner(); // ゲーム結果を判定
    }

    private void setGameOver(boolean b) {
    }

    private void determineWinner() {
        String result = "";

        if (playerScore > 21) {
            result = "ディーラーの勝ち";
        } else if (dealerScore > 21) {
            result = "プレイヤーの勝ち";
        } else if (playerScore > dealerScore) {
            result = "プレイヤーの勝ち";
        } else if (dealerScore > playerScore) {
            result = "ディーラーの勝ち";
        } else {
            result = "引き分け";
        }

        JOptionPane.showMessageDialog(frame, result, "ゲーム結果", JOptionPane.INFORMATION_MESSAGE);
    }

    // ボタンのクリックイベントを設定するメソッド
    private void setupButton(JButton button, ActionListener actionListener) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        });
    }
}
