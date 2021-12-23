package basic;

import special.Effector;
import special.Receptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 *
 * 信息链制造器  狭义ICS 资源受限
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
public class InfoChainMakerLimit {

    //接收器信息池
    HashMap<Integer,InfoUnit> receptorInfoMap = new HashMap<>();

    //行动器信息池
    HashMap<Integer,InfoUnit> effectorInfoMap = new HashMap<>();

    //信息池
    HashMap<Integer,InfoUnit> infoUnitMap = new HashMap<>();

    //信息元序列化编号，由外部demo控制，内部不做修改
    Integer idNumber = 1;

    //接收器更新次数(用于记录次数)
    Integer receptorUpdateTime = 0;

    //行动器更新次数(用于记录次数)
    Integer effectorUpdateTime = 0;

    //信息链创建次数
    Integer infoChainCreateTime = 0;

    /**
     * 根据当前已有信息元、以及存活时常、概率等，生成一个信息链，放入信息池并返回。
     * @return
     */
    public InfoChain getRandomInfoChain(){


        Random random = new Random(666);

        //-1是因为在把信息链放入map后，又加了1变为4，而这里在取的时候，实际上还是只有3个，所以限制为3，取值0，1，2再+1，就能得到正确取值
        int i = random.nextInt(idNumber -1);

        //随机选取一个信息元进行链接
        /**
         * TODO
         * 现实世界的神经元，不是随机进行链接的，
         * 而是基于某种机制，被触发后进行链接
         * 目前不了解这个机制，所以简单设置为随机的
         */
        i = i + 1;
        System.out.println("随机选取的Left信息元：i = " + i);
        InfoUnit infoUnit = infoUnitMap.get(i);

        //System.out.println("当前infoUnitMap = " + infoUnitMap+"size:"+infoUnitMap.size());
        //System.out.println("随机选取一个infoUnit = " + infoUnit+"ID"+infoUnit.getInfoID());

        //初始化信息链
        InfoChain infoChain = new InfoChain();

        //初始化信息链里的信息元列表
        ArrayList<InfoUnit> infoUnitList = new ArrayList<>();

        //判断是否有链接
        System.out.println("当前infoUnitMap:"+infoUnitMap);
        HashMap<InfoUnit, InfoLink> linkToList = infoUnit.getLinkToList();

        //如果没有链接，就随机链接
        if (linkToList.size() == 0){
            //System.out.println("没有链接，随机链接");
            System.out.println("当前idNumber"+idNumber);
            int randomId = random.nextInt(idNumber - 1);
            randomId =randomId + 1;
            InfoUnit linkUnit = infoUnitMap.get(randomId);
            //System.out.println("randomId = " + randomId);
            //System.out.println("linkUnit0 = " + linkUnit);
            InfoLink infoLink = new InfoLink();
            infoLink.setLinkTime(MagicValue.DEFAULT_SURVIVAL_TIME);
            infoLink.setProbablity(random.nextDouble());
            //设置指向信息元及其概率
            if (linkUnit == null){
                System.out.println("当前选取的randomID:"+randomId);
                System.out.println("指向的信息元不能为空");
                System.exit(100);
            }
            //System.out.println("linkUnit1 = " + linkUnit);
            linkToList.put(linkUnit,infoLink);

            //设置后更新到infoUnit
            infoUnit.setLinkToList(linkToList);

            //设置信息链内容,必须按顺序加入
            infoUnitList.add(infoUnit);
            //System.out.println("linkUnit2 = " + linkUnit);
            infoUnitList.add(linkUnit);


        }else{
            //System.out.println("存在链接，选取最大概率进行链接");
            //否则就寻找最大概率的InfoUnit
            InfoUnit maxProbablityInfoUnit = getMaxProbablityInfoUnit(linkToList);

            //设置信息链内容,必须按顺序加入
            infoUnitList.add(infoUnit);
            infoUnitList.add(maxProbablityInfoUnit);

        }

        //设置信息链内容
        infoChain.setInfoUnitList(infoUnitList);

        //设置信息链持续时间
        infoChain.setLinkTime(MagicValue.DEFAULT_SURVIVAL_TIME);


        //信息链作为信息元，加入队列

        //System.out.println("要设置的infoChain ID"+idNumber);
        infoChain.setInfoID(idNumber);



        //必须先放进去，再自增idNumber，不然会出现错位
        infoUnitMap.put(idNumber,infoChain);

        //设置完之后，自身+1，所以下次组合的时候要减一
        idNumber = idNumber + 1;

        //信息链组合次数+1
        infoChainCreateTime = infoChainCreateTime + 1;



        //返回信息链
        return infoChain;
    }

    /**
     * v0.0.4
     * 删除信息池中指定id的infoChain
     */
    public void killInfoChainByID(Integer idNumber){
        infoUnitMap.remove(idNumber);
    }


    /**
     * 获取指向信息元列表中最大概率指向的信息元
     * @param linkToMap
     * @return
     */
    public InfoUnit getMaxProbablityInfoUnit(HashMap<InfoUnit, InfoLink> linkToMap){
        Set<InfoUnit> infoUnitSet = linkToMap.keySet();
        Double max = -1.0;
        InfoUnit maxProbablityInfoUnit = null;
        for (InfoUnit infoUnit : infoUnitSet) {
            InfoLink infoLink = linkToMap.get(infoUnit);
            if (infoLink.getProbablity() > max){
                max = infoLink.getProbablity();
                maxProbablityInfoUnit = infoUnit;
            }

        }
        return maxProbablityInfoUnit ;

    }



    /**
     * 从Recipiter中获取基础信息元加入到信息池中
     * 如果连续不断地获取新信息，则可以每隔一段时间调用一次
     * 例如：视觉细胞每秒能最大识别500多帧，对应就是每2ms调用一次接收函数
     * 接收函数会判断receptor的状态，如果状态变更则更新信息池中对应的信息。
     */
    public void getFromReceptor(Receptor receptor){
        //1.调用接收器方法，获取基础信息元
        //2.基础信息元加入或更新到信息池,序列号由receptor创建时控制
        ArrayList<InfoUnit> basicInfoUnitList = receptor.getBasicInfoUnitList();
        for (InfoUnit infoUnit : basicInfoUnitList) {
            receptorInfoMap.put(infoUnit.getInfoID(),infoUnit);

            infoUnitMap.put(infoUnit.getInfoID(),infoUnit);
        }

        receptorUpdateTime = receptorUpdateTime + 1;
    }

    /**
     * 添加Effector中的行动指令到信息池
     * 和从接收器接收一样，这里也会判断并更新信息池中装有行动指令的信息单元。
     *
     */
    public void getFromEffector(Effector effector){
        //1.调用行动器方法，获取基础指令信息元
        //2.基础指令信息元加入或更新到信息池
            ArrayList<InfoUnit> actionInfoUnitList = effector.getActionInfoUnitList();
            for (InfoUnit infoUnit : actionInfoUnitList) {
                //加入基础指令信息元到effector信息池
                effectorInfoMap.put(infoUnit.getInfoID(),infoUnit);
                //加入基础指令信息元到信息池
                infoUnitMap.put(infoUnit.getInfoID(),infoUnit);
            }
        effectorUpdateTime = effectorUpdateTime + 1;

    }

    /**
     * 存活时常控制，由外部每隔一段时间调用，或者每隔某几步操作调用
     * 1.每次运行killLifeTime,都将所有的InfoUnit的生存时间减去指定额度
     * 2.把所有生存时间小于0的InfoUnit从infoUnitMap中移除
     */
    public void killLifeTime(Integer timeToSub){
        Set<Integer> integers = infoUnitMap.keySet();
        ArrayList<Integer> removeList = new ArrayList<>();
        for (Integer integer : integers) {
            InfoUnit infoUnit = infoUnitMap.get(integer);
            infoUnit.setLinkTime(infoUnit.getLinkTime() - timeToSub);
            if (infoUnit.getLinkTime() <= 0){
                removeList.add(integer);
            }
            infoUnitMap.put(integer,infoUnit);
        }

        for (Integer integer : removeList) {
            infoUnitMap.remove(integer);
        }

    }



    public HashMap<Integer, InfoUnit> getReceptorInfoMap() {
        return receptorInfoMap;
    }

    public void setReceptorInfoMap(HashMap<Integer, InfoUnit> receptorInfoMap) {
        this.receptorInfoMap = receptorInfoMap;
    }

    public HashMap<Integer, InfoUnit> getEffectorInfoMap() {
        return effectorInfoMap;
    }

    public void setEffectorInfoMap(HashMap<Integer, InfoUnit> effectorInfoMap) {
        this.effectorInfoMap = effectorInfoMap;
    }

    public HashMap<Integer, InfoUnit> getInfoUnitMap() {
        return infoUnitMap;
    }

    public void setInfoUnitMap(HashMap<Integer, InfoUnit> infoUnitMap) {
        this.infoUnitMap = infoUnitMap;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getReceptorUpdateTime() {
        return receptorUpdateTime;
    }

    public void setReceptorUpdateTime(Integer receptorUpdateTime) {
        this.receptorUpdateTime = receptorUpdateTime;
    }

    public Integer getEffectorUpdateTime() {
        return effectorUpdateTime;
    }

    public void setEffectorUpdateTime(Integer effectorUpdateTime) {
        this.effectorUpdateTime = effectorUpdateTime;
    }

    public Integer getInfoChainCreateTime() {
        return infoChainCreateTime;
    }

    public void setInfoChainCreateTime(Integer infoChainCreateTime) {
        this.infoChainCreateTime = infoChainCreateTime;
    }


    /**
     * v0.0.3 信息单元展开
     * 通过展开后的列表就可以直接判断信息链长度
     * @return
     */
    public ArrayList<InfoUnit> flattenInfoChain(ArrayList<InfoUnit> infoUnitList){
        ArrayList<InfoUnit> flattenList = new ArrayList<>();
        //System.out.println("infoUnitList = " + infoUnitList);
        for (int i = 0; i < infoUnitList.size(); i++) {
            InfoUnit infoUnit = infoUnitList.get(i);
            //如果是信息链，把它子项展开，再加入
            if (infoUnit.isInfoChain()){
                InfoChain infoChain = (InfoChain) infoUnit;
                ArrayList<InfoUnit> innerInfoChainList = infoChain.getInfoUnitList();
                ArrayList<InfoUnit> innerUnitList = flattenInfoChain(innerInfoChainList);
                flattenList.addAll(innerUnitList);
            }else{
                //是信息单元则直接加入
                flattenList.add(infoUnit);
            }
        }
        return  flattenList;
    }


}
