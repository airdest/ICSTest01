package basic;

import special.Effector;
import demo.targets.TargetNum01;
import special.Receptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * 信息链制造器
 *
 *
 * 流程：
 * 1。信息结构拟合器创造一个信息链，根据当前信息链Map中权重最大的两个信息链组合出一个新的信息链
 * 2.Target读取这个信息链，进行check，check成功则增加当前信息链中元素的权重，并输出到结果展示map，check失败则降低所有元素的权值，更新信息链列表。
 * 3、并加入到信息链map中 ，信息链map存储其<编号,信息链>
 *
 *
 *
 */
public class InfoChainMaker {

    //接收器信息池
    HashMap<Integer,InfoUnit> receptorInfoMap;

    //行动器信息池
    HashMap<Integer,InfoUnit> effectorInfoMap;

    //信息池
    HashMap<Integer,InfoUnit> infoUnitMap;

    //信息元序列化编号
    Integer idNumber = 0;

    /**
     * 根据当前已有信息元、以及存活时常、概率等，生成一个信息链，放入信息池并返回。
     * @return
     */
    public InfoChain getInfoChain(){


        return null;
    }



    /**
     * 从Recipiter中获取基础信息元加入到信息池中
     * 如果连续不断地获取新信息，则可以每隔一段时间调用一次
     * 例如：视觉细胞每秒能最大识别500多帧，对应就是每2ms调用一次接收函数
     * 接收函数会判断receptor的状态，如果状态变更则更新信息池中对应的信息。
     */
    public void getFromReceptor(Receptor receptor){

        //1.调用接收器方法，获取基础信息元

        //2.基础信息元加入或更新到信息池
    }

    /**
     * 添加Effector中的行动指令到信息池
     * 和从接收器接收一样，这里也会判断并更新信息池中装有行动指令的信息单元。
     *
     */
    public void getFromEffector(Effector effector){

        //1.调用行动器方法，获取基础指令信息元

        //2.基础指令信息元加入或更新到信息池

    }

    /**
     * 存活时常控制，由外部每隔一段时间调用，或者每隔某几步操作调用
     * 1.每次运行killLifeTime,都将所有的InfoUnit的生存时间减去指定额度
     * 2.把所有生存时间小于0的InfoUnit从infoUnitMap中移除
     */
    public void killLifeTime(long timeToSub){


    }




}
