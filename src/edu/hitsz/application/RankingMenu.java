package edu.hitsz.application;

import edu.hitsz.dao.Dao;
import edu.hitsz.dao.DaoImplement;
import edu.hitsz.dao.Round;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RankingMenu {
    private JPanel mainRankingMenu;
    private JPanel topMenu;
    private JPanel bottomMenu;
    private JLabel labelDifficulty;
    private JLabel headerLabel;
    private JTable scoreTable;
    private JButton bottomDelete;
    private JScrollPane tableScrollPanel;

    public RankingMenu(Round round){

        String[] columnName = {"名次","玩家名","分数","时间"};
        try {
            Game.dao = new DaoImplement(new File("rank.data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Round> roundList = Game.dao.getAllRounds();
        int number = Game.dao.getRoundsNum();
        String[][]tableData = new String[number + 1][4];
        for (int i = 0; i < roundList.size(); i++) {
            tableData[i][0] = String.valueOf(roundList.get(i).getId());
            tableData[i][1] = roundList.get(i).getName();
            tableData[i][2] = String.valueOf(roundList.get(i).getScore());
            tableData[i][3] = roundList.get(i).getRecordTime();
        }
        tableData[number][0] = String.valueOf(round.getId());
        tableData[number][1] = round.getName();
        tableData[number][2] = String.valueOf(round.getScore());
        tableData[number][3] = round.getRecordTime();
//        String[][]tableData={{"001","Lily","78"},{"002","Jane","89"},{"003","Alex","67"},
//                {"004","Macy","83"},{"005","Nancy","66"},{"006","John","99"}};
        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);
    }



//    public static void main(String[] args) {
//        JFrame frame = new JFrame("RankingMenu");
//        frame.setContentPane(new RankingMenu().mainRankingMenu);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

    public JPanel getMainPanel() {
        return mainRankingMenu;
    }
}
