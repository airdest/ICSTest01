package basic;

/**
 * InfoLink
 */

public class InfoLink extends InfoChain {

    /**
     * 链接存活时长
     */
    Integer linkTime;

    /**
     * 链接触发概率
     */
    Double probablity = 0.0;

    @Override
    public Integer getLinkTime() {
        return linkTime;
    }

    @Override
    public void setLinkTime(Integer linkTime) {
        this.linkTime = linkTime;
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
                ", linkTime=" + linkTime +
                ", probablity=" + probablity +
                '}';
    }
}
