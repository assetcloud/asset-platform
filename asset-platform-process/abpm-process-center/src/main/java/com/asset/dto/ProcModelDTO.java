package com.asset.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProcModelDTO {
    String proc_model_id;
    List<ProcNodeDTO> proc_node_data = new ArrayList<>();

    public ProcModelDTO() {
    }

    private ProcModelDTO(Builder builder) {
        setProc_model_id(builder.proc_model_id);
        setProc_node_data(builder.proc_node_data);
    }


    public static final class Builder {
        private String proc_model_id;
        private List<ProcNodeDTO> proc_node_data;

        public Builder() {
        }

        public Builder proc_model_id(String val) {
            proc_model_id = val;
            return this;
        }

        public Builder proc_node_data(List<ProcNodeDTO> val) {
            proc_node_data = val;
            return this;
        }

        public ProcModelDTO build() {
            return new ProcModelDTO(this);
        }
    }
}
