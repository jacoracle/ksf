import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { KasufySharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [KasufySharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [KasufySharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KasufySharedModule {
  static forRoot() {
    return {
      ngModule: KasufySharedModule
    };
  }
}
