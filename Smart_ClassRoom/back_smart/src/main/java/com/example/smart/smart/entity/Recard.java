package com.example.smart.smart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Recard {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String NumID;
    private LocalDateTime StartT;
    private LocalDateTime EndT;
}
