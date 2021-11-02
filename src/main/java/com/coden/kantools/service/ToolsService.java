package com.coden.kantools.service;

import com.alibaba.fastjson.JSON;
import com.coden.kantools.util.tools.CreatePhoneTools;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class ToolsService {


    public ArrayList<String> createPhoneNumber(int dateLength, boolean isUnrepeat) {
        return CreatePhoneTools.createPhoneNumber(CreatePhoneTools.FRISTNUMLIST, dateLength, isUnrepeat);
    }


    public HashMap<String, Object> getSwaggerJson(String json) {

        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject pathsObject = rootObject.getJSONObject("paths");

            Iterator<String> it = pathsObject.keys();
            HashMap pathsObjectChildsMap = new HashMap<String, JSONObject>();
            HashMap pathsObjectChildMap = new HashMap();
            while (it.hasNext()) {
                String key = it.next();
                JSONObject pathsObjectChild = (JSONObject) pathsObject.get(key);

                JSONArray tagsArray = pathsObjectChild.getJSONArray("tags");
                String summaryStr = pathsObjectChild.getString("summary");
                String descriptionStr = pathsObjectChild.getString("description");
                String operationIdStr = pathsObjectChild.getString("operationId");
                JSONArray consumesArray = pathsObjectChild.getJSONArray("consumes");
                JSONArray producesArray = pathsObjectChild.getJSONArray("produces");
                JSONObject parametersObject = pathsObjectChild.getJSONObject("parameters");
                JSONObject responsesObject = pathsObjectChild.getJSONObject("responses");

                Iterator<String> rcit = responsesObject.keys();
                HashMap responsesObjectChildsMap = new HashMap<String, JSONObject>();
                HashMap responsesObjectChildMap = new HashMap();
                while (rcit.hasNext()) {
                    String ckey = rcit.next();
                    JSONObject responsesObjectChild = (JSONObject) responsesObject.get(ckey);

                    String responsesDescriptionStr = responsesObjectChild.getString("description");
                    JSONObject schemaObject = responsesObjectChild.getJSONObject("schema");

                    HashMap schemaObjectChildMap = new HashMap();
                    schemaObjectChildMap.put("schema", schemaObject.get("type"));


                    responsesObjectChildMap.put("description", responsesDescriptionStr);
                    responsesObjectChildMap.put("schema", schemaObjectChildMap);

                    responsesObjectChildsMap.put(ckey, responsesObjectChildMap);

                }


                pathsObjectChildMap.put("tagsArray", tagsArray);
                pathsObjectChildMap.put("summaryStr", summaryStr);
                pathsObjectChildMap.put("descriptionStr", descriptionStr);
                pathsObjectChildMap.put("operationIdStr", operationIdStr);
                pathsObjectChildMap.put("consumesArray", consumesArray);
                pathsObjectChildMap.put("producesArray", producesArray);

                pathsObjectChildsMap.put(key, pathsObjectChildMap);
            }

            return pathsObjectChildsMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, Object> getSwaggerJson1(String json) {
        List<HashMap<String, Object>> dataList = new ArrayList<>();

        com.alibaba.fastjson.JSONObject ss = JSON.parseObject(json);

        System.out.println();

        return null;
    }

    public String getFileMD5() {
        return "";
    }


}
