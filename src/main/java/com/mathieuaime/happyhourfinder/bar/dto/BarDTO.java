package com.mathieuaime.happyhourfinder.bar.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BarDTO implements Serializable {
    private Long id;

    @NonNull
    private String name;
}
