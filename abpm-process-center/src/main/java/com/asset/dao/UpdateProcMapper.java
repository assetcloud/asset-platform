package com.asset.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
public interface UpdateProcMapper {

    //修改act_ru_task表中的流程定义ID
    //把指定processInstanceID的这一项的PROC_DEF_ID值改为我们现在指定的新的流程定义值
    @Update("UPDATE act_ru_task SET proc_def_id_=#{newDefID} WHERE proc_inst_id_=#{procInstanceID}")
    void updateProc1(@Param("newDefID")String newDefID , @Param("procInstanceID")String procInstanceID);

    //修改act_ru_execution表中的流程定义ID
    //把指定processInstanceID的这一项的PROC_DEF_ID值改为我们现在指定的新的流程定义值
    @Update("UPDATE act_ru_execution SET proc_def_id_=#{newDefID} WHERE proc_inst_id_=#{procInstanceID}")
    void updateProc2(@Param("newDefID")String newDefID , @Param("procInstanceID")String procInstanceID);


//    @Select("SELECT ID " +
//            "FROM act_re_procdef" +
//            "WHERE name_=#{defName} " +
//                   "AND <select max<version> from act_re_procdef WHERE name=#{defName}>")
//    String getNewDefID(@Param("defName")String defName);

    @Select("select ID_ from act_re_procdef WHERE name_=#{defName} ORDER BY VERSION_ DESC")
    ArrayList<String> getDefIDList(@Param("defName")String defName);

    @Select(value = "SELECT PROC_DEF_ID_ FROM act_ru_task WHERE PROC_INST_ID_=#{procInstanceID} ")
    String getDefName(@Param("procInstanceID")String procInstanceID);
}
