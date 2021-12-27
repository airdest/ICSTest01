package basic;

import java.util.HashMap;

/**
 * InfoUnit
 */
public class InfoUnit  {
    /**
     * 被激活（被选作信息链组合）次数
     */
    Integer activatedTime = 0;


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
    HashMap<InfoUnit,InfoLink> linkToList = new HashMap<>();

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

    public Integer getLinkTime() {
        return survivalTime;
    }

    public void setLinkTime(Integer linkTime) {
        this.survivalTime = linkTime;
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


    public String toStringComplicated() {
        return "InfoUnit{" +
                "infoID=" + infoID +
                ", layer=" + layer +
                ", survivalTime=" + survivalTime +
                ", basicInfo=" + basicInfo +
                ", linkToList=" + linkToList +
                '}';
    }

    @Override
    public String toString(){
        return "InfoUnit{" +
                "infoID=" + infoID +
                ", basicInfo=" + basicInfo +
                ", linkToList=" + getLinkedListString() +
                '}';

    }

    public String getLinkedListString(){
        StringBuilder linkedToList = new StringBuilder("");
        linkedToList.append("(");
        for (InfoUnit infoUnit : linkToList.keySet()) {
            linkedToList.append("infoID=");
            linkedToList.append(infoUnit.infoID);
            linkedToList.append(",probablity=");
            linkedToList.append(linkToList.get(infoUnit).getProbablity());
        }

        linkedToList.append(")");
        return linkedToList.toString();
    }


    public boolean isInfoChain(){
        if (this instanceof InfoChain){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 如果时间小于0，就删掉
     */
    public void killLinktoListTime(){

        HashMap<InfoUnit,InfoLink> newLinkedToList = new HashMap<>();

        for (InfoUnit infoUnit : linkToList.keySet()) {
            InfoLink infoLink = linkToList.get(infoUnit);
            if (infoLink.getLinkTime()> 0){
                newLinkedToList.put(infoUnit,infoLink);
            }
        }
        this.linkToList = newLinkedToList;
    }

}
