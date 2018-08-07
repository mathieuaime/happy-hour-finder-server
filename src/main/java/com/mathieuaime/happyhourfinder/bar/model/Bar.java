package com.mathieuaime.happyhourfinder.bar.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Bar {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
}
