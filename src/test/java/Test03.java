import basic.*;
import special.Effector;

import java.util.ArrayList;

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


        System.out.println("actionList = " + actionList);

        //3.初始化ICM，设置行动器
        //infoChainMaker.getFromReceptor(receptor);
        infoChainMaker.getFromEffector(effector);


        //循环生成信息链
        //v0.0.2新增信息池默认最大信息元数量限制部分代码
        int maxInfoUnitLimit = MagicValue.DEFAULT_INFO_UNIT_NUMBER - effector.getActionInfoUnitList().size() ;

        for (int i = 0; i < maxInfoUnitLimit; i++) {

            //4.组合一个信息链
            InfoChain infoChain = infoChainMaker.getRandomInfoChain();

            if (infoChain == null){
                break;
            }

            //5.Target规则检测
            //检测到action1按下按钮，则增加权重和存活时间

            System.out.println("组合出的信息链："+infoChain.toString());

            //v0.0.3 增加信息链最大信息元数量限制部分代码
            ArrayList<InfoUnit> flattenInfoChain = infoChainMaker.flattenInfoChain(infoChain.getInfoUnitList());
            System.out.println("信息链展开："+flattenInfoChain);

            //v0.0.4 最长信息链限制：
            //超出长度限制会被销毁
            if (flattenInfoChain.size() > MagicValue.DEFAULT_INFO_UNIT_LENGTH){
                infoChainMaker.killInfoChainByID(infoChain.getInfoID());
            }

            //6.生存时间控制 单位:ms
            infoChainMaker.killLifeTime(5000);

            //7.执行信息链
            //effector.executeInfoChain(infoChain);


            Thread.sleep(500);

            //如果达到最大信息元数量限制，就停止生成信息元
            if (flattenInfoChain.size() >= MagicValue.DEFAULT_INFO_UNIT_NUMBER){
                break;
            }
        }


    }


}
