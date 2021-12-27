package basic;

import java.util.ArrayList;

/**
 * InfoChain
 */
public class InfoChain extends InfoUnit {


    /**
     * 信息链存储的信息链接，是有序的从左到右，即第一个到最后一个。
     */
    ArrayList<InfoUnit> infoUnitList = new ArrayList<>(Parameter.DEFAULT_INFO_UNIT_LENGTH);

    public ArrayList<InfoUnit> getInfoUnitList() {
        return infoUnitList;
    }

    public void setInfoUnitList(ArrayList<InfoUnit> infoUnitList) {
        this.infoUnitList = infoUnitList;
    }


    @Override
    public String toString() {
        return "信息链{" +
                "infoID=" + infoID +
                ", 信息链存储的信息单元列表=" + infoUnitList +
                ", layer=" + layer +
                ", survivalTime=" + survivalTime +
                '}';
    }


    /**
     * 是不是基础信息链
     * 基础信息链指的是left 和 right都是基础信息单元
     * 这样就不需要递归遍历了。
     * @return
     */
    public boolean isBasicInfoChain(){
        boolean isbasic = true;
        for (InfoUnit infoUnit : infoUnitList) {
            if (infoUnit.isInfoChain()){
                isbasic = false;
                break;
            }
        }
        return isbasic;

    }





}
