/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KasufyTestModule } from '../../../test.module';
import { PropiedadDeleteDialogComponent } from 'app/entities/propiedad/propiedad-delete-dialog.component';
import { PropiedadService } from 'app/entities/propiedad/propiedad.service';

describe('Component Tests', () => {
  describe('Propiedad Management Delete Component', () => {
    let comp: PropiedadDeleteDialogComponent;
    let fixture: ComponentFixture<PropiedadDeleteDialogComponent>;
    let service: PropiedadService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KasufyTestModule],
        declarations: [PropiedadDeleteDialogComponent]
      })
        .overrideTemplate(PropiedadDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PropiedadDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PropiedadService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
