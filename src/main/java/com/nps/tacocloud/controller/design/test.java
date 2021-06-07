package com.nps.tacocloud.controller.design;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.tomcat.util.json.JSONParser;

public class test {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "/Users/pearsonnie/Documents/product/taco-cloud/src/main/java/com/nps/tacocloud/controller/design/0609.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[10];
            while (reader.read(buffer) != -1) {
                stringBuilder.append(new String(buffer));
                buffer = new char[10];
            }
            reader.close();

            String content = stringBuilder.toString();
            JSONObject jsonObject = JSONObject.parseObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            HashMap<Integer, JSONObject> hashMap = new HashMap<>();
            HashMap<Integer, JSONObject> map = findAllArray(jsonArray, hashMap);
            System.out.println(map.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //递归
    private static HashMap<Integer, JSONObject> findAllArray(JSONArray array, HashMap<Integer, JSONObject> map) {
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject obj = (JSONObject) iterator.next();
            JSONArray array2 = obj.getJSONArray("nodes");
            if(null != array2 && !array2.isEmpty()){
                findAllArray(array2, map);
            }
            obj.put("nodes", null);
            map.put(obj.getInteger("id"), obj);
        }
        return map;
    }
}