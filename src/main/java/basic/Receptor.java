package basic;

import basic.InfoUnit;

import java.util.ArrayList;

/**
 * Receptor
 */
public class Receptor {

    /**
     * 基础信息单元列表
     * 接收器接收到的基础信息单元的列表
     */
    public ArrayList<InfoUnit> basicInfoUnitList;

    public ArrayList<InfoUnit> getBasicInfoUnitList() {
        return basicInfoUnitList;
    }

    public void setBasicInfoUnitList(ArrayList<InfoUnit> basicInfoUnitList) {
        this.basicInfoUnitList = basicInfoUnitList;
    }

    public Receptor(ArrayList<InfoUnit> basicInfoUnitList) {
        this.basicInfoUnitList = basicInfoUnitList;
    }

    public Receptor() {

    }

}
