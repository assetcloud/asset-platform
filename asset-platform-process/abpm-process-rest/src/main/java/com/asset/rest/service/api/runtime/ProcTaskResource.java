package com.asset.rest.service.api.runtime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lichao
 */
@RestController
@RequestMapping("/workflow/api")
public class ProcTaskResource extends ProcTaskBaseResource {

    @GetMapping("/runtime/geProcessTasks1")
    public  String getProcessTasks1() {
        return "geProcessTasks1";
    }

    @PostMapping("/runtime/geProcessTasks2")
    public  String getProcessTasks2() {
        return "geProcessTasks2";
    }
}
