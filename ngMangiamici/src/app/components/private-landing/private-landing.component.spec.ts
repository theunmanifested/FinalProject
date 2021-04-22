import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivateLandingComponent } from './private-landing.component';

describe('PrivateLandingComponent', () => {
  let component: PrivateLandingComponent;
  let fixture: ComponentFixture<PrivateLandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrivateLandingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivateLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
