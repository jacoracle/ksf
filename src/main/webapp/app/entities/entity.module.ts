import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'propiedad',
        loadChildren: () => import('./propiedad/propiedad.module').then(m => m.KasufyPropiedadModule)
      },
      {
        path: 'direccion',
        loadChildren: () => import('./direccion/direccion.module').then(m => m.KasufyDireccionModule)
      },
      {
        path: 'detalle',
        loadChildren: () => import('./detalle/detalle.module').then(m => m.KasufyDetalleModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class KasufyEntityModule {}
