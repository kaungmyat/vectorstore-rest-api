package com.example.vectorstore.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PCFilter {

    private String facilityCode;
    private String facilityOrder;
    private String faiclityType;

}
