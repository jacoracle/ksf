import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPropiedad } from 'app/shared/model/propiedad.model';
import { PropiedadService } from './propiedad.service';

@Component({
  selector: 'jhi-propiedad-delete-dialog',
  templateUrl: './propiedad-delete-dialog.component.html'
})
export class PropiedadDeleteDialogComponent {
  propiedad: IPropiedad;

  constructor(protected propiedadService: PropiedadService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.propiedadService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'propiedadListModification',
        content: 'Deleted an propiedad'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-propiedad-delete-popup',
  template: ''
})
export class PropiedadDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ propiedad }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PropiedadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.propiedad = propiedad;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/propiedad', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/propiedad', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
