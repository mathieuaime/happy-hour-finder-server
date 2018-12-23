import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TimelineRowComponent } from './timeline-row.component';

import { Release } from '../../models/release';

describe('TimelineRowComponent', () => {
  let component: TimelineRowComponent;
  let fixture: ComponentFixture<TimelineRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TimelineRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimelineRowComponent);
    component = fixture.componentInstance;
    const mockRelease : Release = new Release();
    mockRelease.name = "Name";
    mockRelease.html_url = "Html Url";
    mockRelease.tag_name = "Tag name";
    mockRelease.body = "Body";
    mockRelease.created_at = Date.now().toString();
    component.release = mockRelease;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a release', () => {
    expect(component.release instanceof Release).toBeTruthy();
  });
});
