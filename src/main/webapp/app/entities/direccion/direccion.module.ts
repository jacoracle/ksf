import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { KasufySharedModule } from 'app/shared';
import {
  DireccionComponent,
  DireccionDetailComponent,
  DireccionUpdateComponent,
  DireccionDeletePopupComponent,
  DireccionDeleteDialogComponent,
  direccionRoute,
  direccionPopupRoute
} from './';

const ENTITY_STATES = [...direccionRoute, ...direccionPopupRoute];

@NgModule({
  imports: [KasufySharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DireccionComponent,
    DireccionDetailComponent,
    DireccionUpdateComponent,
    DireccionDeleteDialogComponent,
    DireccionDeletePopupComponent
  ],
  entryComponents: [DireccionComponent, DireccionUpdateComponent, DireccionDeleteDialogComponent, DireccionDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KasufyDireccionModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
