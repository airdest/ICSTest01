package basic;

/**
 * 规则的抽象概念
 */
public interface Rule {

    //每个target都需要实现规则函数，用于判断一个infoUnit是否符合规则
    //这样每个target都能设定独特的规则
    Boolean checkRule(InfoUnit infoUnit);


}
