import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KasufySharedModule } from 'app/shared';
import {
  DetalleComponent,
  DetalleDetailComponent,
  DetalleUpdateComponent,
  DetalleDeletePopupComponent,
  DetalleDeleteDialogComponent,
  detalleRoute,
  detallePopupRoute
} from './';

const ENTITY_STATES = [...detalleRoute, ...detallePopupRoute];

@NgModule({
  imports: [KasufySharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DetalleComponent,
    DetalleDetailComponent,
    DetalleUpdateComponent,
    DetalleDeleteDialogComponent,
    DetalleDeletePopupComponent
  ],
  entryComponents: [DetalleComponent, DetalleUpdateComponent, DetalleDeleteDialogComponent, DetalleDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KasufyDetalleModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
