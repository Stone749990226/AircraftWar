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
    private JLabel headerLabel;
    private JTable scoreTable;
    private JButton bottomDelete;
    private JScrollPane tableScrollPanel;
    private JLabel labelDifficulty;

    private DefaultTableModel model;
    private final String[] columnName = {"名次","玩家名","分数","时间"};

    public RankingMenu(){
        String[] difficultyNames = {"EASY", "NORMAL", "HARD"};
        updateData();

        //JTable并不存储自己的数据，而是从表格模型那里获取它的数据
        scoreTable.setModel(model);
        tableScrollPanel.setViewportView(scoreTable);

        bottomDelete.addActionListener(e -> {
            int row = scoreTable.getSelectedRow();
            int result = JOptionPane.showConfirmDialog(null, "确定删除选中的玩家?");
            if (JOptionPane.YES_OPTION == result && row != -1) {
                model.removeRow(row);
            }
            List<Round> roundList = Game.dao.getAllRounds();
            roundList.remove(row);
            updateData();
        });

        labelDifficulty.setText("难度:"+ difficultyNames[Game.gameDifficulty]);
    }

    public JPanel getMainPanel() {
        return mainRankingMenu;
    }
    public void updateData(){
        List<Round> roundList = Game.dao.getAllRounds();
        String[][] tableData = new String[roundList.size()][4];
        for (int i = 0; i < roundList.size(); i++) {
            tableData[i][0] = String.valueOf(i+1);
            tableData[i][1] = roundList.get(i).getName();
            tableData[i][2] = String.valueOf(roundList.get(i).getScore());
            tableData[i][3] = roundList.get(i).getRecordTime();
        }
        if(model == null) {
            model = new DefaultTableModel(tableData, columnName){
                @Override
                public boolean isCellEditable(int row, int col){
                    return false;
                }
            };
        }
        model.setDataVector(tableData, columnName);
        Game.dao.save();
    }
}
