package edu.hitsz.dao;

import java.util.List;

/**
 * @author 210810401 石全
 */
public interface Dao {
    /**
     * @return List<Round>排行榜中所有回合数据
     */
    List<Round> getAllRounds();

    void addRound(Round round);
    void removeRound(Round round);
    void save();
    void showRanks();
    int getRoundsNum();
}
