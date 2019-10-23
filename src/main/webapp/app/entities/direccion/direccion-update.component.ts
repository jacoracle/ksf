import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDireccion, Direccion } from 'app/shared/model/direccion.model';
import { DireccionService } from './direccion.service';

@Component({
  selector: 'jhi-direccion-update',
  templateUrl: './direccion-update.component.html'
})
export class DireccionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    calle: [],
    colonia: [],
    munucipio: [],
    estado: [],
    codigoPostl: []
  });

  constructor(protected direccionService: DireccionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ direccion }) => {
      this.updateForm(direccion);
    });
  }

  updateForm(direccion: IDireccion) {
    this.editForm.patchValue({
      id: direccion.id,
      calle: direccion.calle,
      colonia: direccion.colonia,
      munucipio: direccion.munucipio,
      estado: direccion.estado,
      codigoPostl: direccion.codigoPostl
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const direccion = this.createFromForm();
    if (direccion.id !== undefined) {
      this.subscribeToSaveResponse(this.direccionService.update(direccion));
    } else {
      this.subscribeToSaveResponse(this.direccionService.create(direccion));
    }
  }

  private createFromForm(): IDireccion {
    return {
      ...new Direccion(),
      id: this.editForm.get(['id']).value,
      calle: this.editForm.get(['calle']).value,
      colonia: this.editForm.get(['colonia']).value,
      munucipio: this.editForm.get(['munucipio']).value,
      estado: this.editForm.get(['estado']).value,
      codigoPostl: this.editForm.get(['codigoPostl']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDireccion>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
