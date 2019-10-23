/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { KasufyTestModule } from '../../../test.module';
import { DetalleComponent } from 'app/entities/detalle/detalle.component';
import { DetalleService } from 'app/entities/detalle/detalle.service';
import { Detalle } from 'app/shared/model/detalle.model';

describe('Component Tests', () => {
  describe('Detalle Management Component', () => {
    let comp: DetalleComponent;
    let fixture: ComponentFixture<DetalleComponent>;
    let service: DetalleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KasufyTestModule],
        declarations: [DetalleComponent],
        providers: []
      })
        .overrideTemplate(DetalleComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetalleComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetalleService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Detalle(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.detalles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
