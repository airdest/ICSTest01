package demo01;

import basic.InfoUnit;
import basic.Reward;
import basic.Target;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 实现斯纳金箱实验中，对于信息池的权重更新
 */
public class Target01 extends Target {

    HashMap<Integer,InfoUnit> updatedInfoUnitMap = new HashMap<>();

    /**
     * 获取更新的权值增量
     * @param infoUnitList
     * @return
     */
    public Reward getRewardChange(ArrayList<InfoUnit> infoUnitList) {
        //判断展开的信息单元中，信息单元包含按下按钮的数量占总信息单元的比例
        //占比越大，增加的存活时间和权重越大，如果没有按下按钮，则减少权重和存活时间。

        int button =0;
        int stop = 0;

        for (InfoUnit infoUnit : infoUnitList) {
            if ("按下按钮".equals(infoUnit.getBasicInfo())){
                button++;
            }

            if ("静止1秒".equals(infoUnit.getBasicInfo())){
                stop++;
            }
        }


        //计算权值增加的比率
        double buttonRatio = (double) button / (button+stop) ;
        double stopRatio = (double) stop / (button+stop) ;

        System.out.println("buttonRatio = " + buttonRatio);
        //得出此信息链要增加的权值

        double weightAdd = 0;

        if (buttonRatio > 0.5){
            weightAdd = 0.1 * button;
        }else{
            weightAdd = -0.1 ;

        }



        //如果没有按下按钮，则惩罚，这样包含按下按钮的都能存活
        if (weightAdd == 0){
            weightAdd = -0.2;
        }

        Reward reward = new Reward();

        reward.setWeightChange(weightAdd);

        int time =  stop * -5000;

        //不动会减少寿命。
        reward.setTimeChange(time);


        //TODO 权值更新就像神经网络的反向传播算法一样，有点意思...
        return reward;
    }


}
