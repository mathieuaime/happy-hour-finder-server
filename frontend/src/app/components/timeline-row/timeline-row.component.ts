import { Component, Input, OnInit } from '@angular/core';

import { Release } from '../../models/release';

@Component({
  selector: 'app-timeline-row',
  templateUrl: './timeline-row.component.html',
  styleUrls: ['./timeline-row.component.scss']
})
export class TimelineRowComponent implements OnInit {

  @Input() release: Release;

  constructor() { }

  ngOnInit() {
  }

}
