import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPropiedad } from 'app/shared/model/propiedad.model';

@Component({
  selector: 'jhi-propiedad-detail',
  templateUrl: './propiedad-detail.component.html'
})
export class PropiedadDetailComponent implements OnInit {
  propiedad: IPropiedad;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ propiedad }) => {
      this.propiedad = propiedad;
    });
  }

  previousState() {
    window.history.back();
  }
}
