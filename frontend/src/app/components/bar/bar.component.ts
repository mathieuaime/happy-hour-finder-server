import { Component, OnInit } from '@angular/core';

import { Bar } from '../../models/bar';
import { BarService } from '../../services/bar.service';

@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html',
  styleUrls: ['./bar.component.scss']
})
export class BarComponent implements OnInit {
  currentPage: number = 0;

  bars: Bar[] = [];

  constructor(private barService : BarService) { }

  private getLatestBars(): void {
    this.barService.getLatestBars(this.currentPage)
                   .subscribe(this.processData);
    this.currentPage++;
  }

  private processData = (bars) => {
    this.bars.push.apply(this.bars, bars);
  }

  ngOnInit() {
    this.getLatestBars();
  }

  onScroll() {
    this.getLatestBars();
  }
}
