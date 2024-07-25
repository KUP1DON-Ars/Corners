import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Buttons {

    public static JToggleButton [][] board;
    public static Icon whiteFigure;
    public static Icon blackFigure;
    private Icon whiteMin;
    private Icon blackMin;
    private JToggleButton bat;
    private JPanel [] pan;
    private JLabel lab;
    private int buffer_X;
    private int buffer_Y;
    private boolean pushButton = false;
    private ArrayList<Integer> coord_x;
    private ArrayList<Integer> coord_y;
    public static int move;
    public static boolean ii = false;

    public Buttons() {
    }

    public void start(){
        board = new JToggleButton[8][8];
        loadImage();
        createBoard();
        move = 1;
    }

    public int getMove() {
        return move;
    }

    private void loadImage(){
        whiteFigure = new ImageIcon("white.png");
        blackFigure = new ImageIcon("black.png");
        blackMin = new ImageIcon("black pro.png");
        whiteMin = new ImageIcon("white pro.png");
    }

    private void createBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[j][i] = createButton(i,j);
            }
        }
    }

    private JToggleButton createButton(int x, int y){
        bat = new JToggleButton();
        WindowPanel.panel.add(bat);
        bat.setBounds(x * MainWindow.SIZE + MainWindow.BORDER,y * MainWindow.SIZE + MainWindow.BORDER,
                MainWindow.SIZE,MainWindow.SIZE);
        createColor(bat,x,y);
        startPositionFigursOnBoard(bat, x, y);
        pushButtons(bat, y, x);
        bat.setFocusable(false);
        bat.setVisible(true);
        return bat;
    }

    private void createColor(JToggleButton bat, int x, int y){
        if( x % 2 == 0){
            if (y % 2 == 0){
                bat.setBackground(Color.white);
            }
            else
                bat.setBackground(Color.BLACK);
        }
        else{
            if (y % 2 == 0){
                bat.setBackground(Color.BLACK);
            }
            else
                bat.setBackground(Color.white);
        }
    }

    private void startPositionFigursOnBoard(JToggleButton bat, int x, int y){
        figurePosition(bat,blackFigure,x,y);
        figurePosition(bat, whiteFigure,y,x);
    }

    private void figurePosition(JToggleButton bat, Icon icon, int x, int y){
        for (int i = 0, j = 3; i < 4; i++, j++) {
            if(y == i && x > j){
                bat.setIcon(icon);
            }
        }
    }

    private void pushButtons(JToggleButton bat, int x, int y){
        bat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pushButton) {//если я нажал на любую игровую фигуру, до этого не одна не была включина
                    buffer(x,y);//буфер координат кнопки активной
                    pushButton = true;
                    if (bat.getIcon() == whiteFigure && (move % 2 != 0)) {
                        to_button(bat, whiteFigure, blackFigure, whiteMin, x, y);//появляються следующие ходы для белых
                    }
                    else if (bat.getIcon() == blackFigure && (move % 2 == 0)) {
                        to_button(bat, blackFigure, whiteFigure, blackMin, x, y);//появляються след. ходы для черных
                    }
                    else {//если я нажал на пустую кнопку, и при этом раньше не одна не была активирована
                        pushButton = false;
                        bat.setSelected(false);
                    }
                }
                else if (!bat.isSelected()){//если я хочу выключить активируемую кнопку, и потом нажать другую и её активировать
                    clearSetVisibalBoard();
                    bat.setSelected(false);
                    pushButton = false;
                }
                else if (bat.getIcon() != whiteFigure && bat.getIcon() != blackFigure && pushButton
                        && coord_x.contains(x) && coord_y.contains(y)){//следующий ход фигуры
                    newPositionCkekers(x, y);
                    pushButton = false;
                    move++;
                }
                else {
                    bat.setSelected(false);//если я нажал на заполненую с фигурой кнопку, при условии что уже у меня активирована кнопка
                }
                winner();
            }
        });
    }

    private void to_button(JToggleButton bat, Icon icon1, Icon icon2, Icon iconMin, int x, int y){
        if(bat.isSelected()){
            pan = new JPanel[44];
            for (int i = 0; i < pan.length; i++) {
                pan[i] = new JPanel();
            }
            checkersWhite(bat, icon1,icon2, iconMin, x, y);
        }
        else {
            clearSetVisibalBoard();
        }
    }

    private void clearSetVisibalBoard(){//чистка всплывающих окон с игрового поля
        for (int i = 0; i < pan.length; i++) {
            pan[i].setVisible(false);
        }
    }

    private void checkersWhite(JToggleButton bat, Icon icon1, Icon icon2, Icon iconMin, int x, int y){
        if (bat.getIcon() == icon1){
            int z = 0;
            bufferCoordinateButten();
            z = ringtSkrinNextLaben(bat,icon1,icon2,iconMin,x,y,z);
            z = downSkrinNextLaben(bat,icon1,icon2,iconMin,x,y,z);
            z = leftSkrinNextLaben(bat,icon1,icon2,iconMin,x,y,z);
            z = upSkrinNextLaben(bat, icon1,icon2,iconMin,x,y,z);
        }
    }

    private int ringtSkrinNextLaben(JToggleButton bat, Icon icon1, Icon icon2, Icon iconMin, int x, int y, int z){
        for (int i = 1; i < board.length; i++) {//вправо
            if (y + i < board.length) {
                if (board[x][y + 1].getIcon() != icon1 && board[x][y + 1].getIcon() != icon2) {
                    addPanelButton(bat, iconMin, x, y, MainWindow.SIZE, 0, pan[z]);
                    fillBufferButten(x,y + 1);
                    break;
                }
                else if (i != 1 && board[x][y + i].getIcon() != icon1 && board[x][y + i].getIcon() != icon2){
                    break;
                }
                else{
                    if (board[x][y + i].getIcon() == icon1 || board[x][y + i].getIcon() == icon2){
                        if (y + i + 1 >= board.length){
                            break;
                        }
                        if (board[x][y + i + 1].getIcon() == icon1 || board[x][y + i + 1].getIcon() == icon2 ){
                            break;
                        }
                        else {
                            i++;
                            addPanelButton(bat, iconMin, x, y, i * MainWindow.SIZE, 0, pan[z]);
                            fillBufferButten(x,y+i);
                        }
                    }
                }
                z++;
            }
            else break;
        }
        z++;
        return z;
    }

    private int downSkrinNextLaben(JToggleButton bat, Icon icon1, Icon icon2, Icon iconMin, int x, int y, int z){
        for (int i = 1; i < board.length; i++) {//вниз
            if (x + i < board.length) {
                if (board[x + 1][y].getIcon() != icon1 && board[x + 1][y].getIcon() != icon2) {
                    addPanelButton(bat, iconMin, x, y, 0, MainWindow.SIZE, pan[z]);
                    fillBufferButten(x + 1,y);
                    break;
                }
                else if (i != 1 && board[x + i][y].getIcon() != icon1 && board[x + i][y].getIcon() != icon2){
                    break;
                }
                else{
                    if (board[x + i][y].getIcon() == icon1 || board[x + i][y].getIcon() == icon2){
                        if (x + i + 1 >= board.length){
                            break;
                        }
                        if (board[x + i + 1][y].getIcon() == icon1 || board[x + i + 1][y].getIcon() == icon2 ){
                            break;
                        }
                        else {
                            i++;
                            addPanelButton(bat, iconMin, x, y, 0, i * MainWindow.SIZE,  pan[z]);
                            fillBufferButten(x + i, y);
                        }
                    }
                }
                z++;
            }
            else break;
        }
        z++;
        return z;
    }

    private int leftSkrinNextLaben(JToggleButton bat, Icon icon1, Icon icon2, Icon iconMin, int x, int y, int z){
        for (int i = 1; i < board.length; i++) {//влево
            if (y - i >= 0) {
                if (board[x][y - 1].getIcon() != icon1 && board[x][y - 1].getIcon() != icon2) {
                    addPanelButton(bat, iconMin, x, y,  -MainWindow.SIZE,0, pan[z]);
                    fillBufferButten(x,y - 1);
                    break;
                }
                else if (i != 1 && board[x][y - i].getIcon() != icon1 && board[x][y - i].getIcon() != icon2){
                    break;
                }
                else{
                    if (board[x][y - i].getIcon() == icon1 || board[x][y - i].getIcon() == icon2){
                        if (y - i - 1 < 0){
                            break;
                        }
                        if (board[x][y - i - 1].getIcon() == icon1 || board[x][y - i - 1].getIcon() == icon2 ){
                            break;
                        }
                        else {
                            i++;
                            addPanelButton(bat, iconMin, x, y,  i * -MainWindow.SIZE, 0,  pan[z]);
                            fillBufferButten(x, y - i);
                        }
                    }
                }
                z++;
            }
            else break;
        }
        z++;
        return z;
    }

    private int upSkrinNextLaben(JToggleButton bat, Icon icon1, Icon icon2, Icon iconMin, int x, int y, int z){
        for (int i = 1; i < board.length; i++) {//вверх
            if (x - i >= 0) {
                if (board[x - 1][y].getIcon() != icon1 && board[x - 1][y].getIcon() != icon2) {
                    addPanelButton(bat, iconMin, x, y, 0, -MainWindow.SIZE, pan[z]);
                    fillBufferButten(x - 1,y);
                    break;
                }
                else if (i != 1 && board[x - i][y].getIcon() != icon1 && board[x - i][y].getIcon() != icon2){
                    break;
                }
                else{
                    if (board[x - i][y].getIcon() == icon1 || board[x - i][y].getIcon() == icon2){
                        if (x - i - 1 < 0){
                            break;
                        }
                        if (board[x - i - 1][y].getIcon() == icon1 || board[x - i -1][y].getIcon() == icon2 ){
                            break;
                        }
                        else {
                            i++;
                            addPanelButton(bat, iconMin, x, y, 0, i * -MainWindow.SIZE,   pan[z]);
                            fillBufferButten(x - i,y);
                        }
                    }
                }
                z++;
            }
            else break;
        }
        z++;
        return z;
    }

    private void addPanelButton(JToggleButton bat,Icon iconMin, int x, int y, int a, int b, JPanel pan){
        lab = new JLabel();
        WindowPanel.panel.add(pan);
        pan.setLayout(null);
        pan.setBounds(y*MainWindow.SIZE+MainWindow.BORDER + a,x*MainWindow.SIZE+MainWindow.BORDER + b,
                MainWindow.SIZE,MainWindow.SIZE);
        pan.add(lab);
        lab.setBounds(0,0,MainWindow.SIZE,MainWindow.SIZE);
        lab.setIcon(iconMin);
        pan.setVisible(true);
    }

    private void newPositionCkekers(int x, int y){
        Icon icon =  new ImageIcon();
        boolean fillButton = false;
        if (board[buffer_X][buffer_Y].getIcon() == whiteFigure){
            icon = whiteFigure;
            fillButton = true;
        }
        else if (board[buffer_X][buffer_Y].getIcon() == blackFigure){
            icon = blackFigure;
            fillButton = true;
        }
        if (fillButton) {
            board[buffer_X][buffer_Y].setIcon(new ImageIcon());
            board[buffer_X][buffer_Y].setSelected(false);
            clearSetVisibalBoard();
            board[x][y].setIcon(icon);
            board[x][y].setSelected(false);
            if (ii){
                new Bot();// это для игры с ботом, пока тут потому что надо написать играть я хочу с ботом или нет
            }
        }
    }

    private void buffer(int x, int y){
        buffer_X = x;
        buffer_Y = y;
    }

    private void bufferCoordinateButten(){
        coord_x = new ArrayList<>();
        coord_y = new ArrayList<>();
    }

    private void fillBufferButten(int x, int y){
        coord_x.add(x);
        coord_y.add(y);
    }

    private void winner(){
        int a = 0;
        int b = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                for (int i = 0, j = 3; i < 4; i++, j++) {
                    if(y == i && x > j) {
                        if (board[x][y].getIcon() == blackFigure) {
                            a++;
                        }
                    }
                    if(x == i && y > j) {
                        if (board[x][y].getIcon() == whiteFigure) {
                            b++;
                        }
                    }

                }
            }
        }
        if (a == 10 || b == 10){
            JOptionPane.showMessageDialog(null,"Winner!!!");
        }
    }
}
