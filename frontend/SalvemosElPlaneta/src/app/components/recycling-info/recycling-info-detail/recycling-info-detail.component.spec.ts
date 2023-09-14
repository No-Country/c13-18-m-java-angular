import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecyclingInfoDetailComponent } from './recycling-info-detail.component';

describe('RecyclingInfoDetailComponent', () => {
  let component: RecyclingInfoDetailComponent;
  let fixture: ComponentFixture<RecyclingInfoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecyclingInfoDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecyclingInfoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
