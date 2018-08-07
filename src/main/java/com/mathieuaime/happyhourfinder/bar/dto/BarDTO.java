package com.mathieuaime.happyhourfinder.bar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BarDTO implements Serializable {
    private Long id;

    @NonNull
    private String name;
}
