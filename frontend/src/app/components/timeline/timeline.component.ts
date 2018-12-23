import { Component, OnInit } from '@angular/core';

import { GithubService } from '../../services/github.service';

import { Release } from '../../models/release';

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {

  releases: Release[] = [];

  constructor(private githubService : GithubService) { }

  ngOnInit() {
    this.githubService.getReleases().subscribe(this.processData);
  }


  private processData = (releases) => {
    this.releases.push.apply(this.releases, releases);
  }

}
