import { Component, Input, OnInit } from '@angular/core';

import { Bar } from '../../models/bar';

@Component({
  selector: 'app-bar-card',
  templateUrl: './bar-card.component.html',
  styleUrls: ['./bar-card.component.scss']
})
export class BarCardComponent implements OnInit {

  @Input() bar: Bar;

  constructor() { }

  ngOnInit() {
  }

}
