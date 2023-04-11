package edu.hitsz.application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class StartMenu {
    private JButton easy;
    private JButton normal;
    private JButton hard;
    private JComboBox choose;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    /**
     * 考虑到Game只能有一个,所以设置为静态变量
     */
    private static Game game;

    public StartMenu() {

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("简单模式启动");
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                StartMenu.game = new Game();
                Main.cardPanel.add(StartMenu.game);
                Main.cardLayout.next(Main.cardPanel);
                StartMenu.game.action();
            }
        });
        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("普通模式启动");
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                StartMenu.game = new Game();
                Main.cardPanel.add(StartMenu.game);
                Main.cardLayout.next(Main.cardPanel);
                StartMenu.game.action();
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("困难模式启动");
                try {
                    ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg5.jpg"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                StartMenu.game = new Game();
                Main.cardPanel.add(StartMenu.game);
                Main.cardLayout.next(Main.cardPanel);
                StartMenu.game.action();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    //    public static void main(String[] args) {
//        JFrame frame = new JFrame("DifficultySelection");
//        frame.setSize(400, 600);
//        frame.setResizable(false);
//        frame.setContentPane(new StartMenu().mainPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }

//    public JFrame startGame(){
//        System.out.println("Hello Aircraft War");
//        // 获得屏幕的分辨率，初始化 Frame
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        JFrame frame = new JFrame("Aircraft War");
//        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        frame.setResizable(false);
//        //设置窗口的大小和位置,居中放置
//        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
//                WINDOW_WIDTH, WINDOW_HEIGHT);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Game game = new Game();
//
//        frame.add(game);
//        frame.setVisible(true);
//        game.action();
//        return frame;
//    }

    private JPanel upPanel;
    private JPanel lowPanel;
    public JPanel mainPanel;
    private JLabel Label;
}
