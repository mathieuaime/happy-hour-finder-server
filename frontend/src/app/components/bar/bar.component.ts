import { Component, OnInit } from '@angular/core';

import { Bar } from '../../models/bar';
import { BarService } from '../../services/bar.service';

@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html',
  styleUrls: ['./bar.component.scss']
})
export class BarComponent implements OnInit {
  bars: Bar[];

  constructor(private barService : BarService) { }

  getBars(): void {
    this.barService.getBars().subscribe(bars => this.bars = bars);
  }

  ngOnInit() {
    this.getBars();
  }

}
