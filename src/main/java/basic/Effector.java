package basic;

import basic.InfoChain;
import basic.InfoUnit;

import java.util.ArrayList;

/**
 * Effector
 */
public class Effector {

    /**
     * 基础指令信息单元列表
     *
     * 在外部初始化时设置序列号，存活时间等等。
     */
    public ArrayList<InfoUnit> actionInfoUnitList;

    public Effector(ArrayList<InfoUnit> actionInfoUnitList) {
        this.actionInfoUnitList = actionInfoUnitList;
    }

    public ArrayList<InfoUnit> getActionInfoUnitList() {
        return actionInfoUnitList;
    }

    public void setActionInfoUnitList(ArrayList<InfoUnit> actionInfoUnitList) {
        this.actionInfoUnitList = actionInfoUnitList;
    }

    public Effector() {
    }

    public void executeInfoChain(InfoUnit infoUnit){

    }





}
