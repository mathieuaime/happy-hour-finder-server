package com.mathieuaime.happyhourfinder.bar.controller;

import com.mathieuaime.happyhourfinder.bar.error.BarNotFoundException;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bars")
public class BarController {
    private final BarService barService;

    @Autowired
    public BarController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "working";
    }

    @GetMapping
    public Page<Bar> getBars(Pageable pageable) {
        return barService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Bar getBar(@PathVariable Long id) {
        return barService.findById(id).orElseThrow(BarNotFoundException::new);
    }

    @PostMapping
    public Bar saveBar(@RequestBody Bar bar) {
        return barService.save(bar);
    }

    @PutMapping("/{barId}")
    public Bar updateUser(@PathVariable Long barId, @RequestBody Bar bar) {
        return barService.update(barId, bar);
    }

    @DeleteMapping("/{barId}")
    public void deleteBar(@PathVariable Long barId) {
        barService.deleteById(barId);
    }
}
