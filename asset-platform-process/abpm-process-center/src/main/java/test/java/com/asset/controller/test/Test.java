package com.asset.controller.test;

import com.alibaba.fastjson.JSONObject;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static BpmnModel convertToBpmnModel(String json) throws XMLStreamException {
        //创建json对象

        JSONObject jsonData = (JSONObject) JSONObject.toJSON(json);

        String name = jsonData.getString("title");//获取title
        String key = jsonData.getString("key");//获取流程key
        JSONObject jsonObjectnode = jsonData.getJSONObject("nodes");//获取所有的节点信息
        JSONObject jsonObjectLine = jsonData.getJSONObject("lines");//获取所有的线条信息
        // JSONObject jsonObjectAreas = jsonData.getJSONObject("areas");//获取所有的area信息 
        //创建流程对象
        Process process = new Process();
        process.setName(name);
        process.setId(key);

        BpmnModel bm = new BpmnModel();

        GraphicInfo graphicInfo = null;
        //创建节点对象*/
        String startNodeId = "";
        for (Object nodeId : jsonObjectnode.keySet()) {
            JSONObject jsonEntry = jsonObjectnode.getJSONObject(String.valueOf(nodeId));
            String type = String.valueOf(jsonEntry.get("type")).replace("\"", "");
            if ("start round".equals(type)) {
                StartEvent startEvent = new StartEvent();
                startEvent.setId(String.valueOf(nodeId));
                startEvent.setName(String.valueOf(jsonEntry.get("name")).replace("\"", ""));
                process.addFlowElement(startEvent);
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(startEvent);
                startNodeId = String.valueOf(nodeId);
            } else if ("end round".equals(type)) {
                EndEvent endEvent = new EndEvent();
                endEvent.setId(String.valueOf(nodeId));
                endEvent.setName(String.valueOf(jsonEntry.get("name")).replace("\"", ""));
                process.addFlowElement(endEvent);
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(endEvent);
                startNodeId = String.valueOf(nodeId);
            } else if ("task".equals(type)) {
                UserTask userTask = new UserTask();
                userTask.setId(String.valueOf(nodeId));
                userTask.setName(String.valueOf(jsonEntry.get("name")).replace("\"", ""));
                userTask.setAssignee(String.valueOf(jsonEntry.get("assignee")).replace("\"", ""));
                process.addFlowElement(userTask);
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(userTask);
                startNodeId = String.valueOf(nodeId);
            } else if ("node".equals(type)) {
                ServiceTask serviceTask = new ServiceTask();

                serviceTask.setId(String.valueOf(nodeId));
                serviceTask.setName(String.valueOf(jsonEntry.get("name")).replace("\"", ""));
                //将java的任务放到 
                //固定写死调用类 因为自动任务是调用插件
                serviceTask.setImplementationType("expression");
                serviceTask.setImplementation("${serviceTaskNController.implementPlug(execution)}");

                FieldExtension field = new FieldExtension();
                field.setFieldName("plugin");
                field.setStringValue(String.valueOf(jsonEntry.get("plugin")).replace("\"", ""));
                serviceTask.getFieldExtensions().add(field);
                process.addFlowElement(serviceTask);
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(serviceTask);
                startNodeId = String.valueOf(nodeId);
            }
            //设置布局
            graphicInfo.setX(Double.valueOf(String.valueOf(jsonEntry.get("left")).replace("\"", "")));
            graphicInfo.setY(Double.valueOf(String.valueOf(jsonEntry.get("top")).replace("\"", "")));
            graphicInfo.setWidth(Double.valueOf(String.valueOf(jsonEntry.get("width")).replace("\"", "")));
            graphicInfo.setHeight(Double.valueOf(String.valueOf(jsonEntry.get("height")).replace("\"", "")));
            bm.addGraphicInfo(graphicInfo.getElement().getId(), graphicInfo);


            //创建箭头线对象
            for (Object lineId : jsonObjectLine.keySet()) {
                JSONObject jsonEntryline = jsonObjectLine.getJSONObject(String.valueOf(lineId));
                if (!String.valueOf(jsonEntryline.get("from")).replace("\"", "").equals(startNodeId)) {
                    continue;
                }
                SequenceFlow sequenceFlow = new SequenceFlow();
                sequenceFlow.setId(String.valueOf(lineId));
                sequenceFlow.setName(String.valueOf(jsonEntryline.get("name")).replace("\"", ""));
                sequenceFlow.setSourceRef(String.valueOf(jsonEntryline.get("from")).replace("\"", ""));
                sequenceFlow.setTargetRef(String.valueOf(jsonEntryline.get("to")).replace("\"", ""));
                //sequenceFlow.setConditionExpression(String.valueOf(jsonEntryline.get("expression")).replace("\"", ""));
                if (null != jsonEntryline.get("expression") &&
                        !"".equals(String.valueOf(jsonEntryline.get("expression")).replace("\"", ""))) {
                    String expression = String.valueOf(jsonEntryline.get("expression")).replace("\"", "");
                    sequenceFlow.setConditionExpression(expression);
                } else {
                    if (!startNodeId.equals(sequenceFlow.getSourceRef())) {
                        //如果用户不输入，默认状态值大于0就走下一个节点
                        sequenceFlow.setConditionExpression("${status==1||status==2}");
                    }
                }

                //添加线条到流程
                String[] fromXY = String.valueOf(jsonEntryline.get("fromXY")).replace("\"", "").split(",");
                String[] toXY = String.valueOf(jsonEntryline.get("toXY")).replace("\"", "").split(",");
                //	定位线条坐标
                List<GraphicInfo> graphicInfoForWaypoints = new ArrayList<GraphicInfo>();

                //箭头开始
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(sequenceFlow);
                graphicInfo.setX(Double.valueOf(fromXY[0]));
                graphicInfo.setY(Double.valueOf(fromXY[1]));
                graphicInfoForWaypoints.add(graphicInfo);
                //箭头身体
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(sequenceFlow);

                if (Double.valueOf(fromXY[0]) > Double.valueOf(toXY[0])) {
                    graphicInfo.setX(Double.valueOf(fromXY[0]) - 12);
                } else if (Double.valueOf(toXY[0]) > Double.valueOf(fromXY[0])) {
                    graphicInfo.setX(Double.valueOf(fromXY[0]) + 12);
                } else {
                    graphicInfo.setX(Double.valueOf(fromXY[0]));
                }

                graphicInfo.setY(Double.valueOf(fromXY[1]));
                graphicInfoForWaypoints.add(graphicInfo);

                //箭头身体
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(sequenceFlow);
                if (Double.valueOf(fromXY[0]) > Double.valueOf(toXY[0])) {
                    graphicInfo.setX(Double.valueOf(fromXY[0]) - 12);
                } else if (Double.valueOf(toXY[0]) > Double.valueOf(fromXY[0])) {
                    graphicInfo.setX(Double.valueOf(fromXY[0]) + 12);
                } else {
                    graphicInfo.setX(Double.valueOf(fromXY[0]));
                }
                graphicInfo.setY(Double.valueOf(fromXY[1]));
                graphicInfoForWaypoints.add(graphicInfo);

                //箭头结束
                graphicInfo = new GraphicInfo();
                graphicInfo.setElement(sequenceFlow);
                graphicInfo.setX(Double.valueOf(toXY[0]));
                graphicInfo.setY(Double.valueOf(toXY[1]));
                graphicInfoForWaypoints.add(graphicInfo);

                bm.addFlowGraphicInfoList(sequenceFlow.getId(), graphicInfoForWaypoints);

                process.addFlowElement(sequenceFlow);
            }
        }
        bm.addProcess(process);
        return bm;

    }
}
