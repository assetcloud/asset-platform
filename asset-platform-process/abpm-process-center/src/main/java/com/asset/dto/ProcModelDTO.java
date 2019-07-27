package com.asset.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProcModelDTO {
    String proc_model_id;
    List<ProcNodeDTO> proc_node_data = new ArrayList<>();
}
