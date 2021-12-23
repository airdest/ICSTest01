import basic.InfoChain;
import basic.InfoChainMaker;
import special.Effector;
import special.Receptor;
import special.Target;

@Deprecated
public class Test01 {

    public static void main(String[] args) {

        //测试InfoChainMaker
        //1.创建一个信息结构拟合器
        InfoChainMaker infoChainMaker = new InfoChainMaker();

        //2.创建接收器，行动器
        Receptor receptor = new Receptor();
        Effector effector = new Effector();

        //3.初始化信息结构拟合器
        infoChainMaker.getFromReceptor(receptor);
        infoChainMaker.getFromEffector(effector);


        //循环生成信息链
        for (int i = 0; i < 100; i++) {

            //4.组合一个信息链
            InfoChain infoChain = infoChainMaker.getRandomInfoChain();

            //5.Target规则检测

            //6.生存时间控制 单位:ms
            infoChainMaker.killLifeTime(10);

            //7.执行信息链
            effector.executeInfoChain(infoChain);

        }


    }
}
