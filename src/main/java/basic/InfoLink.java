package basic;

/**
 *
 * Info Link 信息链接
 * 信息链接代表两个基本信息单元之间的链接。
 * 模拟两个神经元之间建立的链接。
 * 描述的是一个信息元指向另一个信息元的
 * 概率和长度。
 * 本质上是一个抽象的概念。
 */

public class InfoLink extends InfoChain {

    /**
     * 链接存活时长
     */
    Integer survivalTime;

    /**
     * 链接触发概率
     */
    Double probablity = 0.0;

    @Override
    public Integer getSurvivalTime() {
        return survivalTime;
    }

    @Override
    public void setSurvivalTime(Integer survivalTime) {
        this.survivalTime = survivalTime;
    }

    public Double getProbablity() {
        return probablity;
    }

    public void setProbablity(Double probablity) {
        this.probablity = probablity;
    }

    @Override
    public String toString() {
        return "InfoLink{" +
                "infoUnitList=" + infoUnitList +
                ", survivalTime=" + survivalTime +
                ", probablity=" + probablity +
                ", infoID=" + infoID +
                ", layer=" + layer +
                ", survivalTime=" + survivalTime +
                ", basicInfo=" + basicInfo +
                ", linkToList=" + linkToList +
                '}';
    }
}
