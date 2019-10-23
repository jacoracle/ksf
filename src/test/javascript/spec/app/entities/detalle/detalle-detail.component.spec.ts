/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KasufyTestModule } from '../../../test.module';
import { DetalleDetailComponent } from 'app/entities/detalle/detalle-detail.component';
import { Detalle } from 'app/shared/model/detalle.model';

describe('Component Tests', () => {
  describe('Detalle Management Detail Component', () => {
    let comp: DetalleDetailComponent;
    let fixture: ComponentFixture<DetalleDetailComponent>;
    const route = ({ data: of({ detalle: new Detalle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KasufyTestModule],
        declarations: [DetalleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DetalleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetalleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detalle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
