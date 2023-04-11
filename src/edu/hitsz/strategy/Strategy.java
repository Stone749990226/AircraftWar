package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 210810401 石全
 */
public interface Strategy {
    /**
     * 射击策略
     */
    List<BaseBullet> shootExecute(AbstractAircraft aircraft);
}
