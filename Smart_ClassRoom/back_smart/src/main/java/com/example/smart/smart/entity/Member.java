package com.example.smart.smart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member {
    private Integer id;
    @TableId(value = "id",type = IdType.AUTO)
    private String Name;
    private String Card;
    private Integer Money;
    private Integer Day;
}
