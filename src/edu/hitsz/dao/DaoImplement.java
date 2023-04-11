package edu.hitsz.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 210810401 石全
 */
public class DaoImplement implements Dao{
    private File file;
    private List<Round> roundList;

    public DaoImplement(File file) throws IOException {
        this.file = file;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            try {
                roundList = (List<Round>) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } catch (IOException e) {
            System.out.println("没有找到文件");
            roundList = new ArrayList<Round>();
        }
    }

    @Override
    public List<Round> getAllRounds() {
        return roundList;
    }


    @Override
    public void addRound(Round round) {
        roundList.add(round);
    }

    @Override
    public void removeRound(Round round) {

    }

    @Override
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(roundList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showRanks() {
        Collections.sort(roundList);
        int i = 0;
        for(Round round : roundList) {
            i++;
            System.out.println("第"+i+"名"+"\t"+round.getName()+"\t"+round.getScore()+"\t"+round.getRecordTime());;
        }
    }

    @Override
    public int getRoundsNum() {
        return roundList.size();
    }
}
