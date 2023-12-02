package io.github.lunasaw.zlm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Version {

    @JSONField(name = "buildTime")
    private String buildTime;

    @JSONField(name = "branchName")
    private String branchName;

    @JSONField(name = "commitHash")
    private String commitHash;
}