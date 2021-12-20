package constant;

/**
 * 存放一些默认值，常量值
 */
public interface MagicValue {

    //信息元默认存活时长 单位:ms
    Integer DEFAULT_SURVIVAL_TIME = 1000 * 60 * 10;

    //信息池默认最大信息元数量
    Integer INFO_UNIT_NUMBER = 10000;

    //信息链最大信息元数量，即信息链最大长度
    Integer DEFAULT_INFO_UNIT_LENGTH = 10;


}
