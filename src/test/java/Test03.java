import basic.*;
import special.Effector;

import java.util.ArrayList;

public class Test03 {

    public static void main(String[] args) throws InterruptedException {
        //斯纳金箱 实验1 按下按钮就有奖励(权值提高)
        // 检验ICS系统是否具有"智能"
        //与Test02相比，加入了资源受限，使用狭义ICS系统。

        //1.创建一个信息结构拟合器
        InfoChainMakerLimit infoChainMaker = new InfoChainMakerLimit();

        //2.创建接收器，行动器
        //Receptor receptor = new Receptor();
        Effector effector = new Effector();


        ArrayList<InfoUnit> actionList = new ArrayList<>();

        String action1 = "按下按钮";
        String action2 = "静止1秒";

        InfoUnit actionInfoUnit1 = new InfoUnit();
        actionInfoUnit1.setBasicInfo(action1);
        actionInfoUnit1.setInfoID(infoChainMaker.getIdNumber());
        infoChainMaker.setIdNumber(infoChainMaker.getIdNumber()+1);
        actionInfoUnit1.setLinkTime(MagicValue.DEFAULT_SURVIVAL_TIME);

        InfoUnit actionInfoUnit2 = new InfoUnit();
        actionInfoUnit2.setBasicInfo(action2);
        actionInfoUnit2.setInfoID(infoChainMaker.getIdNumber());
        infoChainMaker.setIdNumber(infoChainMaker.getIdNumber()+1);
        actionInfoUnit2.setLinkTime(MagicValue.DEFAULT_SURVIVAL_TIME);



        actionList.add(actionInfoUnit1);
        actionList.add(actionInfoUnit2);

        effector.setActionInfoUnitList(actionList);

        System.out.println("actionList = " + actionList);

        //3.初始化信息结构拟合器
        //infoChainMaker.getFromReceptor(receptor);
        infoChainMaker.getFromEffector(effector);


        //循环生成信息链
        //v0.0.2新增信息池默认最大信息元数量限制部分代码
        int maxInfoUnitLimit = MagicValue.DEFAULT_INFO_UNIT_NUMBER - effector.getActionInfoUnitList().size() ;

        for (int i = 0; i < maxInfoUnitLimit; i++) {

            //4.组合一个信息链
            InfoChain infoChain = infoChainMaker.getRandomInfoChain();

            //5.Target规则检测
            //检测到action1按下按钮，则增加权重和存活时间

            System.out.println(infoChain.toString());


            //6.生存时间控制 单位:ms
            infoChainMaker.killLifeTime(10);

            //7.执行信息链
            effector.executeInfoChain(infoChain);

            Thread.sleep(500);


        }


    }


}
