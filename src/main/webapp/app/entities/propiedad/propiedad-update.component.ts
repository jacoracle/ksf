import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPropiedad, Propiedad } from 'app/shared/model/propiedad.model';
import { PropiedadService } from './propiedad.service';
import { IDireccion } from 'app/shared/model/direccion.model';
import { DireccionService } from 'app/entities/direccion';
import { IDetalle } from 'app/shared/model/detalle.model';
import { DetalleService } from 'app/entities/detalle';

@Component({
  selector: 'jhi-propiedad-update',
  templateUrl: './propiedad-update.component.html'
})
export class PropiedadUpdateComponent implements OnInit {
  isSaving: boolean;

  direccions: IDireccion[];

  detalles: IDetalle[];

  editForm = this.fb.group({
    id: [],
    nombre: [],
    descripcion: [],
    tipo: [],
    imagen: [],
    geoRef: [],
    precio: [],
    direccion: [],
    detalle: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected propiedadService: PropiedadService,
    protected direccionService: DireccionService,
    protected detalleService: DetalleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ propiedad }) => {
      this.updateForm(propiedad);
    });
    this.direccionService
      .query({ filter: 'propiedad-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IDireccion[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDireccion[]>) => response.body)
      )
      .subscribe(
        (res: IDireccion[]) => {
          if (!this.editForm.get('direccion').value || !this.editForm.get('direccion').value.id) {
            this.direccions = res;
          } else {
            this.direccionService
              .find(this.editForm.get('direccion').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IDireccion>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IDireccion>) => subResponse.body)
              )
              .subscribe(
                (subRes: IDireccion) => (this.direccions = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.detalleService
      .query({ filter: 'propiedad-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IDetalle[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDetalle[]>) => response.body)
      )
      .subscribe(
        (res: IDetalle[]) => {
          if (!this.editForm.get('detalle').value || !this.editForm.get('detalle').value.id) {
            this.detalles = res;
          } else {
            this.detalleService
              .find(this.editForm.get('detalle').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IDetalle>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IDetalle>) => subResponse.body)
              )
              .subscribe(
                (subRes: IDetalle) => (this.detalles = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(propiedad: IPropiedad) {
    this.editForm.patchValue({
      id: propiedad.id,
      nombre: propiedad.nombre,
      descripcion: propiedad.descripcion,
      tipo: propiedad.tipo,
      imagen: propiedad.imagen,
      geoRef: propiedad.geoRef,
      precio: propiedad.precio,
      direccion: propiedad.direccion,
      detalle: propiedad.detalle
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const propiedad = this.createFromForm();
    if (propiedad.id !== undefined) {
      this.subscribeToSaveResponse(this.propiedadService.update(propiedad));
    } else {
      this.subscribeToSaveResponse(this.propiedadService.create(propiedad));
    }
  }

  private createFromForm(): IPropiedad {
    return {
      ...new Propiedad(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      descripcion: this.editForm.get(['descripcion']).value,
      tipo: this.editForm.get(['tipo']).value,
      imagen: this.editForm.get(['imagen']).value,
      geoRef: this.editForm.get(['geoRef']).value,
      precio: this.editForm.get(['precio']).value,
      direccion: this.editForm.get(['direccion']).value,
      detalle: this.editForm.get(['detalle']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPropiedad>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackDireccionById(index: number, item: IDireccion) {
    return item.id;
  }

  trackDetalleById(index: number, item: IDetalle) {
    return item.id;
  }
}
