package com.timi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain = true)   //get set 格式  支持链式
public class Dept implements Serializable {
    private String dname;
    private String db_source;
}
