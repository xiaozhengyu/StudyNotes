package com.learn.java.serial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzy
 * @date 2021/2/3 21:36
 * 说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempEntity implements Serializable {
    private static final long serialVersionUID = 4251017929329054297L;

    private String message;
}
