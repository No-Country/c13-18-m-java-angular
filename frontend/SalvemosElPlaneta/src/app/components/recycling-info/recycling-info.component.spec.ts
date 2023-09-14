import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecyclingInfoComponent } from './recycling-info.component';

describe('RecyclingInfoComponent', () => {
  let component: RecyclingInfoComponent;
  let fixture: ComponentFixture<RecyclingInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecyclingInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecyclingInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
