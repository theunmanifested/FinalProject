import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicLandingComponent } from './public-landing.component';

describe('PublicLandingComponent', () => {
  let component: PublicLandingComponent;
  let fixture: ComponentFixture<PublicLandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicLandingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
