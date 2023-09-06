import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class gacha1 {
    
	public static void main(String[] args) {
        //gamewindowクラスをインスタンス化
		gamewindow gw = new gamewindow("テストウィンドウ",400,300);
        //JFrameのaddメソッドを呼び出し
		gw.add(new DrawCanvas());
		gw.setVisible(true);
	}
}

class gamewindow extends JFrame{
    public gamewindow(String title, int width, int height){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//閉じるボタンの処理
		setSize(width,height);//ウィンドウサイズ
		setLocationRelativeTo(null);//画面中央に配置
		setResizable(false);//リサイズ禁止
		setVisible(true);//ウィンドウ表示
    }

}

class DrawCanvas extends JPanel{
	Image img = Toolkit.getDefaultToolkit().getImage("gachagacha.png");
    //Graphicsオブジェクトで描画。鉛筆みたいなものです。
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
	}
}