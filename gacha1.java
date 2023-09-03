import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

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
    //Graphicsオブジェクトで描画。鉛筆みたいなものです。
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        //線を描画
		g.drawLine(100,100,200,200);
	}
}