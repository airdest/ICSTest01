package special;

import basic.InfoChain;

/**
 * 目标
 * 每个目标都是一个要匹配的规则
 *
 */
public class Target {

    //时间限制
    long timeLimit ;

    //截止时间
    long timeDeadLine ;

    //层数限制
    Integer layers ;

    /**
     * 匹配成功后需要增加权值、生存时间
     * 匹配失败则需要减少权值、生存时间
     * 每个target都需要实现规则函数，用于判断一个infoUnit是否符合规则
     * 这样每个target都能设定独特的规则
     * @param infoChain
     * @return
     */
    public Double checkRule(InfoChain infoChain) {



        return null;
    }




}
