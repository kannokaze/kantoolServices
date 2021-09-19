package com.coden.kantools.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataOperater {

    public static List<HashMap> beanListToHashMapList(List list) {

        List HashList = new ArrayList<HashMap<String, Object>>();
        for (Object i : list) {
            HashList.add(bean2HashMap(i));
            System.out.println(bean2HashMap(i));
        }

        return HashList;
    }


    public static List<Object> hashMapListToBeanList(Class<?> clazz, List<HashMap> list) {
        if (list.isEmpty()) {
            return null;
        }

        List BeanList = new ArrayList<Object>();
        for (HashMap i : list) {
            BeanList.add(hashMapToBean(clazz, i));
        }
        return BeanList;
    }


    public static HashMap<String, Object> bean2HashMap(Object b) {
        if (null == b) {
            return null;
        }
        try {
//            HashMap<String, Object> map = (HashMap<String, Object>) BeanUtils.describe(b);
            HashMap<String, Object> map = (HashMap<String, Object>) PropertyUtils.describe(b);
            if (map.containsKey("class") && map.get("class").toString().contains(".")) {
                map.remove("class");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T hashMapToBean(Class<?> clazz, HashMap map) {
        try {
            T newBeanInstance = (T) clazz.newInstance();
            BeanUtils.populate(newBeanInstance, map);
            return newBeanInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
