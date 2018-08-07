package com.mathieuaime.happyhourfinder.bar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BarDTO implements Serializable {
    private Long id;

    @NonNull
    private String name;
}
