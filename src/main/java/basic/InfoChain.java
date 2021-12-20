package basic;

public class InfoChain extends InfoUnit{

    Object left;
    Object right;
    long createdTime;
    long lifeTime;

    public InfoChain(Object left, Object right, long createdTime, long lifeTime) {
        this.left = left;
        this.right = right;
        this.createdTime = createdTime;
        this.lifeTime = lifeTime;
    }


    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = right;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(long lifeTime) {
        this.lifeTime = lifeTime;
    }
}
