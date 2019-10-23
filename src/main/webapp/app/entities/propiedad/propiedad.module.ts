import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KasufySharedModule } from 'app/shared';
import {
  PropiedadComponent,
  PropiedadDetailComponent,
  PropiedadUpdateComponent,
  PropiedadDeletePopupComponent,
  PropiedadDeleteDialogComponent,
  propiedadRoute,
  propiedadPopupRoute
} from './';

const ENTITY_STATES = [...propiedadRoute, ...propiedadPopupRoute];

@NgModule({
  imports: [KasufySharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PropiedadComponent,
    PropiedadDetailComponent,
    PropiedadUpdateComponent,
    PropiedadDeleteDialogComponent,
    PropiedadDeletePopupComponent
  ],
  entryComponents: [PropiedadComponent, PropiedadUpdateComponent, PropiedadDeleteDialogComponent, PropiedadDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KasufyPropiedadModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
