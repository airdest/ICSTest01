package basic;

import constant.MagicValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 信息链
 * 1.一个信息链可以是一个单独的信息单元
 * 例如：(A)
 * 2.一个信息链可以是任意多个信息单元的链接，但最大数量遵循数量限制
 * 例如:
 * (A)-(B-C)
 * (A)-(B-C)-(D)
 */
public class InfoChain extends InfoUnit {

    /**
     * 信息链存储的信息链接，是有序的从左到右，即第一个到最后一个。
     */
    ArrayList<InfoUnit> infoUnitList = null;


}
