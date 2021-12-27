package basic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Target
 */
public class Target {

    /**
     * sigmoid 用于把任意数字映射到0到1之间作为概率。
     */
    public double mysigMoid(double value) {
        //Math.E=e;Math.Pow(a,b)=a^b
        double ey = Math.pow(Math.E, -value);
        double result = 1 / (1 + ey);
        return result;
    }

    /**
     * 接收信息链，更新权重
     * 返回更新后的信息链
     */
    public InfoChain  updateInfoChainWeights(InfoChain infoChain,Reward reward){
        //信息链所递归式逐级展开，分别更新每一层的两个信息单元的指向概率。
        ArrayList<InfoUnit> infoUnitList = infoChain.getInfoUnitList();

        InfoUnit left = infoUnitList.get(0);

        InfoUnit right = infoUnitList.get(1);
        if (left.isInfoChain()){
            InfoChain leftInfoChain = (InfoChain)left;
            return updateInfoChainWeights(leftInfoChain, reward);


        }else{

            //不是信息链则直接更新
            HashMap<InfoUnit, InfoLink> linkToList = left.getLinkToList();
            for (InfoUnit infoUnit : linkToList.keySet()) {
                //获取对应infoLink
                InfoLink infoLink = linkToList.get(infoUnit);
                //设置概率 必须在0到1之间用sigmoid映射到0到1，所以用了sigmoid
                double weight = infoLink.getProbablity() + reward.getWeightChange();
                double sigmoidWeight = mysigMoid(weight);
                infoLink.setProbablity(sigmoidWeight);

                //设置生存时间
                infoLink.setLinkTime(infoLink.getLinkTime() + reward.getTimeChange());

                //更新到链接列表
                linkToList.put(infoUnit,infoLink);
            }

            //更新后的链接列表，设置到left
            left.setLinkToList(linkToList);

            //设置后，left设置到信息链
            ArrayList<InfoUnit> updatedInfoUnitList = new ArrayList<>();
            updatedInfoUnitList.add(left);
            updatedInfoUnitList.add(right);

            infoChain.setInfoUnitList(updatedInfoUnitList);

            return infoChain;
        }

    }

    /**
     * 获取信息链列表中所有的信息链列表
     * @return
     */
    public ArrayList<InfoUnit> getInfoChainListRecr(ArrayList<InfoUnit> infoUnitList){
        //遍历列表中每个元素，如果是信息链，就加入到信息链列表，并且把信息链的列表继续递归调用,获得的两个信息链列表融合，
        //最后返回信息链列表
        //如果不是信息链，返回是一个空的信息链列表。
        ArrayList<InfoUnit> infoUnitListAll = new ArrayList<>();
        //System.out.println("infoUnitListAll = " + infoUnitListAll);
        for (InfoUnit infoUnit : infoUnitList) {
            if (infoUnit.isInfoChain()){
                InfoChain infoChainInner = (InfoChain)infoUnit;
                infoUnitListAll.add(infoChainInner);
                infoUnitListAll.addAll(getInfoChainListRecr(infoChainInner.getInfoUnitList()));
                return infoUnitListAll;
            }
        }
        return infoUnitList;
    }


    /**
     * 获取信息链中包含的全部信息链列表
     * @param infoChain
     * @return
     */
    public ArrayList<InfoUnit> getInfoUnitList(InfoChain infoChain){
        ArrayList<InfoUnit> returnInfoUnitList = new ArrayList<>();
        returnInfoUnitList.add(infoChain);

        //如果infoChainListRecr是空的，说明处在第2层，也就是第一层信息链，第一层信息链无需权值更新
        // ，所以需要判断信息链包含的left和right是不是基础信息元。
        if (!infoChain.isBasicInfoChain()){
            ArrayList<InfoUnit> infoUnitList = infoChain.getInfoUnitList();
            ArrayList<InfoUnit> infoChainListRecr = getInfoChainListRecr(infoUnitList);

            //infounit转换为infochain。加入并返回
            returnInfoUnitList.addAll(infoChainListRecr);
        }
        return returnInfoUnitList;

    }


    /**
     * 根据更新后的infochain的包含的所有的信息链的列表，遍历获取其包含的信息链列表，分别更新到信息池
     */
    public HashMap<Integer,InfoUnit> updateInfoUnitMap(ArrayList<InfoUnit> infoUnitList,HashMap<Integer,InfoUnit> infoUnitMap){
        for (InfoUnit infoUnit : infoUnitList) {
            Integer infoID = infoUnit.getInfoID();
            infoUnitMap.put(infoID,infoUnit);
        }
        return infoUnitMap;
    }



}
