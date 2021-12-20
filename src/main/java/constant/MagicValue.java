package constant;

/**
 * 存放一些默认值，常量值
 */
public interface MagicValue {

    //信息元默认存活时长 单位:ms
    Integer DEFAULT_SURVIVAL_TIME = 1000 * 60 * 10;

    //信息池默认最大信息元数量
    Integer DEFAULT_INFO_UNIT_NUMBER = 10000;

    //信息链最大信息元数量，即信息链最大长度
    Integer DEFAULT_INFO_UNIT_LENGTH = 10;

    //一个信息元可以指向其它信息元的数量,模拟神经元细胞的突触数量限制
    Integer DEFAULT_MAX_POINT_TO_NUMBER = 100;

    /**
     * 可以建立的信息元的最大层级
     * 比如有5个基础信息元：A B C D E
     * 则基础信息元处于第0级
     * (A-B) 处于第1级
     * ((A-B)-C) 处于第2级
     * 以此类推，括号包裹代表它可以看作一个单独的信息元
     */
    Integer DEFAULT_MAX_INFO_LAYER_NUMBER = 100;


}
