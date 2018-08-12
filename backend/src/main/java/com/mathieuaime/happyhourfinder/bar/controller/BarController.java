package com.mathieuaime.happyhourfinder.bar.controller;

import com.mathieuaime.happyhourfinder.bar.dto.BarDTO;
import com.mathieuaime.happyhourfinder.bar.error.BarNotFoundException;
import com.mathieuaime.happyhourfinder.common.constants.Paths;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = Paths.VERSION + Paths.BARS)
public class BarController {
    private final BarService barService;

    private final ModelMapper modelMapper;

    @Autowired
    public BarController(BarService barService, ModelMapper modelMapper) {
        this.barService = barService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/status/check")
    public String status() {
        return "working";
    }

    @GetMapping
    public Page<BarDTO> getBars(Pageable pageable) {
        return barService.findAll(pageable)
                .map(this::convertToDto);
    }

    @GetMapping("/{id}")
    public BarDTO getBar(@PathVariable Long id) {
        return barService.findById(id)
                .map(this::convertToDto)
                .orElseThrow(BarNotFoundException::new);
    }

    @PostMapping
    public BarDTO saveBar(@RequestBody BarDTO barDTO) {
        Bar bar = barService.save(convertToEntity(barDTO));
        return convertToDto(bar);
    }

    @DeleteMapping("/{id}")
    public void deleteBar(@PathVariable Long id) {
        barService.deleteById(id);
    }

    private BarDTO convertToDto(Bar bar) {
        return modelMapper.map(bar, BarDTO.class);
    }

    private Bar convertToEntity(BarDTO barDTO) {
        return modelMapper.map(barDTO, Bar.class);
    }
}
