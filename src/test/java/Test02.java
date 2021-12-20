import basic.InfoChain;
import basic.InfoChainMaker;
import basic.InfoUnit;
import basic.MagicValue;
import special.Effector;
import special.Receptor;

import java.util.ArrayList;

public class Test02 {

    public static void main(String[] args) throws InterruptedException {
        //斯纳金箱 实验1 按下按钮就有奖励(权值提高)
        // 检验ICS系统是否具有"智能"

        //1.创建一个信息结构拟合器
        InfoChainMaker infoChainMaker = new InfoChainMaker();

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
        actionInfoUnit1.setSurvivalTime(MagicValue.DEFAULT_SURVIVAL_TIME);

        InfoUnit actionInfoUnit2 = new InfoUnit();
        actionInfoUnit2.setBasicInfo(action2);
        actionInfoUnit2.setInfoID(infoChainMaker.getIdNumber());
        infoChainMaker.setIdNumber(infoChainMaker.getIdNumber()+1);
        actionInfoUnit2.setSurvivalTime(MagicValue.DEFAULT_SURVIVAL_TIME);



        actionList.add(actionInfoUnit1);
        actionList.add(actionInfoUnit2);

        effector.setActionInfoUnitList(actionList);

        System.out.println(actionList);

        //3.初始化信息结构拟合器
        //infoChainMaker.getFromReceptor(receptor);
        infoChainMaker.getFromEffector(effector);


        //循环生成信息链
        for (int i = 0; i < 100; i++) {

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
