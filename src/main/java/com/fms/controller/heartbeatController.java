package com.fms.controller;

import com.fms.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class heartbeatController {
    @GetMapping("/heartbeat")
    public Result JWTHeartbeat() {
        return Result.success();
    }
}
