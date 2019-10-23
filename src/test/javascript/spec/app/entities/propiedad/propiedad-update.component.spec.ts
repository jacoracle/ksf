/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { KasufyTestModule } from '../../../test.module';
import { PropiedadUpdateComponent } from 'app/entities/propiedad/propiedad-update.component';
import { PropiedadService } from 'app/entities/propiedad/propiedad.service';
import { Propiedad } from 'app/shared/model/propiedad.model';

describe('Component Tests', () => {
  describe('Propiedad Management Update Component', () => {
    let comp: PropiedadUpdateComponent;
    let fixture: ComponentFixture<PropiedadUpdateComponent>;
    let service: PropiedadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KasufyTestModule],
        declarations: [PropiedadUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PropiedadUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PropiedadUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PropiedadService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Propiedad(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Propiedad();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
