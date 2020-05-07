package com.learn.java.genericclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xzy
 * @date 2020-04-18 19:45
 * 说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Generically<T> {
    T type;
}
