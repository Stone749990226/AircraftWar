package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;

import edu.hitsz.dao.Dao;
import edu.hitsz.dao.DaoImplement;
import edu.hitsz.dao.Round;
import edu.hitsz.factory.BossEnemyFactory;
import edu.hitsz.factory.EliteEnemyFactory;
import edu.hitsz.factory.EnemyFactory;
import edu.hitsz.factory.MobEnemyFactory;
import edu.hitsz.music.MusicThread;
import edu.hitsz.property.AbstractProperty;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;



    private final HeroAircraft heroAircraft;
    private final List<AbstractEnemyAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProperty> properties;

    public List<AbstractEnemyAircraft> getEnemyAircrafts() {
        return enemyAircrafts;
    }

    public List<BaseBullet> getEnemyBullets() {
        return enemyBullets;
    }
    /**
     * 制造敌机的工厂
     */
    public EnemyFactory enemyFactory;

    /**
     * 屏幕中出现的敌机最大数量
     */
    private int enemyMaxNumber = 5;

    public void setEnemyMaxNumber(int enemyMaxNumber) {
        this.enemyMaxNumber = enemyMaxNumber;
    }

    /**
     * 当前得分
     */
    private int score = 0;
    public void increaseScore(int number) {
        this.score += number;
    }
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;
    Random r = new Random();
    /**
     * 小于0.5产生精英敌机
     */
    public static final float POSSIBILITY = 0.5F;
    /**
     * 普通飞机的血量
     */
    public static final int MOB_HP = 30;
    /**
     * 普通飞机的分数奖励
     */
    public static final int MOB_SCORE_UP = 10;
    /**
     * 精英飞机的血量
     */
    public static final int ELITE_HP = 30;
    /**
     * 精英飞机的分数奖励
     */
    public static final int ELITE_SCORE_UP = 20;
    /**
     * 英雄飞机的血量
     */
    public static final int HERO_HP = 1000;
    /**
     * BOSS飞机的血量
     */
    public static final int BOSS_HP = 300;
    /**
     * BOSS飞机的分数奖励
     */
    public static final int BOSS_SCORE_UP = 50;
    /**
     * 控制BOSS飞机的出现频率
     */
    private int bossScoreThreshold = 150;

    public void setBossScoreThreshold(int bossScoreThreshold) {
        this.bossScoreThreshold = bossScoreThreshold;
    }

    /**
     * BOSS是否存在
     */
    private static boolean bossExists = false;
    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;

    public static Dao dao = null;
    /**
     * 游戏难度,0表示简单,1表示普通,2表示困难
     */
    public static final int EASY = 0;
    public static final int NORMAL = 1;
    public static final int DIFFICULT = 2;

    public static int gameDifficulty;

    public static boolean musicOn = true;
    private MusicThread bgm = null;
    private MusicThread bossBgm = null;

    public Game() {
        //如果直接new这里不能保证唯一性
        heroAircraft = HeroAircraft.getInstance();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        properties = new LinkedList<>();
        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }



    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    if (r.nextFloat() < POSSIBILITY) {
                        enemyFactory = new MobEnemyFactory();
                    } else {
                        enemyFactory = new EliteEnemyFactory();
                    }

                    enemyAircrafts.add(enemyFactory.createEnemy());
                }
                // 飞机射出子弹
                shootAction();
            }
            if (score % bossScoreThreshold == 0 && score > 0) {
                boolean flag = false;
                for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                    if (enemyAircraft instanceof BossEnemy) {
                        flag = true;
                    }
                }
                if (!flag) {
                    enemyFactory = new BossEnemyFactory();
                    enemyAircrafts.add(enemyFactory.createEnemy());
                    bossExists = true;
                }
            }
            // 背景音乐控制
            musicControl();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                if(bgm!=null){
                    bgm.stopMusic();
                }
                if(bossBgm!=null){
                    bossBgm.stopMusic();
                }
                printRankingList();
                System.out.println("Game Over!");
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            List<BaseBullet> enemyBulletList = enemyAircraft.shoot(enemyAircraft);
            enemyBullets.addAll(enemyBulletList);
            for(BaseBullet enemyBullet:enemyBulletList) {

            }
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot(heroAircraft));
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
        for (AbstractProperty propertyAircraft : properties) {
            propertyAircraft.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                if(musicOn){
                    new MusicThread("src/videos/bullet_hit.wav").start();
                }
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    if(musicOn){
                        new MusicThread("src/videos/bullet_hit.wav").start();
                    }
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    int x = enemyAircraft.getLocationX();
                    int y = enemyAircraft.getLocationY();
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        //这里我把加分放到了produceProperty方法里面
                        enemyAircraft.produceProperty(properties, x, y);
                        //如果boss机坠毁
                        if(enemyAircraft instanceof BossEnemy){
                            bossExists = false;
                        }
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    if(musicOn){
                        new MusicThread("src/videos/game_over.wav").start();
                    }
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (AbstractProperty propertyAircraft : properties) {
            if (propertyAircraft.notValid()) {
                continue;
            }
            if (heroAircraft.crash(propertyAircraft) || propertyAircraft.crash(heroAircraft)) {
                propertyAircraft.getProperty(heroAircraft);
                propertyAircraft.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        properties.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, properties);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    private void printRankingList() {
        try {
            switch (Game.gameDifficulty){
                case Game.EASY:
                    dao = new DaoImplement(new File("rankEasy.data"));
                    break;
                case Game.NORMAL:
                    dao = new DaoImplement(new File("rankNormal.data"));
                    break;
                case Game.DIFFICULT:
                    dao = new DaoImplement(new File("rankDifficult.data"));
                    break;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao.sortRanks();
        dao.showRanks();
        RankingMenu rankingMenu = new RankingMenu();
        Main.cardPanel.add(rankingMenu.getMainPanel());
        Main.cardLayout.next(Main.cardPanel);
        String userName = JOptionPane.showInputDialog(null, "游戏结束，你的得分是"+score+",\n请输入名字记录得分:");
        if(userName != null){
            Round round = new Round(dao.getRoundsNum()+1,userName,score);
            dao.addRound(round);
            dao.sortRanks();
            rankingMenu.updateData();
            dao.save();
        }
    }


    public static int getGameDifficulty() {
        return gameDifficulty;
    }

    public static void setGameDifficulty(int gameDifficulty) {
        Game.gameDifficulty = gameDifficulty;
    }

    private void musicControl(){
        if(musicOn){
            //如果bgm不存在且boss不存在,播放bgm
            if((bgm == null || !bgm.isAlive()) && !bossExists){
                this.bgm = new MusicThread("src/videos/bgm.wav");
                bgm.start();
            }
            //如果boss存在,停止播放bgm,播放bossBgm
            if((bossBgm == null || !bossBgm.isAlive()) && bossExists){
                bgm.stopMusic();
                this.bossBgm = new MusicThread("src/videos/bgm_boss.wav");
                bossBgm.start();
            }
            if(!bossExists){
                if(bossBgm!=null){
                    bossBgm.stopMusic();
                }
            }
        }
    }
}
