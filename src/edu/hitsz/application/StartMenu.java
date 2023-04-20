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
    private JPanel upPanel;
    private JPanel lowPanel;
    public JPanel mainPanel;
    private JLabel Label;
    private JComboBox choose;

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    /**
     * 考虑到Game只能有一个,所以设置为静态变量
     */
    public static Game game;

    public StartMenu() {

        easy.addActionListener(e -> {
            System.out.println("简单模式启动");
            startGame("bg2.jpg", 0);
        });
        normal.addActionListener(e -> {
            System.out.println("普通模式启动");
            startGame("bg3.jpg", 1);
        });
        hard.addActionListener(e -> {
            System.out.println("困难模式启动");
            startGame("bg5.jpg", 2);
        });
    }

    private void startGame(String backgroundPicture, int difficulty) {
        try {
            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/" + backgroundPicture));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        StartMenu.game = new Game();
        Game.setGameDifficulty(difficulty);
        Main.cardPanel.add(StartMenu.game);
        Main.cardLayout.next(Main.cardPanel);
        if (choose.getSelectedIndex() == 1) {
            Game.musicOn = false;
            System.out.println("背景音乐关闭");
        } else {
            Game.musicOn = true;
            System.out.println("背景音乐开启");
        }
        StartMenu.game.action();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
