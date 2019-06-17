package com.asset.rec;


/**
 * 重命名分组
 */
public class GroupRenameRec {
    String new_name;
    String group_id;

    public GroupRenameRec() {
    }

    public GroupRenameRec(String new_name, String group_id) {
        this.new_name = new_name;
        this.group_id = group_id;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
