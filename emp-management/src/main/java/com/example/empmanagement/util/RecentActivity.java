package com.example.empmanagement.util;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RecentActivity implements Serializable {
    private String getActionBy;
    private String actionOn;
    private String activity;

    private Date date;

}
