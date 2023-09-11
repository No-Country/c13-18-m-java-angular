import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GreenPointInfoComponent } from './green-point-info.component';

describe('GreenPointInfoComponent', () => {
  let component: GreenPointInfoComponent;
  let fixture: ComponentFixture<GreenPointInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GreenPointInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GreenPointInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
