/* racegame.java レース以外のプログラム */

//JFrameクラスのインポート
import javax.swing.JFrame;

public class racegame {

	public static void main(String[] args) {
		//JFrameクラスをインスタンス化
		JFrame frame = new JFrame("レース");
		//ウィンドウのサイズを指定(16:9)
		frame.setSize(1280, 720);
		//ウィンドウの位置を、画面の中心へ
		frame.setLocationRelativeTo(null);
		//×でアプリが終了するようにする
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ウィンドウのサイズを変更できないようにする
		frame.setResizable(false);
		//ウィンドウを表示する
		frame.setVisible(true);
	}
	

}