package com.mathieuaime.happyhourfinder.bar.controller;

import com.mathieuaime.happyhourfinder.bar.dto.BarDto;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import com.mathieuaime.happyhourfinder.common.constants.Paths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "bars", value = "Operations on bars")
@RestController
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

  @ApiOperation("Gets pageable list of bars")
  @ApiResponses(@ApiResponse(code = 200, message = "OK"))
  @GetMapping
  public ResponseEntity<Page<BarDto>> getBars(Pageable pageable) {
    Page<BarDto> page = this.barService.findAll(pageable)
        .map(this::convertToDto);
    return ResponseEntity.ok(page);
  }

  @ApiOperation("Gets a bar by id")
  @ApiResponses({@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Not Found")})
  @GetMapping("/{id}")
  public ResponseEntity<BarDto> getBar(@PathVariable long id) {
    Bar barDto = this.barService.findById(id);
    return ResponseEntity.ok(convertToDto(barDto));
  }

  @ApiOperation("Save or update a bar")
  @ApiResponses(@ApiResponse(code = 201, message = "Created"))
  @PostMapping
  public ResponseEntity<BarDto> saveBar(@RequestBody BarDto barDto) {
    Bar bar = this.barService.save(convertToEntity(barDto));

    URI location = ServletUriComponentsBuilder
        .fromCurrentServletMapping().path("/bar/{id}").build()
        .expand(bar.getId()).toUri();

    return ResponseEntity.created(location).body(convertToDto(bar));
  }

  @ApiOperation("Delete a bar by id")
  @ApiResponses({@ApiResponse(code = 204, message = "Accepted"),
      @ApiResponse(code = 404, message = "Not Found")})
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBar(@PathVariable long id) {
    this.barService.deleteById(id);
    return ResponseEntity.accepted().build();
  }

  private BarDto convertToDto(Bar bar) {
    return this.modelMapper.map(bar, BarDto.class);
  }

  private Bar convertToEntity(BarDto barDto) {
    return this.modelMapper.map(barDto, Bar.class);
  }
}
