import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecyHistorialComponent } from './recy-historial.component';

describe('RecyHistorialComponent', () => {
  let component: RecyHistorialComponent;
  let fixture: ComponentFixture<RecyHistorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecyHistorialComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecyHistorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
