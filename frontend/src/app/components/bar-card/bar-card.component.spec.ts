import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BarCardComponent } from './bar-card.component';

import { Bar } from '../../models/bar';

describe('BarCardComponent', () => {
  let component: BarCardComponent;
  let fixture: ComponentFixture<BarCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BarCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BarCardComponent);
    component = fixture.componentInstance;
    const mockBar : Bar = new Bar();
    mockBar.name = "Bar";
    component.bar = mockBar;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a bar', () => {
    expect(component.bar instanceof Bar).toBeTruthy();
  });
});
