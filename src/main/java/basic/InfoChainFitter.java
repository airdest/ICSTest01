package basic;

import special.Effector;
import demo.targets.TargetNum01;
import special.Receptor;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * 信息结构拟合器
 * 流程：
 * 1。信息结构拟合器创造一个信息链，根据当前信息链Map中权重最大的两个信息链组合出一个新的信息链
 * 2.Target读取这个信息链，进行check，check成功则增加当前信息链中元素的权重，并输出到结果展示map，check失败则降低所有元素的权值，更新信息链列表。
 * 3、并加入到信息链map中 ，信息链map存储其<编号,信息链>
 *
 *
 *
 */
public class InfoChainFitter {

    //信息池 <信息元编号，信息元>
    HashMap<Integer, InfoChain> infoUnitHashMap;

    //信息元序列化编号
    Integer idNumber = 0;


    /**
     * 将给定的两个InfoUnit组合为一个新的InfoUnit,返回一个InfoChain
     * @param left
     * @param right
     * @return
     */
    public InfoChain combineInfoChain(InfoChain left, InfoChain right){

        return null;
    }


    /**
     * 读取InfoUnit的Map
     *
     * 这里假设新生成的InfoMap都处于第0层，判断如果当前所有元素的层数都为0，则启动初始化组合，否则就是依据权值的组合
     *
     * @return
     */
    public InfoChain zeroLayerCombine(){
        //第一次，所有顺序组合俩直接返回，初始权值为前两个的权值最高的一个+1
        Set<Integer> integers = infoUnitHashMap.keySet();
        for (Integer integer : integers) {
            //TODO

        }



        return null;
    }


    public InfoChain multiLayerCombine(){

        return null;
    }


    /**
     * 从Recipiter中获取InfoUnit加入到InfoUnitMap中
     * 如果连续不断地获取新信息，则可以每隔一段时间调用一次
     *
     *
     *
     */
    public void getFromReceptor(Receptor receptor){

    }

    /**
     * 添加Effector中的InfoMap放入InfoUnitHashMap
     *
     */
    public void getFromEffector(Effector effector){





    }

    /**
     * 存活时常控制
     * 1.每次运行killLifeTime,都将所有的InfoUnit的生存时间减去指定额度
     * 2.把所有生存时间小于0的InfoUnit从infoUnitMap中移除
     */
    public void killLifeTime(long timeToSub){




    }



    /**
     * 对比InfoChain是否满足规则Target
     * @param infoChain
     * @param target
     * @return
     */
    public boolean checkInfoUnitWithTarget(InfoChain infoChain, TargetNum01 target){

        return target.checkRule(infoChain);
    }
}
