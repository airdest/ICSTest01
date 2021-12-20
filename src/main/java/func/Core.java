package func;

import basic.Effector;
import basic.InfoChain;
import basic.InfoUnit;
import basic.Receptor;
import demo.targets.TargetNum01;
import constant.MagicValue;

import java.util.HashMap;
import java.util.Set;

/**
 * Core
 * 信息结构拟合器
 *
 *
 * 流程：
 * 1。信息结构拟合器创造一个信息链，根据当前信息链Map中权重最大的两个信息链组合出一个新的信息链，一个信息链中不能出现两次相同的信息单元。
 * 2.Target读取这个信息链，进行check，check成功则增加当前信息链中元素的权重，并输出到结果展示map，check失败则降低所有元素的权值，更新信息链列表。
 * 3、并加入到信息链map中 ，信息链map存储其<编号,信息链>
 *
 *
 *
 */
public class Core {

    HashMap<Integer,InfoUnit> infoUnitHashMap;
    Integer idNumber = 0;


    /**
     * 将给定的两个InfoUnit组合为一个新的InfoUnit,返回一个InfoChain
     * @param left
     * @param right
     * @return
     */
    public InfoUnit combineInfoChain(InfoUnit left,InfoUnit right){
        //前一个单元指向的后一个单元
        left.setPointTo(right);
        //前一个单元指向后一个单元的概率
        left.setPointToProbablity(0.8);
        //组合infoChain
        InfoUnit infoChain = new InfoChain(left,right, System.currentTimeMillis(), MagicValue.defaultLifeTime);
        //返回infoChain
        return infoChain;
    }


    /**
     * 读取InfoUnit的Map
     *
     * 这里假设新生成的InfoMap都处于第0层，判断如果当前所有元素的层数都为0，则启动初始化组合，否则就是依据权值的组合
     *
     * @return
     */
    public InfoUnit zeroLayerCombine(){
        //第一次，所有顺序组合俩直接返回，初始权值为前两个的权值最高的一个+1
        Set<Integer> integers = infoUnitHashMap.keySet();
        for (Integer integer : integers) {
            //TODO

        }



        return null;
    }


    public InfoUnit multiLayerCombine(){

        return null;
    }


    /**
     * 从Recipiter中获取InfoUnit加入到InfoUnitMap中
     * 如果连续不断地获取新信息，则可以每隔一段时间调用一次获取
     *
     * 我认为信息结构组合系统并不应该关心外部具体时间，而只关心内部存活时间就足够了。
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
    public boolean checkInfoUnitWithTarget(InfoUnit infoChain, TargetNum01 target){

        return target.checkRule(infoChain);
    }
}
