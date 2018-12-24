import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ActuatorService } from './actuator.service';

describe('ActuatorService', () => {
  beforeEach(() => TestBed.configureTestingModule({
                         imports: [HttpClientModule],
                         providers: []
                       }));

  it('should be created', () => {
    const service: ActuatorService = TestBed.get(ActuatorService);
    expect(service).toBeTruthy();
  });
});
