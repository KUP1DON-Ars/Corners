import javax.swing.*;

public class MainWindow {

    public static final int SIZE = 80;
    public static final int BORDER = 40;
    public static final int WIDTH_HEIGHT = SIZE*8+BORDER*2;
    public static JFrame jf;

    public MainWindow() {
        jf = new JFrame("Angle");
        jf.setSize((WIDTH_HEIGHT/4)+ 14,(WIDTH_HEIGHT/4)+ 59);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
        WindowPanel winpan = new WindowPanel();
//        javax.swing.SwingUtilities.invokeLater(winpan);//отложеный старт, прорисовывает все лучше, только надо имплиментировать интерфейс Runnable
    }
}
