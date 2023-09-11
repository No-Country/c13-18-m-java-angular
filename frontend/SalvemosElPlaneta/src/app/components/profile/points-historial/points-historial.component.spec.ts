import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointsHistorialComponent } from './points-historial.component';

describe('PointsHistorialComponent', () => {
  let component: PointsHistorialComponent;
  let fixture: ComponentFixture<PointsHistorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointsHistorialComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PointsHistorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
