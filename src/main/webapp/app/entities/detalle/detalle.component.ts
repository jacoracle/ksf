import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDetalle } from 'app/shared/model/detalle.model';
import { AccountService } from 'app/core';
import { DetalleService } from './detalle.service';

@Component({
  selector: 'jhi-detalle',
  templateUrl: './detalle.component.html'
})
export class DetalleComponent implements OnInit, OnDestroy {
  detalles: IDetalle[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected detalleService: DetalleService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.detalleService
      .query()
      .pipe(
        filter((res: HttpResponse<IDetalle[]>) => res.ok),
        map((res: HttpResponse<IDetalle[]>) => res.body)
      )
      .subscribe(
        (res: IDetalle[]) => {
          this.detalles = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInDetalles();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDetalle) {
    return item.id;
  }

  registerChangeInDetalles() {
    this.eventSubscriber = this.eventManager.subscribe('detalleListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
