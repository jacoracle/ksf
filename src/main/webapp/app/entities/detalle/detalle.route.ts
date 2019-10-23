import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Detalle } from 'app/shared/model/detalle.model';
import { DetalleService } from './detalle.service';
import { DetalleComponent } from './detalle.component';
import { DetalleDetailComponent } from './detalle-detail.component';
import { DetalleUpdateComponent } from './detalle-update.component';
import { DetalleDeletePopupComponent } from './detalle-delete-dialog.component';
import { IDetalle } from 'app/shared/model/detalle.model';

@Injectable({ providedIn: 'root' })
export class DetalleResolve implements Resolve<IDetalle> {
  constructor(private service: DetalleService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDetalle> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Detalle>) => response.ok),
        map((detalle: HttpResponse<Detalle>) => detalle.body)
      );
    }
    return of(new Detalle());
  }
}

export const detalleRoute: Routes = [
  {
    path: '',
    component: DetalleComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.detalle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DetalleDetailComponent,
    resolve: {
      detalle: DetalleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.detalle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DetalleUpdateComponent,
    resolve: {
      detalle: DetalleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.detalle.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DetalleUpdateComponent,
    resolve: {
      detalle: DetalleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.detalle.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const detallePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DetalleDeletePopupComponent,
    resolve: {
      detalle: DetalleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.detalle.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
