package io.github.lunasaw.zlm.entity;

import lombok.Data;

import java.util.List;

/**
 * @author luna
 * @version 1.0
 * @date 2023/12/3
 * @description:
 */
@Data
public class Mp4RecordFile {

    private List<String> paths;

    private String       rootPath;

}
