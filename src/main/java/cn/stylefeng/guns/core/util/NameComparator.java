package cn.stylefeng.guns.core.util;

import java.util.Comparator;
import java.util.Hashtable;

/**
 * @description:
 * @author: ZhangLu
 * @time: 2019/5/15 16:33
 */
public class NameComparator implements Comparator {
    public int compare(Object a, Object b) {
        Hashtable hashA = (Hashtable) a;
        Hashtable hashB = (Hashtable) b;
        if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
            return -1;
        } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
            return 1;
        } else {
            return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
        }
    }
}
