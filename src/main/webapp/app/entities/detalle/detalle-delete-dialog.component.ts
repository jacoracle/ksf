import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetalle } from 'app/shared/model/detalle.model';
import { DetalleService } from './detalle.service';

@Component({
  selector: 'jhi-detalle-delete-dialog',
  templateUrl: './detalle-delete-dialog.component.html'
})
export class DetalleDeleteDialogComponent {
  detalle: IDetalle;

  constructor(protected detalleService: DetalleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.detalleService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'detalleListModification',
        content: 'Deleted an detalle'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-detalle-delete-popup',
  template: ''
})
export class DetalleDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ detalle }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DetalleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.detalle = detalle;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/detalle', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/detalle', { outlets: { popup: null } }]);
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
