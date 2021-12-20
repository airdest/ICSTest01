package basic;

import java.util.HashMap;

/**
 * 信息单元:
 *
 * 信息单元简称信息元，是信息链组合中最小的用于组合的单元。
 * 信息元描述的信息精度，取决于创造信息元的接收器、行动器描述或映射现实世界的精度。
 * 它可以是一个单独的信息元，也可以是一个信息链
 *
 *
 *
 * 信息元包含的基础信息：
 * 1.可以是接收器创造的基础信息，如果是基础信息元，则存在时间相对设置更长。
 * 2.可以是行动器创造的行动指令
 *
 */
public class InfoUnit  {

    /**
     * 信息元的id，一般是从0开始的整数，用作系统内对不同信息元进行区分
     */
    Integer infoID = 0;

    /**
     * 信息元所处层级
     */
    Integer layer = 0;

    /**
     * 信息元存活时间
     */
    Integer survivalTime;

    /**
     * 信息元存储的基础信息
     * 可以是接收器传出的基础信息，也可以是信息链
     * 因为抽象层次上，一个信息链整体也可以看作一个信息元
     */
    Object basicInfo;

    /**
     * 信息元指向的其它信息元，及指向概率和时间(信息链接)
     * 简单地说就是：指向哪个信息元，指向的概率是多少，这个指向的存活时间是多少。
     */
    HashMap<InfoUnit,InfoLink> linkToList = new HashMap<>(MagicValue.DEFAULT_MAX_POINT_TO_NUMBER);

    public Integer getInfoID() {
        return infoID;
    }

    public void setInfoID(Integer infoID) {
        this.infoID = infoID;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(Integer survivalTime) {
        this.survivalTime = survivalTime;
    }

    public Object getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(Object basicInfo) {
        this.basicInfo = basicInfo;
    }

    public HashMap<InfoUnit, InfoLink> getLinkToList() {
        return linkToList;
    }

    public void setLinkToList(HashMap<InfoUnit, InfoLink> linkToList) {
        this.linkToList = linkToList;
    }

    @Override
    public String toString() {
        return "InfoUnit{" +
                "infoID=" + infoID +
                ", layer=" + layer +
                ", survivalTime=" + survivalTime +
                ", basicInfo=" + basicInfo +
                ", linkToList=" + linkToList +
                '}';
    }
}
