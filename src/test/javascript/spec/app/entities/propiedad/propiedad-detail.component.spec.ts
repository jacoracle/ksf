/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KasufyTestModule } from '../../../test.module';
import { PropiedadDetailComponent } from 'app/entities/propiedad/propiedad-detail.component';
import { Propiedad } from 'app/shared/model/propiedad.model';

describe('Component Tests', () => {
  describe('Propiedad Management Detail Component', () => {
    let comp: PropiedadDetailComponent;
    let fixture: ComponentFixture<PropiedadDetailComponent>;
    const route = ({ data: of({ propiedad: new Propiedad(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KasufyTestModule],
        declarations: [PropiedadDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PropiedadDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PropiedadDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.propiedad).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
