package com.ny.lg.common.module.po;

import com.ny.lg.common.utils.ValidatedUtils;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/26 16:00
 * @description
 */
@Data
public class User {
    private String id;

    @Pattern(regexp = ".{1,32}", message = "用户名长度必须为1~5位", groups = {ValidatedUtils.add.class, ValidatedUtils.update.class})
    private String username;

    private String password;

}
