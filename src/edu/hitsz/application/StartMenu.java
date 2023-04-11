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


}
