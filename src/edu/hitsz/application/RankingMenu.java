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

    public RankingMenu(){

        String[] columnName = {"名次","玩家名","分数","时间"};
        try {
            Game.dao = new DaoImplement(new File("rank.data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Round> roundList = Game.dao.getAllRounds();
        int number = Game.dao.getRoundsNum();
        String[][]tableData = new String[number][4];
        for (int i = 0; i < number; i++) {
            tableData[i][0] = String.valueOf(i+1);
            tableData[i][1] = roundList.get(i).getName();
            tableData[i][2] = String.valueOf(roundList.get(i).getScore());
            tableData[i][3] = roundList.get(i).getRecordTime();
        }
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
        bottomDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoreTable.getSelectedRow();
                System.out.println(row);
                int result = JOptionPane.showConfirmDialog(bottomDelete,
                        "是否确定中删除？");
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    model.removeRow(row);
                }

                roundList.remove(row);
                String[][] newTableData = new String[roundList.size()][4];
                for (int i = 0; i < roundList.size(); i++) {
                    newTableData[i][0] = String.valueOf(i+1);
                    newTableData[i][1] = roundList.get(i).getName();
                    newTableData[i][2] = String.valueOf(roundList.get(i).getScore());
                    newTableData[i][3] = roundList.get(i).getRecordTime();
                }
                model.setDataVector(newTableData, columnName);
                Game.dao.save();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainRankingMenu;
    }
}
