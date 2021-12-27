package demo01;

import basic.InfoChain;
import basic.InfoChainMaker;
import basic.InfoUnit;
import basic.Parameter;
import basic.Effector;
import basic.Reward;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  斯纳金箱 实验1 按下按钮就有奖励
 *  TODO 斯纳金箱实验三个部分完成，参考：https://zhuanlan.zhihu.com/p/126995823
 */
public class Test04 {

    public static void main(String[] args) throws InterruptedException {

        //1.创建一个信息结构拟合器
        InfoChainMaker infoChainMaker = new InfoChainMaker();

        //2.创建接收器，行动器
        //Receptor receptor = new Receptor();
        Effector effector = new Effector();

        //创建Target
        Target01 target01 = new Target01();

        //行动信息单元列表
        ArrayList<InfoUnit> actionList = new ArrayList<>();

        String action1 = "按下按钮";
        String action2 = "静止1秒";

        InfoUnit actionInfoUnit1 = new InfoUnit();
        actionInfoUnit1.setBasicInfo(action1);
        //设置id 为 1 ，并+1
        actionInfoUnit1.setInfoID(infoChainMaker.getIdNumber());
        infoChainMaker.setIdNumber(infoChainMaker.getIdNumber()+1);
        actionInfoUnit1.setLinkTime(Parameter.DEFAULT_SURVIVAL_TIME);


        InfoUnit actionInfoUnit2 = new InfoUnit();
        actionInfoUnit2.setBasicInfo(action2);
        //设置id 为 2 ，并+1
        actionInfoUnit2.setInfoID(infoChainMaker.getIdNumber());
        infoChainMaker.setIdNumber(infoChainMaker.getIdNumber()+1);
        actionInfoUnit2.setLinkTime(Parameter.DEFAULT_SURVIVAL_TIME);

        //添加到行动信息元列表
        actionList.add(actionInfoUnit1);
        actionList.add(actionInfoUnit2);

        //设置到行动器
        effector.setActionInfoUnitList(actionList);

        //输出查看行动信息单元列表
        System.out.println("actionList = " + actionList);

        //3.初始化ICM，设置行动器
        //infoChainMaker.getFromReceptor(receptor);
        infoChainMaker.getFromEffector(effector);


        //循环生成信息链
        //v0.0.2新增信息池默认最大信息元数量限制部分代码
        int maxInfoUnitLimit = Parameter.DEFAULT_INFO_UNIT_NUMBER - effector.getActionInfoUnitList().size() ;

        for (int i = 0; i < maxInfoUnitLimit; i++) {

            //生存时间控制 单位:ms
            //TODO infoChainMaker应该实现各自的寿命减少方法，或者暂时不考虑寿命问题
            infoChainMaker.killLifeTime(10);

            //4.组合一个信息链
            InfoChain infoChain = infoChainMaker.getRandomInfoChain();

            //如果组合出的信息链为空，说明信息池中所有信息元已经死亡，程序终止
            if (infoChain == null){
                System.out.println("信息池中所有信息元都死掉了，Target拟合失败。");
                break;
            }



            //查看组合出的信息链
            //System.out.println("组合出的信息链："+infoChain.toString());


            //先展开信息链
            ArrayList<InfoUnit> flattenInfoChain = infoChainMaker.flattenInfoChain(infoChain.getInfoUnitList());



            //最长信息链限制，超出长度限制会被销毁
            boolean died = false;
            if (flattenInfoChain.size() > Parameter.DEFAULT_INFO_UNIT_LENGTH){
                infoChainMaker.killInfoChainByID(infoChain.getInfoID());
                died = true;
            }


            //如果信息链没死，进行Target规则判断，并展示，死掉的无需展示

            if (!died){
                System.out.println("信息链展开："+flattenInfoChain);
                Reward rewardChange = target01.getRewardChange(flattenInfoChain);


                //自己以及子链权值递归减少
                //TODO 对每一个子链都递归调用这个过程
                InfoChain infochainWeighted = target01.updateInfoChainWeights(infoChain, rewardChange);

                //这样的信息链本身及其子链存活时间也应该被减少
                infochainWeighted.setLinkTime(infoChain.getLinkTime() + rewardChange.getTimeChange());

                ArrayList<InfoUnit> infoChainList = target01.getInfoUnitList(infochainWeighted);

                HashMap<Integer, InfoUnit> updatedInfoUnitMap = target01.updateInfoUnitMap(infoChainList, infoChainMaker.getInfoUnitMap());

                //更新到信息池
                infoChainMaker.setInfoUnitMap(updatedInfoUnitMap);

                //杀掉死亡的InfoLink
                infoChainMaker.killPointToLifeTime();
            }

        }

        System.out.println("信息链数量达到上限，构造结束。");


    }


}
