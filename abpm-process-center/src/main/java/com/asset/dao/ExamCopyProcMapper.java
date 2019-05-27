package com.asset.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface ExamCopyProcMapper {

    //在act_de_model表中找到model_editor_json字段，对里面的流程信息添加节点
    @Select("SELECT model_editor_json FROM act_de_model WHERE `name` = #{procDefName}")
    String getProcJson(@Param("procDefName")String procDefName );

    @Update("UPDATE act_de_model SET model_editor_json = #{proJson} WHERE `name` =#{procDefName}")
    void updateProcJson(@Param("proJson")String proJson,@Param("procDefName")String procDefName);


    //通过数据表查开始运行实例的时间
    @Select("SELECT START_TIME_ FROM act_hi_actinst WHERE PROC_INST_ID_ = #{instanceID} AND ACT_ID_ = 'startEvent'")
    String getStartTime(@Param("instanceID")String instanceID);

    //通过数据表查结束运行实例的时间
    @Select("SELECT END_TIME_ FROM act_hi_actinst WHERE PROC_INST_ID_ = #{instanceID} AND ACT_ID_ = 'endEvent'")
    String getEndTime(@Param("instanceID")String instanceID);

    //通过数据表查结束运行实例的时间
    @Select("SELECT PROC_DEF_ID_ FROM act_hi_actinst WHERE PROC_INST_ID_ = #{instanceID} AND ACT_ID_ = 'startEvent'")
    String setDefinition(@Param("instanceID")String instanceID);

    //通过数据表查结束运行实例的时间
    @Select("SELECT PROC_DEF_ID_ FROM act_hi_actinst WHERE PROC_INST_ID_ = #{instanceID} AND ACT_ID_ = 'startEvent'")
    String setInstance(@Param("instanceID")String instanceID);

    //通过数据表查结束运行实例的时间
    @Select("SELECT PROC_DEF_ID_ FROM act_hi_actinst WHERE PROC_INST_ID_ = #{instanceID} AND ACT_ID_ = 'startEvent'")
    String setName(@Param("instanceID")String instanceID);

    //通过数据表查结束运行实例的时间
    @Select("SELECT PROC_DEF_ID_ FROM act_hi_actinst WHERE PROC_INST_ID_ = #{instanceID} AND ACT_ID_ = 'startEvent'")
    String setUsr(@Param("instanceID")String instanceID);
}
