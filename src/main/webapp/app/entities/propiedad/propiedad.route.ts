import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Propiedad } from 'app/shared/model/propiedad.model';
import { PropiedadService } from './propiedad.service';
import { PropiedadComponent } from './propiedad.component';
import { PropiedadDetailComponent } from './propiedad-detail.component';
import { PropiedadUpdateComponent } from './propiedad-update.component';
import { PropiedadDeletePopupComponent } from './propiedad-delete-dialog.component';
import { IPropiedad } from 'app/shared/model/propiedad.model';

@Injectable({ providedIn: 'root' })
export class PropiedadResolve implements Resolve<IPropiedad> {
  constructor(private service: PropiedadService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPropiedad> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Propiedad>) => response.ok),
        map((propiedad: HttpResponse<Propiedad>) => propiedad.body)
      );
    }
    return of(new Propiedad());
  }
}

export const propiedadRoute: Routes = [
  {
    path: '',
    component: PropiedadComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'kasufyApp.propiedad.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PropiedadDetailComponent,
    resolve: {
      propiedad: PropiedadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.propiedad.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PropiedadUpdateComponent,
    resolve: {
      propiedad: PropiedadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.propiedad.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PropiedadUpdateComponent,
    resolve: {
      propiedad: PropiedadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.propiedad.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const propiedadPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PropiedadDeletePopupComponent,
    resolve: {
      propiedad: PropiedadResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'kasufyApp.propiedad.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
