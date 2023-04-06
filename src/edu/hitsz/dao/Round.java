package edu.hitsz.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 210810401 石全
 */
public class Round implements Serializable, Comparable<Round>{
    private static final long serialVersionUID = 1111013L;
    private int id;
    private String name;
    private int score;
    private String recordTime;

    public Round(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        this.recordTime = sdf.format(date);
    }

    @Override
    public String toString() {
        return "Round{" +
                "rank=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", recordTime='" + recordTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getRecordTime() {
        return recordTime;
    }


    @Override
    public int compareTo(Round o) {
        if(this.score > o.score){
            return 1;
        }else {
            return -1;
        }
    }
}
