import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetalle } from 'app/shared/model/detalle.model';

@Component({
  selector: 'jhi-detalle-detail',
  templateUrl: './detalle-detail.component.html'
})
export class DetalleDetailComponent implements OnInit {
  detalle: IDetalle;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ detalle }) => {
      this.detalle = detalle;
    });
  }

  previousState() {
    window.history.back();
  }
}
