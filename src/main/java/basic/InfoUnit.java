package basic;

/**
 * 信息单元
 * 信息单元可以是一个信息链也可以是一个信息映射，
 * 大部分情况下是信息链
 */
public class InfoUnit {

    InfoUnit pointTo = null;

    Integer layer = 0;

    double pointToProbablity = 0;

    public InfoUnit getPointTo() {
        return pointTo;
    }

    public void setPointTo(InfoUnit pointTo) {
        this.pointTo = pointTo;
    }

    public double getPointToProbablity() {
        return pointToProbablity;
    }

    public void setPointToProbablity(double pointToProbablity) {
        this.pointToProbablity = pointToProbablity;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}
