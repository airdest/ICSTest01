import basic.*;
import special.Effector;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  斯纳金箱 实验1 按下按钮就有奖励(权值提高)
 *  检验ICS是否具有尽可能拟合目标的能力
 *
 */
public class Test03 {

    public static void main(String[] args) throws InterruptedException {

        //1.创建一个信息结构拟合器
        InfoChainMakerLimit infoChainMaker = new InfoChainMakerLimit();

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
        actionInfoUnit1.setLinkTime(MagicValue.DEFAULT_SURVIVAL_TIME);


        InfoUnit actionInfoUnit2 = new InfoUnit();
        actionInfoUnit2.setBasicInfo(action2);
        //设置id 为 2 ，并+1
        actionInfoUnit2.setInfoID(infoChainMaker.getIdNumber());
        infoChainMaker.setIdNumber(infoChainMaker.getIdNumber()+1);
        actionInfoUnit2.setLinkTime(MagicValue.DEFAULT_SURVIVAL_TIME);

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
        int maxInfoUnitLimit = MagicValue.DEFAULT_INFO_UNIT_NUMBER - effector.getActionInfoUnitList().size() ;

        for (int i = 0; i < maxInfoUnitLimit; i++) {

            //生存时间控制 单位:ms
            infoChainMaker.killLifeTime(3000);

            //4.组合一个信息链
            InfoChain infoChain = infoChainMaker.getRandomInfoChain();

            //如果组合出的信息链为空，说明信息池中所有信息元已经死亡，程序终止
            if (infoChain == null){
                System.out.println("信息池中所有信息元都死掉了，Target拟合失败。");
                break;
            }



            //查看组合出的信息链
            System.out.println("组合出的信息链："+infoChain.toString());


            //先展开信息链
            ArrayList<InfoUnit> flattenInfoChain = infoChainMaker.flattenInfoChain(infoChain.getInfoUnitList());



            //最长信息链限制，超出长度限制会被销毁
            boolean died = false;
            if (flattenInfoChain.size() > MagicValue.DEFAULT_INFO_UNIT_LENGTH){
                infoChainMaker.killInfoChainByID(infoChain.getInfoID());
                died = true;
            }

            System.out.println("信息链展开："+flattenInfoChain);
            //如果信息链没死，进行Target规则判断
            //HashMap<Integer, InfoUnit> weightedInfoPool = target01.checkRule(flattenInfoChain, infoChainMaker.getInfoUnitMap());
            //权值更新
            //infoChainMaker.setInfoUnitMap(weightedInfoPool);

            double weightsAdd = target01.getWeightsAdd(flattenInfoChain);

            InfoChain infochainWeighted = target01.updateInfoChainWeights(infoChain, weightsAdd);

            ArrayList<InfoUnit> infoChainList = target01.getInfoUnitList(infochainWeighted);

            HashMap<Integer, InfoUnit> updatedInfoUnitMap = target01.updateInfoUnitMap(infoChainList, infoChainMaker.getInfoUnitMap());

            infoChainMaker.setInfoUnitMap(updatedInfoUnitMap);


            //7.执行信息链
            //effector.executeInfoChain(infoChain);

        }

        System.out.println("信息链数量达到上限，构造结束。");


    }


}
