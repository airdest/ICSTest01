package basic;

/**
 * Parameter
 */
public class Parameter {

    /**
     * 信息元默认存活时长 单位:ms
     */
    public static Integer DEFAULT_SURVIVAL_TIME = 1000 * 60 * 10;

    /**
     * 信息池默认最大信息元数量
     */
    public static Integer DEFAULT_INFO_UNIT_NUMBER = 100000;

    /**
     * 信息链最大信息元数量，即信息链最大长度
     */
    public static Integer DEFAULT_INFO_UNIT_LENGTH = 100;

    /**
     * 一个信息元可以指向其它信息元的数量,模拟神经元细胞的突触数量限制
     * TODO 增加信息元指向其它信息元数量限制相关代码
     */
    public static Integer DEFAULT_MAX_POINT_TO_NUMBER = 10;

    /**
     * 信息元最大层级
     * TODO 增加信息元最大层级控制相关代码
     */
    public Integer DEFAULT_MAX_INFO_LAYER_NUMBER = 100;



}
